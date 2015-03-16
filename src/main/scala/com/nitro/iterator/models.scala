package com.nitro.iterator
package models

/**
 * Used in the paginating enumerator
 */
trait Pageable {
  val next: Option[String]
}

