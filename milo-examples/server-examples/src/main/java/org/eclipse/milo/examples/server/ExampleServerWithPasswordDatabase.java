/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.examples.server;

import java.io.File;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.identity.UsernameIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.util.HostnameUtil;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DirectoryCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.CryptoRestrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_USERNAME;

public class ExampleServerWithPasswordDatabase {

    //FOLDER NAMES
    private static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
    private static final String SECURITY = "security";
    private static final String PKI = "pki";


    //OPC UA MILO PROPERTIES
    private static final String _0_0_0_0 = "0.0.0.0";
    private static final String ECLIPSE_MILO_OPC_UA_EXAMPLE_SERVER = "Eclipse Milo OPC UA Example Server";
    private static final String ECLIPSE_MILO_EXAMPLE_SERVER = "eclipse milo example server";
    private static final String ECLIPSE = "eclipse";
    private static final String EXAMPLE = "example";
    private static final String URN_ECLIPSE_MILO_EXAMPLES_SERVER = "urn:eclipse:milo:examples:server:";
    private static final String PRODUCT_URI = "urn:eclipse:milo:example-server";



    //DATABASE PROPERTIES
    private static final String JDBC_SQLITE = "jdbc:sqlite:";
    private static final String USERS_DB = "Users.db";
    private static final String DATABASE_PASSWORD_COLUMN = "Password";
    private static final String DATABASE_USER_COLUMN = "Username";
    private static final String DATABASE_NAME = "Users";



    //LOGGER SECURITY DATABASE STATUS
    private static final String PASSWORD_IS_CORRECT = "Password is correct.";
    private static final String FOUND_USER_IN_DATABASE = "Found user in database.";

    //LOGGER DATABASE STATUS
    private static final String PROBLEM_CLOSING_USER_DATABASE = "Problem closing user database";
    private static final String PROBLEM_ACCESSING_USER_DATABASE = "Problem accessing user database";
    private static final String CONNECTED_TO_USER_DATABASE = "Connected to user database";
    private static final String DATABASE_FOUND = "Database found {}";

    //LOGGER FOLDER AND FILE STATUS
    private static final String PKI_DIR = "pki dir: {}";
    private static final String NO_USER_DATABASE = "No database file: ";
    private static final String UNABLE_TO_CREATE_SECURITY_TEMP_DIR = "unable to create security temp dir: ";
    private static final String NO_SECURITY_TEMP_DIR = "No security temp dir: ";
    private static final String SECURITY_TEMP_DIR = "security temp dir: {}";


    //LOGGER SQL ERRORS
    private static final String SQL_STATEMENT = "SQL Statement: ";

    //LOGGER OPC UA SECURITY ERRORS
    private static final String CERTIFICATE_IS_MISSING_THE_APPLICATION_URI = "certificate is missing " +
            "the application URI";


    Logger logger = LoggerFactory.getLogger(this.getClass());

