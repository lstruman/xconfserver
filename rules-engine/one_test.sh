#!/bin/bash

testclass='com.comcast.apps.hesperius.ruleengine.RulesTest#test02'
#testclass='com.comcast.apps.hesperius.ruleengine.RulesTest'

cmd="mvn -DfailIfNoTests=false -Dtest=$testclass test"
echo "$cmd"
eval "$cmd"


