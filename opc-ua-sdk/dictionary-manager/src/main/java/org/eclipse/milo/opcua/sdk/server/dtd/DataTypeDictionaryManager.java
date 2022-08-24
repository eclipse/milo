/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.dtd;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import org.eclipse.milo.opcua.binaryschema.generator.DataTypeDictionaryGenerator;
import org.eclipse.milo.opcua.binaryschema.generator.DataTypeDictionaryGenerator.DataTypeLocation;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.Reference.Direction;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDescriptionTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDictionaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.DataTypeDictionaryType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDescription;
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class DataTypeDictionaryManager implements Lifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<UaNode> nodes = new CopyOnWriteArrayList<>();

    private final Lazy<File> dictionaryFile = new Lazy<>();

    private final Map<NodeId, EnumDescription> enumDescriptions = new ConcurrentHashMap<>();
    private final Map<NodeId, StructureDescription> structureDescriptions = new ConcurrentHashMap<>();

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
    public DataTypeDictionaryManager(UaNodeContext context, String namespaceUri) {
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
            uint(0),
            null,
            null,
            null,
            new DataValue(Variant.NULL_VALUE),
            Identifiers.BaseDataType,
            ValueRanks.Scalar,
            null
        );

        dictionaryNode.setNamespaceUri(namespaceUri);

        dictionaryNode.getFilterChain().addLast(AttributeFilters.getValue(context -> {
            try {
                File file = dictionaryFile.getOrCompute(() -> {
                    try {
                        return writeDictionaryToFile();
                    } catch (IOException e) {
                        throw new RuntimeException("failed to write dictionary file", e);
                    }
                });

                assert file != null;

                try {
                    byte[] bs = Files.readAllBytes(file.toPath());
                    return new DataValue(new Variant(ByteString.of(bs)));
                } catch (IOException e) {
                    logger.warn("Failed to read dictionary file", e);

                    dictionaryFile.reset();

                    byte[] bs = writeDictionaryToMemory();
                    return new DataValue(new Variant(ByteString.of(bs)));
                }
            } catch (Throwable t) {
                logger.warn("Failed to write dictionary file", t);

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

        addNode(dictionaryNode);
    }

    @Override
    public void shutdown() {
        dictionaryNode.delete();
        nodes.forEach(UaNode::delete);
        nodes.clear();
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

        addNode(dataTypeNode);

        // TODO figure out a way to not require re-registration every time...
        getNodeContext().getServer().getDataTypeManager().registerTypeDictionary(dictionary);
    }

    public void registerEnumDescription(EnumDescription description) {
        enumDescriptions.put(description.getDataTypeId(), description);

        // Note: enumerations don't need DataTypeDescription or DataTypeEncoding nodes.

        dictionaryFile.reset();
    }

    public void registerOptionSetCodec(
        OpcUaBinaryDataTypeCodec<?> codec,
        String description,
        NodeId dataTypeId,
        NodeId binaryEncodingId
    ) {

        registerStructureCodec(codec, description, dataTypeId, binaryEncodingId, Identifiers.OptionSet);
    }

    public void registerStructureCodec(
        OpcUaBinaryDataTypeCodec<?> codec,
        String description,
        NodeId dataTypeId,
        NodeId binaryEncodingId
    ) {

        registerStructureCodec(codec, description, dataTypeId, binaryEncodingId, Identifiers.Structure);
    }

    public void registerUnionCodec(
        OpcUaBinaryDataTypeCodec<?> codec,
        String description,
        NodeId dataTypeId,
        NodeId binaryEncodingId
    ) {

        registerStructureCodec(codec, description, dataTypeId, binaryEncodingId, Identifiers.Union);
    }

    public void registerStructureCodec(
        OpcUaBinaryDataTypeCodec<?> codec,
        String description,
        NodeId dataTypeId,
        NodeId binaryEncodingId,
        NodeId parentType
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
            parentType.expanded(),
            Direction.INVERSE
        ));

        addNode(dataTypeNode);

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
            uint(0),
            null,
            null,
            null,
            new DataValue(Variant.NULL_VALUE),
            Identifiers.BaseDataType,
            ValueRanks.Scalar,
            null
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

        addNode(descriptionNode);

        // Add a DataTypeEncodingTypeNode with a HasDescription reference to
        // descriptionNode and an EncodingOf reference to the DataTypeNode.

        DataTypeEncodingTypeNode dataTypeEncodingNode = new DataTypeEncodingTypeNode(
            getNodeContext(),
            binaryEncodingId,
            new QualifiedName(0, "Default Binary"),
            LocalizedText.english("Default Binary"),
            LocalizedText.NULL_VALUE,
            uint(0),
            uint(0),
            null,
            null,
            null
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

        addNode(dataTypeEncodingNode);

        dictionaryFile.reset();
    }

    private File writeDictionaryToFile() throws IOException {
        String encodedUri = URLEncoder.encode(namespaceUri, StandardCharsets.UTF_8.name());
        Path tempFilePath = Files.createTempFile(encodedUri, ".bsd.xml");

        try (FileOutputStream fos = new FileOutputStream(tempFilePath.toFile())) {
            writeDictionaryToStream(fos);

            logger.info("Wrote dictionary for '{}' to {}", namespaceUri, tempFilePath);
        }

        return tempFilePath.toFile();
    }

    private byte[] writeDictionaryToMemory() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        writeDictionaryToStream(baos);

        return baos.toByteArray();
    }

    private void writeDictionaryToStream(OutputStream outputStream) throws IOException {
        DataTypeDictionaryGenerator generator = newDictionaryGenerator(
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

    /**
     * Add {@code node} to the {@link UaNodeManager} and our own bookkeeping so it can be deleted during shutdown.
     *
     * @param node the {@link UaNode} to add.
     */
    private void addNode(UaNode node) {
        getNodeManager().addNode(node);
        nodes.add(node);
    }

    private static DataTypeDictionaryGenerator newDictionaryGenerator(
        String namespaceUri,
        AddressSpaceManager addressSpaceManager
    ) {

        Function<NodeId, DataTypeLocation> dataTypeLookup = dataTypeId -> {

            UaNode dataTypeNode = addressSpaceManager.getManagedNode(dataTypeId).orElse(null);

            checkNotNull(dataTypeNode, "dataTypeNode for dataTypeId=" + dataTypeId);

            if (dataTypeId.getNamespaceIndex().intValue() == 0) {
                long id = ((UInteger) dataTypeId.getIdentifier()).longValue();
                String uri = id <= 15L ? Namespaces.OPC_UA_BSD : Namespaces.OPC_UA;
                return new DataTypeLocation(dataTypeNode.getBrowseName().getName(), uri);
            }

            QualifiedName parentTypeName = dataTypeNode.getReferences()
                .stream()
                .filter(Reference.SUBTYPE_OF)
                .flatMap(r -> opt2stream(addressSpaceManager.getManagedNode(r.getTargetNodeId())))
                .findFirst()
                .map(UaNode::getBrowseName)
                .orElse(QualifiedName.NULL_VALUE);

            if (parentTypeName.equals(new QualifiedName(0, "Enumeration"))) {
                return new DataTypeLocation(dataTypeNode.getBrowseName().getName(), "");
            }

            UaNode dataTypeEncodingNode = dataTypeNode.getReferences()
                .stream()
                .filter(Reference.HAS_ENCODING_PREDICATE)
                .flatMap(r -> opt2stream(addressSpaceManager.getManagedNode(r.getTargetNodeId())))
                .filter(n -> n.getBrowseName().equals(new QualifiedName(0, "Default Binary")))
                .findFirst()
                .orElse(null);

            checkNotNull(dataTypeEncodingNode, "dataTypeEncodingNode for dataTypeId=" + dataTypeId);

            UaNode dataTypeDescriptionNode = dataTypeEncodingNode.getReferences()
                .stream()
                .filter(Reference.HAS_DESCRIPTION_PREDICATE)
                .flatMap(r -> opt2stream(addressSpaceManager.getManagedNode(r.getTargetNodeId())))
                .findFirst()
                .orElse(null);

            checkNotNull(dataTypeDescriptionNode, "dataTypeDescriptionNode for dataTypeId=" + dataTypeId);

            String dataTypeName = dataTypeDescriptionNode.getBrowseName().getName();

            UaNode dictionaryNode = dataTypeDescriptionNode.getReferences().stream()
                .filter(Reference.COMPONENT_OF_PREDICATE)
                .flatMap(r -> opt2stream(addressSpaceManager.getManagedNode(r.getTargetNodeId())))
                .findFirst()
                .orElse(null);

            checkNotNull(dictionaryNode, "dictionaryNode for dataTypeId=" + dataTypeId);

            String dictionaryNamespaceUri = dictionaryNode.getProperty(DataTypeDictionaryType.NAMESPACE_URI)
                .orElse(null);

            checkNotNull(dictionaryNamespaceUri, "dictionaryNamespaceUri for dataTypeId=" + dataTypeId);

            return new DataTypeLocation(dataTypeName, dictionaryNamespaceUri);
        };

        return new DataTypeDictionaryGenerator(namespaceUri, dataTypeLookup);
    }

}
