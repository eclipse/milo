/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTypeDictionarySessionInitializer implements SessionFsm.SessionInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BsdParser bsdParser;

    public DataTypeDictionarySessionInitializer(BsdParser bsdParser) {
        this.bsdParser = bsdParser;
    }

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient client, OpcUaSession session) {
        logger.debug("SessionInitializer: DataTypeDictionary");

        DataTypeDictionaryReader reader = new DataTypeDictionaryReader(
            client,
            session,
            bsdParser
        );

        return reader.readDataTypeDictionaries()
            .thenAccept(dictionaries ->
                dictionaries.forEach(
                    client.getDynamicDataTypeManager()::registerTypeDictionary)
            )
            .thenApply(v -> Unit.VALUE)
            .exceptionally(ex -> {
                logger.warn("SessionInitializer: DataTypeDictionary", ex);
                return Unit.VALUE;
            });
    }

}
