#How to use the standalone examples

The purpose of these examples is to generate standalone jar files encapsulating an OPC UA Client and OPC UA Server.

The client authenticates using a certificate that must be trusted and verified by the server.

Certificates can be generated using openssl or using the SelfSignedCertificateBuilder. The example client demonstrates
how to access an existing certificate in a keystore and the server demonstrates using the SelfSignedCertificateBuilder.

## How to generate a self-signed OPC UA compliant certificate using openssl and load it into a Java keystore:

1. Generate openssl command using the config:
    `openssl req -x509 -sha256 -newkey rsa:2048 -keyout privateKey.key -out certificate.crt -extensions v3_self_signed
    -config=resources/openssl.cnf`

2. Convert into one file
    `openssl pkcs12 -export -name opcua -in certificate.crt -inkey privateKey.key > opcua.p12`

3. Import certificate into keystore
    `keytool -importkeystore -srckeystore opcua.p12 -destkeystore opcua.keystore -srcstoretype pkcs12 -alias opcua`

## How to build the OPC UA Client and Server jars:

Build the client and server jars with the maven profile "standalone":

    `mvn package -P standalone`
    
    The jars will appear in `milo/milo-examples/standalone-examples/target/`


## How to run the standalone examples:

1. Generate the certificate and load it into the keystore as specified above.

2. Make sure the keystore is under `secrets/opcua.keystore`, so it is found by the code.

3. Build the client and server jars as specified above.

4. Make sure the server knows the certificate of the client (or opening a secure channel will be rejected). The server 
 will log the security directory being used upon startup. When a client connects the certificate will be placed in the 
 `pki/rejected` directory if it's not already trusted. Moving a certificate (possibly pre-emptively) to 
 `pki/trusted/certs` will allow clients to connect with that certificate.

5. Execute the server jar:

    `java -jar milo-opcua-server-jar-with-dependencies.jar`

6. Execute the client jar:

    `java -jar milo-opcua-client-jar-with-dependencies.jar`

