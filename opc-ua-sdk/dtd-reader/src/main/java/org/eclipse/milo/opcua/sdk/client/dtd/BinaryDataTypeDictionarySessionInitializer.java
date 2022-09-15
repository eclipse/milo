/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.dtd;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.dtd.BinaryDataTypeDictionaryReader.TypeDictionaryInfo;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.core.dtd.BinaryDataTypeCodec;
import org.eclipse.milo.opcua.sdk.core.dtd.BinaryDataTypeDictionary;
import org.eclipse.milo.opcua.sdk.core.dtd.BinaryDataTypeDictionary.BinaryType;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.opcfoundation.opcua.binaryschema.StructuredType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryDataTypeDictionarySessionInitializer implements SessionFsm.SessionInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CodecFactory codecFactory;

    public BinaryDataTypeDictionarySessionInitializer(CodecFactory codecFactory) {
        this.codecFactory = codecFactory;
    }

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient client, OpcUaSession session) {
        logger.debug("SessionInitializer: DataTypeDictionary");

        var dictionaryReader = new BinaryDataTypeDictionaryReader(client, session);

        return dictionaryReader.readDataTypeDictionaries()
            .thenAccept(typeDictionaryInfos -> {
                    DataTypeManager dataTypeManager = client.getDynamicDataTypeManager();

                    for (TypeDictionaryInfo typeDictionaryInfo : typeDictionaryInfos) {
                        var dictionary = new BinaryDataTypeDictionary(
                            typeDictionaryInfo.typeDictionary
                        );

                        Map<String, StructuredType> structuredTypes = new HashMap<>();

                        typeDictionaryInfo.typeDictionary.getOpaqueTypeOrEnumeratedTypeOrStructuredType()
                            .stream()
                            .filter(typeDescription -> typeDescription instanceof StructuredType)
                            .map(StructuredType.class::cast)
                            .forEach(structuredType -> structuredTypes.put(structuredType.getName(), structuredType));

                        typeDictionaryInfo.structEncodingInfos.forEach(structEncodingInfo -> {
                            StructuredType structuredType = structuredTypes.get(structEncodingInfo.description);

                            BinaryDataTypeCodec codec = codecFactory.createCodec(structuredType);

                            BinaryType binaryType = new BinaryType(
                                structEncodingInfo.description,
                                structEncodingInfo.dataTypeId,
                                structEncodingInfo.encodingId,
                                codec
                            );

                            dictionary.registerType(binaryType);
                        });

                        dataTypeManager.registerTypeDictionary(dictionary);

                        dictionary.getTypes().forEach(
                            type ->
                                dataTypeManager.registerType(
                                    type.getDataTypeId(), type.getCodec(), type.getEncodingId(), null, null)
                        );
                    }
                }
            )
            .thenApply(v -> Unit.VALUE)
            .exceptionally(ex -> {
                logger.warn("SessionInitializer: DataTypeDictionary", ex);
                return Unit.VALUE;
            });
    }

    public interface CodecFactory {

        BinaryDataTypeCodec createCodec(StructuredType structuredType);

    }

}
