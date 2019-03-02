/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.server;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import org.eclipse.milo.examples.server.methods.GenerateEventMethod;
import org.eclipse.milo.examples.server.methods.SqrtMethod;
import org.eclipse.milo.examples.server.types.CustomDataType;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.NamespaceNodeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegateChain;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.EventFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class ExampleNamespace implements Namespace {

    public static final String NAMESPACE_URI = "urn:eclipse:milo:hello-world";

    private static final Object[][] STATIC_SCALAR_NODES = new Object[][]{
        {"Boolean", Identifiers.Boolean, new Variant(false)},
        {"Byte", Identifiers.Byte, new Variant(ubyte(0x00))},
        {"SByte", Identifiers.SByte, new Variant((byte) 0x00)},
        {"Integer", Identifiers.Integer, new Variant(32)},
        {"Int16", Identifiers.Int16, new Variant((short) 16)},
        {"Int32", Identifiers.Int32, new Variant(32)},
        {"Int64", Identifiers.Int64, new Variant(64L)},
        {"UInteger", Identifiers.UInteger, new Variant(uint(32))},
        {"UInt16", Identifiers.UInt16, new Variant(ushort(16))},
        {"UInt32", Identifiers.UInt32, new Variant(uint(32))},
        {"UInt64", Identifiers.UInt64, new Variant(ulong(64L))},
        {"Float", Identifiers.Float, new Variant(3.14f)},
        {"Double", Identifiers.Double, new Variant(3.14d)},
        {"String", Identifiers.String, new Variant("string value")},
        {"DateTime", Identifiers.DateTime, new Variant(DateTime.now())},
        {"Guid", Identifiers.Guid, new Variant(UUID.randomUUID())},
        {"ByteString", Identifiers.ByteString, new Variant(new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04}))},
        {"XmlElement", Identifiers.XmlElement, new Variant(new XmlElement("<a>hello</a>"))},
        {"LocalizedText", Identifiers.LocalizedText, new Variant(LocalizedText.english("localized text"))},
        {"QualifiedName", Identifiers.QualifiedName, new Variant(new QualifiedName(1234, "defg"))},
        {"NodeId", Identifiers.NodeId, new Variant(new NodeId(1234, "abcd"))},
        {"Variant", Identifiers.BaseDataType, new Variant(32)},
        {"Duration", Identifiers.Duration, new Variant(1.0)},
        {"UtcTime", Identifiers.UtcTime, new Variant(DateTime.now())},
    };

    private static final Object[][] STATIC_ARRAY_NODES = new Object[][]{
        {"BooleanArray", Identifiers.Boolean, false},
        {"ByteArray", Identifiers.Byte, ubyte(0)},
        {"SByteArray", Identifiers.SByte, (byte) 0x00},
        {"Int16Array", Identifiers.Int16, (short) 16},
        {"Int32Array", Identifiers.Int32, 32},
        {"Int64Array", Identifiers.Int64, 64L},
        {"UInt16Array", Identifiers.UInt16, ushort(16)},
        {"UInt32Array", Identifiers.UInt32, uint(32)},
        {"UInt64Array", Identifiers.UInt64, ulong(64L)},
        {"FloatArray", Identifiers.Float, 3.14f},
        {"DoubleArray", Identifiers.Double, 3.14d},
        {"StringArray", Identifiers.String, "string value"},
        {"DateTimeArray", Identifiers.DateTime, DateTime.now()},
        {"GuidArray", Identifiers.Guid, UUID.randomUUID()},
        {"ByteStringArray", Identifiers.ByteString, new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04})},
        {"XmlElementArray", Identifiers.XmlElement, new XmlElement("<a>hello</a>")},
        {"LocalizedTextArray", Identifiers.LocalizedText, LocalizedText.english("localized text")},
        {"QualifiedNameArray", Identifiers.QualifiedName, new QualifiedName(1234, "defg")},
        {"NodeIdArray", Identifiers.NodeId, new NodeId(1234, "abcd")}
    };


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Random random = new Random();

    private final NamespaceNodeManager nodeManager;
    private final SubscriptionModel subscriptionModel;

    private final EventFactory eventFactory;
    private final NodeFactory nodeFactory;

    private final OpcUaServer server;
    private final UShort namespaceIndex;

    public ExampleNamespace(OpcUaServer server, UShort namespaceIndex) {
        this.server = server;
        this.namespaceIndex = namespaceIndex;

        nodeManager = new NamespaceNodeManager(server);
        subscriptionModel = new SubscriptionModel(server, this);

        eventFactory = server.getEventFactory();
        nodeFactory = server.getNodeFactory();
    }

    public void initialize() {

        // Create a "HelloWorld" folder and add it to the node manager
        NodeId folderNodeId = new NodeId(namespaceIndex, "HelloWorld");

        UaFolderNode folderNode = new UaFolderNode(
            server,
            folderNodeId,
            new QualifiedName(namespaceIndex, "HelloWorld"),
            LocalizedText.english("HelloWorld")
        );

        nodeManager.addNode(folderNode);

        // Make sure our new folder shows up under the server's Objects folder.
        folderNode.addReference(new Reference(
            folderNode.getNodeId(),
            Identifiers.Organizes,
            Identifiers.ObjectsFolder.expanded(),
            false
        ));

        // Add the rest of the nodes
        addVariableNodes(folderNode);

        addSqrtMethod(folderNode);

        addGenerateEventMethod(folderNode);

        addCustomDataTypeVariable(folderNode);

        addCustomObjectTypeAndInstance(folderNode);

        // Set the EventNotifier bit on Server Node for Events.
        // Use the server NodeManager to get nodes in other namespaces.
        UaNode serverNode = server.getNodeManager().get(Identifiers.Server);

        if (serverNode instanceof ServerNode) {
            ((ServerNode) serverNode).setEventNotifier(ubyte(1));

            // Post a bogus Event every couple seconds
            server.getScheduledExecutorService().scheduleAtFixedRate(() -> {
                try {
                    BaseEventNode eventNode = eventFactory.createEvent(
                        new NodeId(1, UUID.randomUUID()),
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

                    server.getEventBus().post(eventNode);
                } catch (Throwable e) {
                    logger.error("Error creating EventNode: {}", e.getMessage(), e);
                }
            }, 0, 2, TimeUnit.SECONDS);
        }
    }

    @Override
    public UShort getNamespaceIndex() {
        return namespaceIndex;
    }

    @Override
    public String getNamespaceUri() {
        return NAMESPACE_URI;
    }

    @Override
    public NamespaceNodeManager getNodeManager() {
        return nodeManager;
    }

    private void addVariableNodes(UaFolderNode rootNode) {
        addArrayNodes(rootNode);
        addScalarNodes(rootNode);
        addAdminReadableNodes(rootNode);
        addAdminWritableNodes(rootNode);
        addDynamicNodes(rootNode);
        addDataAccessNodes(rootNode);
        addWriteOnlyNodes(rootNode);
    }

    private void addArrayNodes(UaFolderNode rootNode) {
        UaFolderNode arrayTypesFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/ArrayTypes"),
            new QualifiedName(namespaceIndex, "ArrayTypes"),
            LocalizedText.english("ArrayTypes")
        );

        nodeManager.addNode(arrayTypesFolder);
        rootNode.addOrganizes(arrayTypesFolder);

        for (Object[] os : STATIC_ARRAY_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Object value = os[2];
            Object array = Array.newInstance(value.getClass(), 5);
            for (int i = 0; i < 5; i++) {
                Array.set(array, i, value);
            }
            Variant variant = new Variant(array);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/ArrayTypes/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .setValueRank(ValueRank.OneDimension.getValue())
                .setArrayDimensions(new UInteger[]{uint(0)})
                .build();

            node.setValue(new DataValue(variant));

            node.setAttributeDelegate(new ValueLoggingDelegate());

            nodeManager.addNode(node);
            arrayTypesFolder.addOrganizes(node);
        }
    }

    private void addScalarNodes(UaFolderNode rootNode) {
        UaFolderNode scalarTypesFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/ScalarTypes"),
            new QualifiedName(namespaceIndex, "ScalarTypes"),
            LocalizedText.english("ScalarTypes")
        );

        nodeManager.addNode(scalarTypesFolder);
        rootNode.addOrganizes(scalarTypesFolder);

        for (Object[] os : STATIC_SCALAR_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/ScalarTypes/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

            node.setValue(new DataValue(variant));

            node.setAttributeDelegate(new ValueLoggingDelegate());

            nodeManager.addNode(node);
            scalarTypesFolder.addOrganizes(node);
        }
    }

    private void addWriteOnlyNodes(UaFolderNode rootNode) {
        UaFolderNode writeOnlyFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/WriteOnly"),
            new QualifiedName(namespaceIndex, "WriteOnly"),
            LocalizedText.english("WriteOnly")
        );

        nodeManager.addNode(writeOnlyFolder);
        rootNode.addOrganizes(writeOnlyFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
            .setNodeId(new NodeId(namespaceIndex, "HelloWorld/WriteOnly/" + name))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY)))
            .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY)))
            .setBrowseName(new QualifiedName(namespaceIndex, name))
            .setDisplayName(LocalizedText.english(name))
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        node.setValue(new DataValue(new Variant("can't read this")));

        nodeManager.addNode(node);
        writeOnlyFolder.addOrganizes(node);
    }

    private void addAdminReadableNodes(UaFolderNode rootNode) {
        UaFolderNode adminFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanRead"),
            new QualifiedName(namespaceIndex, "OnlyAdminCanRead"),
            LocalizedText.english("OnlyAdminCanRead")
        );

        nodeManager.addNode(adminFolder);
        rootNode.addOrganizes(adminFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
            .setNodeId(new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanRead/" + name))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(new QualifiedName(namespaceIndex, name))
            .setDisplayName(LocalizedText.english(name))
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        node.setValue(new DataValue(new Variant("shh... don't tell the lusers")));

        node.setAttributeDelegate(new RestrictedAccessDelegate(identity -> {
            if ("admin".equals(identity)) {
                return AccessLevel.READ_WRITE;
            } else {
                return AccessLevel.NONE;
            }
        }));

        nodeManager.addNode(node);
        adminFolder.addOrganizes(node);
    }

    private void addAdminWritableNodes(UaFolderNode rootNode) {
        UaFolderNode adminFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanWrite"),
            new QualifiedName(namespaceIndex, "OnlyAdminCanWrite"),
            LocalizedText.english("OnlyAdminCanWrite")
        );

        nodeManager.addNode(adminFolder);
        rootNode.addOrganizes(adminFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
            .setNodeId(new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanWrite/" + name))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(new QualifiedName(namespaceIndex, name))
            .setDisplayName(LocalizedText.english(name))
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        node.setValue(new DataValue(new Variant("admin was here")));

        node.setAttributeDelegate(new RestrictedAccessDelegate(identity -> {
            if ("admin".equals(identity)) {
                return AccessLevel.READ_WRITE;
            } else {
                return AccessLevel.READ_ONLY;
            }
        }));

        nodeManager.addNode(node);
        adminFolder.addOrganizes(node);
    }

    private void addDynamicNodes(UaFolderNode rootNode) {
        UaFolderNode dynamicFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/Dynamic"),
            new QualifiedName(namespaceIndex, "Dynamic"),
            LocalizedText.english("Dynamic")
        );

        nodeManager.addNode(dynamicFolder);
        rootNode.addOrganizes(dynamicFolder);

        // Dynamic Boolean
        {
            String name = "Boolean";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/Dynamic/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

            node.setValue(new DataValue(variant));

            AttributeDelegate delegate = AttributeDelegateChain.create(
                new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                        return new DataValue(new Variant(random.nextBoolean()));
                    }
                },
                ValueLoggingDelegate::new
            );

            node.setAttributeDelegate(delegate);

            nodeManager.addNode(node);
            dynamicFolder.addOrganizes(node);
        }

        // Dynamic Int32
        {
            String name = "Int32";
            NodeId typeId = Identifiers.Int32;
            Variant variant = new Variant(0);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/Dynamic/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

            node.setValue(new DataValue(variant));

            AttributeDelegate delegate = AttributeDelegateChain.create(
                new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                        return new DataValue(new Variant(random.nextInt()));
                    }
                },
                ValueLoggingDelegate::new
            );

            node.setAttributeDelegate(delegate);

            nodeManager.addNode(node);
            dynamicFolder.addOrganizes(node);
        }

        // Dynamic Double
        {
            String name = "Double";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0.0);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server)
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/Dynamic/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

            node.setValue(new DataValue(variant));

            AttributeDelegate delegate = AttributeDelegateChain.create(
                new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                        return new DataValue(new Variant(random.nextDouble()));
                    }
                },
                ValueLoggingDelegate::new
            );

            node.setAttributeDelegate(delegate);

            nodeManager.addNode(node);
            dynamicFolder.addOrganizes(node);
        }
    }

    private void addDataAccessNodes(UaFolderNode rootNode) {
        // DataAccess folder
        UaFolderNode dataAccessFolder = new UaFolderNode(
            server,
            new NodeId(namespaceIndex, "HelloWorld/DataAccess"),
            new QualifiedName(namespaceIndex, "DataAccess"),
            LocalizedText.english("DataAccess")
        );

        nodeManager.addNode(dataAccessFolder);
        rootNode.addOrganizes(dataAccessFolder);

        // AnalogItemType node
        try {
            AnalogItemNode node = (AnalogItemNode) nodeFactory.createNode(
                new NodeId(namespaceIndex, "HelloWorld/DataAccess/AnalogValue"),
                Identifiers.AnalogItemType,
                true
            );

            node.setBrowseName(new QualifiedName(namespaceIndex, "AnalogValue"));
            node.setDisplayName(LocalizedText.english("AnalogValue"));
            node.setDataType(Identifiers.Double);
            node.setValue(new DataValue(new Variant(3.14d)));

            node.setEURange(new Range(0.0, 100.0));

            nodeManager.addNode(node);
            dataAccessFolder.addOrganizes(node);
        } catch (UaException e) {
            logger.error("Error creating AnalogItemType instance: {}", e.getMessage(), e);
        }
    }

    private void addSqrtMethod(UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(server)
            .setNodeId(new NodeId(namespaceIndex, "HelloWorld/sqrt(x)"))
            .setBrowseName(new QualifiedName(namespaceIndex, "sqrt(x)"))
            .setDisplayName(new LocalizedText(null, "sqrt(x)"))
            .setDescription(
                LocalizedText.english("Returns the correctly rounded positive square root of a double value."))
            .build();

        SqrtMethod sqrtMethod = new SqrtMethod(methodNode);
        methodNode.setProperty(UaMethodNode.InputArguments, sqrtMethod.getInputArguments());
        methodNode.setProperty(UaMethodNode.OutputArguments, sqrtMethod.getOutputArguments());
        methodNode.setInvocationHandler(sqrtMethod);

        nodeManager.addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            folderNode.getNodeId().expanded(),
            folderNode.getNodeClass(),
            false
        ));
    }

    private void addGenerateEventMethod(UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(server)
            .setNodeId(new NodeId(namespaceIndex, "HelloWorld/generateEvent(eventTypeId)"))
            .setBrowseName(new QualifiedName(namespaceIndex, "generateEvent(eventTypeId)"))
            .setDisplayName(new LocalizedText(null, "generateEvent(eventTypeId)"))
            .setDescription(
                LocalizedText.english("Generate an Event with the TypeDefinition indicated by eventTypeId."))
            .build();

        GenerateEventMethod generateEventMethod = new GenerateEventMethod(methodNode);
        methodNode.setProperty(UaMethodNode.InputArguments, generateEventMethod.getInputArguments());
        methodNode.setProperty(UaMethodNode.OutputArguments, generateEventMethod.getOutputArguments());
        methodNode.setInvocationHandler(generateEventMethod);

        nodeManager.addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            folderNode.getNodeId().expanded(),
            folderNode.getNodeClass(),
            false
        ));
    }

    private void addCustomObjectTypeAndInstance(UaFolderNode rootFolder) {
        // Define a new ObjectType called "MyObjectType".
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(server)
            .setNodeId(new NodeId(namespaceIndex, "ObjectTypes/MyObjectType"))
            .setBrowseName(new QualifiedName(namespaceIndex, "MyObjectType"))
            .setDisplayName(LocalizedText.english("MyObjectType"))
            .setIsAbstract(false)
            .build();

        // "Foo" and "Bar" are members. These nodes are what are called "instance declarations" by the spec.
        UaVariableNode foo = UaVariableNode.builder(server)
            .setNodeId(new NodeId(namespaceIndex, "ObjectTypes/MyObjectType.Foo"))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(new QualifiedName(namespaceIndex, "Foo"))
            .setDisplayName(LocalizedText.english("Foo"))
            .setDataType(Identifiers.Int16)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        foo.addReference(new Reference(
            foo.getNodeId(),
            Identifiers.HasModellingRule,
            Identifiers.ModellingRule_Mandatory.expanded(),
            true
        ));

        foo.setValue(new DataValue(new Variant(0)));
        objectTypeNode.addComponent(foo);

        UaVariableNode bar = UaVariableNode.builder(server)
            .setNodeId(new NodeId(namespaceIndex, "ObjectTypes/MyObjectType.Bar"))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(new QualifiedName(namespaceIndex, "Bar"))
            .setDisplayName(LocalizedText.english("Bar"))
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        bar.addReference(new Reference(
            bar.getNodeId(),
            Identifiers.HasModellingRule,
            Identifiers.ModellingRule_Mandatory.expanded(),
            true
        ));

        bar.setValue(new DataValue(new Variant("bar")));
        objectTypeNode.addComponent(bar);

        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
        server.getObjectTypeManager().registerObjectType(
            objectTypeNode.getNodeId(),
            UaObjectNode.class,
            UaObjectNode::new
        );

        // Add the inverse SubtypeOf relationship.
        objectTypeNode.addReference(new Reference(
            objectTypeNode.getNodeId(),
            Identifiers.HasSubtype,
            Identifiers.BaseObjectType.expanded(),
            false
        ));

        // Add type definition and declarations to address space.
        nodeManager.addNode(objectTypeNode);
        nodeManager.addNode(foo);
        nodeManager.addNode(bar);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        try {
            UaObjectNode myObject = (UaObjectNode) nodeFactory.createNode(
                new NodeId(namespaceIndex, "HelloWorld/MyObject"),
                objectTypeNode.getNodeId(),
                false
            );
            myObject.setBrowseName(new QualifiedName(namespaceIndex, "MyObject"));
            myObject.setDisplayName(LocalizedText.english("MyObject"));

            // Add forward and inverse references from the root folder.
            rootFolder.addOrganizes(myObject);

            myObject.addReference(new Reference(
                myObject.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                false
            ));
        } catch (UaException e) {
            logger.error("Error creating MyObjectType instance: {}", e.getMessage(), e);
        }
    }

    private void addCustomDataTypeVariable(UaFolderNode rootFolder) {
        // add a custom DataTypeNode as a subtype of the built-in Structure DataTypeNode
        NodeId dataTypeId = new NodeId(namespaceIndex, "DataType.CustomDataType");

        UaDataTypeNode dataTypeNode = new UaDataTypeNode(
            server,
            dataTypeId,
            new QualifiedName(namespaceIndex, "CustomDataType"),
            LocalizedText.english("CustomDataType"),
            LocalizedText.english("CustomDataType"),
            uint(0),
            uint(0),
            false
        );

        nodeManager.addNode(dataTypeNode);

        // Inverse ref to Structure
        dataTypeNode.addReference(new Reference(
            dataTypeId,
            Identifiers.HasSubtype,
            Identifiers.Structure.expanded(),
            false
        ));

        // Forward ref from Structure
        Optional<UaDataTypeNode> structureDataTypeNode = nodeManager
            .getNode(Identifiers.Structure)
            .map(UaDataTypeNode.class::cast);

        structureDataTypeNode.ifPresent(node ->
            node.addReference(new Reference(
                node.getNodeId(),
                Identifiers.HasSubtype,
                dataTypeId.expanded(),
                true
            ))
        );

        // Create a dictionary, binaryEncodingId, and register the codec under that id
        OpcUaBinaryDataTypeDictionary dictionary = new OpcUaBinaryDataTypeDictionary(
            "urn:eclipse:milo:example:custom-data-type"
        );

        NodeId binaryEncodingId = new NodeId(namespaceIndex, "DataType.CustomDataType.BinaryEncoding");

        dictionary.registerStructCodec(
            new CustomDataType.Codec().asBinaryCodec(),
            "CustomDataType",
            binaryEncodingId
        );

        // Register dictionary with the shared DataTypeManager instance
        OpcUaDataTypeManager.getInstance().registerTypeDictionary(dictionary);


        UaVariableNode customDataTypeVariable = UaVariableNode.builder(server)
            .setNodeId(new NodeId(namespaceIndex, "HelloWorld/CustomDataTypeVariable"))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(new QualifiedName(namespaceIndex, "CustomDataTypeVariable"))
            .setDisplayName(LocalizedText.english("CustomDataTypeVariable"))
            .setDataType(dataTypeId)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        nodeManager.addNode(customDataTypeVariable);

        CustomDataType value = new CustomDataType(
            "foo",
            uint(42),
            true
        );

        ExtensionObject xo = ExtensionObject.encodeDefaultBinary(
            value, binaryEncodingId, OpcUaDataTypeManager.getInstance());

        customDataTypeVariable.setValue(new DataValue(new Variant(xo)));

        rootFolder.addOrganizes(customDataTypeVariable);

        customDataTypeVariable.addReference(new Reference(
            customDataTypeVariable.getNodeId(),
            Identifiers.Organizes,
            rootFolder.getNodeId().expanded(),
            false
        ));
    }

    @Override
    public CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId) {
        UaServerNode node = nodeManager.get(nodeId);

        if (node != null) {
            return CompletableFuture.completedFuture(node.getReferences());
        } else {
            return FutureUtils.failedFuture(new UaException(StatusCodes.Bad_NodeIdUnknown));
        }
    }

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds) {

        List<DataValue> results = Lists.newArrayListWithCapacity(readValueIds.size());

        for (ReadValueId readValueId : readValueIds) {
            UaServerNode node = nodeManager.get(readValueId.getNodeId());

            if (node != null) {
                DataValue value = node.readAttribute(
                    new AttributeContext(context),
                    readValueId.getAttributeId(),
                    timestamps,
                    readValueId.getIndexRange(),
                    readValueId.getDataEncoding()
                );

                results.add(value);
            } else {
                results.add(new DataValue(StatusCodes.Bad_NodeIdUnknown));
            }
        }

        context.complete(results);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        List<StatusCode> results = Lists.newArrayListWithCapacity(writeValues.size());

        for (WriteValue writeValue : writeValues) {
            UaServerNode node = nodeManager.get(writeValue.getNodeId());

            if (node != null) {
                try {
                    node.writeAttribute(
                        new AttributeContext(context),
                        writeValue.getAttributeId(),
                        writeValue.getValue(),
                        writeValue.getIndexRange()
                    );

                    results.add(StatusCode.GOOD);

                    logger.info(
                        "Wrote value {} to {} attribute of {}",
                        writeValue.getValue().getValue(),
                        AttributeId.from(writeValue.getAttributeId()).map(Object::toString).orElse("unknown"),
                        node.getNodeId());
                } catch (UaException e) {
                    logger.error("Unable to write value={}", writeValue.getValue(), e);
                    results.add(e.getStatusCode());
                }
            } else {
                results.add(new StatusCode(StatusCodes.Bad_NodeIdUnknown));
            }
        }

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

}
