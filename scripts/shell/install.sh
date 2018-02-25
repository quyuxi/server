#!/bin/bash

HOME=/var/resthome

SERVICE_NAME=server
VERSION=0.0.1-SNAPSHOT
MAIN_JAR=$SERVICE_NAME-$VERSION.jar
#1.先将软件包cp到/var并解压
#2.解压后的目录是 bin 和scripts
#3.执行  bash scripts/install.sh
#4.执行  bash scripts/start.sh

echo "----begin install----"

#如果文件夹不存在，创建文件夹
if [ ! -d "$HOME" ]; then
  mkdir  $HOME
fi

#拷贝jar包到 /var/resthome
cp -f /var/bin/$MAIN_JAR  /$HOME

echo "----end install----"

exit 0;

