package org.decaf.s3cryptview
import scala.swing._
import scala.swing.event._
import org.decaf.s3cryptview.fs._

object Boot extends SimpleSwingApplication {
  println("s3cryptview is starting")

  def top = new MainFrame {
    title = "s3cryptview"

    val configFileDirectory = "~/.s3cryptview/"
    if (FSUtils.directoryHasFilesUnder(configFileDirectory)) {

      val encryptedAccessKeyFiles = FSUtils.getFilesDirectlyUnderDirectory(configFileDirectory)
      if (encryptedAccessKeyFiles.size == 1) {
        // prompt for the password and then try and decrypt and show the contents.

        // todo: create differnet grip panels from views/ based on the conditions
        // contents = new GridPanel(2, 2) {
        //   contents.append(gridPanel)
        //   border = Swing.EmptyBorder(10, 10, 10, 10)
        // }

      } else {
        // show them to the user (the filename is unencrypted by default) and ask them to enter which one they want to view.

      }
    } else {
      // prompt them for a bucket name and AWS credentials (key and secret key)

    }

    // val gridPanel = new GridBagPanel { grid =>
    //   import GridBagPanel._
    //   val fileChooser = new FileChooser
    //   PasswordField(text0: String, colums0: Int)
    //   val c = new Constraints
    //   c.fill = Fill.Horizontal
    //   c.grid = (1, 1)
    //   FileChooser.SelectionMode.DirectoriesOnly
    //   def fileSelectionMode_=(s: SelectionMode.Value) { peer.setFileSelectionMode(s.id) }
    //   def openAction = Action("Open") { fileChooser.showOpenDialog(grid) }
    //   def openButton = new Button(openAction)
    //   layout(openButton) = c
    //   def saveAction = Action("Save") { fileChooser.showSaveDialog(grid) }
    //   def saveButton = new Button(saveAction)
    //   layout(saveButton) = c
    // }

    // contents = new GridPanel(2, 2) {
    //   contents.append(gridPanel)
    //   border = Swing.EmptyBorder(10, 10, 10, 10)
    // }
  }
}
