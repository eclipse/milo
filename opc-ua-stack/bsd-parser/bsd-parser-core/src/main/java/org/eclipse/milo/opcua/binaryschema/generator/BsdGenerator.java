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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.opcfoundation.opcua.binaryschema.ByteOrder;
import org.opcfoundation.opcua.binaryschema.FieldType;
import org.opcfoundation.opcua.binaryschema.ImportDirective;
import org.opcfoundation.opcua.binaryschema.ObjectFactory;
import org.opcfoundation.opcua.binaryschema.StructuredType;
import org.opcfoundation.opcua.binaryschema.TypeDescription;
import org.opcfoundation.opcua.binaryschema.TypeDictionary;

public class BsdGenerator {

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

        typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().add(createTypeDescription());

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

    private static TypeDescription createTypeDescription() {
        StructuredType typeDescription = new StructuredType();

        typeDescription.setName("MyStruct");

        FieldType fooField = new FieldType();
        fooField.setName("foo");
        fooField.setTypeName(new QName(
            Namespaces.OPC_UA_BSD,
            "Int32"
        ));

        FieldType barField = new FieldType();
        barField.setName("bar");
        barField.setTypeName(new QName(
            Namespaces.OPC_UA_BSD,
            "String"
        ));

        FieldType bazField = new FieldType();
        bazField.setName("baz");
        bazField.setTypeName(new QName(
            Namespaces.OPC_UA,
            "StatusCode"
        ));

        typeDescription.getField().add(fooField);
        typeDescription.getField().add(barField);
        typeDescription.getField().add(bazField);

        return typeDescription;
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
