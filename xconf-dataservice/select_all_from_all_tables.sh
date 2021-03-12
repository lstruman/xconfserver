#!/bin/bash

keyspace='appds'
#keyspace='demo'

for t in \
"ApprovedChange" \
"Change" \
"ConfigurationServiceURLs" \
"DcmRule" \
"DeviceSettings2" \
"Environment" \
"Feature" \
"FeatureControlRule" \
"FeatureControlRule2" \
"FeatureSet" \
"FilterAction" \
"FirmwareConfig" \
"FirmwareRule2" \
"FirmwareRule3" \
"FirmwareRule4" \
"FirmwareRuleTemplate" \
"Formula2" \
"GenericXconfNamedList" \
"IndexedLogFiles" \
"IpAddressGroupExtended" \
"LogFile" \
"LogFileList" \
"LogFilesGroups" \
"LogUploadSettings2" \
"Logs2" \
"Model" \
"PermanentTelemetry" \
"SettingProfiles" \
"SettingRules" \
"SingletonFilterValue" \
"Telemetry" \
"TelemetryRules" \
"UploadRepository" \
"VodSettings2" \
"XconfApprovedChange" \
"XconfChange" \
"XconfFeature" \
"XconfNamedList" \
"XconfChangedKeys" \
"TelemetryTwoProfiles" \
"TelemetryTwoRules"
do
   printf "\n%s\n\n" $t
   cmd="cqlsh -e 'SELECT * FROM \"$t\"' -k $keyspace"
   echo "$cmd"
   eval "$cmd"
done

