package com.nitro.iterator.s3.throttling

import com.amazonaws.services.simpleworkflow.flow.worker.BackoffThrottler
import com.nitro.iterator.logging._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util._
import scala.util.control.NonFatal

/**
 * Throttling exponential back off for S3
 */
object BackoffThrottling {

  def throttled[T](maxRetries: Int = 5,
    initialSleep: Int = 2000,
    maxSleep: Int = 40000,
    backOffCoefficient: Double = 2D)(work: => T) = {
    def retry(retriesRemaining: Int): T = {

      val throttler = new BackoffThrottler(initialSleep, maxSleep, backOffCoefficient)
      Try(work) match {
        case Success(result) =>
          throttler.success()
          result
        case Failure(NonFatal(t: Throwable)) if retriesRemaining > 0 =>
          throttler.failure()
          throttler.throttle()
          retry(retriesRemaining - 1)
        case Failure(NonFatal(t: Throwable)) =>
          throttler.failure()
          throw new Exception(
            s"Throttling backoff unsuccessful at [$maxRetries] retries, $initialSleep initial sleep, $maxSleep max sleep, $backOffCoefficient back off coefficient.",
            t)
      }
    }
    retry(maxRetries)
  }

  def throttledAsync[T](maxRetries: Int = 5,
    initialSleep: Int = 2000,
    maxSleep: Int = 40000,
    backOffCoefficient: Double = 2D)(work: => Future[T]) = {
    def retry(retriesRemaining: Int): Future[T] = {

      val throttler = new BackoffThrottler(initialSleep, maxSleep, backOffCoefficient)
      work map { successfulResult: T =>
        throttler.success()
        successfulResult
      } recoverWith {
        case NonFatal(t: Throwable) if retriesRemaining > 0 =>
          throttler.failure()
          throttler.throttle()
          retry(retriesRemaining - 1)
        case NonFatal(t: Throwable) =>
          throttler.failure()
          val message = s"Throttling backoff unsuccessful at [$maxRetries] retries, $initialSleep initial sleep, $maxSleep max sleep, $backOffCoefficient back off coefficient."
          logger.error(message, t)
          Future.failed(new Exception(message, t))
      }
    }
    retry(maxRetries)
  }

}