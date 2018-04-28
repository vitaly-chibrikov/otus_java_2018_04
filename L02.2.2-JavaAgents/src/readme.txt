javac agents/*.java

jar -cvfm Agent007.jar manifest.mf agents/*.class

java -javaagent:Agent007.jar agents.AgentTester
java -javaagent:Agent007.jar agents.AgentTester2

