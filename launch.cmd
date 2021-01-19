@ECHO OFF

set JAVA_HOME=D:\Development\Java\Coretto_11\jdk11.0.9_12
set PATH=%JAVA_HOME%\bin

cd passnail.core/target
java -cp "C:\Users\Pszemko\.m2\repository" -jar passnail.core-0.0.1-SNAPSHOT.jar INSTALLATION_PATH