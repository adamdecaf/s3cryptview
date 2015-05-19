package org.decaf.s3cryptview.fs
import java.io.{File, FileWriter, IOException}

object FSUtils {
  final def makeDirectoryIfMissing(path: String): Boolean =
    catchIOExceptions(false) {
      val folder = new File(path)
      folder.mkdirs()
      true
    }

  final def directoryHasFilesUnder(path: String, ignorableFileNames: List[String] = List.empty): Boolean =
    catchIOExceptions(false) {
      val folder = new File(path)
      folder.exists && {
        (for {
           file <- folder.listFiles().toList
           if nonIgnorableFile(file.getName(), ignorableFileNames)
         } yield file).nonEmpty
      }
}

  final def getFilesDirectlyUnderDirectory(path: String, ignorableFileNames: List[String] = List.empty): List[File] =
    catchIOExceptions(List.empty[File]) {
      val folder = new File(path)
      if (folder.exists) {
        for {
          file <- folder.listFiles().toList
          if nonIgnorableFile(file.getName(), ignorableFileNames)
        } yield file
      } else {
        List.empty
      }
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

  private[this] def nonIgnorableFile(filename: String, ignorableFileNames: List[String]) =
    !ignorableFileNames.exists(ignorable => filename.endsWith(s"${ignorable}"))

  private[this] def catchIOExceptions[T](empty: T)(body: => T): T =
    try {
      body
    } catch {
      case err: IOException =>
        err.printStackTrace
        empty
    }
}
