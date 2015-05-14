package org.decaf.s3cryptview.fs
import java.io.{File, IOException}

object FSUtils {
  final def directoryHasFilesUnder(path: String): Boolean =
    catchIOExceptions(false) {
      val folder = new File(path)
      lazy val files = folder.listFiles().iterator.take(1)
      folder.exists && files.hasNext
    }

  final def getFilesDirectlyUnderDirectory(path: String): List[File] =
    catchIOExceptions(List.empty[File]) {
      val folder = new File(path)
      val builder = List.newBuilder[File]
      if (folder.exists) {
        val files = folder.listFiles().iterator
        while(files.hasNext) {
          val file = files.next()
          if (file.exists) {
            builder += file
          }
        }
      }
      builder.result
    }

  private[this] def catchIOExceptions[T](empty: T)(body: => T): T =
    try {
      body
    } catch {
      case err: IOException => empty
    }
}
