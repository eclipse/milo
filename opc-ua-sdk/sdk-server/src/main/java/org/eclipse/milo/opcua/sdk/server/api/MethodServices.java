/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.slf4j.LoggerFactory;

public interface MethodServices {

    /**
     * Invoke one or more methods belonging to this {@link MethodServices}.
     *
     * @param context  the {@link CallContext}.
     * @param requests The {@link CallMethodRequest}s for the methods to invoke.
     */
    default void call(CallContext context, List<CallMethodRequest> requests) {
        List<CallMethodResult> results = Lists.newArrayListWithCapacity(requests.size());

        for (CallMethodRequest request : requests) {
            MethodInvocationHandler handler = getInvocationHandler(
                context.getServer(),
                request.getObjectId(),
                request.getMethodId()
            ).orElse(MethodInvocationHandler.NODE_ID_UNKNOWN);

            try {
                results.add(handler.invoke(context, request));
            } catch (Throwable t) {
                LoggerFactory.getLogger(getClass())
                    .error("Uncaught Throwable invoking method handler for methodId={}.", request.getMethodId(), t);

                results.add(
                    new CallMethodResult(
                        new StatusCode(StatusCodes.Bad_InternalError),
                        new StatusCode[0], new DiagnosticInfo[0], new Variant[0])
                );
            }
        }

        context.complete(results);
    }

    /**
     * Get the {@link MethodInvocationHandler} for the method identified by {@code methodId}, if it exists.
     *
     * @param server   the {@link OpcUaServer}.
     * @param objectId the {@link NodeId} identifying the object the method will be invoked on.
     * @param methodId the {@link NodeId} identifying the method.
     * @return the {@link MethodInvocationHandler} for {@code methodId}, if it exists.
     */
    default Optional<MethodInvocationHandler> getInvocationHandler(
        OpcUaServer server,
        NodeId objectId,
        NodeId methodId
    ) {

        return server.getNodeManager().getNode(objectId).flatMap(node -> {
            UaMethodNode methodNode = null;

            if (node instanceof UaObjectNode) {
                UaObjectNode objectNode = (UaObjectNode) node;

                methodNode = objectNode.findMethodNode(methodId);
            } else if (node instanceof UaObjectTypeNode) {
                UaObjectTypeNode objectTypeNode = (UaObjectTypeNode) node;

                methodNode = objectTypeNode.findMethodNode(methodId);
            }

            if (methodNode != null) {
                return Optional.of(methodNode.getInvocationHandler());
            } else {
                return Optional.empty();
            }
        });
    }

    final class CallContext extends OperationContext<CallMethodRequest, CallMethodResult> {
        public CallContext(OpcUaServer server,
                           @Nullable Session session,
                           DiagnosticsContext<CallMethodRequest> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public CallContext(OpcUaServer server,
                           @Nullable Session session,
                           CompletableFuture<List<CallMethodResult>> future,
                           DiagnosticsContext<CallMethodRequest> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }

}
