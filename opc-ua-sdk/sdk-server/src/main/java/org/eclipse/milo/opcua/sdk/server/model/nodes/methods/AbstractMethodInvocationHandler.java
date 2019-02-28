/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.methods;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;

abstract class AbstractMethodInvocationHandler implements MethodInvocationHandler {

    private final UaMethodNode node;

    public AbstractMethodInvocationHandler(UaMethodNode node) {
        this.node = node;
    }

    @Override
    public void invoke(
        AccessContext accessContext,
        CallMethodRequest request,
        CompletableFuture<CallMethodResult> future) {

        StatusCode[] inputArgumentResults = new StatusCode[0];

        try {
            checkExecutableAttributes(accessContext);

            Variant[] inputValues = request.getInputArguments();
            if (inputValues == null) inputValues = new Variant[0];

            if (inputValues.length != getInputArguments().length) {
                throw new UaException(StatusCodes.Bad_ArgumentsMissing);
            }

            inputArgumentResults = new StatusCode[inputValues.length];

            for (int i = 0; i < inputValues.length; i++) {
                Argument argument = getInputArguments()[i];

                Variant variant = inputValues[i];

                boolean dataTypeMatch = variant.getDataType()
                    .map(type -> type.equals(argument.getDataType()))
                    .orElse(false);

                if (dataTypeMatch) {
                    inputArgumentResults[i] = StatusCode.GOOD;
                } else {
                    inputArgumentResults[i] = new StatusCode(StatusCodes.Bad_TypeMismatch);
                }
            }

            if (Arrays.stream(inputArgumentResults).anyMatch(StatusCode::isBad)) {
                throw new UaException(StatusCodes.Bad_InvalidArgument);
            }

            Variant[] outputValues = invoke(accessContext, inputValues);

            CallMethodResult result = new CallMethodResult(
                StatusCode.GOOD,
                inputArgumentResults,
                new DiagnosticInfo[0],
                outputValues
            );

            future.complete(result);
        } catch (UaException e) {
            CallMethodResult result = new CallMethodResult(
                e.getStatusCode(),
                inputArgumentResults,
                new DiagnosticInfo[0],
                new Variant[0]
            );

            future.complete(result);
        }
    }

    private void checkExecutableAttributes(AccessContext accessContext) throws UaException {
        AttributeContext attributeContext = new AttributeContext(
            node.getNodeContext().getServer(),
            accessContext.getSession().orElse(null)
        );

        Boolean executable = AttributeUtil.extract(
            node.getAttribute(
                attributeContext,
                AttributeId.Executable
            )
        );

        if (executable == null || !executable) {
            throw new UaException(StatusCode.BAD);
        }

        Boolean userExecutable = AttributeUtil.extract(
            node.getAttribute(
                attributeContext,
                AttributeId.UserExecutable
            )
        );

        if (userExecutable == null || !userExecutable) {
            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

    protected abstract Argument[] getInputArguments();

    protected abstract Argument[] getOutputArguments();

    protected abstract Variant[] invoke(AccessContext accessContext, Variant[] inputArguments) throws UaException;

}
