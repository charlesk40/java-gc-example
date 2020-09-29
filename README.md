# java-gc-example



##Building:

``javac src/com/cds/example/CDSExample.java  -d `pwd`; jar cvfm CDSExample.jar src/com/cds/example/manifest.txt com/cds/example/CDSExample.class;``



##Running with CDS options (JDK 8):


Generate the archive classes.


`` sudo java -Xshare:dump -jar CDSExample.jar ``


Should produce the archive file called classes.jsa at:

`` ls -lh /Library/Java/JavaVirtualMachines/yjava_jdk-8.0_8u242b08.3911256/Contents/Home/jre/lib/server/classes.jsa ``


Use the share file to run:

``java  -Xshare:on  -jar CDSExample.jar ``



##Running with CDS options (JDK 11):

create a list of classes to include in the archive  -XX:DumpLoadedClassList

``java -XX:DumpLoadedClassList=classes.lst -jar CDSExample.jar``


Generate the archive classes.


`` sudo java -Xshare:dump -jar CDSExample.jar ``

Use the share file to run:

``java  -Xshare:on  -jar CDSExample.jar ``


##Running with CDS options (JDK 12):

The JDK comes with one and uses it automatically. If you want to turn that off, launch your application with -Xshare:off.



##Launch Time Measurements

On:

``time java  -Xshare:on  -jar CDSExample.jar ``

```
amountblood-lm:java-gc-example charlesk$ time java  -Xshare:on  -jar CDSExample.jar 
Starting CDSExample...
args:  []
Finished CDSExample.

real    0m0.070s
user    0m0.049s
sys 0m0.022s
```


Off:

``time java  -Xshare:off  -jar CDSExample.jar ``

```
amountblood-lm:java-gc-example charlesk$ time java  -Xshare:off  -jar CDSExample.jar 
Starting CDSExample...
args:  []
Finished CDSExample.

real    0m0.088s
user    0m0.065s
sys 0m0.025s
```



The interesting bit is real, which gives the wall-clock time it took to run the app. 70 ms to 88ms (without class data sharing)





## NOTES:

On Java 12+, Java classes are automatically loaded from the archive included in the JDK

On Java 13+, class-data archives can be automatically created by the JVM on shutdown with the command line option -XX:ArchiveClassesAtExit=${ARCHIVE}; to use it, add -XX:SharedArchiveFile=${ARCHIVE} on launch

On Java 10+, it is possible to hand-craft archives in three steps:

create a list of classes to include in the archive
$ java
    -XX:DumpLoadedClassList=classes.lst
    -jar app.jar
 
create the archive
$ java
    -Xshare:dump
    -XX:SharedClassListFile=classes.lst
    -XX:SharedArchiveFile=app-cds.jsa
    --class-path app.jar
 
use the archive
$ java
    -XX:SharedArchiveFile=app-cds.jsa
    -jar app.jar

    