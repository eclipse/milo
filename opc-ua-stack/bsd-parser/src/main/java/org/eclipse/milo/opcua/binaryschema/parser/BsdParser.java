/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.binaryschema.parser;

import java.io.InputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.opcfoundation.opcua.binaryschema.EnumeratedType;
import org.opcfoundation.opcua.binaryschema.ObjectFactory;
import org.opcfoundation.opcua.binaryschema.StructuredType;
import org.opcfoundation.opcua.binaryschema.TypeDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toList;

public abstract class BsdParser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Parse an XML document containing a type dictionary conforming to the OPC Binary XML Schema.
     *
     * @param inputStream the {@link InputStream} to read the XML document from.
     * @return a {@link DictionaryDescription}.
     * @throws JAXBException if parsing fails.
     */
    public DictionaryDescription parse(InputStream inputStream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);

        TypeDictionary typeDictionary = (TypeDictionary) context.createUnmarshaller().unmarshal(inputStream);

        List<CodecDescription> enumCodecs = typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().stream()
            .filter(typeDescription -> typeDescription instanceof EnumeratedType)
            .map(typeDescription -> {
                EnumeratedType enumeratedType = (EnumeratedType) typeDescription;

                logger.debug("EnumeratedType: {}", typeDescription.getName());

                return new CodecDescription(getEnumCodec(enumeratedType), enumeratedType.getName());
            })
            .collect(toList());

        List<CodecDescription> structCodecs = typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType().stream()
            .filter(typeDescription -> typeDescription instanceof StructuredType)
            .map(typeDescription -> {
                StructuredType structuredType = (StructuredType) typeDescription;

                logger.debug("StructuredType: {}", typeDescription.getName());

                return new CodecDescription(getStructCodec(structuredType), structuredType.getName());
            })
            .collect(toList());

        return new DictionaryDescription(typeDictionary.getTargetNamespace(), enumCodecs, structCodecs);
    }

    /**
     * Create an {@link OpcUaBinaryDataTypeCodec} for the provided {@link EnumeratedType}.
     *
     * @param enumeratedType the {@link EnumeratedType}.
     * @return an {@link OpcUaBinaryDataTypeCodec} for the provided {@link EnumeratedType}.
     */
    protected abstract OpcUaBinaryDataTypeCodec<?> getEnumCodec(EnumeratedType enumeratedType);

    /**
     * Create an {@link OpcUaBinaryDataTypeCodec} for the provided {@link StructuredType}.
     *
     * @param structuredType the {@link StructuredType}.
     * @return an {@link OpcUaBinaryDataTypeCodec} for the provided {@link StructuredType}.
     */
    protected abstract OpcUaBinaryDataTypeCodec<?> getStructCodec(StructuredType structuredType);

}
