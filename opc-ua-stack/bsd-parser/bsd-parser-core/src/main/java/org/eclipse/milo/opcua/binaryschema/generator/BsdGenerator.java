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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.future.DataTypeDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.future.EnumDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.future.StructureField;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.opcfoundation.opcua.binaryschema.ByteOrder;
import org.opcfoundation.opcua.binaryschema.EnumeratedType;
import org.opcfoundation.opcua.binaryschema.FieldType;
import org.opcfoundation.opcua.binaryschema.ImportDirective;
import org.opcfoundation.opcua.binaryschema.ObjectFactory;
import org.opcfoundation.opcua.binaryschema.StructuredType;
import org.opcfoundation.opcua.binaryschema.TypeDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toMap;

public class BsdGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BsdGenerator.class);

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File("/Users/kevin/Desktop/bsd-test.xml"));

        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);

        TypeDictionary typeDictionary = new TypeDictionary();

        typeDictionary.setDefaultByteOrder(ByteOrder.LITTLE_ENDIAN);
        typeDictionary.setTargetNamespace("https://github.com/eclipse/milo");

        ImportDirective opcImport = new ImportDirective();
        opcImport.setNamespace("opc");
        opcImport.setLocation("http://opcfoundation.org/BinarySchema/");
        typeDictionary.getImport().add(opcImport);

        ImportDirective uaImport = new ImportDirective();
        uaImport.setNamespace("ua");
        uaImport.setLocation(Namespaces.OPC_UA);
        typeDictionary.getImport().add(uaImport);

        typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().add(createStructuredType(null));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespacePrefixMapper());
        } catch (PropertyException e) {
            // Not supported by this JAXB implementation
            e.printStackTrace();
        }
        marshaller.marshal(typeDictionary, fos);
    }

    public static void writeBsdXml(
        String namespaceUri,
        List<DataTypeDescription> dataTypeDescriptions,
        OutputStream outputStream
    ) throws JAXBException {

        TypeDictionary typeDictionary = new TypeDictionary();
        typeDictionary.setDefaultByteOrder(ByteOrder.LITTLE_ENDIAN);
        typeDictionary.setTargetNamespace(namespaceUri);

        Map<NodeId, DataTypeDescription> byDataTypeId = dataTypeDescriptions.stream()
            .collect(toMap(DataTypeDescription::getDataTypeId, Function.identity()));

        byDataTypeId.forEach((dataTypeId, description) -> {
            if (description instanceof EnumDescription) {
                try {
                    EnumeratedType enumeratedType = createEnumeratedType((EnumDescription) description);

                    typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().add(enumeratedType);
                } catch (Throwable t) {
                    LOGGER.warn("Failed to create EnumeratedType for \"" + description.getName() + "\"", t);
                }
            } else if (description instanceof StructureDescription) {
                try {
                    StructuredType structuredType = createStructuredType((StructureDescription) description);

                    typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().add(structuredType);
                } catch (Throwable t) {
                    LOGGER.warn("Failed to create StructuredType for \"" + description.getName() + "\"", t);
                }
            }
        });

        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespacePrefixMapper());
        } catch (PropertyException e) {
            LOGGER.debug("NamespacePrefixMapper not supported", e);
        }

        marshaller.marshal(typeDictionary, outputStream);
    }

    private static StructuredType createStructuredType(StructureDescription description) {
        QualifiedName name = description.getName();
        NodeId dataTypeId = description.getDataTypeId();
        StructureDefinition structureDefinition = description.getStructureDefinition();

        StructuredType structuredType = new StructuredType();
        structuredType.setName(name.getName());

        for (StructureField field : structureDefinition.getFields()) {
            String fieldName = field.getName();
            NodeId fieldDataTypeId = field.getDataType();

            // TODO Need to know the corresponding namespace URI for field datatype:
            //  DataTypeNode --HasEncoding-> DataTypeEncodingNode --HasDescription->
            //  DataTypeDescriptionNode --ComponentOf-> DataTypeDictionaryNode --HasProperty-> NamespaceUri

            FieldType fieldType = new FieldType();
            fieldType.setName(fieldName);
            fieldType.setTypeName(new QName("TODO", "TODO")); // TODO

            if (field.getValueRank() >= 1) {
                // Possibly an array... specify a LengthField
                FieldType lengthFieldType = new FieldType();
                lengthFieldType.setName(fieldName + "Length");
                lengthFieldType.setTypeName(new QName(Namespaces.OPC_UA_BSD, "UInt32"));

                structuredType.getField().add(lengthFieldType);

                fieldType.setLengthField(fieldName + "Length");
            } else if (field.getValueRank() != -1) {
                throw new IllegalArgumentException(
                    "cannot encode field \"" + fieldName + "\" " +
                        "with ValueRank: %s" + field.getValueRank()
                );
            }

            structuredType.getField().add(fieldType);
        }

        return structuredType;
//        StructuredType typeDescription = new StructuredType();
//
//        typeDescription.setName("CustomDataType");
//
//        FieldType fooField = new FieldType();
//        fooField.setName("foo");
//        fooField.setTypeName(new QName(
//            Namespaces.OPC_UA_BSD,
//            "String"
//        ));
//
//        FieldType barField = new FieldType();
//        barField.setName("bar");
//        barField.setTypeName(new QName(
//            Namespaces.OPC_UA_BSD,
//            "UInt32"
//        ));
//
//        FieldType bazField = new FieldType();
//        bazField.setName("baz");
//        bazField.setTypeName(new QName(
//            Namespaces.OPC_UA_BSD,
//            "Boolean"
//        ));
//
//        typeDescription.getField().add(fooField);
//        typeDescription.getField().add(barField);
//        typeDescription.getField().add(bazField);
//
//        return typeDescription;
    }

    private static EnumeratedType createEnumeratedType(EnumDescription description) {

        return null;
    }

    private static class MyNamespacePrefixMapper extends NamespacePrefixMapper {
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
