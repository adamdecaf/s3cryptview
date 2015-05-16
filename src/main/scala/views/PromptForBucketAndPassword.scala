package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._

trait PromptForBucketAndPassword {
  this: AskForAWSCreds =>

  protected def promptForBucketAndPassword(main: MainFrame) = new GridPanel(3, 1) { grid =>
    val bucketField = new TextField(15)
    val passwordField = new PasswordField(13)

    contents += new FlowPanel {
      contents += new Label("Bucket: ")
      contents += bucketField
    }
    contents += new FlowPanel {
      contents += new Label("Password: ")
      contents += passwordField
    }
    contents += new FlowPanel {
      contents += new Button { button =>
        button.text = "Setup Local Cache"
        button.reactions += {
          case ButtonClicked(_) =>
            button.text = "Setting up..."
            button.enabled = false
            main.contents = askForAWSCreds(main)(bucketField.text, passwordField.password.mkString)
        }
      }
    }
  }
}
