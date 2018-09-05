cd /usr/local/lib
curl -O http://www.antlr.org/download/antlr-4.7.1-complete.jar

export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"

