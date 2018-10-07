///*
// * Copyright (c) 2016 Kevin Herron
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * and Eclipse Distribution License v1.0 which accompany this distribution.
// *
// * The Eclipse Public License is available at
// *   http://www.eclipse.org/legal/epl-v10.html
// * and the Eclipse Distribution License is available at
// *   http://www.eclipse.org/org/documents/edl-v10.html.
// */
//
//package org.eclipse.milo.examples.server;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.Security;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.EnumSet;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.CompletableFuture;
//import java.util.function.Predicate;
//
//import com.google.common.collect.ImmutableList;
//import de.mkammerer.argon2.Argon2;
//import de.mkammerer.argon2.Argon2Factory;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
//import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
//import org.eclipse.milo.opcua.sdk.server.identity.UsernameIdentityValidator;
//import org.eclipse.milo.opcua.sdk.server.util.HostnameUtil;
//import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
//import org.eclipse.milo.opcua.stack.core.application.DirectoryCertificateValidator;
//import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
//import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
//import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
//import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
//import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import static com.google.common.collect.Lists.newArrayList;
//import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_USERNAME;
//
//public class ExampleServerWithPasswordDatabase {
//
//    private File baseDbDir;
//    private File trustedDbDir;
//    private File rejectedDbDir;
//    private File trustedUserDatabase;
//    private File rejectedUserDatabase;
//    private File securityTempDir;
//
//    // Argon2, the password-hashing function that won the Password Hashing Competition (PHC).
//    // Source: https://github.com/phxql/argon2-jvm
//    private Argon2 argon2 = Argon2Factory.create();
//
//    // FOLDER NAMES
//    private static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
//    private static final String SECURITY = "security";
//    private static final String PKI_DIR = "pki";
//    private static final String BASE_DB_DIR = "db";
//    private static final String TRUSTED_DB_DIR = "trusted";
//    private static final String REJECTED_DB_DIR = "rejected";
//
//    // OPC UA MILO PROPERTIES
//    private static final String _0_0_0_0 = "0.0.0.0";
//    private static final String ECLIPSE_MILO_OPC_UA_EXAMPLE_SERVER = "Eclipse Milo OPC UA Example Server";
//    private static final String ECLIPSE_MILO_EXAMPLE_SERVER = "eclipse milo example server";
//    private static final String ECLIPSE = "eclipse";
//    private static final String EXAMPLE = "example";
//    private static final String URN_ECLIPSE_MILO_EXAMPLES_SERVER = "urn:eclipse:milo:examples:server:";
//    private static final String PRODUCT_URI = "urn:eclipse:milo:example-server";
//
//    // DATABASE PROPERTIES
//    private static final String JDBC_SQLITE = "jdbc:sqlite:";
//    private static final String USERS_DB = "Users.db";
//    private static final String DATABASE_PASSWORD_COLUMN = "Password";
//    private static final String DATABASE_USER_COLUMN = "Username";
//    private static final String DATABASE_NAME = "Users";
//
//    // LOGGER SECURITY DATABASE STATUS
//    private static final String PASSWORD_IS_CORRECT = "Password is correct.";
//    private static final String FOUND_USER_IN_DATABASE = "Found user in database.";
//
//    // LOGGER DATABASE STATUS
//    private static final String SUCCESSFULLY_INSERTED_USER_INTO_TABLE = "Successfully inserted user into table";
//    private static final String SUCCESSFULLY_CREATED_TABLE = "Successfully created table";
//    private static final String NOT_SUCCESSFULLY_CREATED_TABLE = "Not successfully created table";
//    private static final String SUCCESSFULLY_CREATED_DATABASE = "Successfully created database:";
//    private static final String PROBLEM_CLOSING_USER_DATABASE = "Problem closing user database";
//    private static final String PROBLEM_ACCESSING_USER_DATABASE = "Problem accessing user database";
//    private static final String CONNECTED_TO_USER_DATABASE = "Connected to user database";
//
//    // LOGGER FOLDER AND FILE STATUS
//    private static final String PKI_DIR_LOG = "pki dir: {}";
//    private static final String DB_DIR_LOG = "db dir: {}";
//    private static final String UNABLE_TO_CREATE_SECURITY_TEMP_DIR = "unable to create security temp dir: ";
//    private static final String NO_SECURITY_TEMP_DIR = "No security temp dir: ";
//    private static final String SECURITY_TEMP_DIR = "security temp dir: {}";
//
//    // LOGGER SQL ERRORS
//    private static final String SQL_STATEMENT = "SQL Statement: ";
//
//    // LOGGER OPC UA SECURITY ERRORS
//    private static final String CERTIFICATE_IS_MISSING_THE_APPLICATION_URI = "certificate is missing " +
//            "the application URI";
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    static {
//        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
//        Security.addProvider(new BouncyCastleProvider());
//    }
//
//    public static void main(String[] args) throws Exception {
//        ExampleServerWithPasswordDatabase server = new ExampleServerWithPasswordDatabase();
//
//        server.startup().get();
//
//        final CompletableFuture<Void> future = new CompletableFuture<>();
//
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));
//
//        future.get();
//    }
//
//    public File getTrustedDbDir() {
//        return trustedDbDir;
//    }
//
//    public File getBaseDbDir() {
//        return baseDbDir;
//    }
//
//    public File getRejectedDbDir() {
//        return rejectedDbDir;
//    }
//
//    private final OpcUaServer server;
//
//    private void createTable(Connection connection) {
//        // CREATE TABLE ONLY IF IT DOES NOT EXIST
//        String sql = "CREATE TABLE IF NOT EXISTS " + DATABASE_NAME + " ( " + DATABASE_USER_COLUMN + " TEXT not NULL, " +
//                DATABASE_PASSWORD_COLUMN + " TEXT not NULL, " + " PRIMARY KEY ('" + DATABASE_USER_COLUMN + "'))";
//        try {
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sql);
//            logger.info(SUCCESSFULLY_CREATED_TABLE);
//        } catch (SQLException e) {
//            logger.info(NOT_SUCCESSFULLY_CREATED_TABLE);
//            e.printStackTrace();
//        }
//    }
//
//    private void insertUserIntoDatabase(Connection connection, String username, String password,
//            boolean isPasswordHashed) {
//        try {
//            // INSERT OR REPLACE USERNAME AND HASHED PASSWORD INTO REJECTED DATABASE
//            String sql = "REPLACE INTO " + DATABASE_NAME + " (" + DATABASE_USER_COLUMN + "," +
//                    DATABASE_PASSWORD_COLUMN + ")" + "VALUES (?,?)";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, username);
//            if (isPasswordHashed) {
//                pstmt.setString(2, password);
//            } else {
//                pstmt.setString(2, argon2.hash(2, 65536, 1, password));
//            }
//
//            pstmt.executeUpdate();
//            logger.info(SUCCESSFULLY_INSERTED_USER_INTO_TABLE);
//        } catch (SQLException e) {
//            logger.info(NOT_SUCCESSFULLY_CREATED_TABLE);
//            e.printStackTrace();
//        }
//    }
//
//    private ResultSet selectUserFromDatabase(Connection connection, String username) {
//        ResultSet result = null;
//        try {
//            // https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet
//            // Prepared Statements (with Parameterized Queries)
//            String sql = "SELECT " + DATABASE_PASSWORD_COLUMN + " FROM " + DATABASE_NAME + " WHERE " +
//                    DATABASE_USER_COLUMN + "=?";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, username);
//
//            // Execute query looking for the user specified in the authenticationChallenge
//            result = pstmt.executeQuery();
//            logger.info(SQL_STATEMENT + sql);
//            if (result.isClosed() || !result.next()) {
//                result = null;
//                logger.info("user not found");
//            }
//        } catch (SQLException e) {
//            logger.info("problem when searching for user");
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private void deleteUserFromDatabase(Connection connection, String username) {
//        try {
//            // https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet
//            // Prepared Statements (with Parameterized Queries)
//            String sql = "DELETE FROM " + DATABASE_NAME + " WHERE " + DATABASE_USER_COLUMN + "=?";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, username);
//
//            // Execute query looking for the user
//            pstmt.executeUpdate();
//            logger.info(SQL_STATEMENT + sql);
//        } catch (SQLException e) {
//            logger.info("not successfull deletion");
//            e.printStackTrace();
//        }
//    }
//
//    private void createDatabaseDirectories() throws Exception {
//        securityTempDir = new File(System.getProperty(JAVA_IO_TMPDIR), SECURITY);
//        if (!securityTempDir.exists() && !securityTempDir.mkdirs()) {
//            throw new Exception(UNABLE_TO_CREATE_SECURITY_TEMP_DIR + securityTempDir.getAbsolutePath());
//        }
//        logger.info(SECURITY_TEMP_DIR, securityTempDir.getAbsolutePath());
//
//        baseDbDir = securityTempDir.toPath().resolve(BASE_DB_DIR).toFile();
//        if (!baseDbDir.exists() && !baseDbDir.mkdirs()) {
//            logger.info("unable to create directory at " + baseDbDir.getAbsolutePath());
//            throw new IOException("unable to create directory at " + baseDbDir.getAbsolutePath());
//        } else {
//            logger.info("Base Database dir: {}", baseDbDir.getAbsolutePath());
//            trustedDbDir = baseDbDir.toPath().resolve(TRUSTED_DB_DIR).toFile();
//            if (!trustedDbDir.exists() && !trustedDbDir.mkdirs()) {
//                logger.info("unable to create directory at " + trustedDbDir.getAbsolutePath());
//                throw new IOException("unable to create directory at " + trustedDbDir.getAbsolutePath());
//            }
//            logger.info("Trusted Database dir: {}", trustedDbDir.getAbsolutePath());
//            rejectedDbDir = baseDbDir.toPath().resolve(REJECTED_DB_DIR).toFile();
//            if (!rejectedDbDir.exists() && !rejectedDbDir.mkdirs()) {
//                logger.info("unable to create directory at " + rejectedDbDir.getAbsolutePath());
//                throw new IOException("unable to create directory at " + rejectedDbDir.getAbsolutePath());
//            }
//            rejectedUserDatabase = rejectedDbDir.toPath().resolve(USERS_DB).toFile();
//            if (!rejectedUserDatabase.exists()) {
//                rejectedUserDatabase.createNewFile();
//            }
//            logger.info("Rejected Database dir: {}", rejectedDbDir.getAbsolutePath());
//
//        }
//        trustedUserDatabase = trustedDbDir.toPath().resolve(USERS_DB).toFile();
//    }
//
//    private void moveUsersFromRejectedDbToTrustedDb(List<String> usernames) {
//        usernames.forEach(user -> {
//            try {
//                moveUserFromRejectedDbToTrustedDb(user);
//            } catch (SQLException e) {
//                logger.info(e.toString());
//            }
//        });
//
//    }
//
//    private void moveUserFromRejectedDbToTrustedDb(String username) throws SQLException {
//        Connection trustedConnection = null;
//        Connection rejectedConnnection = null;
//
//        String trustedDatabaseUrl = JDBC_SQLITE + trustedUserDatabase.getAbsolutePath();
//        String rejectedDatabaseUrl = JDBC_SQLITE + rejectedUserDatabase.getAbsolutePath();
//        trustedConnection = DriverManager.getConnection(trustedDatabaseUrl);
//        rejectedConnnection = DriverManager.getConnection(rejectedDatabaseUrl);
//
//        ResultSet rs = selectUserFromDatabase(rejectedConnnection, username);
//
//        if (rs == null) {
//            return;
//        }
//
//        String hashedPassword = rs.getString(DATABASE_PASSWORD_COLUMN);
//        insertUserIntoDatabase(trustedConnection, username, hashedPassword, true);
//        deleteUserFromDatabase(rejectedConnnection, username);
//
//    }
//
//    /**
//     * OPC UA Server with local Trusted and Rejected User Database
//     * Rejected users are stored within the Rejected Database if the Client login attempt fails
//     * The operation moveUsersFromRejectedDbToTrustedDb(...)
//     * allows to move users from the rejected db to the trusted db.
//     */
//    public ExampleServerWithPasswordDatabase() throws Exception {
//
//        createDatabaseDirectories();
//
//        KeyStoreLoader loader = new KeyStoreLoader().load(securityTempDir);
//
//        DefaultCertificateManager certificateManager = new DefaultCertificateManager(loader.getServerKeyPair(),
//                loader.getServerCertificateChain());
//
//        File pkiDir = securityTempDir.toPath().resolve(PKI_DIR).toFile();
//        DirectoryCertificateValidator certificateValidator = new DirectoryCertificateValidator(pkiDir);
//        logger.info(PKI_DIR_LOG, pkiDir.getAbsolutePath());
//
//        // Build the predicate to check login data
//        Predicate<UsernameIdentityValidator.AuthenticationChallenge> authPredicate = authenticationChallenge -> {
//
//            // Check if the user database exists in the security directory
//            if (!securityTempDir.exists()) {
//                logger.debug(NO_SECURITY_TEMP_DIR + securityTempDir);
//                return false;
//            }
//            logger.info(DB_DIR_LOG, baseDbDir.getAbsolutePath());
//
//            Connection trustedConnection = null;
//            Connection rejectedConnnection = null;
//
//            try {
//
//                String trustedDatabaseUrl = JDBC_SQLITE + trustedUserDatabase.getAbsolutePath();
//                trustedConnection = DriverManager.getConnection(trustedDatabaseUrl);
//
//                logger.info(CONNECTED_TO_USER_DATABASE);
//                if (trustedConnection != null) {
//                    DatabaseMetaData meta = trustedConnection.getMetaData();
//                    logger.info(SUCCESSFULLY_CREATED_DATABASE + meta.getDriverName());
//                } else {
//                    return false;
//                }
//
//                createTable(trustedConnection);
//                logger.info(CONNECTED_TO_USER_DATABASE);
//
//                ResultSet rs = selectUserFromDatabase(trustedConnection, authenticationChallenge.getUsername());
//
//                if (rs.isClosed() || !rs.next()) {
//
//                    try {
//                        String rejectedDatabaseUrl = JDBC_SQLITE + rejectedUserDatabase.getAbsolutePath();
//                        rejectedConnnection = DriverManager.getConnection(rejectedDatabaseUrl);
//                        logger.info(CONNECTED_TO_USER_DATABASE);
//                        if (rejectedConnnection != null) {
//                            DatabaseMetaData meta = rejectedConnnection.getMetaData();
//                            logger.info(SUCCESSFULLY_CREATED_DATABASE + meta.getDriverName());
//                        } else {
//                            return false;
//                        }
//
//                        createTable(rejectedConnnection);
//                        insertUserIntoDatabase(rejectedConnnection, authenticationChallenge.getUsername(),
//                            authenticationChallenge.getPassword(), false);
//
//                    } catch (SQLException se) {
//                        se.printStackTrace();
//                    }
//                    return false;
//                } else {
//                    logger.info(FOUND_USER_IN_DATABASE);
//                }
//
//                // Password is stored in the database in hashed form, so verify the password against the hash
//                // verify hashes the password and compare it to the hashed password from the database
//                // The hash includes the salt. The verify method extracts the salt from the hash and uses that.
//                // (https://github.com/phxql/argon2-jvm/issues/19)
//                if (argon2.verify(rs.getString(DATABASE_PASSWORD_COLUMN), authenticationChallenge.getPassword())) {
//                    logger.info(PASSWORD_IS_CORRECT);
//                    return true;
//                }
//
//            } catch (SQLException e) {
//                logger.error(PROBLEM_ACCESSING_USER_DATABASE, e);
//                return false;
//
//            } finally {
//                try {
//                    if (trustedConnection != null) {
//                        trustedConnection.close();
//                    }
//                    if (rejectedConnnection != null) {
//                        rejectedConnnection.close();
//                    }
//                } catch (SQLException ex) {
//                    logger.error(PROBLEM_CLOSING_USER_DATABASE, ex);
//                }
//            }
//
//            return false;
//        };
//
//        UsernameIdentityValidator identityValidator = new UsernameIdentityValidator(false, authPredicate);
//
//        List<String> bindAddresses = newArrayList();
//        bindAddresses.add(_0_0_0_0);
//
//        List<String> endpointAddresses = newArrayList();
//        endpointAddresses.add(HostnameUtil.getHostname());
//        endpointAddresses.addAll(HostnameUtil.getHostnames(_0_0_0_0));
//
//        // The configured application URI must match the one in the certificate(s)
//        String applicationUri = certificateManager.getCertificates().stream().findFirst()
//                .map(certificate -> CertificateUtil
//                        .getSubjectAltNameField(certificate, CertificateUtil.SUBJECT_ALT_NAME_URI).map(Object::toString)
//                        .orElseThrow(() -> new RuntimeException(CERTIFICATE_IS_MISSING_THE_APPLICATION_URI)))
//                .orElse(URN_ECLIPSE_MILO_EXAMPLES_SERVER + UUID.randomUUID());
//
//        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder().setApplicationUri(applicationUri)
//                .setApplicationName(LocalizedText.english(ECLIPSE_MILO_OPC_UA_EXAMPLE_SERVER)).setBindPort(4840)
//                .setBindAddresses(bindAddresses).setEndpointAddresses(endpointAddresses)
//                .setBuildInfo(new BuildInfo(PRODUCT_URI, ECLIPSE, ECLIPSE_MILO_EXAMPLE_SERVER, OpcUaServer.SDK_VERSION,
//                        "", DateTime.now()))
//                .setCertificateManager(certificateManager).setCertificateValidator(certificateValidator)
//                .setIdentityValidator(identityValidator).setProductUri(PRODUCT_URI)
//                .setSecurityPolicies(EnumSet.of(SecurityPolicy.None, SecurityPolicy.Basic128Rsa15,
//                    SecurityPolicy.Basic256, SecurityPolicy.Basic256Sha256, SecurityPolicy.Aes128_Sha256_RsaOaep,
//                    SecurityPolicy.Aes256_Sha256_RsaPss))
//                .setUserTokenPolicies(ImmutableList.of(USER_TOKEN_POLICY_USERNAME)).build();
//
//        server = new OpcUaServer(serverConfig);
//
//        server.getNamespaceManager().registerAndAdd(ExampleNamespace.NAMESPACE_URI,
//            idx -> new ExampleNamespace(server, idx));
//    }
//
//    public OpcUaServer getServer() {
//        return server;
//    }
//
//    public CompletableFuture<OpcUaServer> startup() {
//        return server.startup();
//    }
//
//    public CompletableFuture<OpcUaServer> shutdown() {
//        return server.shutdown();
//    }
//
//}
