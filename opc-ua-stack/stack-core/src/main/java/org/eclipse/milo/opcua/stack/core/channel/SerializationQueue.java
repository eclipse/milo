/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel;

import java.util.concurrent.ExecutorService;

import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;

public class SerializationQueue {

    private final ChunkEncoder chunkEncoder;
    private final ChunkDecoder chunkDecoder;

    private final ExecutionQueue encodingQueue;
    private final ExecutionQueue decodingQueue;

    private final ChannelParameters parameters;
    private final EncodingLimits encodingLimits;

    public SerializationQueue(ExecutorService executor,
                              ChannelParameters parameters,
                              EncodingLimits encodingLimits) {

        this.parameters = parameters;
        this.encodingLimits = encodingLimits;

        chunkEncoder = new ChunkEncoder(parameters);

        chunkDecoder = new ChunkDecoder(
            parameters,
            encodingLimits.getMaxArrayLength(),
            encodingLimits.getMaxStringLength()
        );

        encodingQueue = new ExecutionQueue(executor);
        decodingQueue = new ExecutionQueue(executor);
    }

    public void encode(Encoder encoder) {
        encodingQueue.submit(() -> {
            OpcUaBinaryStreamEncoder binaryEncoder =
                new OpcUaBinaryStreamEncoder(encodingLimits);

            encoder.encode(binaryEncoder, chunkEncoder);
        });
    }

    public void decode(Decoder decoder) {
        decodingQueue.submit(() -> {
            OpcUaBinaryStreamDecoder binaryDecoder =
                new OpcUaBinaryStreamDecoder(encodingLimits);

            decoder.decode(binaryDecoder, chunkDecoder);
        });
    }

    public void pause() {
        encodingQueue.pause();
        decodingQueue.pause();
    }

    public ChannelParameters getParameters() {
        return parameters;
    }

    @FunctionalInterface
    public interface Decoder {
        void decode(OpcUaBinaryStreamDecoder binaryDecoder, ChunkDecoder chunkDecoder);
    }

    @FunctionalInterface
    public interface Encoder {
        void encode(OpcUaBinaryStreamEncoder binaryEncoder, ChunkEncoder chunkEncoder);
    }

}
