@ECHO OFF

set JAVA_HOME=D:\Development\Java\Coretto_11\jdk11.0.9_12
set PATH=%JAVA_HOME%\bin

set PROJECT_ENV_PATH=%CD%

cd passnail.core/target
java -jar passnail.core-0.0.1-SNAPSHOT.jar %PROJECT_ENV_PATH%