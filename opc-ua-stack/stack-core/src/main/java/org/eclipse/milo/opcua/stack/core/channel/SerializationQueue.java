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

import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.util.TaskQueue;

public class SerializationQueue {

    private static final int MAX_QUEUE_SIZE =
        Integer.getInteger("milo.stack.serialization.maxQueueSize", 256);

    private final OpcUaBinaryStreamEncoder binaryEncoder;
    private final OpcUaBinaryStreamDecoder binaryDecoder;

    private final ChunkEncoder chunkEncoder;
    private final ChunkDecoder chunkDecoder;

    private final TaskQueue encodingQueue;
    private final TaskQueue decodingQueue;

    private final ChannelParameters parameters;

    public SerializationQueue(
        ExecutorService executor,
        ChannelParameters parameters,
        SerializationContext context
    ) {

        this.parameters = parameters;

        chunkEncoder = new ChunkEncoder(parameters);
        chunkDecoder = new ChunkDecoder(parameters, context.getEncodingLimits());

        binaryEncoder = new OpcUaBinaryStreamEncoder(context);
        binaryDecoder = new OpcUaBinaryStreamDecoder(context);

        encodingQueue = new TaskQueue(executor);

        decodingQueue = TaskQueue.newBuilder()
            .setExecutor(executor)
            .setMaxQueueSize(MAX_QUEUE_SIZE)
            .build();
    }

    public boolean encode(Encoder encoder) {
        return encodingQueue.execute(() -> encoder.encode(binaryEncoder, chunkEncoder));
    }

    public boolean decode(Decoder decoder) {
        return decodingQueue.execute(() -> decoder.decode(binaryDecoder, chunkDecoder));
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
