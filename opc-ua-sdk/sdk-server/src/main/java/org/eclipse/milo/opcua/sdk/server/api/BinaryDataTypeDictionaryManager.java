/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.Map;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDescriptionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDictionaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.future.EnumDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureDescription;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class BinaryDataTypeDictionaryManager implements Lifecycle {

    private final Map<NodeId, EnumDescription> enumDescriptions = Maps.newConcurrentMap();
    private final Map<NodeId, StructureDescription> structureDescriptions = Maps.newConcurrentMap();

    private final OpcUaBinaryDataTypeDictionary dictionary;

    private DataTypeDictionaryTypeNode dictionaryNode;

    private final UaNodeContext context;
    private final String namespaceUri;

    public BinaryDataTypeDictionaryManager(UaNodeContext context, String namespaceUri) {
        this.context = context;
        this.namespaceUri = namespaceUri;

        dictionary = new OpcUaBinaryDataTypeDictionary(namespaceUri);
    }

    private UaNodeContext getNodeContext() {
        return context;
    }

    private UaNodeManager getNodeManager() {
        return (UaNodeManager) getNodeContext().getNodeManager();
    }

    @Override
    public void startup() {
        getNodeContext().getServer().getDataTypeManager().registerTypeDictionary(dictionary);

        // Add a DataTypeDictionary Node...
        dictionaryNode = new DataTypeDictionaryTypeNode(
            getNodeContext(),
            newNodeId("DataTypeDictionary:" + namespaceUri),
            newQualifiedName("DataTypeDictionary:" + namespaceUri),
            LocalizedText.english("DataTypeDictionary:" + namespaceUri),
            LocalizedText.NULL_VALUE,
            uint(0),
            uint(0)
        );

        dictionaryNode.setNamespaceUri(namespaceUri);

        dictionaryNode.addReference(new Reference(
            dictionaryNode.getNodeId(),
            Identifiers.HasTypeDefinition,
            Identifiers.DataTypeDictionaryType.expanded(),
            Reference.Direction.FORWARD
        ));

        dictionaryNode.addReference(new Reference(
            dictionaryNode.getNodeId(),
            Identifiers.HasComponent,
            Identifiers.OPCBinarySchema_TypeSystem.expanded(),
            Reference.Direction.INVERSE
        ));

        getNodeManager().addNode(dictionaryNode);
    }

    @Override
    public void shutdown() {
        // TODO remove all the nodes we added

        dictionaryNode.delete();
    }

    public OpcUaBinaryDataTypeDictionary getDictionary() {
        return dictionary;
    }

    public void registerStructureCodec(
        OpcUaBinaryDataTypeCodec<?> codec,
        String description,
        NodeId dataTypeId,
        NodeId binaryEncodingId
    ) {

        dictionary.registerStructCodec(codec, description, dataTypeId, binaryEncodingId);

        // Add a custom DataTypeNode with a SubtypeOf reference to Structure

        UaDataTypeNode dataTypeNode = new UaDataTypeNode(
            getNodeContext(),
            dataTypeId,
            newQualifiedName(description),
            LocalizedText.english(description),
            LocalizedText.NULL_VALUE,
            uint(0),
            uint(0),
            false
        );

        dataTypeNode.addReference(new Reference(
            dataTypeId,
            Identifiers.HasSubtype,
            Identifiers.Structure.expanded(),
            Reference.Direction.INVERSE
        ));

        getNodeManager().addNode(dataTypeNode);

        // TODO figure out a way to not require re-registration every time...
        getNodeContext().getServer().getDataTypeManager().registerTypeDictionary(dictionary);
    }

    public void registerStructureDescription(StructureDescription description, NodeId binaryEncodingId) {
        structureDescriptions.put(description.getDataTypeId(), description);

        // Add a DataTypeDescriptionTypeNode with a ComponentOf reference to
        // dictionaryNode.

        DataTypeDescriptionTypeNode descriptionNode = new DataTypeDescriptionTypeNode(
            getNodeContext(),
            newNodeId(String.format("%s.Description", description.getName())),
            newQualifiedName(description.getName().getName()),
            LocalizedText.english(description.getName().getName()),
            LocalizedText.NULL_VALUE,
            uint(0),
            uint(0)
        );

        descriptionNode.setValue(new DataValue(new Variant(description.getName().getName())));
        descriptionNode.setDataType(Identifiers.String);

        descriptionNode.addReference(new Reference(
            descriptionNode.getNodeId(),
            Identifiers.HasTypeDefinition,
            Identifiers.DataTypeDescriptionType.expanded(),
            Reference.Direction.FORWARD
        ));

        descriptionNode.addReference(new Reference(
            descriptionNode.getNodeId(),
            Identifiers.HasComponent,
            dictionaryNode.getNodeId().expanded(),
            Reference.Direction.INVERSE
        ));

        getNodeManager().addNode(descriptionNode);

        // Add a DataTypeEncodingTypeNode with a HasDescription reference to
        // descriptionNode and an EncodingOf reference to the DataTypeNode.

        DataTypeEncodingTypeNode dataTypeEncodingNode = new DataTypeEncodingTypeNode(
            getNodeContext(),
            binaryEncodingId,
            new QualifiedName(0, "Default Binary"),
            LocalizedText.english("Default Binary"),
            LocalizedText.NULL_VALUE,
            uint(0),
            uint(0)
        );

        dataTypeEncodingNode.addReference(new Reference(
            dataTypeEncodingNode.getNodeId(),
            Identifiers.HasTypeDefinition,
            Identifiers.DataTypeEncodingType.expanded(),
            Reference.Direction.FORWARD
        ));

        dataTypeEncodingNode.addReference(new Reference(
            dataTypeEncodingNode.getNodeId(),
            Identifiers.HasDescription,
            descriptionNode.getNodeId().expanded(),
            Reference.Direction.FORWARD
        ));

        dataTypeEncodingNode.addReference(new Reference(
            dataTypeEncodingNode.getNodeId(),
            Identifiers.HasEncoding,
            description.getDataTypeId().expanded(),
            Reference.Direction.INVERSE
        ));

        getNodeManager().addNode(dataTypeEncodingNode);
    }

    private UShort getNamespaceIndex() {
        return getNodeContext().getNamespaceTable().getIndex(namespaceUri);
    }

    private NodeId newNodeId(String id) {
        return new NodeId(getNamespaceIndex(), id);
    }

    private QualifiedName newQualifiedName(String name) {
        return new QualifiedName(getNamespaceIndex(), name);
    }

}
