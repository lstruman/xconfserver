#!/bin/bash

#testclass='com.comcast.apps.hesperius.ruleengine.RulesTest'
#testclass='com.comcast.apps.hesperius.ruleengine.RulesTest#test02'

testclass='com.comcast.apps.hesperius.ruleengine.RulesTest#testJsonMapping'

cmd="mvn -DfailIfNoTests=false -Dtest=$testclass test"
echo "$cmd"
eval "$cmd"


