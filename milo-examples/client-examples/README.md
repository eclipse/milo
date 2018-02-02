# How to use the SecureClientStandalone example

The purpose of this example is to show how to generate a jar file encapsulating an OPCUA client, which tries to connect
 to a server on a secure channel using the best available security. The client authenticates using a
 certificate, which has to be put into the server's trusted folder or has to be verifiable in the servers trust chain.
 It can be used along with the SecureServerStandalone example.

## How to generate a self-signed OPCUA-compliant certificate and load it into a java keystore:

1. Generate openssl command using the config:
    `openssl req -x509 -sha256 -newkey rsa:2048 -keyout privateKey.key -out certificate.crt -extensions v3_self_signed
    -config=resources/openssl.cnf`

2. Convert into one file
    `openssl pkcs12 -export -name opcua -in certificate.crt -inkey privateKey.key > opcua.p12`

3. Import certificate into keystore
    `keytool -importkeystore -srckeystore opcua.p12 -destkeystore opcua.keystore -srcstoretype pkcs12 -alias opcua`

## How to build and start the opcua client

1. Generate the certificate and load it into the keystore as specified above.

2. Build the client jar with the maven profile "standalone-client"
   `mvn package -P standalone-client -Dmaven.test.failure.ignore=true`
   The jar will appear in "milo/milo-examples/client-examples/target/"

3. Make sure the keystore is under secrets/opcua.keystore, so it is found by the code.

4. Make sure the server knows the certificate of the client (or opening a secure channel will be rejected)

3. Execute the client jar
  `java -jar milo-opcua-client-jar-with-dependencies.jar`
