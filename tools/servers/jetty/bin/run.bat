@echo off

if "" == "%JAVA_HOME%" goto errorJavaHome

set "JAVA_OPTS=-Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -DSTART=../etc/start.config -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

"%JAVA_HOME%/bin/java" %JAVA_OPTS% -jar ../start.jar ../etc/jetty.xml

goto end

:errorJavaHome
	echo JAVA_HOME not defined.

	goto end

:end