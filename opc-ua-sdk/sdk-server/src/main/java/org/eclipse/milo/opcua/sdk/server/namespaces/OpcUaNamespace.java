/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.namespaces;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.NamespaceNodeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.EventItem;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler.NotImplementedHandler;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfigLimits;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.methods.ConditionRefresh;
import org.eclipse.milo.opcua.sdk.server.model.methods.GetMonitoredItems;
import org.eclipse.milo.opcua.sdk.server.model.methods.ResendData;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.OperationLimitsNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerCapabilitiesNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.RedundancySupport;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class OpcUaNamespace implements Namespace {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NamespaceNodeManager nodeManager;
    private final SubscriptionModel subscriptionModel;

    private final OpcUaServer server;

    public OpcUaNamespace(OpcUaServer server) {
        this.server = server;

        nodeManager = new NamespaceNodeManager(server);
        subscriptionModel = new SubscriptionModel(server, this);
    }

    public void initialize() {
        loadNodes();
        configureServerObject();

        try {
            UaMethodNode conditionRefresh = (UaMethodNode) nodeManager.get(Identifiers.ConditionType_ConditionRefresh);
            assert conditionRefresh != null;

            AnnotationBasedInvocationHandler handler =
                AnnotationBasedInvocationHandler.fromAnnotatedObject(server, new ConditionRefresh(server));

            conditionRefresh.setInvocationHandler(handler);
            conditionRefresh.setInputArguments(handler.getInputArguments());
            conditionRefresh.setOutputArguments(handler.getOutputArguments());
        } catch (Exception e) {
            logger.error("Error setting up ConditionRefresh Method.", e);
        }
    }

    @Override
    public UShort getNamespaceIndex() {
        return ushort(0);
    }

    @Override
    public String getNamespaceUri() {
        return NamespaceTable.OPC_UA_NAMESPACE;
    }

    @Override
    public NodeManager<UaNode> getNodeManager() {
        return nodeManager;
    }

    @Override
    public CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId) {
        UaNode node = nodeManager.get(nodeId);

        if (node != null) {
            return CompletableFuture.completedFuture(node.getReferences());
        } else {
            CompletableFuture<List<Reference>> f = new CompletableFuture<>();
            f.completeExceptionally(new UaException(StatusCodes.Bad_NodeIdUnknown));
            return f;
        }
    }

    @Override
    public void read(ReadContext context, Double maxAge,
                     TimestampsToReturn timestamps,
                     List<ReadValueId> readValueIds) {

        List<DataValue> results = newArrayListWithCapacity(readValueIds.size());

        for (ReadValueId id : readValueIds) {
            DataValue value;

            UaServerNode node = nodeManager.get(id.getNodeId());

            if (node != null) {
                value = node.readAttribute(
                    new AttributeContext(context),
                    id.getAttributeId(),
                    timestamps,
                    id.getIndexRange(),
                    id.getDataEncoding()
                );
            } else {
                value = new DataValue(new StatusCode(StatusCodes.Bad_NodeIdUnknown));
            }

            results.add(value);
        }

        context.complete(results);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        List<StatusCode> results = writeValues.stream()
            .map(value -> {
                if (nodeManager.containsNode(value.getNodeId())) {
                    return new StatusCode(StatusCodes.Bad_NotWritable);
                } else {
                    return new StatusCode(StatusCodes.Bad_NodeIdUnknown);
                }
            })
            .collect(toList());

        context.complete(results);
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }

    @Override
    public void onEventItemsCreated(List<EventItem> eventItems) {
        eventItems.stream()
            .filter(MonitoredItem::isSamplingEnabled)
            .forEach(item -> server.getEventBus().register(item));
    }

    @Override
    public void onEventItemsModified(List<EventItem> eventItems) {
        for (EventItem item : eventItems) {
            if (item.isSamplingEnabled()) {
                server.getEventBus().register(item);
            } else {
                server.getEventBus().unregister(item);
            }
        }
    }

    @Override
    public void onEventItemsDeleted(List<EventItem> eventItems) {
        eventItems.forEach(item -> server.getEventBus().unregister(item));
    }

    @Override
    public Optional<MethodInvocationHandler> getInvocationHandler(NodeId methodId) {
        return Optional.ofNullable(nodeManager.get(methodId))
            .filter(n -> n instanceof UaMethodNode)
            .map(n -> {
                UaMethodNode m = (UaMethodNode) n;
                return m.getInvocationHandler()
                    .orElse(new NotImplementedHandler());
            });
    }

    private void loadNodes() {
        try {
            long startTime = System.nanoTime();

            new UaNodeLoader(server).loadNodes();

            long endTime = System.nanoTime();
            long deltaMs = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);

            logger.info("Loaded nodes in {}ms.", deltaMs);
        } catch (Exception e) {
            logger.error("Error loading nodes.", e);
        }
    }

    private void configureServerObject() {
        ServerNode serverNode = (ServerNode) nodeManager.get(Identifiers.Server);

        assert serverNode != null;

        serverNode.getNamespaceArrayNode().setAttributeDelegate(new AttributeDelegate() {
            @Override
            public DataValue getValue(AttributeContext context, VariableNode node) {
                return new DataValue(new Variant(server.getNamespaceManager().getNamespaceTable().toArray()));
            }
        });
        serverNode.getServerArrayNode().setAttributeDelegate(new AttributeDelegate() {
            @Override
            public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                return new DataValue(new Variant(server.getServerTable().toArray()));
            }
        });
        serverNode.setAuditing(false);
        serverNode.getServerDiagnosticsNode().setEnabledFlag(false);
        serverNode.setServiceLevel(ubyte(255));

        ServerStatusNode serverStatus = serverNode.getServerStatusNode();

        BuildInfo buildInfo = server.getConfig().getBuildInfo();
        serverStatus.setBuildInfo(buildInfo);
        serverStatus.getBuildInfoNode().setBuildDate(buildInfo.getBuildDate());
        serverStatus.getBuildInfoNode().setBuildNumber(buildInfo.getBuildNumber());
        serverStatus.getBuildInfoNode().setManufacturerName(buildInfo.getManufacturerName());
        serverStatus.getBuildInfoNode().setProductName(buildInfo.getProductName());
        serverStatus.getBuildInfoNode().setProductUri(buildInfo.getProductUri());
        serverStatus.getBuildInfoNode().setSoftwareVersion(buildInfo.getSoftwareVersion());

        serverStatus.setCurrentTime(DateTime.now());
        serverStatus.setSecondsTillShutdown(uint(0));
        serverStatus.setShutdownReason(LocalizedText.NULL_VALUE);
        serverStatus.setState(ServerState.Running);
        serverStatus.setStartTime(DateTime.now());
        serverStatus.getCurrentTimeNode().setAttributeDelegate(new AttributeDelegate() {
            @Override
            public DataValue getValue(AttributeContext context, VariableNode node) {
                return new DataValue(new Variant(DateTime.now()));
            }
        });

        final OpcUaServerConfigLimits limits = server.getConfig().getLimits();
        ServerCapabilitiesNode serverCapabilities = serverNode.getServerCapabilitiesNode();
        serverCapabilities.setLocaleIdArray(new String[]{Locale.ENGLISH.getLanguage()});
        serverCapabilities.setMaxArrayLength(limits.getMaxArrayLength());
        serverCapabilities.setMaxBrowseContinuationPoints(limits.getMaxBrowseContinuationPoints());
        serverCapabilities.setMaxHistoryContinuationPoints(limits.getMaxHistoryContinuationPoints());
        serverCapabilities.setMaxQueryContinuationPoints(limits.getMaxQueryContinuationPoints());
        serverCapabilities.setMaxStringLength(limits.getMaxStringLength());
        serverCapabilities.setMinSupportedSampleRate(limits.getMinSupportedSampleRate());

        OperationLimitsNode limitsNode = serverCapabilities.getOperationLimitsNode();
        limitsNode.setMaxMonitoredItemsPerCall(limits.getMaxMonitoredItemsPerCall());
        limitsNode.setMaxNodesPerBrowse(limits.getMaxNodesPerBrowse());
        limitsNode.setMaxNodesPerHistoryReadData(limits.getMaxNodesPerHistoryReadData());
        limitsNode.setMaxNodesPerHistoryReadEvents(limits.getMaxNodesPerHistoryReadEvents());
        limitsNode.setMaxNodesPerHistoryUpdateData(limits.getMaxNodesPerHistoryUpdateData());
        limitsNode.setMaxNodesPerHistoryUpdateEvents(limits.getMaxNodesPerHistoryUpdateEvents());
        limitsNode.setMaxNodesPerMethodCall(limits.getMaxNodesPerMethodCall());
        limitsNode.setMaxNodesPerNodeManagement(limits.getMaxNodesPerNodeManagement());
        limitsNode.setMaxNodesPerRead(limits.getMaxNodesPerRead());
        limitsNode.setMaxNodesPerRegisterNodes(limits.getMaxNodesPerRegisterNodes());
        limitsNode.setMaxNodesPerTranslateBrowsePathsToNodeIds(limits.getMaxNodesPerTranslateBrowsePathsToNodeIds());
        limitsNode.setMaxNodesPerWrite(limits.getMaxNodesPerWrite());

        serverNode.getServerRedundancyNode().setRedundancySupport(RedundancySupport.None);

        try {
            UaNode node = nodeManager.get(Identifiers.Server_GetMonitoredItems);

            if (node instanceof UaMethodNode) {
                UaMethodNode getMonitoredItems = (UaMethodNode) node;

                AnnotationBasedInvocationHandler handler =
                    AnnotationBasedInvocationHandler.fromAnnotatedObject(server, new GetMonitoredItems(server));

                getMonitoredItems.setInvocationHandler(handler);
                getMonitoredItems.setInputArguments(handler.getInputArguments());
                getMonitoredItems.setOutputArguments(handler.getOutputArguments());
            }
        } catch (Exception e) {
            logger.error("Error setting up GetMonitoredItems Method.", e);
        }

        try {
            UaNode node = nodeManager.get(Identifiers.Server_ResendData);

            if (node instanceof UaMethodNode) {
                UaMethodNode resendData = (UaMethodNode) node;

                AnnotationBasedInvocationHandler handler =
                    AnnotationBasedInvocationHandler.fromAnnotatedObject(server, new ResendData(server));

                resendData.setInvocationHandler(handler);
                resendData.setInputArguments(handler.getInputArguments());
            }
        } catch (Exception e) {
            logger.error("Error setting up ResendData Method.", e);
        }
    }

}
