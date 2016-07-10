/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.channel;

import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;

import org.eclipse.milo.opcua.stack.core.serialization.binary.BinaryDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.binary.BinaryEncoder;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;

public class SerializationQueue {

    private final BinaryEncoder binaryEncoder;
    private final BinaryDecoder binaryDecoder;

    private final ChunkEncoder chunkEncoder;
    private final ChunkDecoder chunkDecoder;

    private final ExecutionQueue encodingQueue;
    private final ExecutionQueue decodingQueue;

    private final ChannelParameters parameters;

    public SerializationQueue(ExecutorService executor,
                              ChannelParameters parameters,
                              int maxArrayLength,
                              int maxStringLength) {

        this.parameters = parameters;

        binaryEncoder = new BinaryEncoder(maxArrayLength, maxStringLength);
        binaryDecoder = new BinaryDecoder(maxArrayLength, maxStringLength);

        chunkEncoder = new ChunkEncoder(parameters);
        chunkDecoder = new ChunkDecoder(parameters);

        encodingQueue = new ExecutionQueue(executor);
        decodingQueue = new ExecutionQueue(executor);
    }

    public void encode(BiConsumer<BinaryEncoder, ChunkEncoder> consumer) {
        encodingQueue.submit(() -> consumer.accept(binaryEncoder, chunkEncoder));
    }

    public void decode(BiConsumer<BinaryDecoder, ChunkDecoder> consumer) {
        decodingQueue.submit(() -> consumer.accept(binaryDecoder, chunkDecoder));
    }

    public void pause() {
        encodingQueue.pause();
        decodingQueue.pause();
    }

    public ChannelParameters getParameters() {
        return parameters;
    }

}
