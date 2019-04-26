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
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.examples.server.methods.GenerateEventMethod;
import org.eclipse.milo.examples.server.methods.SqrtMethod;
import org.eclipse.milo.examples.server.types.CustomDataType;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
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
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegateChain;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class ExampleNamespace extends ManagedNamespace {

    static final String NAMESPACE_URI = "urn:eclipse:milo:hello-world";

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

    private final SubscriptionModel subscriptionModel;

    ExampleNamespace(OpcUaServer server) {
        super(server, NAMESPACE_URI);

        subscriptionModel = new SubscriptionModel(server, this);
    }

    @Override
    protected void onStartup() {
        super.onStartup();

        // Create a "HelloWorld" folder and add it to the node manager
        NodeId folderNodeId = newNodeId("HelloWorld");

        UaFolderNode folderNode = new UaFolderNode(
            getNodeContext(),
            folderNodeId,
            newQualifiedName("HelloWorld"),
            LocalizedText.english("HelloWorld")
        );

        getNodeManager().addNode(folderNode);

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
        UaNode serverNode = getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server)
            .orElse(null);

        if (serverNode instanceof ServerNode) {
            ((ServerNode) serverNode).setEventNotifier(ubyte(1));

            // Post a bogus Event every couple seconds
            getServer().getScheduledExecutorService().scheduleAtFixedRate(() -> {
                try {
                    BaseEventNode eventNode = getServer().getEventFactory().createEvent(
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

                    getServer().getEventBus().post(eventNode);

                    eventNode.delete();
                } catch (Throwable e) {
                    logger.error("Error creating EventNode: {}", e.getMessage(), e);
                }
            }, 0, 2, TimeUnit.SECONDS);
        }
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
            getNodeContext(),
            newNodeId("HelloWorld/ArrayTypes"),
            newQualifiedName("ArrayTypes"),
            LocalizedText.english("ArrayTypes")
        );

        getNodeManager().addNode(arrayTypesFolder);
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

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(newNodeId("HelloWorld/ArrayTypes/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(newQualifiedName(name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .setValueRank(ValueRank.OneDimension.getValue())
                .setArrayDimensions(new UInteger[]{uint(0)})
                .build();

            node.setValue(new DataValue(variant));

            node.setAttributeDelegate(new ValueLoggingDelegate());

            getNodeManager().addNode(node);
            arrayTypesFolder.addOrganizes(node);
        }
    }

    private void addScalarNodes(UaFolderNode rootNode) {
        UaFolderNode scalarTypesFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId("HelloWorld/ScalarTypes"),
            newQualifiedName("ScalarTypes"),
            LocalizedText.english("ScalarTypes")
        );

        getNodeManager().addNode(scalarTypesFolder);
        rootNode.addOrganizes(scalarTypesFolder);

        for (Object[] os : STATIC_SCALAR_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(newNodeId("HelloWorld/ScalarTypes/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(newQualifiedName(name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

            node.setValue(new DataValue(variant));

            node.setAttributeDelegate(new ValueLoggingDelegate());

            getNodeManager().addNode(node);
            scalarTypesFolder.addOrganizes(node);
        }
    }

    private void addWriteOnlyNodes(UaFolderNode rootNode) {
        UaFolderNode writeOnlyFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId("HelloWorld/WriteOnly"),
            newQualifiedName("WriteOnly"),
            LocalizedText.english("WriteOnly")
        );

        getNodeManager().addNode(writeOnlyFolder);
        rootNode.addOrganizes(writeOnlyFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
            .setNodeId(newNodeId("HelloWorld/WriteOnly/" + name))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY)))
            .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY)))
            .setBrowseName(newQualifiedName(name))
            .setDisplayName(LocalizedText.english(name))
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        node.setValue(new DataValue(new Variant("can't read this")));

        getNodeManager().addNode(node);
        writeOnlyFolder.addOrganizes(node);
    }

    private void addAdminReadableNodes(UaFolderNode rootNode) {
        UaFolderNode adminFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId("HelloWorld/OnlyAdminCanRead"),
            newQualifiedName("OnlyAdminCanRead"),
            LocalizedText.english("OnlyAdminCanRead")
        );

        getNodeManager().addNode(adminFolder);
        rootNode.addOrganizes(adminFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
            .setNodeId(newNodeId("HelloWorld/OnlyAdminCanRead/" + name))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(newQualifiedName(name))
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

        getNodeManager().addNode(node);
        adminFolder.addOrganizes(node);
    }

    private void addAdminWritableNodes(UaFolderNode rootNode) {
        UaFolderNode adminFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId("HelloWorld/OnlyAdminCanWrite"),
            newQualifiedName("OnlyAdminCanWrite"),
            LocalizedText.english("OnlyAdminCanWrite")
        );

        getNodeManager().addNode(adminFolder);
        rootNode.addOrganizes(adminFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
            .setNodeId(newNodeId("HelloWorld/OnlyAdminCanWrite/" + name))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(newQualifiedName(name))
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

        getNodeManager().addNode(node);
        adminFolder.addOrganizes(node);
    }

    private void addDynamicNodes(UaFolderNode rootNode) {
        UaFolderNode dynamicFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId("HelloWorld/Dynamic"),
            newQualifiedName("Dynamic"),
            LocalizedText.english("Dynamic")
        );

        getNodeManager().addNode(dynamicFolder);
        rootNode.addOrganizes(dynamicFolder);

        // Dynamic Boolean
        {
            String name = "Boolean";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(newNodeId("HelloWorld/Dynamic/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(newQualifiedName(name))
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

            getNodeManager().addNode(node);
            dynamicFolder.addOrganizes(node);
        }

        // Dynamic Int32
        {
            String name = "Int32";
            NodeId typeId = Identifiers.Int32;
            Variant variant = new Variant(0);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(newNodeId("HelloWorld/Dynamic/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(newQualifiedName(name))
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

            getNodeManager().addNode(node);
            dynamicFolder.addOrganizes(node);
        }

        // Dynamic Double
        {
            String name = "Double";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0.0);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(newNodeId("HelloWorld/Dynamic/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(newQualifiedName(name))
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

            getNodeManager().addNode(node);
            dynamicFolder.addOrganizes(node);
        }
    }

    private void addDataAccessNodes(UaFolderNode rootNode) {
        // DataAccess folder
        UaFolderNode dataAccessFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId("HelloWorld/DataAccess"),
            newQualifiedName("DataAccess"),
            LocalizedText.english("DataAccess")
        );

        getNodeManager().addNode(dataAccessFolder);
        rootNode.addOrganizes(dataAccessFolder);

        // AnalogItemType node
        try {
            AnalogItemNode node = (AnalogItemNode) getNodeFactory().createNode(
                newNodeId("HelloWorld/DataAccess/AnalogValue"),
                Identifiers.AnalogItemType,
                true
            );

            node.setBrowseName(newQualifiedName("AnalogValue"));
            node.setDisplayName(LocalizedText.english("AnalogValue"));
            node.setDataType(Identifiers.Double);
            node.setValue(new DataValue(new Variant(3.14d)));

            node.setEURange(new Range(0.0, 100.0));

            getNodeManager().addNode(node);
            dataAccessFolder.addOrganizes(node);
        } catch (UaException e) {
            logger.error("Error creating AnalogItemType instance: {}", e.getMessage(), e);
        }
    }

    private void addSqrtMethod(UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newNodeId("HelloWorld/sqrt(x)"))
            .setBrowseName(newQualifiedName("sqrt(x)"))
            .setDisplayName(new LocalizedText(null, "sqrt(x)"))
            .setDescription(
                LocalizedText.english("Returns the correctly rounded positive square root of a double value."))
            .build();

        SqrtMethod sqrtMethod = new SqrtMethod(methodNode);
        methodNode.setProperty(UaMethodNode.InputArguments, sqrtMethod.getInputArguments());
        methodNode.setProperty(UaMethodNode.OutputArguments, sqrtMethod.getOutputArguments());
        methodNode.setInvocationHandler(sqrtMethod);

        getNodeManager().addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            folderNode.getNodeId().expanded(),
            false
        ));
    }

    private void addGenerateEventMethod(UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newNodeId("HelloWorld/generateEvent(eventTypeId)"))
            .setBrowseName(newQualifiedName("generateEvent(eventTypeId)"))
            .setDisplayName(new LocalizedText(null, "generateEvent(eventTypeId)"))
            .setDescription(
                LocalizedText.english("Generate an Event with the TypeDefinition indicated by eventTypeId."))
            .build();

        GenerateEventMethod generateEventMethod = new GenerateEventMethod(methodNode);
        methodNode.setProperty(UaMethodNode.InputArguments, generateEventMethod.getInputArguments());
        methodNode.setProperty(UaMethodNode.OutputArguments, generateEventMethod.getOutputArguments());
        methodNode.setInvocationHandler(generateEventMethod);

        getNodeManager().addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            folderNode.getNodeId().expanded(),
            false
        ));
    }

    private void addCustomObjectTypeAndInstance(UaFolderNode rootFolder) {
        // Define a new ObjectType called "MyObjectType".
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(getNodeContext())
            .setNodeId(newNodeId("ObjectTypes/MyObjectType"))
            .setBrowseName(newQualifiedName("MyObjectType"))
            .setDisplayName(LocalizedText.english("MyObjectType"))
            .setIsAbstract(false)
            .build();

        // "Foo" and "Bar" are members. These nodes are what are called "instance declarations" by the spec.
        UaVariableNode foo = UaVariableNode.builder(getNodeContext())
            .setNodeId(newNodeId("ObjectTypes/MyObjectType.Foo"))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(newQualifiedName("Foo"))
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

        UaVariableNode bar = UaVariableNode.builder(getNodeContext())
            .setNodeId(newNodeId("ObjectTypes/MyObjectType.Bar"))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(newQualifiedName("Bar"))
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
        getServer().getObjectTypeManager().registerObjectType(
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
        getNodeManager().addNode(objectTypeNode);
        getNodeManager().addNode(foo);
        getNodeManager().addNode(bar);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        try {
            UaObjectNode myObject = (UaObjectNode) getNodeFactory().createNode(
                newNodeId("HelloWorld/MyObject"),
                objectTypeNode.getNodeId(),
                false
            );
            myObject.setBrowseName(newQualifiedName("MyObject"));
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
        NodeId dataTypeId = newNodeId("DataType.CustomDataType");

        UaDataTypeNode dataTypeNode = new UaDataTypeNode(
            getNodeContext(),
            dataTypeId,
            newQualifiedName("CustomDataType"),
            LocalizedText.english("CustomDataType"),
            LocalizedText.english("CustomDataType"),
            uint(0),
            uint(0),
            false
        );

        getNodeManager().addNode(dataTypeNode);

        // Inverse ref to Structure
        dataTypeNode.addReference(new Reference(
            dataTypeId,
            Identifiers.HasSubtype,
            Identifiers.Structure.expanded(),
            false
        ));

        // Forward ref from Structure
        Optional<UaDataTypeNode> structureDataTypeNode = getNodeManager()
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

        // TODO this should probably get a node and a HasEncoding reference from dataTypeNode...
        NodeId binaryEncodingId = newNodeId("DataType.CustomDataType.BinaryEncoding");

        // Register codec with the server DataTypeManager instance
        getServer().getDataTypeManager().registerCodec(
            binaryEncodingId,
            new CustomDataType.Codec().asBinaryCodec()
        );

        UaVariableNode customDataTypeVariable = UaVariableNode.builder(getNodeContext())
            .setNodeId(newNodeId("HelloWorld/CustomDataTypeVariable"))
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(newQualifiedName("CustomDataTypeVariable"))
            .setDisplayName(LocalizedText.english("CustomDataTypeVariable"))
            .setDataType(dataTypeId)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        getNodeManager().addNode(customDataTypeVariable);

        CustomDataType value = new CustomDataType(
            "foo",
            uint(42),
            true
        );

        ExtensionObject xo = ExtensionObject.encodeDefaultBinary(
            getServer().getSerializationContext(),
            value,
            binaryEncodingId
        );

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
