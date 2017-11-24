/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.List;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.testng.Assert.assertEquals;

public class DataTypeDictionaryReaderTest {

    private final OpcUaClient client = Mockito.mock(OpcUaClient.class);
    private final BsdParser bsdParser = Mockito.mock(BsdParser.class);

    private final DataTypeDictionaryReader dictionaryReader = new DataTypeDictionaryReader(client, bsdParser);

    @Test
    public void testReadDataTypeDictionaryBytes() throws Exception {
        int[] fragmentSizes = new int[]{1, 2, 1024, 2048, 4096, 8192};
        int[] dictionarySizes = new int[]{1, 2, 1024, 1025, 2048, 2049, 4096, 4097, 8192, 8193, 65535, 65536};


        for (int fragmentSize : fragmentSizes) {
            for (int dictionarySize : dictionarySizes) {
                byte[] dictionary = new byte[dictionarySize];
                for (int i = 0; i < dictionarySize; i++) {
                    dictionary[i] = (byte) i;
                }

                System.out.printf("fragmentSize=%d dictionarySize=%d\n", fragmentSize, dictionarySize);

                testReadDataTypeDictionaryBytes(ByteString.of(dictionary), fragmentSize);
            }
        }
    }

    private void testReadDataTypeDictionaryBytes(ByteString dictionary, int fragmentSize) throws Exception {
        Mockito
            .when(
                client.read(
                    anyDouble(),
                    any(TimestampsToReturn.class),
                    anyList()
                )
            )
            .then(invocationOnMock -> {
                List<ReadValueId> readValueIds = invocationOnMock.getArgument(2);
                ReadValueId readValueId = readValueIds.get(0);

                NumericRange numericRange = NumericRange.parse(readValueId.getIndexRange());

                try {
                    Object fragment = NumericRange.readFromValueAtRange(new Variant(dictionary), numericRange);

                    return completedFuture(new ReadResponse(
                        null,
                        new DataValue[]{new DataValue(new Variant(fragment))},
                        null
                    ));
                } catch (UaException e) {
                    return completedFuture(new ReadResponse(
                        null,
                        new DataValue[]{new DataValue(e.getStatusCode())},
                        null
                    ));
                }
            });

        ByteString typeDictionaryBs = dictionaryReader.readDataTypeDictionaryBytes(
            NodeId.NULL_VALUE,
            fragmentSize
        ).get();

        assertEquals(typeDictionaryBs, dictionary);
    }

}