package org.decaf.s3cryptview.crypto
import javax.crypto.{Cipher, SecretKeyFactory}
import javax.crypto.spec.{IvParameterSpec, PBEKeySpec, SecretKeySpec}

object AESUtils {
  case class AESEncryptionResult(iv: Array[Byte], ciphertext: Array[Byte])

  def encrypt(contents: Array[Byte], password: String, salt: Array[Byte]): AESEncryptionResult = {
    val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val keySpec = new PBEKeySpec(password.toArray, salt, 65536, 256)
    val temp = secretKeyFactory.generateSecret(keySpec)
    val secret = new SecretKeySpec(temp.getEncoded(), "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, secret)

    val params = cipher.getParameters()
    val iv = params.getParameterSpec(classOf[IvParameterSpec]).getIV()
    val ciphertext = cipher.doFinal(contents)

    AESEncryptionResult(iv, ciphertext)
  }

  final def decrypt(contents: Array[Byte], password: String, iv: Array[Byte], salt: Array[Byte]): Option[Array[Byte]] = {
    val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val keySpec = new PBEKeySpec(password.toArray, salt, 65536, 256)
    val temp = secretKeyFactory.generateSecret(keySpec)
    val secret = new SecretKeySpec(temp.getEncoded(), "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val params = cipher.getParameters()

    cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv))
    Some(cipher.doFinal(contents))
  }
}
