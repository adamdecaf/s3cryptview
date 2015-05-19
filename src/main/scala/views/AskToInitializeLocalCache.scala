package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._

trait AskToInitializeLocalCache {
  this: ListAvailableObjects =>

  // Ask if we should sync (initialize) S3 state and local state.
  //  - Include a way to search? Or just view objects in the bucket?
  // Need to figure out how to specify and store the local dir (since we have bucket already)
  protected def askToInitializeLocalCache(main: MainFrame)(bucekt: String, awsKey: String, awsSecretKey: String) = new GridPanel(1, 1) {

    // main.contents = listAvailableObjects(main)(bucket, awsKey, awsSecretKey)
  }
}
