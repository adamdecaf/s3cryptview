package org.decaf.s3cryptview.fs
import java.io.{File, FileWriter, IOException}

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

  final def overwriteFile(path: String)(contents: Array[Char]): Unit =
    catchIOExceptions() {
      val file = new File(path)
      val writer = new FileWriter(file)
      try {
        writer.write(contents)
      } finally {
        writer.close
      }
    }

  private[this] def catchIOExceptions[T](empty: T)(body: => T): T =
    try {
      body
    } catch {
      case err: IOException => empty
    }
}
