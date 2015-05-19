package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._

trait PromptForPasswordToDecryptLocally {
  this: AskToInitializeLocalCache =>

  protected def promptForPasswordToDecryptLocally(main: MainFrame) = new GridPanel(2,1) { grid =>
    val passwordField = new PasswordField(13)

    contents += new FlowPanel {
      contents += new Label("Password: ")
      contents += passwordField
    }
    contents += new FlowPanel {
      contents += new Button { button =>
        button.text = "Decrypt Local Cache"
        button.reactions += {
          case ButtonClicked(_) =>
            button.text = "Decrypting.."
            button.enabled = false

            val bucket = ""
            val awsAccessKey = ""
            val awsSecretKey = ""
            main.contents = askToInitializeLocalCache(main)(bucket, awsAccessKey, awsSecretKey)
        }
      }
    }
  }
}
