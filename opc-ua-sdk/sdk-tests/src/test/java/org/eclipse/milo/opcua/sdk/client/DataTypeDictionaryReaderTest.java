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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.mockito.ArgumentMatchers.any;
import static org.testng.Assert.assertEquals;

public class DataTypeDictionaryReaderTest {

    private final UaStackClient stackClient = Mockito.mock(UaStackClient.class);
    private final OpcUaSession session = Mockito.mock(OpcUaSession.class);
    private final BsdParser bsdParser = Mockito.mock(BsdParser.class);

    private final DataTypeDictionaryReader dictionaryReader =
        new DataTypeDictionaryReader(stackClient, session, bsdParser);

    @Test
    public void testReadDataTypeDictionaryBytes() throws Exception {
        int[] fragmentSizes = new int[]{1, 2, 1024, 2048, 4096, 8192};
        int[] dictionarySizes = new int[]{1, 2, 1024, 1025, 2048, 2049, 4096, 4097, 8192, 8193, 65535, 65536};


        for (int fragmentSize : fragmentSizes) {
            for (int dictionarySize : dictionarySizes) {
                byte[] dictionary = new byte[dictionarySize];
                for (int i = 0; i < dictionarySize; i++) {
                    byte b = (byte) i;
                    if (b == 0) b++; // no null bytes because they get trimmed
                    dictionary[i] = b;
                }

                System.out.printf("fragmentSize=%d dictionarySize=%d\n", fragmentSize, dictionarySize);

                testReadDataTypeDictionaryBytes(ByteString.of(dictionary), fragmentSize);
            }
        }
    }

    private void testReadDataTypeDictionaryBytes(ByteString dictionary, int fragmentSize) throws Exception {
        Mockito
            .when(stackClient.sendRequest(any(ReadRequest.class)))
            .then(invocationOnMock -> {
                ReadRequest readRequest = invocationOnMock.getArgument(0);

                List<ReadValueId> readValueIds =
                    Arrays.stream(Objects.requireNonNull(readRequest.getNodesToRead()))
                        .collect(Collectors.toList());

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
