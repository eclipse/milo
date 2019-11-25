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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.Reference.Direction;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDescriptionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDictionaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
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

    /**
     * Create a BinaryDataTypeDictionaryManager for an {@link OpcUaBinaryDataTypeDictionary} in {@code namespaceUri}.
     * <p>
     * Note that the namespace URI is that of the dictionary, and is not necessarily the same as the namespace the
     * Nodes created by the manager will reside in, i.e. datatype dictionaries are namespaced independently from the
     * namespaces server Nodes reside in.
     *
     * @param context      a {@link UaNodeContext}. Nodes will be created and added using this context.
     * @param namespaceUri the namespace URI of the dictionary.
     */
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
            newNodeId(namespaceUri),
            newQualifiedName(namespaceUri),
            LocalizedText.english(namespaceUri),
            LocalizedText.english("DataTypeDictionary for " + namespaceUri),
            uint(0),
            uint(0)
        );

        dictionaryNode.setNamespaceUri(namespaceUri);

        dictionaryNode.getFilterChain().addLast(AttributeFilters.getValue(context -> {
            // TODO generate BSD file / read from cached BSD file
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream(new File("/Users/kevin/Desktop/bsd-test.xml"));

                ByteStreams.copy(fis, baos);

                return new DataValue(new Variant(ByteString.of(baos.toByteArray())));
            } catch (IOException e) {
                return new DataValue(new Variant(ByteString.NULL_VALUE));
            }
        }));

        dictionaryNode.addReference(new Reference(
            dictionaryNode.getNodeId(),
            Identifiers.HasTypeDefinition,
            Identifiers.DataTypeDictionaryType.expanded(),
            Direction.FORWARD
        ));

        dictionaryNode.addReference(new Reference(
            dictionaryNode.getNodeId(),
            Identifiers.HasComponent,
            Identifiers.OPCBinarySchema_TypeSystem.expanded(),
            Direction.INVERSE
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
            Direction.INVERSE
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
            Direction.FORWARD
        ));

        descriptionNode.addReference(new Reference(
            descriptionNode.getNodeId(),
            Identifiers.HasComponent,
            dictionaryNode.getNodeId().expanded(),
            Direction.INVERSE
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
            Direction.FORWARD
        ));

        dataTypeEncodingNode.addReference(new Reference(
            dataTypeEncodingNode.getNodeId(),
            Identifiers.HasDescription,
            descriptionNode.getNodeId().expanded(),
            Direction.FORWARD
        ));

        dataTypeEncodingNode.addReference(new Reference(
            dataTypeEncodingNode.getNodeId(),
            Identifiers.HasEncoding,
            description.getDataTypeId().expanded(),
            Direction.INVERSE
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
