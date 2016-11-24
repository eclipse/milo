/*
 * Copyright (c) 2016 Kevin Herron and others
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

package org.eclipse.milo.opcua.sdk.server.namespaces;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import com.sun.management.UnixOperatingSystemMXBean;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

@SuppressWarnings("restriction")
public class VendorNamespace implements Namespace {

    public static final UShort NAMESPACE_INDEX = ushort(1);

    private final ServerNodeMap nodeMap;
    private final SubscriptionModel subscriptionModel;

    private final OpcUaServer server;
    private final String namespaceUri;

    public VendorNamespace(OpcUaServer server, String namespaceUri) {
        this.server = server;
        this.namespaceUri = namespaceUri;

        nodeMap = server.getNodeMap();
        subscriptionModel = new SubscriptionModel(server, this);

        addVendorServerInfoNodes();
    }

    @Override
    public UShort getNamespaceIndex() {
        return NAMESPACE_INDEX;
    }

    @Override
    public String getNamespaceUri() {
        return namespaceUri;
    }

    @Override
    public CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId) {
        ServerNode node = nodeMap.get(nodeId);

        if (node != null) {
            return CompletableFuture.completedFuture(node.getReferences());
        } else {
            CompletableFuture<List<Reference>> f = new CompletableFuture<>();
            f.completeExceptionally(new UaException(StatusCodes.Bad_NodeIdUnknown));
            return f;
        }
    }

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds) {

        List<DataValue> results = Lists.newArrayListWithCapacity(readValueIds.size());

        for (ReadValueId id : readValueIds) {
            ServerNode node = nodeMap.get(id.getNodeId());

            DataValue value = (node != null) ?
                node.readAttribute(new AttributeContext(context), id.getAttributeId()) :
                new DataValue(StatusCodes.Bad_NodeIdUnknown);

            results.add(value);
        }

        context.complete(results);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        List<StatusCode> results = writeValues.stream()
            .map(value -> {
                if (nodeMap.containsKey(value.getNodeId())) {
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

    private void addVendorServerInfoNodes() {
        nodeMap.getNode(Identifiers.Server_VendorServerInfo).ifPresent(node -> {
            UaObjectNode vendorServerInfo = (UaObjectNode) node;

            // standard Java API
            addVendorInfoPlainJava(vendorServerInfo);

            // JMX API
            addVendorInfoJmx(vendorServerInfo);

            // com.sun API
            addVendorInfoSunJmx(vendorServerInfo);
        });

    }

    private void addVendorInfoPlainJava(UaObjectNode vendorServerInfo) {
        UaVariableNode availableProcessors = new UaVariableNode(
            nodeMap,
            new NodeId(1, "VendorServerInfo/AvailableProcessors"),
            new QualifiedName(1, "AvailableProcessors"),
            LocalizedText.english("AvailableProcessors")) {

            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(Runtime.getRuntime().availableProcessors()));
            }
        };
        availableProcessors.setDataType(Identifiers.Int32);

        vendorServerInfo.addComponent(availableProcessors);
    }

    private void addVendorInfoJmx(UaObjectNode vendorServerInfo) {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        UaVariableNode usedMemory = new UaVariableNode(
            nodeMap,
            new NodeId(1, "VendorServerInfo/UsedMemory"),
            new QualifiedName(1, "UsedMemory"),
            LocalizedText.english("UsedMemory")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(memoryBean.getHeapMemoryUsage().getUsed() / 1000));
            }
        };
        usedMemory.setDataType(Identifiers.Int64);

        UaVariableNode maxMemory = new UaVariableNode(
            nodeMap,
            new NodeId(1, "VendorServerInfo/MaxMemory"),
            new QualifiedName(1, "MaxMemory"),
            LocalizedText.english("MaxMemory")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(memoryBean.getHeapMemoryUsage().getMax()));
            }
        };
        maxMemory.setDataType(Identifiers.Int64);

        UaVariableNode osName = new UaVariableNode(
            nodeMap,
            new NodeId(1, "VendorServerInfo/OsName"),
            new QualifiedName(1, "OsName"),
            LocalizedText.english("OsName")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(osBean.getName()));
            }
        };
        osName.setDataType(Identifiers.String);

        UaVariableNode osArch = new UaVariableNode(
            nodeMap,
            new NodeId(1, "VendorServerInfo/OsArch"),
            new QualifiedName(1, "OsArch"),
            LocalizedText.english("OsArch")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(osBean.getArch()));
            }
        };
        osArch.setDataType(Identifiers.String);

        UaVariableNode osVersion = new UaVariableNode(
            nodeMap,
            new NodeId(1, "VendorServerInfo/OsVersion"),
            new QualifiedName(1, "OsVersion"),
            LocalizedText.english("OsVersion")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(osBean.getVersion()));
            }
        };
        osVersion.setDataType(Identifiers.String);

        vendorServerInfo.addComponent(usedMemory);
        vendorServerInfo.addComponent(maxMemory);
        vendorServerInfo.addComponent(osName);
        vendorServerInfo.addComponent(osArch);
        vendorServerInfo.addComponent(osVersion);
    }

    private void addVendorInfoSunJmx(UaObjectNode vendorServerInfo) {
        try {
            com.sun.management.OperatingSystemMXBean osBean =
                (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            UaVariableNode processCpuLoad = new UaVariableNode(
                nodeMap,
                new NodeId(1, "VendorServerInfo/ProcessCpuLoad"),
                new QualifiedName(1, "ProcessCpuLoad"),
                LocalizedText.english("ProcessCpuLoad")) {

                @Override
                public DataValue getValue() {
                    return new DataValue(new Variant(osBean.getProcessCpuLoad() * 100d));
                }
            };
            processCpuLoad.setDataType(Identifiers.Double);

            UaVariableNode systemCpuLoad = new UaVariableNode(
                nodeMap,
                new NodeId(1, "VendorServerInfo/SystemCpuLoad"),
                new QualifiedName(1, "SystemCpuLoad"),
                LocalizedText.english("SystemCpuLoad")) {
                @Override
                public DataValue getValue() {
                    return new DataValue(new Variant(osBean.getSystemCpuLoad() * 100d));
                }
            };
            systemCpuLoad.setDataType(Identifiers.Double);

            vendorServerInfo.addComponent(processCpuLoad);
            vendorServerInfo.addComponent(systemCpuLoad);

            if (osBean instanceof UnixOperatingSystemMXBean) {
                UnixOperatingSystemMXBean unixBean = (UnixOperatingSystemMXBean) osBean;

                UaVariableNode openFileDescriptors = new UaVariableNode(
                    nodeMap,
                    new NodeId(1, "VendorServerInfo/OpenFileDescriptors"),
                    new QualifiedName(1, "OpenFileDescriptors"),
                    LocalizedText.english("OpenFileDescriptors")) {
                    @Override
                    public DataValue getValue() {
                        return new DataValue(new Variant(unixBean.getOpenFileDescriptorCount()));
                    }
                };
                openFileDescriptors.setDataType(Identifiers.Int64);

                UaVariableNode maxFileDescriptors = new UaVariableNode(
                    nodeMap,
                    new NodeId(1, "VendorServerInfo/MaxFileDescriptors"),
                    new QualifiedName(1, "MaxFileDescriptors"),
                    LocalizedText.english("MaxFileDescriptors")) {
                    @Override
                    public DataValue getValue() {
                        return new DataValue(new Variant(unixBean.getMaxFileDescriptorCount()));
                    }
                };
                maxFileDescriptors.setDataType(Identifiers.Int64);

                vendorServerInfo.addComponent(openFileDescriptors);
                vendorServerInfo.addComponent(maxFileDescriptors);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

}
