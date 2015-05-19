package org.decaf.s3cryptview
import scala.swing._
import scala.swing.event._
import org.decaf.s3cryptview.aws._
import org.decaf.s3cryptview.fs._
import org.decaf.s3cryptview.views._

object Boot extends SimpleSwingApplication with Settings
    with PromptForBucketAndPassword
    with AskForAWSCreds
    with AskToInitializeLocalCache
    with ListAvailableObjects
    with PromptForPasswordToDecryptLocally {

  println("s3cryptview is starting")

  def top = new MainFrame { main =>
    main.title = "s3cryptview"

    FSUtils.makeDirectoryIfMissing(configFileDirectory)
    if (FSUtils.directoryHasFilesUnder(configFileDirectory, List("salt"))) {

      val encryptedAccessKeyFiles = FSUtils.getFilesDirectlyUnderDirectory(configFileDirectory, List("salt"))
      println(encryptedAccessKeyFiles)
      if (encryptedAccessKeyFiles.size == 1) {

        // prompt for the password and then try and decrypt and show the contents.
        main.contents = promptForPasswordToDecryptLocally(main)

      } else {

        // todo: show them to the user (the filename is unencrypted by default) and ask them to enter which one they want to view.
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
  }
}
