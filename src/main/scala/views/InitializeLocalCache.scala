package org.decaf.s3cryptview.views
import scala.swing._
import scala.swing.event._

trait InitializeLocalCache {
  this: ListAvailableObjects =>

  protected def initializeLocalCache(main: MainFrame)(bucekt: String, awsKey: String, awsSecretKey: String) = new GridPanel(1, 1) {
    // todo: Ask if we want to initialize local cache, probably shouldn't just automatically do it
    //  - Instead include a way to search? Or just view objects in the bucket?

    // todo: Just pass along s3client instead?
    // main.contents = listAvailableObjects(main)(bucket, awsKey, awsSecretKey)
  }
}
