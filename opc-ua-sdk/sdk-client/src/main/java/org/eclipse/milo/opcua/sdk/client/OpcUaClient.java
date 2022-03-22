/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.client.api.ServiceFaultListener;
import org.eclipse.milo.opcua.sdk.client.api.UaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.model.ObjectTypeInitializer;
import org.eclipse.milo.opcua.sdk.client.model.VariableTypeInitializer;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsmFactory;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaServiceFaultException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.ManifestUtil;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newCopyOnWriteArrayList;
import static org.eclipse.milo.opcua.sdk.client.session.SessionFsm.SessionInitializer;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class OpcUaClient implements UaClient {

    public static final String SDK_VERSION =
        ManifestUtil.read("X-SDK-Version").orElse("dev");

    static {
        Logger logger = LoggerFactory.getLogger(OpcUaClient.class);
        logger.info("Java version: " + System.getProperty("java.version"));
        logger.info("Eclipse Milo OPC UA Stack version: {}", Stack.VERSION);
        logger.info("Eclipse Milo OPC UA Client SDK version: {}", SDK_VERSION);
    }

    /**
     * Create an {@link OpcUaClient} configured with {@code config}.
     *
     * @param config the {@link OpcUaClientConfig}.
     * @return an {@link OpcUaClient} configured with {@code config}.
     * @throws UaException if the client could not be created (e.g. transport/encoding not supported).
     */
    public static OpcUaClient create(OpcUaClientConfig config) throws UaException {
        UaStackClient stackClient = UaStackClient.create(config);

        return new OpcUaClient(config, stackClient);
    }

    /**
     * Select the first endpoint with no security that allows anonymous connections and create an
     * {@link OpcUaClient} with the default configuration.
     * <p>
     * If the server is not configured with an endpoint with no security or authentication you
     * must use {@link #create(String, Function, Function)} to select an endpoint and configure
     * any certificates or identity provider that the selected endpoint would require.
     *
     * @param endpointUrl the endpoint URL of the server to connect to and get endpoints from.
     * @return an {@link OpcUaClient} configured to connect to the server identified by
     * {@code endpointUrl}.
     * @throws UaException if the endpoints could not be retrieved or the client could not be
     *                     created.
     */
    public static OpcUaClient create(String endpointUrl) throws UaException {
        // select the first EndpointDescription with no security and anonymous authentication
        Predicate<EndpointDescription> predicate = e ->
            SecurityPolicy.None.getUri().equals(e.getSecurityPolicyUri()) &&
                Arrays.stream(e.getUserIdentityTokens())
                    .anyMatch(p -> p.getTokenType() == UserTokenType.Anonymous);

        return create(
            endpointUrl,
            endpoints -> endpoints.stream()
                .filter(predicate)
                .findFirst(),
            OpcUaClientConfigBuilder::build
        );
    }

    /**
     * Create and configure an {@link OpcUaClient} by selecting an {@link EndpointDescription} from a list of endpoints
     * retrieved via the GetEndpoints service from the server at {@code endpointUrl} and building an
     * {@link OpcUaClientConfig} using that endpoint.
     *
     * @param endpointUrl    the endpoint URL of the server to connect to and retrieve endpoints from.
     * @param selectEndpoint a function that selects the {@link EndpointDescription} to connect to from the list of
     *                       endpoints from the server.
     * @param buildConfig    a function that configures an {@link OpcUaClientConfigBuilder} and then builds and returns
     *                       an {@link OpcUaClientConfig}.
     * @return a configured {@link OpcUaClient}.
     * @throws UaException if the endpoints could not be retrieved or the client could not be created.
     */
    public static OpcUaClient create(
        String endpointUrl,
        Function<List<EndpointDescription>, Optional<EndpointDescription>> selectEndpoint,
        Function<OpcUaClientConfigBuilder, OpcUaClientConfig> buildConfig
    ) throws UaException {

        try {
            List<EndpointDescription> endpoints =
                DiscoveryClient.getEndpoints(endpointUrl).get();

            EndpointDescription endpoint = selectEndpoint.apply(endpoints).orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_ConfigurationError,
                    "no endpoint selected"
                )
            );

            OpcUaClientConfigBuilder builder = OpcUaClientConfig.builder()
                .setEndpoint(endpoint);

            return create(buildConfig.apply(builder));
        } catch (InterruptedException | ExecutionException e) {
            if (!endpointUrl.endsWith("/discovery")) {
                StringBuilder discoveryUrl = new StringBuilder(endpointUrl);
                if (!endpointUrl.endsWith("/")) {
                    discoveryUrl.append("/");
                }
                discoveryUrl.append("discovery");

                return create(discoveryUrl.toString(), selectEndpoint, buildConfig);
            } else {
                throw UaException.extract(e)
                    .orElseGet(() -> new UaException(e));
            }
        }
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<ServiceFaultListener> faultListeners = newCopyOnWriteArrayList();
    private final ExecutionQueue faultNotificationQueue;

    private final AddressSpace addressSpace;

    private final ObjectTypeManager objectTypeManager = new ObjectTypeManager();
    private final VariableTypeManager variableTypeManager = new VariableTypeManager();

    private final OpcUaSubscriptionManager subscriptionManager;

    private final SessionFsm sessionFsm;

    private final OpcUaClientConfig config;
    private final UaStackClient stackClient;

    public OpcUaClient(OpcUaClientConfig config, UaStackClient stackClient) {
        this.config = config;
        this.stackClient = stackClient;

        sessionFsm = SessionFsmFactory.newSessionFsm(this);

        sessionFsm.addInitializer((client, session) -> {
            logger.debug("SessionInitializer: NamespaceTable");
            RequestHeader requestHeader = newRequestHeader(session.getAuthenticationToken());

            ReadRequest readRequest = new ReadRequest(
                requestHeader,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        Identifiers.Server_NamespaceArray,
                        AttributeId.Value.uid(),
                        null,
                        QualifiedName.NULL_VALUE)
                }
            );

            return client.sendRequest(readRequest)
                .thenApply(ReadResponse.class::cast)
                .thenApply(response -> Objects.requireNonNull(response.getResults()))
                .thenApply(results -> (String[]) results[0].getValue().getValue())
                .thenAccept(this::updateNamespaceTable)
                .thenApply(v -> Unit.VALUE)
                .exceptionally(ex -> {
                    logger.warn("SessionInitializer: NamespaceTable", ex);
                    return Unit.VALUE;
                });
        });


        faultNotificationQueue = new ExecutionQueue(config.getExecutor());

        addressSpace = new AddressSpace(this);
        subscriptionManager = new OpcUaSubscriptionManager(this);

        ObjectTypeInitializer.initialize(
            stackClient.getNamespaceTable(),
            objectTypeManager
        );

        VariableTypeInitializer.initialize(
            stackClient.getNamespaceTable(),
            variableTypeManager
        );
    }

    @Override
    public OpcUaClientConfig getConfig() {
        return config;
    }

    public UaStackClient getStackClient() {
        return stackClient;
    }

    @Override
    public AddressSpace getAddressSpace() {
        return addressSpace;
    }

    public ObjectTypeManager getObjectTypeManager() {
        return objectTypeManager;
    }

    public VariableTypeManager getVariableTypeManager() {
        return variableTypeManager;
    }

    /**
     * @see UaStackClient#getStaticDataTypeManager()
     */
    public DataTypeManager getStaticDataTypeManager() {
        return stackClient.getStaticDataTypeManager();
    }

    /**
     * @see UaStackClient#getDynamicDataTypeManager()
     */
    public DataTypeManager getDynamicDataTypeManager() {
        return stackClient.getDynamicDataTypeManager();
    }

    /**
     * @see UaStackClient#getStaticSerializationContext()
     */
    public SerializationContext getStaticSerializationContext() {
        return stackClient.getStaticSerializationContext();
    }

    /**
     * @see UaStackClient#getDynamicSerializationContext()
     */
    public SerializationContext getDynamicSerializationContext() {
        return stackClient.getDynamicSerializationContext();
    }

    /**
     * Get the local copy of the server's NamespaceTable (NamespaceArray).
     *
     * @return the local copy of the server's NamespaceTable (NamespaceArray).
     */
    public NamespaceTable getNamespaceTable() {
        return stackClient.getNamespaceTable();
    }

    /**
     * Read the server's NamespaceTable (NamespaceArray) and update the local copy.
     *
     * @return the updated {@link NamespaceTable}.
     * @throws UaException if an operation- or service-level error occurs.
     */
    public NamespaceTable readNamespaceTable() throws UaException {
        try {
            return readNamespaceTableAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Read the server's NamespaceTable (NamespaceArray) and update the local copy.
     * <p>
     * This call completes asynchronously.
     *
     * @return a {@link CompletableFuture} that completes successfully with the updated
     * {@link NamespaceTable} or completes exceptionally if a service- or operation-level error
     * occurs.
     */
    public CompletableFuture<NamespaceTable> readNamespaceTableAsync() {
        return getSession().thenCompose(session -> {
            RequestHeader requestHeader = newRequestHeader(session.getAuthenticationToken());

            ReadRequest readRequest = new ReadRequest(
                requestHeader,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        Identifiers.Server_NamespaceArray,
                        AttributeId.Value.uid(),
                        null,
                        QualifiedName.NULL_VALUE)
                }
            );

            CompletableFuture<String[]> namespaceArray = sendRequest(readRequest)
                .thenApply(ReadResponse.class::cast)
                .thenApply(response -> Objects.requireNonNull(response.getResults()))
                .thenApply(results -> (String[]) results[0].getValue().getValue());

            return namespaceArray
                .thenAccept(this::updateNamespaceTable)
                .thenApply(v -> getNamespaceTable());
        });
    }

    /**
     * Get the local copy of the server's ServerTable (ServerArray).
     *
     * @return the local copy of the server's ServerTable (ServerArray).
     */
    public ServerTable getServerTable() {
        return stackClient.getServerTable();
    }

    /**
     * Read the server's ServerTable (ServerArray) and update the local copy.
     *
     * @return the updated {@link ServerTable}.
     * @throws UaException if an operation- or service-level error occurs.
     */
    public ServerTable readServerTable() throws UaException {
        try {
            return readServerTableAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Read the server's ServerTable (ServerArray) and update the local copy.
     * <p>
     * This call completes asynchronously.
     *
     * @return a {@link CompletableFuture} that completes successfully with the updated
     * {@link ServerTable} or completes exceptionally if a service- or operation-level error
     * occurs.
     */
    public CompletableFuture<ServerTable> readServerTableAsync() {
        return getSession().thenCompose(session -> {
            RequestHeader requestHeader = newRequestHeader(session.getAuthenticationToken());

            ReadRequest readRequest = new ReadRequest(
                requestHeader,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        Identifiers.Server_ServerArray,
                        AttributeId.Value.uid(),
                        null,
                        QualifiedName.NULL_VALUE)
                }
            );

            CompletableFuture<String[]> namespaceArray = sendRequest(readRequest)
                .thenApply(ReadResponse.class::cast)
                .thenApply(response -> Objects.requireNonNull(response.getResults()))
                .thenApply(results -> (String[]) results[0].getValue().getValue());

            return namespaceArray
                .thenAccept(this::updateServerTable)
                .thenApply(v -> getServerTable());
        });
    }

    private void updateNamespaceTable(String[] namespaceArray) {
        if (namespaceArray.length > UShort.MAX_VALUE) {
            logger.warn("NamespaceTable returned by " +
                "server contains " + namespaceArray.length + " entries");
        }

        NamespaceTable namespaceTable = getNamespaceTable();

        namespaceTable.update(uriTable -> {
            uriTable.clear();

            namespaceTable.add(Namespaces.OPC_UA);
            for (String uri : namespaceArray) {namespaceTable.add(uri);}
        });
    }

    private void updateServerTable(String[] serverArray) {
        if (serverArray.length > UShort.MAX_VALUE) {
            logger.warn("ServerTable returned by " +
                "server contains " + serverArray.length + " entries");
        }

        ServerTable serverTable = getServerTable();

        serverTable.update(map -> {
            map.clear();

            for (String uri : serverArray) {serverTable.add(uri);}
        });
    }

    /**
     * Build a new {@link RequestHeader} using a null authentication token.
     *
     * @return a new {@link RequestHeader} with a null authentication token.
     */
    public RequestHeader newRequestHeader() {
        return newRequestHeader(NodeId.NULL_VALUE, config.getRequestTimeout());
    }

    /**
     * Build a new {@link RequestHeader} using {@code authToken}.
     *
     * @param authToken the authentication token (from the session) to use.
     * @return a new {@link RequestHeader}.
     */
    public RequestHeader newRequestHeader(NodeId authToken) {
        return newRequestHeader(authToken, config.getRequestTimeout());
    }

    /**
     * Build a new {@link RequestHeader} using a null authentication token and a custom {@code requestTimeout}.
     *
     * @param requestTimeout the custom request timeout to use.
     * @return a new {@link RequestHeader} with a null authentication token and a custom request timeout.
     */
    public RequestHeader newRequestHeader(UInteger requestTimeout) {
        return newRequestHeader(NodeId.NULL_VALUE, requestTimeout);
    }

    /**
     * Build a new {@link RequestHeader} using {@code authToken} and a custom {@code requestTimeout}.
     *
     * @param authToken      the authentication token (from the session) to use.
     * @param requestTimeout the custom request timeout to use.
     * @return a new {@link RequestHeader}.
     */
    public RequestHeader newRequestHeader(NodeId authToken, UInteger requestTimeout) {
        return getStackClient().newRequestHeader(authToken, requestTimeout);
    }

    @Override
    public CompletableFuture<UaClient> connect() {
        return getStackClient()
            .connect()
            .thenCompose(c -> sessionFsm.openSession())
            .thenApply(s -> OpcUaClient.this);
    }

    @Override
    public CompletableFuture<OpcUaClient> disconnect() {
        return sessionFsm
            .closeSession()
            .exceptionally(ex -> Unit.VALUE)
            .thenCompose(u ->
                getStackClient()
                    .disconnect()
                    .thenApply(c -> OpcUaClient.this))
            .exceptionally(ex -> OpcUaClient.this);
    }

    @Override
    public OpcUaSubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }

    @Override
    public CompletableFuture<ReadResponse> read(double maxAge,
                                                TimestampsToReturn timestampsToReturn,
                                                List<ReadValueId> readValueIds) {

        return getSession().thenCompose(session -> {
            ReadRequest request = new ReadRequest(
                newRequestHeader(session.getAuthenticationToken()),
                maxAge,
                timestampsToReturn,
                a(readValueIds, ReadValueId.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<WriteResponse> write(List<WriteValue> writeValues) {
        return getSession().thenCompose(session -> {
            WriteRequest request = new WriteRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(writeValues, WriteValue.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<HistoryReadResponse> historyRead(HistoryReadDetails historyReadDetails,
                                                              TimestampsToReturn timestampsToReturn,
                                                              boolean releaseContinuationPoints,
                                                              List<HistoryReadValueId> nodesToRead) {

        return getSession().thenCompose(session -> {
            HistoryReadRequest request = new HistoryReadRequest(
                newRequestHeader(session.getAuthenticationToken()),
                ExtensionObject.encode(getStaticSerializationContext(), historyReadDetails),
                timestampsToReturn,
                releaseContinuationPoints,
                a(nodesToRead, HistoryReadValueId.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<HistoryUpdateResponse> historyUpdate(List<HistoryUpdateDetails> historyUpdateDetails) {
        return getSession().thenCompose(session -> {
            ExtensionObject[] details = historyUpdateDetails.stream()
                .map(hud -> ExtensionObject.encode(getStaticSerializationContext(), hud))
                .toArray(ExtensionObject[]::new);

            HistoryUpdateRequest request = new HistoryUpdateRequest(
                newRequestHeader(session.getAuthenticationToken()),
                details
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<BrowseResponse> browse(ViewDescription viewDescription,
                                                    UInteger maxReferencesPerNode,
                                                    List<BrowseDescription> nodesToBrowse) {

        return getSession().thenCompose(session -> {
            BrowseRequest request = new BrowseRequest(
                newRequestHeader(session.getAuthenticationToken()),
                viewDescription,
                maxReferencesPerNode,
                a(nodesToBrowse, BrowseDescription.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<BrowseNextResponse> browseNext(boolean releaseContinuationPoints,
                                                            List<ByteString> continuationPoints) {

        return getSession().thenCompose(session -> {
            BrowseNextRequest request = new BrowseNextRequest(
                newRequestHeader(session.getAuthenticationToken()),
                releaseContinuationPoints,
                a(continuationPoints, ByteString.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> translateBrowsePaths(List<BrowsePath> browsePaths) {
        return getSession().thenCompose(session -> {
            TranslateBrowsePathsToNodeIdsRequest request = new TranslateBrowsePathsToNodeIdsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(browsePaths, BrowsePath.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<RegisterNodesResponse> registerNodes(List<NodeId> nodesToRegister) {
        return getSession().thenCompose(session -> {
            RegisterNodesRequest request = new RegisterNodesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(nodesToRegister, NodeId.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<UnregisterNodesResponse> unregisterNodes(List<NodeId> nodesToUnregister) {
        return getSession().thenCompose(session -> {
            UnregisterNodesRequest request = new UnregisterNodesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(nodesToUnregister, NodeId.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<CallResponse> call(List<CallMethodRequest> methodsToCall) {
        return getSession().thenCompose(session -> {
            CallRequest request = new CallRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(methodsToCall, CallMethodRequest.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<CreateSubscriptionResponse> createSubscription(
        double requestedPublishingInterval,
        UInteger requestedLifetimeCount,
        UInteger requestedMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        boolean publishingEnabled,
        UByte priority) {

        return getSession().thenCompose(session -> {
            CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                newRequestHeader(session.getAuthenticationToken()),
                requestedPublishingInterval,
                requestedLifetimeCount,
                requestedMaxKeepAliveCount,
                maxNotificationsPerPublish,
                publishingEnabled,
                priority
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<ModifySubscriptionResponse> modifySubscription(
        UInteger subscriptionId,
        double requestedPublishingInterval,
        UInteger requestedLifetimeCount,
        UInteger requestedMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        UByte priority) {

        return getSession().thenCompose(session -> {
            ModifySubscriptionRequest request = new ModifySubscriptionRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                requestedPublishingInterval,
                requestedLifetimeCount,
                requestedMaxKeepAliveCount,
                maxNotificationsPerPublish,
                priority
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<DeleteSubscriptionsResponse> deleteSubscriptions(List<UInteger> subscriptionIds) {
        return getSession().thenCompose(session -> {
            DeleteSubscriptionsRequest request = new DeleteSubscriptionsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(subscriptionIds, UInteger.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<TransferSubscriptionsResponse> transferSubscriptions(List<UInteger> subscriptionIds,
                                                                                  boolean sendInitialValues) {

        return getSession().thenCompose(session -> {
            TransferSubscriptionsRequest request = new TransferSubscriptionsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(subscriptionIds, UInteger.class),
                sendInitialValues
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<SetPublishingModeResponse> setPublishingMode(boolean publishingEnabled,
                                                                          List<UInteger> subscriptionIds) {

        return getSession().thenCompose(session -> {
            SetPublishingModeRequest request = new SetPublishingModeRequest(
                newRequestHeader(session.getAuthenticationToken()),
                publishingEnabled,
                a(subscriptionIds, UInteger.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<PublishResponse> publish(List<SubscriptionAcknowledgement> subscriptionAcknowledgements) {
        return getSession().thenCompose(session -> {
            PublishRequest request = new PublishRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(subscriptionAcknowledgements, SubscriptionAcknowledgement.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<RepublishResponse> republish(UInteger subscriptionId, UInteger retransmitSequenceNumber) {
        return getSession().thenCompose(session -> {
            RepublishRequest request = new RepublishRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                retransmitSequenceNumber
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<CreateMonitoredItemsResponse> createMonitoredItems(
        UInteger subscriptionId,
        TimestampsToReturn timestampsToReturn,
        List<MonitoredItemCreateRequest> itemsToCreate) {

        return getSession().thenCompose(session -> {
            CreateMonitoredItemsRequest request = new CreateMonitoredItemsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                timestampsToReturn,
                a(itemsToCreate, MonitoredItemCreateRequest.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<ModifyMonitoredItemsResponse> modifyMonitoredItems(
        UInteger subscriptionId,
        TimestampsToReturn timestampsToReturn,
        List<MonitoredItemModifyRequest> itemsToModify) {

        return getSession().thenCompose(session -> {
            ModifyMonitoredItemsRequest request = new ModifyMonitoredItemsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                timestampsToReturn,
                a(itemsToModify, MonitoredItemModifyRequest.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<DeleteMonitoredItemsResponse> deleteMonitoredItems(UInteger subscriptionId,
                                                                                List<UInteger> monitoredItemIds) {

        return getSession().thenCompose(session -> {
            DeleteMonitoredItemsRequest request = new DeleteMonitoredItemsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                a(monitoredItemIds, UInteger.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<SetMonitoringModeResponse> setMonitoringMode(UInteger subscriptionId,
                                                                          MonitoringMode monitoringMode,
                                                                          List<UInteger> monitoredItemIds) {

        return getSession().thenCompose(session -> {
            SetMonitoringModeRequest request = new SetMonitoringModeRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                monitoringMode,
                a(monitoredItemIds, UInteger.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<SetTriggeringResponse> setTriggering(UInteger subscriptionId,
                                                                  UInteger triggeringItemId,
                                                                  List<UInteger> linksToAdd,
                                                                  List<UInteger> linksToRemove) {

        return getSession().thenCompose(session -> {
            SetTriggeringRequest request = new SetTriggeringRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                triggeringItemId,
                a(linksToAdd, UInteger.class),
                a(linksToRemove, UInteger.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<AddNodesResponse> addNodes(List<AddNodesItem> nodesToAdd) {
        return getSession().thenCompose(session -> {
            AddNodesRequest request = new AddNodesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(nodesToAdd, AddNodesItem.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<AddReferencesResponse> addReferences(List<AddReferencesItem> referencesToAdd) {
        return getSession().thenCompose(session -> {
            AddReferencesRequest request = new AddReferencesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(referencesToAdd, AddReferencesItem.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<DeleteNodesResponse> deleteNodes(List<DeleteNodesItem> nodesToDelete) {
        return getSession().thenCompose(session -> {
            DeleteNodesRequest request = new DeleteNodesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(nodesToDelete, DeleteNodesItem.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<DeleteReferencesResponse> deleteReferences(List<DeleteReferencesItem> referencesToDelete) {
        return getSession().thenCompose(session -> {
            DeleteReferencesRequest request = new DeleteReferencesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(referencesToDelete, DeleteReferencesItem.class)
            );

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<OpcUaSession> getSession() {
        return sessionFsm.getSession();
    }

    @Override
    public <T extends UaResponseMessage> CompletableFuture<T> sendRequest(UaRequestMessage request) {
        CompletableFuture<UaResponseMessage> f = getStackClient().sendRequest(request);

        if (faultListeners.size() > 0) {
            f.whenComplete(this::maybeHandleServiceFault);
        }

        return f.thenApply(r -> (T) r);
    }


    private void maybeHandleServiceFault(UaResponseMessage response, Throwable ex) {
        if (faultListeners.isEmpty()) return;

        if (ex != null) {
            if (ex instanceof UaServiceFaultException) {
                UaServiceFaultException faultException = (UaServiceFaultException) ex;
                ServiceFault serviceFault = faultException.getServiceFault();

                logger.debug("Notifying {} ServiceFaultListeners", faultListeners.size());

                faultNotificationQueue.submit(() ->
                    faultListeners.forEach(h -> h.onServiceFault(serviceFault)));
            } else if (ex.getCause() instanceof UaServiceFaultException) {
                UaServiceFaultException faultException = (UaServiceFaultException) ex.getCause();
                ServiceFault serviceFault = faultException.getServiceFault();

                logger.debug("Notifying {} ServiceFaultListeners", faultListeners.size());

                faultNotificationQueue.submit(() ->
                    faultListeners.forEach(h -> h.onServiceFault(serviceFault)));
            }
        }
    }

    public void addFaultListener(ServiceFaultListener faultListener) {
        faultListeners.add(faultListener);
        logger.debug("Added ServiceFaultListener: {}", faultListener);
    }

    public void removeFaultListener(ServiceFaultListener faultListener) {
        faultListeners.remove(faultListener);
        logger.debug("Removed ServiceFaultListener: {}", faultListener);
    }

    public void addSessionActivityListener(SessionActivityListener listener) {
        sessionFsm.addActivityListener(listener);
        logger.debug("Added SessionActivityListener: {}", listener);
    }

    public void removeSessionActivityListener(SessionActivityListener listener) {
        sessionFsm.removeActivityListener(listener);
        logger.debug("Removed SessionActivityListener: {}", listener);
    }

    public void addSessionInitializer(SessionInitializer initializer) {
        sessionFsm.addInitializer(initializer);
        logger.debug("Added SessionInitializer: {}", initializer);
    }

    public void removeSessionInitializer(SessionInitializer initializer) {
        sessionFsm.removeInitializer(initializer);
        logger.debug("Removed SessionInitializer: {}", initializer);
    }

}
