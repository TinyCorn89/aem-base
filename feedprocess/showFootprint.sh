#!/bin/bash

function showSize {
	for i in {1..5}
	do
		j=`du -hks $1 | cut -f1`
		echo 'disk size in KB for '$1': ' $j 'at ' `date`
		#sleep 1
	done
}

function runImport {
	showSize $1
	echo "We are about to import assets..." $j
	oldVal=$j

	dateBefore=$(date +"%s")
	echo "before running $2:" $(date +"%F %T %z")
	source $(eval echo /Users/mpetzold/Documents/development/aem-base/feedprocess/$2)

	dateAfter=$(date +"%s")
	let elapsedTime=$dateAfter-$dateBefore
	let elapsedMinutes=$elapsedTime/60
	let elapsedSeconds=$elapsedTime%60
	echo "after running $2:" $(date +"%F %T %z") " - it took $elapsedMinutes min and $elapsedSeconds seconds - after: $dateAfter and before: $dateBefore"
	showSize $1

	echo "going to delete the just imported package..."
	sleep 1

	zipFile=""
	case "$2" in
		unisimporter.sh) zipFile="articles.zip";;
		canadianpressftpfeed.sh) zipFile="cp.zip";;
		poolpartytagsimporter*.sh) zipFile="PoolPartyTags.zip";;
		siteimporter*.sh) zipFile="tcsiteconfig.zip";;
	esac
	curl -u admin:admin -X POST $(eval echo http://localhost:4502/crx/packmgr/service/.json/etc/packages/tc/$zipFile?cmd=uninstall)
	curl -u admin:admin -X POST $(eval echo http://localhost:4502/crx/packmgr/service/.json/etc/packages/tc/$zipFile?cmd=delete)
	sleep 1
}

echo "Going to run importer for $2 times"
sum=0
totalTime=0
for i in $(eval echo {1..$2}); do
		runImport $1 $3
		let delta=j-oldVal
		let sum=sum+delta
		let totalTime=totalTime+elapsedTime
		echo "before:" $oldVal "and after:" $j "and delta is" $delta " and sum " $sum 
done

sleep 1
let average=$sum/$2
let averageTime=$totalTime/$2
echo "sum at end of "$2" imports, in KB:" $sum
let averageMin=$averageTime/60
let averageSec=$averageTime%60
echo "average delta in KB after "$2" imports:" $average " - it took an average time of $averageMin min and $averageSec seconds"
