/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.binaryschema.generator;

import java.io.IOException;
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
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
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

public class BinaryDataTypeDictionaryGenerator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<String> namespaces = new HashSet<>();
    private final List<EnumeratedType> enumeratedTypes = new ArrayList<>();
    private final List<StructuredType> structuredTypes = new ArrayList<>();

    private final String namespaceUri;
    private final Function<NodeId, DataTypeLocation> dataTypeLookup;

    public BinaryDataTypeDictionaryGenerator(
        String namespaceUri,
        Function<NodeId, DataTypeLocation> dataTypeLookup
    ) {

        this.namespaceUri = namespaceUri;
        this.dataTypeLookup = dataTypeLookup;
    }

    public void addEnumDescription(EnumDescription description) {
        UByte builtInType = description.getBuiltInType();

        if (builtInType.intValue() != BuiltinDataType.Int32.getTypeId()) {
            throw new IllegalArgumentException("BuiltInType must be Int32");
        }

        enumeratedTypes.add(createEnumeratedType(description));
    }

    public void addStructureDescription(StructureDescription description) {
        StructureType structureType = description.getStructureDefinition().getStructureType();

        if (structureType != StructureType.Structure) {
            throw new IllegalArgumentException("StructureType not supported: " + structureType);
        }

        structuredTypes.add(createStructuredType(description));
    }

    public void writeToOutputStream(OutputStream outputStream) throws IOException {
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

        try {
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
        } catch (JAXBException e) {
            throw new IOException("failed to write dictionary to OutputStream", e);
        }
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

            DataTypeLocation dataTypeLocation = dataTypeLookup.apply(fieldDataTypeId);

            String dataTypeName = dataTypeLocation.dataTypeName;
            String dictionaryNamespaceUri = dataTypeLocation.dictionaryNamespaceUri;

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

    /**
     * Pair of DataType name and the namespace URI of the DataTypeDictionary it's defined in.
     */
    public static class DataTypeLocation {
        final String dataTypeName;
        final String dictionaryNamespaceUri;

        public DataTypeLocation(
            String dataTypeName,
            String dictionaryNamespaceUri
        ) {

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
