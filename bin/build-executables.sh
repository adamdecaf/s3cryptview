#!/bin/bash
RELEASE_VERSION=0.1-SNAPSHOT

sbt assembly
ln -s target/scala-2.11/s3cryptview-assembly-$RELEASE_VERSION.jar build/s3cryptview-$RELEASE_VERSION.jar

# todo: Run tests over built jar?
