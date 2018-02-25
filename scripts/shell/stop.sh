#!/bin/bash

HOME=/var/resthome

SERVICE_NAME=server
VERSION=0.0.1-SNAPSHOT

MAIN_JAR=$SERVICE_NAME-$VERSION.jar

ps -fe|grep $MAIN_JAR |grep -v grep
if [ $? -ne 0 ]
then
echo "server is already stop....."
else
     kill -s 9 `ps -aux | grep $MAIN_JAR | awk '{print $2}'`
echo "server stop....."
fi

