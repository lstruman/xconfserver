#!/bin/bash

testclass='com.comcast.xconf.util.OneTest'

cmd="mvn -Dtest=$testclass test"
echo "$cmd"
eval "$cmd"
