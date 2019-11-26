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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import javax.xml.bind.JAXBException;

import com.google.common.collect.Maps;
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
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class BinaryDataTypeDictionaryManager implements Lifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Lazy<File> dictionaryFile = new Lazy<>();

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
            try {
                File file = dictionaryFile.getOrCompute(this::writeDictionaryToFile);
                assert file != null;

                try {
                    byte[] bs = Files.readAllBytes(file.toPath());
                    return new DataValue(new Variant(ByteString.of(bs)));
                } catch (IOException e) {
                    dictionaryFile.reset();

                    byte[] bs = writeDictionaryToMemory();
                    return new DataValue(new Variant(ByteString.of(bs)));
                }
            } catch (Throwable t) {
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

    public void registerEnumCodec(OpcUaBinaryDataTypeCodec<?> codec, String description, NodeId dataTypeId) {
        dictionary.registerEnumCodec(codec, description, dataTypeId);

        // Add a custom DataTypeNode with a SubtypeOf reference to Enumeration

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
            Identifiers.Enumeration.expanded(),
            Direction.INVERSE
        ));

        getNodeManager().addNode(dataTypeNode);

        // TODO figure out a way to not require re-registration every time...
        getNodeContext().getServer().getDataTypeManager().registerTypeDictionary(dictionary);
    }

    public void registerEnumDescription(EnumDescription description) {
        enumDescriptions.put(description.getDataTypeId(), description);

        // Note: enumerations don't need DataTypeDescription or DataTypeEncoding nodes.

        dictionaryFile.reset();
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

        dictionaryFile.reset();
    }

    private File writeDictionaryToFile() {
        try {
            String encodedUri = URLEncoder.encode(namespaceUri, StandardCharsets.UTF_8.name());
            Path tempFilePath = Files.createTempFile(encodedUri, ".bsd.xml");

            try (FileOutputStream fos = new FileOutputStream(tempFilePath.toFile())) {
                writeDictionaryToStream(fos);

                logger.info("Wrote dictionary for '{}' to {}", namespaceUri, tempFilePath);
            }

            return tempFilePath.toFile();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] writeDictionaryToMemory() throws JAXBException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        writeDictionaryToStream(baos);

        return baos.toByteArray();
    }

    private void writeDictionaryToStream(OutputStream outputStream) throws JAXBException {
        BinaryDataTypeDictionaryGenerator generator = BinaryDataTypeDictionaryGenerator.newInstance(
            namespaceUri,
            getNodeContext().getServer().getAddressSpaceManager()
        );

        enumDescriptions.values().forEach(description -> {
            try {
                generator.addEnumDescription(description);
            } catch (Throwable t) {
                logger.warn("Failed to add EnumDescription: " + description.getName(), t);
            }
        });

        structureDescriptions.values().forEach(description -> {
            try {
                generator.addStructureDescription(description);
            } catch (Throwable t) {
                logger.warn("Failed to add StructureDescription: " + description.getName(), t);
            }
        });

        generator.writeToOutputStream(outputStream);
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
