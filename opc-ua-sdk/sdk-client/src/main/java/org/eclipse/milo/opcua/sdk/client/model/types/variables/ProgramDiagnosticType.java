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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;

public interface ProgramDiagnosticType extends BaseDataVariableType {
    QualifiedProperty<NodeId> CREATE_SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CreateSessionId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<String> CREATE_CLIENT_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CreateClientName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<DateTime> INVOCATION_CREATION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InvocationCreationTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> LAST_TRANSITION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastTransitionTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<String> LAST_METHOD_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodCall",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<NodeId> LAST_METHOD_SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodSessionId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Argument[]> LAST_METHOD_INPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodInputArguments",
        NodeId.parse("ns=0;i=296"),
        ValueRanks.OneDimension,
        Argument[].class
    );

    QualifiedProperty<Argument[]> LAST_METHOD_OUTPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodOutputArguments",
        NodeId.parse("ns=0;i=296"),
        ValueRanks.OneDimension,
        Argument[].class
    );

    QualifiedProperty<DateTime> LAST_METHOD_CALL_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodCallTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<StatusResult> LAST_METHOD_RETURN_STATUS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodReturnStatus",
        NodeId.parse("ns=0;i=299"),
        ValueRanks.Scalar,
        StatusResult.class
    );

    CompletableFuture<? extends PropertyType> getCreateSessionIdNode();

    CompletableFuture<NodeId> getCreateSessionId();

    CompletableFuture<StatusCode> setCreateSessionId(NodeId value);

    CompletableFuture<? extends PropertyType> getCreateClientNameNode();

    CompletableFuture<String> getCreateClientName();

    CompletableFuture<StatusCode> setCreateClientName(String value);

    CompletableFuture<? extends PropertyType> getInvocationCreationTimeNode();

    CompletableFuture<DateTime> getInvocationCreationTime();

    CompletableFuture<StatusCode> setInvocationCreationTime(DateTime value);

    CompletableFuture<? extends PropertyType> getLastTransitionTimeNode();

    CompletableFuture<DateTime> getLastTransitionTime();

    CompletableFuture<StatusCode> setLastTransitionTime(DateTime value);

    CompletableFuture<? extends PropertyType> getLastMethodCallNode();

    CompletableFuture<String> getLastMethodCall();

    CompletableFuture<StatusCode> setLastMethodCall(String value);

    CompletableFuture<? extends PropertyType> getLastMethodSessionIdNode();

    CompletableFuture<NodeId> getLastMethodSessionId();

    CompletableFuture<StatusCode> setLastMethodSessionId(NodeId value);

    CompletableFuture<? extends PropertyType> getLastMethodInputArgumentsNode();

    CompletableFuture<Argument[]> getLastMethodInputArguments();

    CompletableFuture<StatusCode> setLastMethodInputArguments(Argument[] value);

    CompletableFuture<? extends PropertyType> getLastMethodOutputArgumentsNode();

    CompletableFuture<Argument[]> getLastMethodOutputArguments();

    CompletableFuture<StatusCode> setLastMethodOutputArguments(Argument[] value);

    CompletableFuture<? extends PropertyType> getLastMethodCallTimeNode();

    CompletableFuture<DateTime> getLastMethodCallTime();

    CompletableFuture<StatusCode> setLastMethodCallTime(DateTime value);

    CompletableFuture<? extends PropertyType> getLastMethodReturnStatusNode();

    CompletableFuture<StatusResult> getLastMethodReturnStatus();

    CompletableFuture<StatusCode> setLastMethodReturnStatus(StatusResult value);
}
