package org.decaf.s3cryptview.views
import java.io.FileOutputStream
import scala.swing._
import scala.swing.event._
import org.decaf.s3cryptview.Settings
import org.decaf.s3cryptview.aws._
import org.decaf.s3cryptview.crypto._

trait AskForAWSCreds extends AWSClient with SaltUtils with Settings {
  this: AskToInitializeLocalCache =>

  protected def askForAWSCreds(main: MainFrame)(bucket: String, password: String) = new GridPanel(3, 1) {
    val awsKeyField = new TextField(15)
    val awsSecretKeyField = new TextField(15)

    contents += new FlowPanel {
      contents += new Label("AWS Access Key: ")
      contents += awsKeyField
    }
    contents += new FlowPanel {
      contents += new Label("AWS Secret Access Key: ")
      contents += awsSecretKeyField
    }
    contents += new FlowPanel {
      contents += new Button { button =>
        button.text = "Encrypt Local AWS Credentials"
        button.reactions += {
          case ButtonClicked(_) =>
            button.text = "Encrypting..."
            button.enabled = false
            storeAWSCredentials(bucket, password, awsKeyField.text, awsSecretKeyField.text)
            main.contents = askToInitializeLocalCache(main)(bucket, awsKeyField.text, awsSecretKeyField.text)
        }
      }
    }
  }

  private[this] def storeAWSCredentials(bucket: String, password: String, awsAccessKey: String, awsSecretAccessKey: String): Unit = {
    val salt = loadSalt()
    val encryptedBucketName = AESUtils.encrypt(bucket.getBytes("UTF-8"), password, salt)
    val encodedBucketName = Hex.encode(encryptedBucketName.ciphertext)
    val fileNameForBucket = s"${configFileDirectory}/${encodedBucketName}"

    val eeAccessKey = Hex.encode(AESUtils.encrypt(awsAccessKey.getBytes("UTF-8"), password, salt).ciphertext)
    val eeSecretKey = Hex.encode(AESUtils.encrypt(awsSecretAccessKey.getBytes("UTF-8"), password, salt).ciphertext)
    val contents = s"""${eeAccessKey}\n${eeSecretKey}""".toArray.map(_.toByte)

    val out = new FileOutputStream(fileNameForBucket)
    try {
      out.write(contents)
    } finally {
      out.close()
    }
  }
}
