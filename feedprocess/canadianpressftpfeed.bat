@echo off
set exec_path=C:/projects/transc/aem-base/feedprocess
java -cp %exec_path%/config;%exec_path%/lib/commons-net-3.3.jar;%exec_path%/lib/log4j.jar;%exec_path%/feedprocess.jar com.tc.process.pressfeed.TCCanadianPressFeedProcessor
@echo on