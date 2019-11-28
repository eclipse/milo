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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.Identifiers;
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

    private final Map<NodeId, EnumDescription> enumDescriptions = new HashMap<>();
    private final Map<NodeId, StructureDescription> structureDescriptions = new HashMap<>();

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

        enumDescriptions.put(description.getDataTypeId(), description);
    }

    public void addStructureDescription(StructureDescription description) {
        StructureType structureType = description.getStructureDefinition().getStructureType();

        if (structureType == StructureType.Union) {
            throw new IllegalArgumentException("StructureType not supported: " + structureType);
        }

        structuredTypes.add(createStructuredType(description));

        structureDescriptions.put(description.getDataTypeId(), description);
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
        EnumDefinition definition = description.getEnumDefinition();

        EnumeratedType enumeratedType = new EnumeratedType();
        enumeratedType.setName(name.getName());
        enumeratedType.setLengthInBits(32);

        for (EnumField field : definition.getFields()) {
            EnumeratedValue enumeratedValue = new EnumeratedValue();
            enumeratedValue.setName(field.getName());
            enumeratedValue.setValue(field.getValue().intValue());

            enumeratedType.getEnumeratedValue().add(enumeratedValue);
        }

        return enumeratedType;
    }

    private StructuredType createStructuredType(StructureDescription description) {
        QualifiedName name = description.getName();

        StructureDefinition definition = description.getStructureDefinition();
        StructureType structureType = definition.getStructureType();

        StructuredType structuredType = new StructuredType();
        structuredType.setName(name.getName());

        // Create a combined list of StructuredFields from all parent types
        LinkedList<StructureDefinition> definitions = new LinkedList<>();
        definitions.addFirst(definition);

        NodeId baseDataTypeId = definition.getBaseDataType();

        while (baseDataTypeId != null && baseDataTypeId.isNotNull()
            && !Identifiers.Structure.equals(baseDataTypeId)
            && !Identifiers.Union.equals(baseDataTypeId)
        ) {

            StructureDescription baseDescription = structureDescriptions.get(baseDataTypeId);
            StructureDefinition baseDefinition = baseDescription.getStructureDefinition();

            definitions.addFirst(baseDefinition);

            baseDataTypeId = baseDefinition.getBaseDataType();
        }

        LinkedHashMap<String, StructureField> allFields = new LinkedHashMap<>();

        for (StructureDefinition d : definitions) {
            for (StructureField f : d.getFields()) {
                allFields.put(f.getName(), f);
            }
        }

        List<StructureField> fields = new ArrayList<>(allFields.values());

        if (structureType == StructureType.StructureWithOptionalFields) {
            int optionalFieldCount = 0;

            for (StructureField field : fields) {
                if (field.getIsOptional()) {
                    optionalFieldCount++;

                    FieldType fieldType = new FieldType();
                    fieldType.setName(field.getName() + "Present");
                    fieldType.setTypeName(new QName(Namespaces.OPC_UA_BSD, "Bit"));

                    structuredType.getField().add(fieldType);
                }
            }

            if (optionalFieldCount > 0) {
                int reservedFieldCount = (optionalFieldCount + 31) / 32;

                for (int i = 0; i < reservedFieldCount; i++) {
                    long reservedBits = 32 - optionalFieldCount;
                    optionalFieldCount -= 32;

                    FieldType fieldType = new FieldType();
                    fieldType.setLength(reservedBits);
                    fieldType.setName("Reserved" + i);
                    fieldType.setTypeName(new QName(Namespaces.OPC_UA_BSD, "Bit"));

                    structuredType.getField().add(fieldType);
                }
            }
        } else if (structureType == StructureType.Union) {
            FieldType fieldType = new FieldType();
            fieldType.setName("SwitchField");
            fieldType.setTypeName(new QName(Namespaces.OPC_UA_BSD, "UInt32"));

            structuredType.getField().add(fieldType);
        }

        long switchValue = 0L;

        for (StructureField field : fields) {
            String fieldName = field.getName();
            NodeId fieldDataTypeId = field.getDataType();

            DataTypeLocation dataTypeLocation = dataTypeLookup.apply(fieldDataTypeId);

            String dataTypeName = dataTypeLocation.dataTypeName;
            String dictionaryNamespaceUri = dataTypeLocation.dictionaryNamespaceUri;

            namespaces.add(dictionaryNamespaceUri);

            FieldType fieldType = new FieldType();
            fieldType.setName(fieldName);
            fieldType.setTypeName(new QName(dictionaryNamespaceUri, dataTypeName));

            if (structureType == StructureType.StructureWithOptionalFields) {
                if (field.getIsOptional()) {
                    fieldType.setSwitchField(fieldName + "Present");
                }
            } else if (structureType == StructureType.Union) {
                fieldType.setSwitchField("SwitchField");
                fieldType.setSwitchValue(++switchValue);
            }

            if (field.getValueRank() >= 1) {
                // Fixed-dimension array... specify a LengthField
                FieldType lengthFieldType = new FieldType();
                lengthFieldType.setName(fieldName + "Length");
                lengthFieldType.setTypeName(new QName(Namespaces.OPC_UA_BSD, "Int32"));

                if (structureType == StructureType.StructureWithOptionalFields) {
                    if (field.getIsOptional()) {
                        lengthFieldType.setSwitchField(fieldName + "Present");
                    }
                } else if (structureType == StructureType.Union) {
                    fieldType.setSwitchField("SwitchField");
                    fieldType.setSwitchValue(switchValue);
                }

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
