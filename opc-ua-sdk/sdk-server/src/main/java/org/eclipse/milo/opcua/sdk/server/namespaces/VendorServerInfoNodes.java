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

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

import com.sun.management.UnixOperatingSystemMXBean;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class VendorServerInfoNodes {

    public static void add(UaNodeContext context) {
        addVendorServerInfoNodes(context);
    }

    private static void addVendorServerInfoNodes(UaNodeContext context) {
        Optional<UaNode> maybeVendorServerInfo = context.getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_VendorServerInfo);

        maybeVendorServerInfo.ifPresent(node -> {
            UaObjectNode vendorServerInfo = (UaObjectNode) node;

            // standard Java API
            addVendorInfoPlainJava(context, vendorServerInfo);

            // JMX API
            addVendorInfoJmx(context, vendorServerInfo);

            // com.sun API
            addVendorInfoSunJmx(context, vendorServerInfo);
        });
    }

    private static void addVendorInfoPlainJava(UaNodeContext context, UaObjectNode vendorServerInfo) {
        UaVariableNode availableProcessors = new UaVariableNode(
            context,
            new NodeId(1, "VendorServerInfo/AvailableProcessors"),
            new QualifiedName(1, "AvailableProcessors"),
            LocalizedText.english("AvailableProcessors")) {

            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(Runtime.getRuntime().availableProcessors()));
            }
        };
        availableProcessors.setDataType(Identifiers.Int32);
        context.getNodeManager().addNode(availableProcessors);

        vendorServerInfo.addComponent(availableProcessors);
    }

    private static void addVendorInfoJmx(UaNodeContext context, UaObjectNode vendorServerInfo) {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        UaVariableNode usedMemory = new UaVariableNode(
            context,
            new NodeId(1, "VendorServerInfo/UsedMemory"),
            new QualifiedName(1, "UsedMemory"),
            LocalizedText.english("UsedMemory")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(memoryBean.getHeapMemoryUsage().getUsed() / 1000));
            }
        };
        usedMemory.setDataType(Identifiers.Int64);
        context.getNodeManager().addNode(usedMemory);

        UaVariableNode maxMemory = new UaVariableNode(
            context,
            new NodeId(1, "VendorServerInfo/MaxMemory"),
            new QualifiedName(1, "MaxMemory"),
            LocalizedText.english("MaxMemory")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(memoryBean.getHeapMemoryUsage().getMax()));
            }
        };
        maxMemory.setDataType(Identifiers.Int64);
        context.getNodeManager().addNode(maxMemory);

        UaVariableNode osName = new UaVariableNode(
            context,
            new NodeId(1, "VendorServerInfo/OsName"),
            new QualifiedName(1, "OsName"),
            LocalizedText.english("OsName")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(osBean.getName()));
            }
        };
        osName.setDataType(Identifiers.String);
        context.getNodeManager().addNode(osName);

        UaVariableNode osArch = new UaVariableNode(
            context,
            new NodeId(1, "VendorServerInfo/OsArch"),
            new QualifiedName(1, "OsArch"),
            LocalizedText.english("OsArch")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(osBean.getArch()));
            }
        };
        osArch.setDataType(Identifiers.String);
        context.getNodeManager().addNode(osArch);

        UaVariableNode osVersion = new UaVariableNode(
            context,
            new NodeId(1, "VendorServerInfo/OsVersion"),
            new QualifiedName(1, "OsVersion"),
            LocalizedText.english("OsVersion")) {
            @Override
            public DataValue getValue() {
                return new DataValue(new Variant(osBean.getVersion()));
            }
        };
        osVersion.setDataType(Identifiers.String);
        context.getNodeManager().addNode(osVersion);

        vendorServerInfo.addComponent(usedMemory);
        vendorServerInfo.addComponent(maxMemory);
        vendorServerInfo.addComponent(osName);
        vendorServerInfo.addComponent(osArch);
        vendorServerInfo.addComponent(osVersion);
    }

    private static void addVendorInfoSunJmx(UaNodeContext context, UaObjectNode vendorServerInfo) {
        OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();

        if (osMxBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean sunOsMxBean =
                (com.sun.management.OperatingSystemMXBean) osMxBean;

            UaVariableNode processCpuLoad = new UaVariableNode(
                context,
                new NodeId(1, "VendorServerInfo/ProcessCpuLoad"),
                new QualifiedName(1, "ProcessCpuLoad"),
                LocalizedText.english("ProcessCpuLoad")) {

                @Override
                public DataValue getValue() {
                    return new DataValue(new Variant(sunOsMxBean.getProcessCpuLoad() * 100d));
                }
            };
            processCpuLoad.setDataType(Identifiers.Double);
            context.getNodeManager().addNode(processCpuLoad);

            UaVariableNode systemCpuLoad = new UaVariableNode(
                context,
                new NodeId(1, "VendorServerInfo/SystemCpuLoad"),
                new QualifiedName(1, "SystemCpuLoad"),
                LocalizedText.english("SystemCpuLoad")) {
                @Override
                public DataValue getValue() {
                    return new DataValue(new Variant(sunOsMxBean.getSystemCpuLoad() * 100d));
                }
            };
            systemCpuLoad.setDataType(Identifiers.Double);
            context.getNodeManager().addNode(systemCpuLoad);

            vendorServerInfo.addComponent(processCpuLoad);
            vendorServerInfo.addComponent(systemCpuLoad);

            if (sunOsMxBean instanceof UnixOperatingSystemMXBean) {
                UnixOperatingSystemMXBean unixBean = (UnixOperatingSystemMXBean) sunOsMxBean;

                UaVariableNode openFileDescriptors = new UaVariableNode(
                    context,
                    new NodeId(1, "VendorServerInfo/OpenFileDescriptors"),
                    new QualifiedName(1, "OpenFileDescriptors"),
                    LocalizedText.english("OpenFileDescriptors")) {
                    @Override
                    public DataValue getValue() {
                        return new DataValue(new Variant(unixBean.getOpenFileDescriptorCount()));
                    }
                };
                openFileDescriptors.setDataType(Identifiers.Int64);
                context.getNodeManager().addNode(openFileDescriptors);

                UaVariableNode maxFileDescriptors = new UaVariableNode(
                    context,
                    new NodeId(1, "VendorServerInfo/MaxFileDescriptors"),
                    new QualifiedName(1, "MaxFileDescriptors"),
                    LocalizedText.english("MaxFileDescriptors")) {
                    @Override
                    public DataValue getValue() {
                        return new DataValue(new Variant(unixBean.getMaxFileDescriptorCount()));
                    }
                };
                maxFileDescriptors.setDataType(Identifiers.Int64);
                context.getNodeManager().addNode(maxFileDescriptors);

                vendorServerInfo.addComponent(openFileDescriptors);
                vendorServerInfo.addComponent(maxFileDescriptors);
            }
        }
    }

}
