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

package org.eclipse.milo.opcua.sdk.server.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.annotations.UaOutputArgument;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class AnnotationBasedInvocationHandler implements MethodInvocationHandler {

    private final Method annotatedMethod;

    private final ServerNodeMap nodeMap;
    private final List<Argument> inputArguments;
    private final List<Argument> outputArguments;
    private final Object annotatedObject;

    public AnnotationBasedInvocationHandler(
        ServerNodeMap nodeMap,
        Argument[] inputArguments,
        Argument[] outputArguments,
        Object annotatedObject) {

        this(nodeMap, Lists.newArrayList(inputArguments), Lists.newArrayList(outputArguments), annotatedObject);
    }

    public AnnotationBasedInvocationHandler(
        ServerNodeMap nodeMap,
        List<Argument> inputArguments,
        List<Argument> outputArguments,
        Object annotatedObject) {

        this.nodeMap = nodeMap;
        this.inputArguments = inputArguments;
        this.outputArguments = outputArguments;
        this.annotatedObject = annotatedObject;

        annotatedMethod = Arrays.stream(annotatedObject.getClass().getMethods())
            .filter(m -> m.isAnnotationPresent(UaMethod.class))
            .findFirst().orElseThrow(() -> new RuntimeException("no @UaMethod annotated annotatedMethod found"));
    }

    public Argument[] getInputArguments() {
        return a(inputArguments, Argument.class);
    }

    public Argument[] getOutputArguments() {
        return a(outputArguments, Argument.class);
    }

    @Override
    public void invoke(
        AccessContext accessContext,
        CallMethodRequest request,
        CompletableFuture<CallMethodResult> future) {
        
        NodeId objectId = request.getObjectId();

        List<Variant> inputVariants = l(request.getInputArguments());

        if (inputVariants.size() != inputArguments.size()) {
            future.complete(new CallMethodResult(
                new StatusCode(StatusCodes.Bad_ArgumentsMissing),
                new StatusCode[0], new DiagnosticInfo[0], new Variant[0]
            ));
        }

        Object[] inputs = new Object[inputVariants.size()];
        StatusCode[] inputArgumentResults = new StatusCode[inputVariants.size()];

        for (int i = 0; i < inputVariants.size(); i++) {
            Argument argument = inputArguments.get(i);

            Variant variant = inputVariants.get(i);

            boolean dataTypeMatch = variant.getDataType()
                .map(type -> type.equals(argument.getDataType()))
                .orElse(false);

            if (!dataTypeMatch) {
                inputArgumentResults[i] = new StatusCode(StatusCodes.Bad_TypeMismatch);
            } else {
                inputArgumentResults[i] = StatusCode.GOOD;
            }

            inputs[i] = variant.getValue();
        }

        int outputCount = outputArguments.size();
        CountDownLatch latch = new CountDownLatch(outputCount);
        Object[] outputs = new Object[outputCount];

        for (int i = 0; i < outputCount; i++) {
            outputs[i] = new OutImpl<>(latch);
        }

        // TODO Implement an AsyncCountDownLatch and ditch this thread

        new Thread(() -> {
            try {
                Object[] parameters = new Object[1 + inputs.length + outputs.length];

                UaObjectNode objectNode =
                    (UaObjectNode) nodeMap.getNode(objectId)
                        .orElseThrow(() -> new Exception("owner Object node found"));

                InvocationContext context = new InvocationContextImpl(
                    accessContext,
                    objectNode,
                    future,
                    inputArgumentResults,
                    latch
                );

                parameters[0] = context;

                System.arraycopy(inputs, 0, parameters, 1, inputs.length);
                System.arraycopy(outputs, 0, parameters, 1 + inputs.length, outputs.length);

                annotatedMethod.invoke(annotatedObject, parameters);
                latch.await();

                // Check if they called context.setFailure(...)
                if (!future.isDone()) {
                    Variant[] values = new Variant[outputCount];
                    for (int i = 0; i < outputCount; i++) {
                        values[i] = new Variant(((OutImpl<?>) outputs[i]).get());
                    }

                    future.complete(new CallMethodResult(
                        StatusCode.GOOD, inputArgumentResults,
                        new DiagnosticInfo[0], values
                    ));
                }
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException();

                if (targetException instanceof UaException) {
                    StatusCode statusCode = ((UaException) targetException).getStatusCode();

                    future.complete(new CallMethodResult(
                        statusCode, inputArgumentResults,
                        new DiagnosticInfo[0], new Variant[0]
                    ));
                } else {
                    future.complete(new CallMethodResult(
                        new StatusCode(StatusCodes.Bad_InternalError),
                        inputArgumentResults, new DiagnosticInfo[0], new Variant[0]
                    ));
                }
            } catch (Throwable t) {
                future.complete(new CallMethodResult(
                    new StatusCode(StatusCodes.Bad_InternalError),
                    inputArgumentResults, new DiagnosticInfo[0], new Variant[0]
                ));
            }
        }).start();
    }


    public static AnnotationBasedInvocationHandler fromAnnotatedObject(
        ServerNodeMap nodeMap, Object annotatedObject) throws Exception {
        // TODO Make this work when parameter types are not built-in types

        Method annotatedMethod = Arrays.stream(annotatedObject.getClass().getMethods())
            .filter(m -> m.isAnnotationPresent(UaMethod.class))
            .findFirst().orElseThrow(() -> new Exception("no @UaMethod annotated annotatedMethod found"));

        Parameter[] parameters = annotatedMethod.getParameters();
        Type[] parameterTypes = annotatedMethod.getGenericParameterTypes();

        assert (parameters.length == parameterTypes.length);

        List<Argument> inputArguments = Lists.newArrayList();
        List<Argument> outputArguments = Lists.newArrayList();

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];

            if (parameter.isAnnotationPresent(UaInputArgument.class)) {
                String name = parameter.getAnnotation(UaInputArgument.class).name();
                String description = parameter.getAnnotation(UaInputArgument.class).description();

                Class<?> parameterType = (Class<?>) parameterTypes[i];

                int dimensions = 0;
                while (parameterType.isArray()) {
                    parameterType = parameterType.getComponentType();
                    dimensions++;
                }

                UInteger[] arrayDimensions = dimensions > 0 ? new UInteger[dimensions] : null;

                inputArguments.add(new Argument(
                    name,
                    getDataType(parameterType),
                    dimensions > 0 ? dimensions : ValueRanks.Scalar,
                    arrayDimensions,
                    LocalizedText.english(description)
                ));
            }

            if (parameter.isAnnotationPresent(UaOutputArgument.class)) {
                String name = parameter.getAnnotation(UaOutputArgument.class).name();
                String description = parameter.getAnnotation(UaOutputArgument.class).description();

                ParameterizedType parameterType = (ParameterizedType) parameterTypes[i];
                Class<?> actualType = (Class<?>) parameterType.getActualTypeArguments()[0];

                int dimensions = 0;
                while (actualType.isArray()) {
                    actualType = actualType.getComponentType();
                    dimensions++;
                }

                UInteger[] arrayDimensions = dimensions > 0 ? new UInteger[dimensions] : null;

                outputArguments.add(new Argument(
                    name,
                    getDataType(actualType),
                    dimensions > 0 ? dimensions : ValueRanks.Scalar,
                    arrayDimensions,
                    LocalizedText.english(description)
                ));
            }
        }

        return new AnnotationBasedInvocationHandler(
            nodeMap,
            inputArguments,
            outputArguments,
            annotatedObject
        );
    }

    private static NodeId getDataType(Class<?> clazz) {
        return new NodeId(0, TypeUtil.getBuiltinTypeId(clazz));
    }

    public interface Out<T> {
        void set(T value);
    }

    public interface InvocationContext extends AccessContext {
        UaObjectNode getObjectNode();

        void setFailure(UaException failure);
    }

    private static class OutImpl<T> implements Out<T> {

        private final AtomicReference<T> value = new AtomicReference<>();

        private volatile boolean set = false;
        private final CountDownLatch latch;

        public OutImpl(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public synchronized void set(T value) {
            this.value.set(value);

            if (!set) {
                latch.countDown();
                set = true;
            }
        }

        T get() {
            return value.get();
        }

    }

    private static class InvocationContextImpl implements InvocationContext {
        private final AccessContext accessContext;
        private final UaObjectNode objectNode;
        private final CompletableFuture<CallMethodResult> future;
        private final StatusCode[] inputArgumentResults;
        private final CountDownLatch latch;

        private InvocationContextImpl(
            AccessContext accessContext,
            UaObjectNode objectNode,
            CompletableFuture<CallMethodResult> future,
            StatusCode[] inputArgumentResults,
            CountDownLatch latch) {

            this.accessContext = accessContext;
            this.objectNode = objectNode;
            this.inputArgumentResults = inputArgumentResults;
            this.future = future;
            this.latch = latch;
        }

        @Override
        public Optional<Session> getSession() {
            return accessContext.getSession();
        }

        @Override
        public UaObjectNode getObjectNode() {
            return objectNode;
        }

        @Override
        public void setFailure(UaException failure) {
            StatusCode statusCode = failure.getStatusCode();

            future.complete(new CallMethodResult(
                statusCode, inputArgumentResults,
                new DiagnosticInfo[0], new Variant[0]
            ));

            while (latch.getCount() > 0) latch.countDown();
        }
    }

}
