package org.decaf.s3cryptview
import java.io.File

trait Settings {
  def configFileDirectory: String = {
    val home = System.getProperty("user.home")
    s"${home}/.s3cryptview/"
  }
  def saltLength: Int = 20
}
