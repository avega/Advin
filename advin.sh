#!/bin/sh

PRG=$0

while [ -h "$PRG" ]; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '^.*-> \(.*\)$' 2>/dev/null`
    if expr "$link" : '^/' 2> /dev/null >/dev/null; then
        PRG="$link"
    else
        PRG="`dirname "$PRG"`/$link"
    fi
done

progdir=`dirname "$PRG"`

java -cp $progdir/engine/target/engine.jar org.advin.engine.Main -modpath=modules %*
