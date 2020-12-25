#!/bin/bash
configfile='./angular_service.properties'
logfile='./angular_logback.xml'
cmd="mvn -DappConfig=$configfile -Dlogback.configurationFile=$logfile jetty:run"
echo "$cmd"
eval "$cmd"
