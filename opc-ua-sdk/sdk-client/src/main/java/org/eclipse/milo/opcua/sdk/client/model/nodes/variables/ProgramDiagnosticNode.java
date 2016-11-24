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

    @Override
    public CompletableFuture<PropertyNode> createSessionId() {
        return getPropertyNode(ProgramDiagnosticType.CREATE_SESSION_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getCreateSessionId() {
        return getProperty(ProgramDiagnosticType.CREATE_SESSION_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setCreateSessionId(NodeId value) {
        return setProperty(ProgramDiagnosticType.CREATE_SESSION_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> createClientName() {
        return getPropertyNode(ProgramDiagnosticType.CREATE_CLIENT_NAME.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getCreateClientName() {
        return getProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME);
    }

    @Override
    public CompletableFuture<StatusCode> setCreateClientName(String value) {
        return setProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> invocationCreationTime() {
        return getPropertyNode(ProgramDiagnosticType.INVOCATION_CREATION_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getInvocationCreationTime() {
        return getProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setInvocationCreationTime(DateTime value) {
        return setProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastTransitionTime() {
        return getPropertyNode(ProgramDiagnosticType.LAST_TRANSITION_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getLastTransitionTime() {
        return getProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setLastTransitionTime(DateTime value) {
        return setProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastMethodCall() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getLastMethodCall() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_CALL);
    }

    @Override
    public CompletableFuture<StatusCode> setLastMethodCall(String value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_CALL, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastMethodSessionId() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_SESSION_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getLastMethodSessionId() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setLastMethodSessionId(NodeId value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastMethodInputArguments() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS.getBrowseName());
    }

    @Override
    public CompletableFuture<Argument[]> getLastMethodInputArguments() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS);
    }

    @Override
    public CompletableFuture<StatusCode> setLastMethodInputArguments(Argument[] value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastMethodOutputArguments() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS.getBrowseName());
    }

    @Override
    public CompletableFuture<Argument[]> getLastMethodOutputArguments() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS);
    }

    @Override
    public CompletableFuture<StatusCode> setLastMethodOutputArguments(Argument[] value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastMethodCallTime() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getLastMethodCallTime() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setLastMethodCallTime(DateTime value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastMethodReturnStatus() {
        return getPropertyNode(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS.getBrowseName());
    }

    @Override
    public CompletableFuture<StatusResult> getLastMethodReturnStatus() {
        return getProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS);
    }

    @Override
    public CompletableFuture<StatusCode> setLastMethodReturnStatus(StatusResult value) {
        return setProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS, value);
    }


}