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

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelegateRegistry {

    private static final Map<Class<?>, EncoderDelegate<?>> encodersByClass = Maps.newConcurrentMap();

    private static final Map<NodeId, EncoderDelegate<?>> encodersById = Maps.newConcurrentMap();

    private static final Map<Class<?>, DecoderDelegate<?>> decodersByClass = Maps.newConcurrentMap();

    private static final Map<NodeId, DecoderDelegate<?>> decodersById = Maps.newConcurrentMap();

    public static <T> void registerEncoder(EncoderDelegate<T> delegate, Class<T> clazz, NodeId... ids) {
        encodersByClass.put(clazz, delegate);

        if (ids != null) {
            Arrays.stream(ids).forEach(id -> encodersById.put(id, delegate));
        }
    }

    public static <T> void registerDecoder(DecoderDelegate<T> delegate, Class<T> clazz, NodeId... ids) {
        decodersByClass.put(clazz, delegate);

        if (ids != null) {
            Arrays.stream(ids).forEach(id -> decodersById.put(id, delegate));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> EncoderDelegate<T> getEncoder(Object t) throws UaSerializationException {
        try {
            return (EncoderDelegate<T>) encodersByClass.get(t.getClass());
        } catch (NullPointerException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                "no encoder registered for class=" + t);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> EncoderDelegate<T> getEncoder(Class<?> clazz) throws UaSerializationException {
        try {
            return (EncoderDelegate<T>) encodersByClass.get(clazz);
        } catch (NullPointerException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                "no encoder registered for class=" + clazz);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> EncoderDelegate<T> getEncoder(NodeId encodingId) throws UaSerializationException {
        try {
            return (EncoderDelegate<T>) encodersById.get(encodingId);
        } catch (NullPointerException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError,
                "no encoder registered for encodingId=" + encodingId);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> DecoderDelegate<T> getDecoder(T t) throws UaSerializationException {
        try {
            return (DecoderDelegate<T>) decodersByClass.get(t.getClass());
        } catch (NullPointerException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                "no decoder registered for class=" + t);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> DecoderDelegate<T> getDecoder(Class<T> clazz) throws UaSerializationException {
        try {
            return (DecoderDelegate<T>) decodersByClass.get(clazz);
        } catch (NullPointerException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                "no decoder registered for class=" + clazz);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> DecoderDelegate<T> getDecoder(NodeId encodingId) {
        DecoderDelegate<T> decoder = (DecoderDelegate<T>) decodersById.get(encodingId);

        if (decoder == null) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                "no decoder registered for encodingId=" + encodingId);
        }

        return decoder;
    }

    static {
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
    }

    private static void loadGeneratedClasses(ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ClassPath classPath = ClassPath.from(classLoader);

        ImmutableSet<ClassInfo> structures =
            classPath.getTopLevelClasses("org.eclipse.milo.opcua.stack.core.types.structured");

        ImmutableSet<ClassInfo> enumerations =
            classPath.getTopLevelClasses("org.eclipse.milo.opcua.stack.core.types.enumerated");

        for (ClassInfo classInfo : Sets.union(structures, enumerations)) {
            Class<?> clazz = classInfo.load();
            Class.forName(clazz.getName(), true, classLoader);
        }
    }

}
