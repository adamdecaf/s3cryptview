package org.decaf.s3cryptview.crypto
import java.util.Arrays
import org.specs2.mutable.Specification

object AESUtilsSpec extends Specification {
  "encrypt and decrypt a value" in {
    val password = "my awesome password"
    val salt = "some salt".getBytes("UTF-8")
    val message = ("this is my message" * 1).getBytes("UTF-8")

    val AESUtils.AESEncryptionResult(iv, ciphertext) = AESUtils.encrypt(message, password, salt)
    val decrypted = AESUtils.decrypt(ciphertext, password, iv, salt)
    decrypted must beSome
    decrypted.filter { str =>
      Arrays.equals(str, message)
    } must beSome
  }
}
