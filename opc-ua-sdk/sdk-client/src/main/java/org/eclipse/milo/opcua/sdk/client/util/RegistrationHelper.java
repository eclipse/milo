package org.eclipse.milo.opcua.sdk.client.util;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.MdnsDiscoveryConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Request;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisteredServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class RegistrationHelper {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private EndpointDescription registeredWithDiscoveryServer;
    private ScheduledExecutorService periodicServerRegisterScheduler;
    private String registerSemaphoreFilePath;
    private long registerNextTryInterval;
    private long registerDefaultInterval;

    RegistrationHelper() {
        this.registeredWithDiscoveryServer = null;
        this.registerNextTryInterval = 0;
    }


    /**
     * Register this server instance with a Discovery Server (LDS) on the given endpoint url.
     * The registering will repeated periodically to keep the registered data up to date.
     * <p>
     * The semaphoreFilePath is optional. If the given file is deleted,
     * the server will automatically be unregistered. This could be
     * for example a pid file which is deleted if the server crashes.
     * NOTE: The path to the semaphore file must be accessible by the LDS server.
     * <p>
     * The default register frequency is 10 minutes and the first register call is delayed by 500ms.
     *
     * @param discoveryServerUrl the endpoint URL of the LDS/Discovery Server
     * @param semaphoreFilePath  optional file path to a semaphore file
     * @return true if periodic registering successfully started, false if already registered
     */
    public boolean registerWithDiscoveryServer(String discoveryServerUrl, String semaphoreFilePath,
                                               CompletableFuture<StatusCode> registered) {
        return this.registerWithDiscoveryServer(discoveryServerUrl, 10 * 60 * 1000, 500, semaphoreFilePath, registered);
    }

    /**
     * Register this server instance with a Discovery Server (LDS) on the given endpoint url.
     * The registering will repeated periodically to keep the registered data up to date.
     * <p>
     * The semaphoreFilePath is optional. If the given file is deleted,
     * the server will automatically be unregistered. This could be
     * for example a pid file which is deleted if the server crashes.
     * NOTE: The path to the semaphore file must be accessible by the LDS server.
     *
     * @param discoveryServerUrl   the endpoint URL of the LDS/Discovery Server
     * @param intervalMs           interval in milliseconds of the periodic register. It should be around 10 minutes.
     * @param delayFirstRegisterMs the first call to register can be delayed to e.g. start up the server or do other
     *                             initialization stuff.
     * @param semaphoreFilePath    optional file path to a semaphore file
     * @return true if periodic registering successfully started, false if already registered
     */
    public boolean registerWithDiscoveryServer(String discoveryServerUrl, long intervalMs, long delayFirstRegisterMs,
                                               String semaphoreFilePath, CompletableFuture<StatusCode> registered) {
        if (registeredWithDiscoveryServer != null) {
            logger.warn("Can not register server with discovery server " + discoveryServerUrl +
                ". Already registered with: " + registeredWithDiscoveryServer);
            return false;
        }

        this.registerSemaphoreFilePath = semaphoreFilePath;
        this.registerDefaultInterval = intervalMs;

        periodicServerRegisterScheduler = Executors.newScheduledThreadPool(2);

        periodicServerRegisterScheduler.scheduleWithFixedDelay(
            new PeriodicServerRegister(discoveryServerUrl, registered),
            delayFirstRegisterMs, intervalMs, TimeUnit.MILLISECONDS);

        return true;
    }

    public CompletableFuture<StatusCode> unregisterFromDiscoveryServer() {
        if (registeredWithDiscoveryServer == null) {
            logger.warn("Can not unregister from discovery server. Not registered yet.");
            CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<StatusCode>();
            futureRegisterResult.completeExceptionally(
                new RuntimeException("Can not unregister from discovery server. Not registered yet."));
            return futureRegisterResult;
        }
        periodicServerRegisterScheduler.shutdown();
        return registerWithDiscoveryServer(false, registeredWithDiscoveryServer, null)
            .whenComplete((result, ex) -> {
                if (result.isGood()) {
                    registeredWithDiscoveryServer = null;
                }
            });
    }

    private CompletableFuture<StatusCode> registerServer2(OpcUaClient stackClient,
                                                          RegisteredServer serverToBeRegistered,
                                                          MdnsDiscoveryConfiguration mdnsDiscoveryConfig) {

        CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<>();

        ExtensionObject[] discoveryConfig = {ExtensionObject.encode(mdnsDiscoveryConfig)};

        RegisterServer2Request registerServer2Request;
        try {
            registerServer2Request = new RegisterServer2Request(
                stackClient.newRequestHeader(stackClient.getSession().get().getAuthenticationToken()),
                serverToBeRegistered, discoveryConfig);
        } catch (InterruptedException | ExecutionException e) {
            futureRegisterResult.completeExceptionally(e);
            return futureRegisterResult;
        }

        // first try RegisterServer2
        stackClient.sendRequest(registerServer2Request).whenComplete((response2, ex2) -> {
            if (response2 == null) {
                futureRegisterResult.completeExceptionally(ex2);
            } else {
                futureRegisterResult.complete(response2.getResponseHeader().getServiceResult());
            }
        });

        return futureRegisterResult;
    }

    private CompletableFuture<StatusCode> registerServer(OpcUaClient stackClient,
                                                         RegisteredServer serverToBeRegistered) {

        CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<>();
        RegisterServerRequest registerServerRequest = null;
        try {
            registerServerRequest = new RegisterServerRequest(
                stackClient.newRequestHeader(stackClient.getSession().get().getAuthenticationToken()),
                serverToBeRegistered);
        } catch (InterruptedException | ExecutionException e) {
            futureRegisterResult.completeExceptionally(e);
        }
        CompletableFuture<RegisterServerResponse> future = stackClient.sendRequest(registerServerRequest);
        future.whenComplete((response, ex) -> {
            if (response == null) {
                futureRegisterResult.completeExceptionally(ex);
            } else {
                futureRegisterResult.complete(response.getResponseHeader().getServiceResult());
            }
        });
        return futureRegisterResult;
    }

    private CompletableFuture<StatusCode> registerWithDiscoveryServer(boolean online,
                                                                      EndpointDescription discoveryEndpoint,
                                                                      String semaphoreFilePath) {
        OpcUaClientConfig clientConfig = OpcUaClientConfig.builder()
            .setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
            .setApplicationUri("urn:eclipse:milo:examples:client")
            //.setCertificate(loader.getClientCertificate())
            //.setKeyPair(loader.getClientKeyPair())
            .setEndpoint(discoveryEndpoint)
            .setIdentityProvider(new AnonymousProvider())
            .setRequestTimeout(uint(1000))
            .build();

        CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<StatusCode>();

        OpcUaClient stackClient = new OpcUaClient(clientConfig);
        try {
            stackClient.connect().get();
        } catch (InterruptedException | ExecutionException e) {
            futureRegisterResult.completeExceptionally(e);
            return futureRegisterResult;
        }

        LocalizedText[] serverNames = new LocalizedText[1];
        serverNames[0] = config.getApplicationName();
        ApplicationType serverType = ApplicationType.ClientAndServer;

        String[] discoveryUrls = Arrays.stream(stackServer.getEndpointDescriptions()).map(
            EndpointDescription::getEndpointUrl).distinct().toArray(String[]::new);

        RegisteredServer serverToBeRegistered = new RegisteredServer(config.getApplicationUri(), config.getProductUri(),
            serverNames, serverType, null, discoveryUrls, semaphoreFilePath, online);

        //TODO set capabilities correct
        MdnsDiscoveryConfiguration mdnsDiscoveryConfig = new MdnsDiscoveryConfiguration(
            config.getApplicationName().getText(), new String[0]);


        registerServer2(stackClient, serverToBeRegistered, mdnsDiscoveryConfig).whenComplete(
            (statusCode, throwable) -> {
                if (statusCode == null) {
                    logger.error("RegisterServer2 failed with error: {}", throwable.getMessage(), throwable);
                    futureRegisterResult.completeExceptionally(throwable);
                } else if (statusCode.getValue() == StatusCodes.Bad_NotImplemented ||
                    statusCode.getValue() == StatusCodes.Bad_ServiceUnsupported) {
                    // RegisterServer2 failed, try RegisterServer
                    registerServer(stackClient, serverToBeRegistered).whenComplete((statusCode1, throwable1) -> {
                        if (statusCode1 == null) {
                            logger.error("RegisterServer failed with error: {}", throwable1.getMessage(), throwable1);
                            futureRegisterResult.completeExceptionally(throwable1);
                        } else {
                            if (statusCode1.isBad()) {
                                logger.error("RegisterServer failed with status code: {}", statusCode1);
                            }
                            futureRegisterResult.complete(statusCode1);
                        }
                    });
                } else {
                    futureRegisterResult.complete(statusCode);
                }
            });

        return futureRegisterResult;
    }

    private void retryPeriodicServerRegister(String discoveryServerUrl, CompletableFuture<StatusCode> registered) {

        // reschedule server registering with backing off strategy as defined in specification.
        // first retry in 1s, then double each time, i.e. 2,4,8,... until main interval is reached.
        if (registerNextTryInterval == 0) {
            registerNextTryInterval = 1000;
        } else {
            registerNextTryInterval *= 2;
            if (registerNextTryInterval > registerDefaultInterval) {
                registerNextTryInterval = 0;
                logger.warn("Retry interval of register server reached maximum. Falling back to default retry cycle");
                return;
            }
        }

        logger.info("Retry register server in " + (registerNextTryInterval / 1000) + " seconds");

        periodicServerRegisterScheduler.schedule(new PeriodicServerRegister(discoveryServerUrl, registered),
            registerNextTryInterval, TimeUnit.MILLISECONDS);
    }

    private class PeriodicServerRegister implements Runnable {

        String discoveryServerUrl;
        CompletableFuture<StatusCode> registered;

        public PeriodicServerRegister(String discoveryServerUrl, CompletableFuture<StatusCode> registered) {
            this.discoveryServerUrl = discoveryServerUrl;
            this.registered = registered;
        }

        @Override
        public void run() {

            if (registeredWithDiscoveryServer == null) {
                EndpointDescription endpoint;
                try {
                    EndpointDescription[] endpoints = UaTcpStackClient.getEndpoints(discoveryServerUrl).get();
                    endpoint = Arrays.stream(endpoints)
                        .filter(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getSecurityPolicyUri()))
                        .findFirst().orElseThrow(() -> new Exception("no desired endpoints returned"));
                } catch (Exception e) {
                    logger.warn("Can not get endpoints from discovery server: " + e.getMessage() + ". Retrying...");
                    retryPeriodicServerRegister(discoveryServerUrl, registered);
                    return;
                }


                registeredWithDiscoveryServer = endpoint;
            }


            registerWithDiscoveryServer(true, registeredWithDiscoveryServer, registerSemaphoreFilePath)
                .whenComplete((statusCode, ex) -> {
                    if (statusCode == null) {
                        logger.error("Could not register server with discovery server. Error {}",
                            ex.getMessage(), ex);
                        if (registered != null && !registered.isDone()) {
                            registered.completeExceptionally(
                                new Throwable("Could not register server with discovery server. Error " +
                                    ex.getMessage()));
                        }
                        return;
                    }
                    if (statusCode.isBad()) {
                        logger.warn("Could not register server with discovery server: " + statusCode);
                        retryPeriodicServerRegister(discoveryServerUrl, registered);
                    } else {
                        logger.info("Successfully registered with discovery server " + discoveryServerUrl);
                        if (registered != null && !registered.isDone()) {
                            registered.complete(StatusCode.GOOD);
                        }
                    }
                });
        }
    }

}
