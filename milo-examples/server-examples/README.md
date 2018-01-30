# How to use the SecureServerStandalone example

The purpose of this example is to show how to generate a jar file encapsulating an OPCUA server, which accepts
authenticated clients. It can be used along with the SecureClientStandalone example.

## How to build and start the opcua server

1. Build the server jar with the maven profile "standalone-server"
   `mvn package -P standalone-server -Dmaven.test.failure.ignore=true`
   The jar will appear in "milo/milo-examples/server-examples/target/"

2. Make sure the server knows (or can verify) the certificate of any client you'll try to connect

3. Execute the server jar
  `java -jar milo-opcua-server-jar-with-dependencies.jar`
