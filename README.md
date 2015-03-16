Stream Collections
==================

This is an abstraction for streams as Scala collections that was developed during our workat Nitro on traversing our production S3 bucket.

The iterator is currently written on top of Play Iteratees, but can be generalized to any stream framework that works with Scala futures. Our immediate plans are to support Akka streams.

Usage
-----
See [PlayStreamIteratorSpec](https://www.github.com/nitro/blob/master/src/test/scala/PlayStreamIteratorSpec.scala) for examples.


Feature Suggestions, Bugs, Questions?
-------------------------------------
Please open an issue and someone from our team will respond shortly.

Contributing
------------
We welcome contributions!  Please open a Pull Request.
