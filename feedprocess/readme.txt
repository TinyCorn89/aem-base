1)for build use the command following command from this folder
mvn clean install

2) this will create a feedprocess-1.0-SNAPSHOT.jar in the target folder .rename it to feedprocess.jar place it in feedprocess directory (one levelup)

3) execute the canadianpressftpfeed.bat file  (please read the confi section below)

#Configuration

1)/config/aem.properties provide the username/password/
a) in the aem url ,change the hostname and the dam folderpath
 ex:if the plan is to upload the files in a dam folder called /content/dam/projects/tc then the change the  patch to /content/dam/projects/tc.createasset.html
 http://localhost:4502/content/dam/projects/tc.createasset.html
 
 2)/config/canadianftp.properties
 
 // this the hostname
ftp.serverAddress=ftp.transcontinentalnewsnet.com 
ftp.userId=cp_atlantic
ftp.password=@ccess2ftp!
// remote dir on ftp from where need to download the files
ftp.remoteDirectory=/xml/sports/ 
// local directory to process download and process
ftp.localDirectory=c:\\curl\\ftptest5 //
ftp.timeout=600
// below three properties will control the download .below config will download the files from FTP whose timestamnp is after 5/5/2014 00:00:00 .for now these are mandotory
ftp.day=5
ftp.year=2014
ftp.month=5
 
3)in the same config folder "CPNewsML.xsl" and "CPNewsML.dtd" are present .Make sure to use the latest ones always.
