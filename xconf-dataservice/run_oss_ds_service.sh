#!/bin/bash
configfile='./oss_ds_service.properties'
logfile='./oss_ds_logback.xml'
cmd="mvn -DappConfig=$configfile -Dlogback.configurationFile=$logfile jetty:run"
echo "$cmd"
eval "$cmd"
