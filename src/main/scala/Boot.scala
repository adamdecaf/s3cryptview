package org.decaf.s3cryptview
import scala.swing._
import scala.swing.event._
import org.decaf.s3cryptview.aws._
import org.decaf.s3cryptview.fs._
import org.decaf.s3cryptview.views._

object Boot extends SimpleSwingApplication
    with PromptForBucketAndPassword
    with AskForAWSCreds
    with EncryptLocalCredentials
    with InitializeLocalCache
    with ListAvailableObjects {

  println("s3cryptview is starting")

  def top = new MainFrame { main =>
    main.title = "s3cryptview"
    // main.size = new Dimension(600, 350)

    val configFileDirectory = "~/.s3cryptview/"
    if (FSUtils.directoryHasFilesUnder(configFileDirectory)) {

      val encryptedAccessKeyFiles = FSUtils.getFilesDirectlyUnderDirectory(configFileDirectory)
      if (encryptedAccessKeyFiles.size == 1) {
        // prompt for the password and then try and decrypt and show the contents.
        // val gridPanel = promptForPassword()

        val initial = new GridPanel(2, 2) { grid =>
          grid.hGap = 3
          grid.vGap = 3
          grid.contents += new Button { button =>
            button.text = "Press me!"
            button.reactions += {
              case ButtonClicked(b) =>
                println(b)
                button.text = "Pressed!"
            }
          }
        }

        main.contents = initial

        // todo: create differnet grip panels from views/ based on the conditions
        // contents = new GridPanel(2, 2) {
        //   contents.append(gridPanel)
        //   border = Swing.EmptyBorder(10, 10, 10, 10)
        // }

      } else {
        // show them to the user (the filename is unencrypted by default) and ask them to enter which one they want to view.
        val listModel: Array[String] = Array("Jane Doe", "John Smith", "Kathy Green")
        val initiallySelected = 0
        val listMe: ListView[String] = new ListView[String](listModel) {
          selection.intervalMode = ListView.IntervalMode.Single
          selectIndices(initiallySelected)
          visibleRowCount = 5
        }
        val list = new GridPanel(2,2) { grid =>
          grid.contents += new ScrollPane(listMe)
        }
        main.contents = list
      }
    } else {
      // prompt them for a bucket name and AWS credentials (key and secret key)
      main.contents = promptForBucketAndPassword(main)
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
