package org.decaf.s3cryptview.fs
import org.specs2.mutable.Specification

object FSUtilsSpec extends Specification {
  import FSUtils._

  "tell me if files exist where they should" in {
    directoryHasFilesUnder("../") must beTrue
  }

  "don't tell me there are files where there are none" in {
    directoryHasFilesUnder("some-directory-that-doesnt-exist") must beFalse
  }

  "give me a list of the files under a directory" in {
    getFilesDirectlyUnderDirectory("src/test/scala/fs").map(_.getName) must contain("FSUtilsSpec.scala")
    getFilesDirectlyUnderDirectory("src/test/scala/fs/").map(_.getName) must contain("FSUtilsSpec.scala")
  }
}
