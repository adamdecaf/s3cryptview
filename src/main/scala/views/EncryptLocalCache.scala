package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._

trait EncryptLocalCredentials {
  this: InitializeLocalCache =>

  // Okay so.
  // - Make folder ~/.s3cryptview/$(encrypt bucket name with password)/
  //   - Make file called "credentials", (filename encrypted with password?) encrypted with aws keys
  //   - Make file called "metadata" (filename encrypted with password?) with line-by-line info for each file
  // - Pull down from bucket, save all and start listing out to the UI

  protected def encryptLocalCredentials(main: MainFrame)(bucket: String, password: String, awsKey: String, awsSecretKey: String) = new GridPanel(1,1) {

    // main.contents = initializeLocalCache(main)(bucket, awsKey, awsSecretKey)
  }

  // Notes
  // FSUtils.overwriteFile(path)(contents)
}
