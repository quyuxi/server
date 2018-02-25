#!/bin/bash

HOME=/var/resthome

SERVICE_NAME=server
VERSION=0.0.1-SNAPSHOT
MAIN_JAR=$SERVICE_NAME-$VERSION.jar


echo "----begin uninstall----"

#判断进程存在，存在kill
ps -fe|grep $MAIN_JAR |grep -v grep
if [ $? -ne 0 ];then
echo "kill server....."
    kill -s 9 `ps -aux | grep $MAIN_JAR | awk '{print $2}'`
fi


#删除/var/resthome
if [ -d "$MAIN_JAR" ]; then
  rm -f /var/resthome/$MAIN_JAR
  rm -f /var/resthome
  echo "delete dir ..."
fi

exit 0;

