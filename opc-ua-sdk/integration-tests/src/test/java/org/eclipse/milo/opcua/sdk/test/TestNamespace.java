/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.test;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespaceWithLifecycle;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.methods.InvalidArgumentException;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.PermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class TestNamespace extends ManagedNamespaceWithLifecycle {

    public static final String NAMESPACE_URI = "urn:eclipse:milo:test";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private volatile Thread eventThread;
    private volatile boolean keepPostingEvents = true;

    public TestNamespace(OpcUaServer server) {
        super(server, NAMESPACE_URI);

        getLifecycleManager().addLifecycle(new Lifecycle() {
            @Override
            public void startup() {
                startBogusEventNotifier();
            }

            @Override
            public void shutdown() {
                try {
                    keepPostingEvents = false;
                    eventThread.interrupt();
                    eventThread.join();
                } catch (InterruptedException ignored) {
                    // ignored
                }
            }
        });

        getLifecycleManager().addStartupTask(() -> {
            UaVariableNode testInt32Node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(newNodeId("TestInt32"))
                .setAccessLevel(AccessLevel.READ_WRITE)
                .setUserAccessLevel(AccessLevel.READ_WRITE)
                .setBrowseName(newQualifiedName("TestInt32"))
                .setDisplayName(LocalizedText.english("TestInt32"))
                .setDataType(Identifiers.Int32)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .setRolePermissions(new RolePermissionType[] {
                    new RolePermissionType(newNodeId("roleId"), new PermissionType(uint(0)))
                })
                .setUserRolePermissions(new RolePermissionType[] {
                    new RolePermissionType(newNodeId("roleId"), new PermissionType(uint(0)))
                })
                .setAccessRestrictions(new AccessRestrictionType(uint(0)))
                .build();

            testInt32Node.setValue(new DataValue(new Variant(0)));

            testInt32Node.addReference(new Reference(
                testInt32Node.getNodeId(),
                Identifiers.HasComponent,
                Identifiers.ObjectsFolder.expanded(),
                Reference.Direction.INVERSE
            ));

            getNodeManager().addNode(testInt32Node);
        });

        getLifecycleManager().addStartupTask(() -> {
            try {
                AnalogItemTypeNode node = (AnalogItemTypeNode) getNodeFactory().createNode(
                    newNodeId("TestAnalogValue"),
                    Identifiers.AnalogItemType,
                    new NodeFactory.InstantiationCallback() {
                        @Override
                        public boolean includeOptionalNode(NodeId typeDefinitionId, QualifiedName browseName) {
                            return true;
                        }
                    }
                );

                node.setBrowseName(newQualifiedName("TestAnalogValue"));
                node.setDisplayName(LocalizedText.english("TestAnalogValue"));
                node.setDataType(Identifiers.Double);
                node.setValue(new DataValue(new Variant(3.14d)));

                node.setEURange(new Range(0.0, 100.0));

                getNodeManager().addNode(node);
            } catch (UaException e) {
                throw new RuntimeException(e);
            }
        });

        getLifecycleManager().addStartupTask(() -> {
            UaMethodNode.build(getNodeContext(), b -> {
                b.setNodeId(newNodeId("sqrt(x)"));
                b.setBrowseName(newQualifiedName("sqrt(x)"));
                b.setDisplayName(LocalizedText.english("sqrt(x)"));

                b.addReference(new Reference(
                    b.getNodeId(),
                    Identifiers.HasComponent,
                    Identifiers.ObjectsFolder.expanded(),
                    Reference.Direction.INVERSE
                ));

                UaMethodNode methodNode = b.buildAndAdd();

                SqrtMethod sqrtMethod = new SqrtMethod(methodNode);
                methodNode.setInputArguments(sqrtMethod.getInputArguments());
                methodNode.setOutputArguments(sqrtMethod.getOutputArguments());
                methodNode.setInvocationHandler(sqrtMethod);

                return methodNode;
            });

            UaMethodNode.build(getNodeContext(), b -> {
                b.setNodeId(newNodeId("sqrt2(x)"));
                b.setBrowseName(newQualifiedName("sqrt2(x)"));
                b.setDisplayName(LocalizedText.english("sqrt2(x)"));

                b.addReference(new Reference(
                    b.getNodeId(),
                    Identifiers.HasOrderedComponent,
                    Identifiers.ObjectsFolder.expanded(),
                    Reference.Direction.INVERSE
                ));

                UaMethodNode methodNode = b.buildAndAdd();

                SqrtMethod sqrtMethod = new SqrtMethod(methodNode);
                methodNode.setInputArguments(sqrtMethod.getInputArguments());
                methodNode.setOutputArguments(sqrtMethod.getOutputArguments());
                methodNode.setInvocationHandler(sqrtMethod);

                return methodNode;
            });

            UaMethodNode.build(getNodeContext(), b -> {
                b.setNodeId(newNodeId("hasNoInputsOrOutputs()"));
                b.setBrowseName(newQualifiedName("hasNoInputsOrOutputs()"));
                b.setDisplayName(LocalizedText.english("hasNoInputsOrOutputs()"));

                b.addReference(new Reference(
                    b.getNodeId(),
                    Identifiers.HasOrderedComponent,
                    Identifiers.ObjectsFolder.expanded(),
                    Reference.Direction.INVERSE
                ));

                UaMethodNode methodNode = b.buildAndAdd();

                methodNode.setInvocationHandler(new AbstractMethodInvocationHandler(methodNode) {
                    @Override
                    public Argument[] getInputArguments() {
                        return new Argument[0];
                    }

                    @Override
                    public Argument[] getOutputArguments() {
                        return new Argument[0];
                    }

                    @Override
                    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) {
                        return new Variant[0];
                    }
                });

                return methodNode;
            });

            UaMethodNode.build(getNodeContext(), b -> {
                b.setNodeId(newNodeId("onlyAcceptsPositiveInputs()"));
                b.setBrowseName(newQualifiedName("onlyAcceptsPositiveInputs()"));
                b.setDisplayName(LocalizedText.english("onlyAcceptsPositiveInputs()"));

                b.addReference(new Reference(
                    b.getNodeId(),
                    Identifiers.HasOrderedComponent,
                    Identifiers.ObjectsFolder.expanded(),
                    Reference.Direction.INVERSE
                ));

                UaMethodNode methodNode = b.buildAndAdd();

                methodNode.setInvocationHandler(new AbstractMethodInvocationHandler(methodNode) {
                    @Override
                    public Argument[] getInputArguments() {
                        return new Argument[]{
                            new Argument(
                                "i",
                                Identifiers.Int32,
                                ValueRanks.Scalar,
                                null,
                                LocalizedText.NULL_VALUE)
                        };
                    }

                    @Override
                    public Argument[] getOutputArguments() {
                        return new Argument[0];
                    }

                    @Override
                    protected void validateInputArgumentValues(
                        Variant[] inputArgumentValues
                    ) throws InvalidArgumentException {

                        int i = (int) inputArgumentValues[0].getValue();

                        if (i < 0) {
                            StatusCode[] inputArgumentResults = {
                                new StatusCode(StatusCodes.Bad_OutOfRange)
                            };

                            throw new InvalidArgumentException(inputArgumentResults);
                        }
                    }

                    @Override
                    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) {
                        return new Variant[0];
                    }
                });

                return methodNode;
            });
        });
    }

    private void startBogusEventNotifier() {
        // Set the EventNotifier bit on Server Node for Events.
        UaNode serverNode = getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server)
            .orElse(null);

        if (serverNode instanceof ServerTypeNode) {
            ((ServerTypeNode) serverNode).setEventNotifier(ubyte(1));

            // Post a bogus Event every couple seconds
            eventThread = new Thread(() -> {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException ignored) {
                    // ignored
                }

                while (keepPostingEvents) {
                    try {
                        BaseEventTypeNode eventNode = getServer().getEventFactory().createEvent(
                            newNodeId(UUID.randomUUID()),
                            Identifiers.BaseEventType
                        );

                        eventNode.setBrowseName(new QualifiedName(1, "foo"));
                        eventNode.setDisplayName(LocalizedText.english("foo"));
                        eventNode.setEventId(ByteString.of(new byte[]{0, 1, 2, 3}));
                        eventNode.setEventType(Identifiers.BaseEventType);
                        eventNode.setSourceNode(serverNode.getNodeId());
                        eventNode.setSourceName(serverNode.getDisplayName().getText());
                        eventNode.setTime(DateTime.now());
                        eventNode.setReceiveTime(DateTime.NULL_VALUE);
                        eventNode.setMessage(LocalizedText.english("event message!"));
                        eventNode.setSeverity(ushort(2));

                        //noinspection UnstableApiUsage
                        getServer().getEventBus().post(eventNode);

                        eventNode.delete();
                    } catch (Throwable e) {
                        logger.error("Error creating EventNode: {}", e.getMessage(), e);
                    }

                    try {
                        //noinspection BusyWait
                        Thread.sleep(2_000);
                    } catch (InterruptedException ignored) {
                        // ignored
                    }
                }
            }, "bogus-event-poster");

            eventThread.start();
        }
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {

    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {

    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {

    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {

    }

    public void configureNode(BiConsumer<UaNodeContext, UaNodeManager> consumer) {
        consumer.accept(getNodeContext(), getNodeManager());
    }

}
