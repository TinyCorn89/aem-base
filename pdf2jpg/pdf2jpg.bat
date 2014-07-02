@echo off
set exec_path=c:/projects/tc/aem-base/pdf2jpg
java -cp %exec_path%/lib/commons-logging-1.1.1.jar;%exec_path%/lib/fontbox-1.8.6.jar;%exec_path%/lib/pdfbox-1.8.6.jar;%exec_path%/pdf2jpg.jar com.tc.pdf2jpg.ApachePDFImage %1
@echo on