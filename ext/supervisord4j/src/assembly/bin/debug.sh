#!/bin/bash

PROC_HOME=$(cd `dirname $0`;pwd)
JAR_FILE=$1

[[ ! -f ${JAR_FILE} ]] && echo " Usage $0 <JAR_FILE>" && exit 1

if ( ps -ef | grep -v grep | grep -v $$ | grep -q $JAR_FILE ); then
    echo "$JAR_FILE process already running,kill now."
    jps | grep -vi jps | grep $JAR_FILE | awk '{print $1}' | xargs kill
fi

echo "java -jar ${PROC_HOME}/../${JAR_FILE}"

java -jar ${PROC_HOME}/../${JAR_FILE}

jps