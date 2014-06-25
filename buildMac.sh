export userDefFolder=/usr/share/maven

cd cq-parent

$userDefFolder/bin/mvn install

cd ../AEM—56-parent

$userDefFolder/bin/mvn install

cd ../AEM—56-common

$userDefFolder/bin/mvn install

cd ../AEM—56-vault

$userDefFolder/bin/mvn clean package content-package:install

cd ..
