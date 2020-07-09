/*
 * Copyright (c) 2020 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;

public class UaMethod {

    private final OpcUaClient client;
    private final UaObjectNode objectNode;
    private final UaMethodNode methodNode;
    private final Argument[] inputArguments;
    private final Argument[] outputArguments;

    public UaMethod(
        OpcUaClient client,
        UaObjectNode objectNode,
        UaMethodNode methodNode,
        Argument[] inputArguments,
        Argument[] outputArguments
    ) {

        this.client = client;
        this.objectNode = objectNode;
        this.methodNode = methodNode;
        this.inputArguments = inputArguments;
        this.outputArguments = outputArguments;
    }

    public Argument[] getInputArguments() {
        return inputArguments;
    }

    public Argument[] getOutputArguments() {
        return outputArguments;
    }

    public Variant[] call(Variant[] inputs) throws UaException {
        try {
            return callAsync(inputs).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Variant[]> callAsync(Variant[] inputs) {
        CallMethodRequest request = new CallMethodRequest(
            objectNode.getNodeId(),
            methodNode.getNodeId(),
            inputs
        );

        return client.call(request).thenApply(result -> {
            StatusCode statusCode = result.getStatusCode();

            if (statusCode.isGood()) {
                return result.getOutputArguments();
            } else {
                throw new RuntimeException(new UaMethodException(
                    statusCode,
                    result.getInputArgumentResults(),
                    result.getInputArgumentDiagnosticInfos()
                ));
            }
        });
    }

}
