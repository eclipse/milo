/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import com.google.common.collect.Maps;
import com.google.common.reflect.ClassPath;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.OpenSecureChannelResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.eclipse.milo.opcua.stack.core.util.ClassEnumerator.getClassesForPackage;

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
            /*
             * Reflect-o-magically find all generated structured and enumerated types and force their static initialization
             * blocks to run, registering their encode/decode methods with the delegate registry.
             */
            Logger logger = LoggerFactory.getLogger(DelegateRegistry.class);

            ClassLoader classLoader = Stack.getCustomClassLoader()
                .orElse(DelegateRegistry.class.getClassLoader());

            try {
                loadGeneratedClasses(classLoader);
            } catch (Exception e1) {
                // Temporarily set the thread context ClassLoader to our
                // ClassLoader and try loading the classes one more time.

                Thread thread = Thread.currentThread();
                ClassLoader contextClassLoader = thread.getContextClassLoader();

                thread.setContextClassLoader(classLoader);

                try {
                    loadGeneratedClasses(classLoader);
                } catch (Exception e2) {
                    logger.error("Error loading generated classes.", e2);
                } finally {
                    thread.setContextClassLoader(contextClassLoader);
                }
            }

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

    private static void loadGeneratedClasses(ClassLoader classLoader) throws IOException, ClassNotFoundException {
        List<Class<?>> structures = getClassesForPackage(OpenSecureChannelResponse.class.getPackage());

        List<Class<?>> enumerations = getClassesForPackage(NodeClass.class.getPackage());

        Set<Class<?>> totalClasses = new HashSet();
        totalClasses.addAll(structures);
        totalClasses.addAll(enumerations);

        for (Class<?> clazz :  totalClasses) {
            Class.forName(clazz.getName(), true, classLoader);
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
            try {
                return (EncoderDelegate<T>) encodersByClass.get(clazz);
            } catch (NullPointerException e) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                    "no encoder registered for class=" + clazz);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> EncoderDelegate<T> getEncoder(NodeId encodingId) throws UaSerializationException {
            try {
                return (EncoderDelegate<T>) encodersById.get(encodingId);
            } catch (NullPointerException e) {
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
            try {
                return (DecoderDelegate<T>) decodersByClass.get(clazz);
            } catch (NullPointerException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                    "no decoder registered for class=" + clazz);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> DecoderDelegate<T> getDecoder(NodeId encodingId) {
            DecoderDelegate<T> decoder = (DecoderDelegate<T>) decodersById.get(encodingId);

            if (decoder == null) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                    "no decoder registered for encodingId=" + encodingId);
            }

            return decoder;
        }

    }

}
