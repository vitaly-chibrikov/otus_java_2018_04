javac src/com/javapapers/java/instrumentation/*.java -cp lib/javassist-3.14.0-GA.jar

jar -cvfm agent.jar META-INF/MANIFEST.MF src/com/javapapers/java/instrumentation/*.java

java -cp src:./lib/javassist-3.14.0-GA.jar -javaagent:agent.jar com.javapapers.java.instrumentation.TestInstrumentation