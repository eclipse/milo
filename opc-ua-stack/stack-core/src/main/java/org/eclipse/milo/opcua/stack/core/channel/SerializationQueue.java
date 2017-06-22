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

import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;

public class SerializationQueue {

    private final OpcBinaryStreamReader reader;
    private final OpcBinaryStreamWriter writer;

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

        reader = new OpcBinaryStreamReader(maxArrayLength, maxStringLength);
        writer = new OpcBinaryStreamWriter(maxArrayLength, maxStringLength);

        chunkEncoder = new ChunkEncoder(parameters);
        chunkDecoder = new ChunkDecoder(parameters);

        encodingQueue = new ExecutionQueue(executor);
        decodingQueue = new ExecutionQueue(executor);
    }

    public void encode(Encoder encoder) {
        encodingQueue.submit(() -> encoder.encode(SerializationContext.INTERNAL, writer, chunkEncoder));
    }

    public void decode(Decoder decoder) {
        decodingQueue.submit(() -> decoder.decode(SerializationContext.INTERNAL, reader, chunkDecoder));
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
        void decode(SerializationContext context, OpcBinaryStreamReader reader, ChunkDecoder chunkDecoder);
    }

    @FunctionalInterface
    public interface Encoder {
        void encode(SerializationContext context, OpcBinaryStreamWriter writer, ChunkEncoder chunkEncoder);
    }

}
