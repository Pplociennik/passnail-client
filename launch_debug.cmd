@ECHO OFF

set JAVA_HOME=D:\Development\Java\Coretto_11\jdk11.0.9_12
set PATH=%JAVA_HOME%\bin

set PROJECT_ENV_PATH=%CD%
set PROJECT_DB_PATH=%PROJECT_ENV_PATH%

set AUTH_DB_LOGIN=%USERNAME%
set AUTH_DB_PASSWORD=%COMPUTERNAME%

cd passnail.core/target
java -Xdebug -Djava.awt.headless=false -Xrunjdwp:transport=dt_socket,server=y,address=8001,suspend=y -jar passnail.core-1.0.0.jar %PROJECT_ENV_PATH%\passnail.generator %PROJECT_DB_PATH% %AUTH_DB_LOGIN% %AUTH_DB_PASSWORD% 