package org.decaf.s3cryptview.crypto
import java.util.Arrays
import javax.crypto.BadPaddingException
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

  "operate without the salt also" in {
    val password = "my awesome password"
    val salt = "some salt".getBytes("UTF-8")
    val message = ("this is my message" * 1).getBytes("UTF-8")

    val AESUtils.AESEncryptionResult(_, ciphertext) = AESUtils.encrypt(message, password, salt)
    val decrypted = AESUtils.decrypt(ciphertext, password, salt)
    decrypted must beSome
    decrypted.filter { str =>
      Arrays.equals(str, message)
    } must beSome
  }

  "using a diff salt won't let us decrypt" in {
    val password = "my awesome password"
    val salt1 = "some salt".getBytes("UTF-8")
    val salt2 = "othersalt".getBytes("UTF-8")
    val message = ("this is my message" * 1).getBytes("UTF-8")

    val AESUtils.AESEncryptionResult(iv, ciphertext) = AESUtils.encrypt(message, password, salt1)
    lazy val decrypted = AESUtils.decrypt(ciphertext, password, iv, salt2)
    decrypted must throwA[BadPaddingException]
  }
}
