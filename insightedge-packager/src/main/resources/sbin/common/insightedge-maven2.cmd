@echo off

set INSIGHTEDGE_VER=1.1.0-SNAPSHOT

if "x%INSIGHTEDGE_HOME%"=="x" (
    set INSIGHTEDGE_HOME=%~dp0..
)

echo Installing InsightEdge %INSIGHTEDGE_VER% artifacts

call mvn install:install-file ^
    -Dpackaging=pom ^
    -Dfile=%INSIGHTEDGE_HOME%\tools\maven\poms\insightedge-package\pom.xml ^
    -DpomFile=%INSIGHTEDGE_HOME%\tools\maven\poms\insightedge-package\pom.xml

call mvn install:install-file ^
 -DgroupId=org.gigaspaces.insightedge ^
 -DcreateChecksum=true ^
 -DartifactId=insightedge-core ^
 -Dversion=%INSIGHTEDGE_VER% ^
 -DpomFile=%INSIGHTEDGE_HOME%\tools\maven\poms\insightedge-core\pom.xml ^
 -Dpackaging=jar ^
 -Dfile=%INSIGHTEDGE_HOME%\lib\insightedge-core-%INSIGHTEDGE_VER%.jar

call mvn install:install-file ^
 -DgroupId=org.gigaspaces.insightedge ^
 -DcreateChecksum=true ^
 -DartifactId=insightedge-scala ^
 -Dversion=%INSIGHTEDGE_VER% ^
 -DpomFile=%INSIGHTEDGE_HOME%\tools\maven\poms\insightedge-scala\pom.xml ^
 -Dpackaging=jar ^
 -Dfile=%INSIGHTEDGE_HOME%\lib\insightedge-scala-%INSIGHTEDGE_VER%.jar

rem Install spring.aopalliance to local maven repo (fixes SBT builds)
call mvn dependency:get ^
 -Dartifact=org.aopalliance:com.springsource.org.aopalliance:1.0.0 ^
 -DremoteRepositories=http://repository.springsource.com/maven/bundles/external/
