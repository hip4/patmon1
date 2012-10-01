@echo off
REM This batch file generates the source java-files for the jax-ws webservice 
REM
REM To execute this file the PatientMonitorServer has to be running!

del /S /Q src\main\java\ch\bfh\ti\sed\patmon1\ws\*

wsimport -b custombinding_login.xml -b adapterbindings.xsd -d src/main/java/ -Xnocompile -keep http://localhost:8888/login?wsdl
wsimport -b custombinding_session.xml -b adapterbindings.xsd -d src/main/java/ -Xnocompile -keep http://localhost:8888/session?wsdl

PAUSE
