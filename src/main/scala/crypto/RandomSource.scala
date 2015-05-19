package org.decaf.s3cryptview.crypto
import java.security.SecureRandom

object RandomSource {
  val random = new SecureRandom()

  final def createArray(bytes: Int): Array[Byte] = {
    var key = new Array[Byte](bytes)
    random.nextBytes(key)
    key
  }
}
