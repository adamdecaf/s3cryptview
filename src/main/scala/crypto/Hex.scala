package org.decaf.s3cryptview.crypto
import org.apache.commons.codec.binary.{Hex => ApacheHex}

object Hex {
  final def encode(arr: Array[Byte]): String = ApacheHex.encodeHexString(arr)
  final def decode(str: String): Array[Byte] = ApacheHex.decodeHex(str.toArray)
}
