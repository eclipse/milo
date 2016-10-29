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

package org.eclipse.milo.opcua.stack.core.serialization;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DelegateRegistry {

    /**
     * Get the singleton instance of the DelegateRegistry: {@link DelegateRegistry.Instance}.
     *
     * @return the singleton instance of the DelegateRegistry: {@link DelegateRegistry.Instance}.
     */
    public static Instance getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final Instance INSTANCE = getOrInitialize();
    }

    private static final Map<Class<?>, EncoderDelegate<?>> ENCODERS_BY_CLASS = Maps.newConcurrentMap();
    private static final Map<NodeId, EncoderDelegate<?>> ENCODERS_BY_ID = Maps.newConcurrentMap();

    private static final Map<Class<?>, DecoderDelegate<?>> DECODERS_BY_CLASS = Maps.newConcurrentMap();
    private static final Map<NodeId, DecoderDelegate<?>> DECODERS_BY_ID = Maps.newConcurrentMap();

    private static final AtomicReference<Instance> INSTANCE_REF = new AtomicReference<>();

    private static synchronized Instance getOrInitialize() {
        Instance instance = INSTANCE_REF.get();

        if (instance == null) {
            DelegateRegistryInitializer.initialize();

            instance = new Instance(
                ENCODERS_BY_CLASS,
                ENCODERS_BY_ID,
                DECODERS_BY_CLASS,
                DECODERS_BY_ID
            );

            INSTANCE_REF.set(instance);

            return instance;
        } else {
            return instance;
        }
    }

    public static synchronized <T> void register(
        EncoderDelegate<T> encoder,
        DecoderDelegate<T> decoder,
        Class<T> clazz, NodeId... ids) {

        registerEncoder(encoder, clazz, ids);
        registerDecoder(decoder, clazz, ids);
    }

    public static synchronized <T> void registerEncoder(EncoderDelegate<T> delegate, Class<T> clazz, NodeId... ids) {
        ENCODERS_BY_CLASS.put(clazz, delegate);

        if (ids != null) {
            Arrays.stream(ids).forEach(id -> ENCODERS_BY_ID.put(id, delegate));
        }
    }

    public static synchronized <T> void registerDecoder(DecoderDelegate<T> delegate, Class<T> clazz, NodeId... ids) {
        DECODERS_BY_CLASS.put(clazz, delegate);

        if (ids != null) {
            Arrays.stream(ids).forEach(id -> DECODERS_BY_ID.put(id, delegate));
        }
    }

    public static class Instance {

        private final Map<Class<?>, EncoderDelegate<?>> encodersByClass;
        private final Map<NodeId, EncoderDelegate<?>> encodersById;

        private final Map<Class<?>, DecoderDelegate<?>> decodersByClass;
        private final Map<NodeId, DecoderDelegate<?>> decodersById;

        private Instance(
            Map<Class<?>, EncoderDelegate<?>> encodersByClass,
            Map<NodeId, EncoderDelegate<?>> encodersById,
            Map<Class<?>, DecoderDelegate<?>> decodersByClass,
            Map<NodeId, DecoderDelegate<?>> decodersById) {

            this.encodersByClass = encodersByClass;
            this.encodersById = encodersById;
            this.decodersByClass = decodersByClass;
            this.decodersById = decodersById;
        }

        @SuppressWarnings("unchecked")
        public <T> EncoderDelegate<T> getEncoder(Object t) throws UaSerializationException {
            try {
                return (EncoderDelegate<T>) encodersByClass.get(t.getClass());
            } catch (NullPointerException e) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                    "no encoder registered for class=" + t);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> EncoderDelegate<T> getEncoder(Class<?> clazz) throws UaSerializationException {
            EncoderDelegate<?> encoderDelegate = encodersByClass.get(clazz);

            if (encoderDelegate != null) {
                try {
                    return (EncoderDelegate<T>) encoderDelegate;
                } catch (Exception e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            } else {
                throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                    "no encoder registered for class=" + clazz);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> EncoderDelegate<T> getEncoder(NodeId encodingId) throws UaSerializationException {
            EncoderDelegate<?> encoderDelegate = encodersById.get(encodingId);

            if (encoderDelegate != null) {
                try {
                    return (EncoderDelegate<T>) encoderDelegate;
                } catch (Exception e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            } else {
                throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                    "no encoder registered for encodingId=" + encodingId);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> DecoderDelegate<T> getDecoder(T t) throws UaSerializationException {
            try {
                return (DecoderDelegate<T>) decodersByClass.get(t.getClass());
            } catch (NullPointerException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                    "no decoder registered for class=" + t);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> DecoderDelegate<T> getDecoder(Class<T> clazz) throws UaSerializationException {
            DecoderDelegate<?> decoderDelegate = decodersByClass.get(clazz);

            if (decoderDelegate != null) {
                try {
                    return (DecoderDelegate<T>) decoderDelegate;
                } catch (Exception e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }
            } else {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                    "no decoder registered for class=" + clazz);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> DecoderDelegate<T> getDecoder(NodeId encodingId) {
            DecoderDelegate<?> decoderDelegate = decodersById.get(encodingId);

            if (decoderDelegate != null) {
                try {
                    return (DecoderDelegate<T>) decoderDelegate;
                } catch (Exception e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }
            } else {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                    "no decoder registered for encodingId=" + encodingId);
            }
        }

    }

}
