# java-gc-example



##Building:

``javac src/com/gc/example/GCExample.java  -d `pwd`; jar cvfm GCExample.jar src/com/gc/example/manifest.txt com/gc/example/GCExample.class;``



##Running with GC options (JDK 11):

``rm -f `pwd`/gc*; java -Xms50m -Xmx50m  -Xlog:gc*=debug:file=`pwd`/gc.log:tags,time:filecount=10,filesize=4M -jar GCExample.jar ``


##Running with JFR options (JDK 11):

``java -Xms50m -Xmx50m  -XX:+FlightRecorder -XX:StartFlightRecording=disk=true,maxage=2h -jar GCExample.jar ``

##Taking the dump:

``jcmd 25270 JFR.dump name=1 filename=`pwd`/gc.jfr``


##Open JFR file with Java Mission Control and analyze.

Running GCViewer to analyze gc logs:

``java -jar gcviewer-1.36.jar``


