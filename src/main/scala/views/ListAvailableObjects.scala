package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._
import org.decaf.s3cryptview.aws.AWSClient

trait ListAvailableObjects extends AWSClient {
  protected def listAvailableObjects(main: MainFrame)(bucket: String) = new GridPanel(1, 1) {

  }
}
