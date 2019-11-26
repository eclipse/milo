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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.DataTypeDictionaryType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.future.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.future.EnumDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.future.EnumField;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureField;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.opcfoundation.opcua.binaryschema.ByteOrder;
import org.opcfoundation.opcua.binaryschema.EnumeratedType;
import org.opcfoundation.opcua.binaryschema.EnumeratedValue;
import org.opcfoundation.opcua.binaryschema.FieldType;
import org.opcfoundation.opcua.binaryschema.ImportDirective;
import org.opcfoundation.opcua.binaryschema.ObjectFactory;
import org.opcfoundation.opcua.binaryschema.StructuredType;
import org.opcfoundation.opcua.binaryschema.TypeDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

class BinaryDataTypeDictionaryGenerator {

    static BinaryDataTypeDictionaryGenerator newInstance(
        String namespaceUri,
        AddressSpaceManager addressSpaceManager
    ) {

        Function<NodeId, FullyQualifiedDataType> dataTypeLookup = dataTypeId -> {
            String dataTypeName;
            String dictionaryNamespaceUri;

            UaNode dataTypeNode = addressSpaceManager.getManagedNode(dataTypeId).orElse(null);

            checkNotNull(dataTypeNode, "dataTypeNode for dataTypeId=" + dataTypeId);

            if (dataTypeId.getNamespaceIndex().intValue() == 0) {
                return new FullyQualifiedDataType(
                    dataTypeId,
                    dataTypeNode.getBrowseName().getName(),
                    Namespaces.OPC_UA_BSD
                );
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

            dataTypeName = dataTypeDescriptionNode.getBrowseName().getName();

            UaNode dictionaryNode = dataTypeDescriptionNode.getReferences().stream()
                .filter(Reference.COMPONENT_OF_PREDICATE)
                .flatMap(r -> opt2stream(addressSpaceManager.getManagedNode(r.getTargetNodeId())))
                .findFirst()
                .orElse(null);

            checkNotNull(dictionaryNode, "dictionaryNode for dataTypeId=" + dataTypeId);

            dictionaryNamespaceUri = dictionaryNode.getProperty(DataTypeDictionaryType.NAMESPACE_URI).orElse(null);

            checkNotNull(dictionaryNamespaceUri, "dictionaryNamespaceUri for dataTypeId=" + dataTypeId);

            return new FullyQualifiedDataType(dataTypeId, dataTypeName, dictionaryNamespaceUri);
        };

        return new BinaryDataTypeDictionaryGenerator(namespaceUri, dataTypeLookup);
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<String> namespaces = new HashSet<>();
    private final List<EnumeratedType> enumeratedTypes = new ArrayList<>();
    private final List<StructuredType> structuredTypes = new ArrayList<>();

    private final String namespaceUri;
    private final Function<NodeId, FullyQualifiedDataType> dataTypeLookup;

    private BinaryDataTypeDictionaryGenerator(
        String namespaceUri,
        Function<NodeId, FullyQualifiedDataType> dataTypeLookup
    ) {

        this.namespaceUri = namespaceUri;
        this.dataTypeLookup = dataTypeLookup;
    }

    void addEnumDescription(EnumDescription description) {
        EnumeratedType enumeratedType = createEnumeratedType(description);

        enumeratedTypes.add(enumeratedType);
    }

    void addStructureDescription(StructureDescription description) {
        StructuredType structuredType = createStructuredType(description);

        structuredTypes.add(structuredType);
    }

    void writeToOutputStream(OutputStream outputStream) throws JAXBException {
        TypeDictionary typeDictionary = new TypeDictionary();
        typeDictionary.setDefaultByteOrder(ByteOrder.LITTLE_ENDIAN);
        typeDictionary.setTargetNamespace(namespaceUri);

        enumeratedTypes.forEach(t -> typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().add(t));
        structuredTypes.forEach(t -> typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().add(t));

        namespaces.forEach(namespace -> {
            ImportDirective importDirective = new ImportDirective();
            importDirective.setNamespace(namespace);
            typeDictionary.getImport().add(importDirective);
        });

        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            marshaller.setProperty(
                "com.sun.xml.bind.namespacePrefixMapper",
                new OpcUaNamespacePrefixMapper()
            );
        } catch (PropertyException e) {
            logger.debug("NamespacePrefixMapper not supported", e);
        }

        marshaller.marshal(typeDictionary, outputStream);
    }

    private EnumeratedType createEnumeratedType(EnumDescription description) {
        QualifiedName name = description.getName();
        EnumDefinition enumDefinition = description.getEnumDefinition();

        EnumeratedType enumeratedType = new EnumeratedType();
        enumeratedType.setName(name.getName());
        enumeratedType.setLengthInBits(32);

        for (EnumField field : enumDefinition.getFields()) {
            EnumeratedValue enumeratedValue = new EnumeratedValue();
            enumeratedValue.setName(field.getName());
            enumeratedValue.setValue(field.getValue().intValue());

            enumeratedType.getEnumeratedValue().add(enumeratedValue);
        }

        return enumeratedType;
    }

    private StructuredType createStructuredType(StructureDescription description) {
        QualifiedName name = description.getName();
        StructureDefinition structureDefinition = description.getStructureDefinition();

        StructuredType structuredType = new StructuredType();
        structuredType.setName(name.getName());

        for (StructureField field : structureDefinition.getFields()) {
            String fieldName = field.getName();
            NodeId fieldDataTypeId = field.getDataType();

            FullyQualifiedDataType fullyQualifiedDataType = dataTypeLookup.apply(fieldDataTypeId);

            String dataTypeName = fullyQualifiedDataType.dataTypeName;
            String dictionaryNamespaceUri = fullyQualifiedDataType.dictionaryNamespaceUri;

            namespaces.add(dictionaryNamespaceUri);

            FieldType fieldType = new FieldType();
            fieldType.setName(fieldName);
            fieldType.setTypeName(new QName(dictionaryNamespaceUri, dataTypeName));

            if (field.getValueRank() >= 1) {
                // Fixed-dimension array... specify a LengthField
                FieldType lengthFieldType = new FieldType();
                lengthFieldType.setName(fieldName + "Length");
                lengthFieldType.setTypeName(new QName(Namespaces.OPC_UA_BSD, "Int32"));

                structuredType.getField().add(lengthFieldType);

                fieldType.setLengthField(fieldName + "Length");
            } else if (field.getValueRank() != -1) {
                // Not scalar, not fixed-dimension, not supported
                throw new IllegalArgumentException(
                    "cannot encode field \"" + fieldName + "\" " +
                        "with ValueRank: %s" + field.getValueRank()
                );
            }

            structuredType.getField().add(fieldType);
        }

        return structuredType;
    }

    public static class FullyQualifiedDataType {
        final NodeId dataTypeId;
        final String dataTypeName;
        final String dictionaryNamespaceUri;

        FullyQualifiedDataType(NodeId dataTypeId, String dataTypeName, String dictionaryNamespaceUri) {
            this.dataTypeId = dataTypeId;
            this.dictionaryNamespaceUri = dictionaryNamespaceUri;
            this.dataTypeName = dataTypeName;
        }
    }

    private static class OpcUaNamespacePrefixMapper extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if (Namespaces.OPC_UA_BSD.equals(namespaceUri)) {
                return "opc";
            } else if (Namespaces.OPC_UA.equals(namespaceUri)) {
                return "ua";
            } else {
                return null;
            }
        }
    }

}
