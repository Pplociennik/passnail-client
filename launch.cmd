@ECHO OFF

set JAVA_HOME=D:\Development\Java\Coretto_11\jdk11.0.9_12
set PATH=%JAVA_HOME%\bin

set PROJECT_ENV_PATH=%CD%
set PROJECT_DB_PATH=%PROJECT_ENV_PATH%

set AUTH_DB_LOGIN=%USERNAME%
set AUTH_DB_PASSWORD=%COMPUTERNAME%

cd passnail.core/target
java -jar passnail.core-0.0.1-SNAPSHOT.jar %PROJECT_ENV_PATH%\passnail.generator %PROJECT_DB_PATH% %AUTH_DB_LOGIN% %AUTH_DB_PASSWORD%