package org.decaf.s3cryptview.crypto
import java.io.{File, FileReader, FileOutputStream}
import org.decaf.s3cryptview.Settings

trait SaltUtils extends Settings {
  final def loadSalt(): Array[Byte] = {
    val file = new File(s"${configFileDirectory}/salt")
    if (file.exists) {
      var buf = new Array[Char](saltLength)
      val reader = new FileReader(file)
      reader.read(buf, 0, saltLength)
      buf.map(_.toByte)
    } else {
      val salt = RandomSource.createArray(saltLength)
      val out = new FileOutputStream(file)
      try {
        out.write(salt)
      } finally {
        out.close()
      }
      salt
    }
  }
}
