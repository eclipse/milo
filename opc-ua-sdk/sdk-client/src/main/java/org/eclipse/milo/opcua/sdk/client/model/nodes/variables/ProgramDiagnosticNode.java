/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;

public class ProgramDiagnosticNode extends BaseDataVariableNode implements ProgramDiagnosticType {
    public ProgramDiagnosticNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getCreateSessionIdNode() {
        return getPropertyNode(ProgramDiagnosticType.CREATE_SESSION_ID);
    }

    public CompletableFuture<NodeId> getCreateSessionId() {
        return getProperty(ProgramDiagnosticType.CREATE_SESSION_ID);
    }

    public CompletableFuture<StatusCode> setCreateSessionId(NodeId value) {
        return setProperty(ProgramDiagnosticType.CREATE_SESSION_ID, value);
    }

    public CompletableFuture<PropertyNode> getCreateClientNameNode() {
        return getPropertyNode(ProgramDiagnosticType.CREATE_CLIENT_NAME);
    }

    public CompletableFuture<String> getCreateClientName() {
        return getProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME);
    }

    public CompletableFuture<StatusCode> setCreateClientName(String value) {
        return setProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME, value);
    }

    public CompletableFuture<PropertyNode> getInvocationCreationTimeNode() {
        return getPropertyNode(ProgramDiagnosticType.INVOCATION_CREATION_TIME);
    }

    public CompletableFuture<DateTime> getInvocationCreationTime() {
        return getProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME);
    }

    public CompletableFuture<StatusCode> setInvocationCreationTime(DateTime value) {
        return setProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME, value);
    }

    public CompletableFuture<PropertyNode> getLastTransitionTimeNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_TRANSITION_TIME);
    }

    public CompletableFuture<DateTime> getLastTransitionTime() {
        return getProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME);
    }

    public CompletableFuture<StatusCode> setLastTransitionTime(DateTime value) {
        return setProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME, value);
    }

    public CompletableFuture<PropertyNode> getLastMethodCallNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL);
    }

    public CompletableFuture<String> getLastMethodCall() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_CALL);
    }

    public CompletableFuture<StatusCode> setLastMethodCall(String value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_CALL, value);
    }

    public CompletableFuture<PropertyNode> getLastMethodSessionIdNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_SESSION_ID);
    }

    public CompletableFuture<NodeId> getLastMethodSessionId() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID);
    }

    public CompletableFuture<StatusCode> setLastMethodSessionId(NodeId value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID, value);
    }

    public CompletableFuture<PropertyNode> getLastMethodInputArgumentsNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS);
    }

    public CompletableFuture<Argument[]> getLastMethodInputArguments() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS);
    }

    public CompletableFuture<StatusCode> setLastMethodInputArguments(Argument[] value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS, value);
    }

    public CompletableFuture<PropertyNode> getLastMethodOutputArgumentsNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS);
    }

    public CompletableFuture<Argument[]> getLastMethodOutputArguments() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS);
    }

    public CompletableFuture<StatusCode> setLastMethodOutputArguments(Argument[] value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS, value);
    }

    public CompletableFuture<PropertyNode> getLastMethodCallTimeNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL_TIME);
    }

    public CompletableFuture<DateTime> getLastMethodCallTime() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME);
    }

    public CompletableFuture<StatusCode> setLastMethodCallTime(DateTime value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME, value);
    }

    public CompletableFuture<PropertyNode> getLastMethodReturnStatusNode() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS);
    }

    public CompletableFuture<StatusResult> getLastMethodReturnStatus() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS);
    }

    public CompletableFuture<StatusCode> setLastMethodReturnStatus(StatusResult value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS, value);
    }
}
