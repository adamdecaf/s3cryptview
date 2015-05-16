package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._

trait AskForAWSCreds {
  this: EncryptLocalCredentials =>

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
            main.contents = encryptLocalCredentials(main)(bucket, password, awsKeyField.text, awsSecretKeyField.text)
        }
      }
    }
  }
}
