# How to use the standalone examples

The purpose of these examples is to generate standalone jar files encapsulating an OPC UA Client and OPC UA Server.

## How to build the OPC UA Client and Server jars:

The build of the jar takes a while, so there are seperate profiles for
the different examples.

For the version using certificate, build with the maven profile "certificate-example":

    mvn package -P certificate-example

For the version using a database of users, build with the maven profile "userdatabase-example":

    mvn package -P userdatabase-example

The jars will appear in `milo/milo-examples/standalone-examples/target/



## Example with authentication using certificates
In `SecureServerStandalone` and `SecureClientStandalone`, the client
authenticates using a certificate that must be trusted and verified by the server.

Certificates can be generated using openssl or using the SelfSignedCertificateBuilder. The example client demonstrates
how to access an existing certificate in a keystore and the server demonstrates using the SelfSignedCertificateBuilder.

### How to generate a self-signed OPC UA compliant certificate using openssl and load it into a Java keystore:

1. Generate openssl command using the config:

        openssl req -x509 -sha256 -newkey rsa:2048 -keyout privateKey.key -out certificate.crt -extensions v3_self_signed -config=resources/openssl.cnf

2. Convert into one file

        openssl pkcs12 -export -name opcua -in certificate.crt -inkey privateKey.key > opcua.p12

3. Import certificate into keystore

        keytool -importkeystore -srckeystore opcua.p12 -destkeystore opcua.keystore -srcstoretype pkcs12 -alias opcua


## How to run the example using certificates:

1. Generate the certificate and load it into the keystore as specified above.

2. Make sure the keystore is under `secrets/opcua.keystore`, so it is found by the code.

3. Build the client and server jars as specified above.

4. Make sure the server knows the certificate of the client (or opening a secure channel will be rejected). The server
 will log the security directory being used upon startup. When a client connects the certificate will be placed in the
 `pki/rejected` directory if it's not already trusted. Moving a certificate (possibly pre-emptively) to
 `pki/trusted/certs` will allow clients to connect with that certificate.

5. Execute the server jar:

        java -jar milo-certificate-server-jar-with-dependencies.jar

6. Execute the client jar:

        java -jar milo-certificate-client-jar-with-dependencies.jar

## Example with authentication using a password database

In `ExampleServerWithPasswordDatabase` and `ClientWithUserNameAndPasswordExample`
the authentication is done via username and password. The server maintains
a database of usernames and corresponding hashed passwords.

1. Build the client and server jars as specified above.

2. Execute the server jar:

        java -jar milo-userdatabase-server-jar-with-dependencies.jar

    This will generate the user database in a file called `Users.db`.
    That is an SQLite database. You can open the file with an
    SQLite browser and add any user plus the argon2 hash for their
    passwords directly. Alternatively:

3. Execute the client jar:

        java -jar milo-userdatabase-client-jar-with-dependencies.jar

    The user will be recjected. However, this moves the user into a
    database of rejected users, in the `rejected` directory. You can
    open this database too and copy over any users, you do want to allow.

4. Restart the server and then the client. The connection should succeed
    now.