    static {
        CryptoRestrictions.remove();

        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        ExampleServerWithPasswordDatabase server = new ExampleServerWithPasswordDatabase();

        server.startup().get();

        final CompletableFuture<Void> future = new CompletableFuture<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));

        future.get();
    }

    private final OpcUaServer server;

    public ExampleServerWithPasswordDatabase() throws Exception {
        File securityTempDir = new File(System.getProperty(JAVA_IO_TMPDIR), SECURITY);
        if (!securityTempDir.exists() && !securityTempDir.mkdirs()) {
            throw new Exception(UNABLE_TO_CREATE_SECURITY_TEMP_DIR + securityTempDir);
        }
        logger.info(SECURITY_TEMP_DIR, securityTempDir.getAbsolutePath());

        KeyStoreLoader loader = new KeyStoreLoader().load(securityTempDir);

        DefaultCertificateManager certificateManager = new DefaultCertificateManager(
                loader.getServerKeyPair(),
                loader.getServerCertificateChain()
        );

        File pkiDir = securityTempDir.toPath().resolve(PKI).toFile();
        DirectoryCertificateValidator certificateValidator = new DirectoryCertificateValidator(pkiDir);
        logger.info(PKI_DIR, pkiDir.getAbsolutePath());

        //Build the predicate to check login data
        Predicate<UsernameIdentityValidator.AuthenticationChallenge> authPredicate = authenticationChallenge -> {
            //Check access to the security directory
            File securityTempDir1 = new File(System.getProperty(JAVA_IO_TMPDIR), SECURITY);
            if (!securityTempDir1.exists()) {
                logger.debug(NO_SECURITY_TEMP_DIR + securityTempDir1);
                return false;
            }
            //Check if the user database exists in the security directory
            File userDatabase = securityTempDir.toPath().resolve(USERS_DB).toFile();
            if (!userDatabase.exists()) {
                logger.debug(NO_USER_DATABASE + userDatabase);
                return false;
            }
            logger.info(DATABASE_FOUND, userDatabase.getAbsolutePath());

            //There is a database, try to connect to it
            Connection conn = null;
            try {
                String databaseUrl = JDBC_SQLITE + userDatabase.getAbsolutePath();
                conn = DriverManager.getConnection(databaseUrl);
                logger.info(CONNECTED_TO_USER_DATABASE);

                //https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet
                //Prepared Statements (with Parameterized Queries)
                String sql = "SELECT " + DATABASE_PASSWORD_COLUMN + " FROM " + DATABASE_NAME +
                        " WHERE " + DATABASE_USER_COLUMN + "=?";
                String custname = authenticationChallenge.getUsername();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, custname);

                //Execute query looking for the user specified in the authenticationChallenge
                ResultSet rs = pstmt.executeQuery();
                logger.info(SQL_STATEMENT + sql);

                if (!rs.next()) {
                    return false;
                } else {
                    logger.info(FOUND_USER_IN_DATABASE);
                }

                //Password is stored in the database in hashed form, so verify the password against the hash

                //Argon2, the password-hashing function that won the Password Hashing Competition (PHC).
                //Source: https://github.com/phxql/argon2-jvm
                Argon2 argon2 = Argon2Factory.create();
                //verify hashes the password and compare it to the hashed password from the database
                //The hash includes the salt. The verify method extracts the salt from the hash and uses that.
                // (https://github.com/phxql/argon2-jvm/issues/19)
                if (argon2. verify(rs.getString(DATABASE_PASSWORD_COLUMN), authenticationChallenge.getPassword())) {
                    logger.info(PASSWORD_IS_CORRECT);
                    return true;
                }

            } catch (SQLException e) {
                logger.error(PROBLEM_ACCESSING_USER_DATABASE, e);
                return false;

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    logger.error(PROBLEM_CLOSING_USER_DATABASE, ex);
                }
            }

            return false;
        };

        UsernameIdentityValidator identityValidator = new UsernameIdentityValidator(false, authPredicate);

        List<String> bindAddresses = newArrayList();
        bindAddresses.add(_0_0_0_0);

        List<String> endpointAddresses = newArrayList();
        endpointAddresses.add(HostnameUtil.getHostname());
        endpointAddresses.addAll(HostnameUtil.getHostnames(_0_0_0_0));

        // The configured application URI must match the one in the certificate(s)
        String applicationUri = certificateManager.getCertificates().stream()
                .findFirst()
                .map(certificate ->
                        CertificateUtil.getSubjectAltNameField(certificate, CertificateUtil.SUBJECT_ALT_NAME_URI)
                                .map(Object::toString)
                                .orElseThrow(() -> new RuntimeException(CERTIFICATE_IS_MISSING_THE_APPLICATION_URI)))
                .orElse(URN_ECLIPSE_MILO_EXAMPLES_SERVER + UUID.randomUUID());

        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
                .setApplicationUri(applicationUri)
                .setApplicationName(LocalizedText.english(ECLIPSE_MILO_OPC_UA_EXAMPLE_SERVER))
                .setBindPort(12686)
                .setBindAddresses(bindAddresses)
                .setEndpointAddresses(endpointAddresses)
                .setBuildInfo(
                        new BuildInfo(
                                PRODUCT_URI,
                                ECLIPSE,
                                ECLIPSE_MILO_EXAMPLE_SERVER,
                                OpcUaServer.SDK_VERSION,
                                "", DateTime.now()))
                .setCertificateManager(certificateManager)
                .setCertificateValidator(certificateValidator)
                .setIdentityValidator(identityValidator)
                .setProductUri(PRODUCT_URI)
                .setServerName(EXAMPLE)
                .setSecurityPolicies(
                        EnumSet.of(
                                SecurityPolicy.None,
                                SecurityPolicy.Basic128Rsa15,
                                SecurityPolicy.Basic256,
                                SecurityPolicy.Basic256Sha256,
                                SecurityPolicy.Aes128_Sha256_RsaOaep,
                                SecurityPolicy.Aes256_Sha256_RsaPss))
                .setUserTokenPolicies(
                        ImmutableList.of(
                                USER_TOKEN_POLICY_USERNAME))
                .build();

        server = new OpcUaServer(serverConfig);

        server.getNamespaceManager().registerAndAdd(
            ExampleNamespace.NAMESPACE_URI,
            idx -> new ExampleNamespace(server, idx));
    }

    public OpcUaServer getServer() {
        return server;
    }

    public CompletableFuture<OpcUaServer> startup() {
        return server.startup();
    }

    public CompletableFuture<OpcUaServer> shutdown() {
        return server.shutdown();
    }

}
