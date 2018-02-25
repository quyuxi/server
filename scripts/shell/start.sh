#!/bin/bash

HOME=/var/resthome

SERVICE_NAME=server
VERSION=0.0.1-SNAPSHOT

MAIN_JAR=$SERVICE_NAME-$VERSION.jar

ps -fe|grep $MAIN_JAR |grep -v grep
if [ $? -ne 0 ]
then
echo "start server....."
    nohup java -jar $HOME/$MAIN_JAR >$HOME/start.log
else
echo "server is already runing....."
fi

