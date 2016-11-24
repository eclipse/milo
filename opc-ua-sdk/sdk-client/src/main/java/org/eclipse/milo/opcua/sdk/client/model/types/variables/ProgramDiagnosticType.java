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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;


public interface ProgramDiagnosticType extends BaseDataVariableType {

    Property<NodeId> CREATE_SESSION_ID = new BasicProperty<>(
        QualifiedName.parse("0:CreateSessionId"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<String> CREATE_CLIENT_NAME = new BasicProperty<>(
        QualifiedName.parse("0:CreateClientName"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<DateTime> INVOCATION_CREATION_TIME = new BasicProperty<>(
        QualifiedName.parse("0:InvocationCreationTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<DateTime> LAST_TRANSITION_TIME = new BasicProperty<>(
        QualifiedName.parse("0:LastTransitionTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<String> LAST_METHOD_CALL = new BasicProperty<>(
        QualifiedName.parse("0:LastMethodCall"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<NodeId> LAST_METHOD_SESSION_ID = new BasicProperty<>(
        QualifiedName.parse("0:LastMethodSessionId"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<Argument[]> LAST_METHOD_INPUT_ARGUMENTS = new BasicProperty<>(
        QualifiedName.parse("0:LastMethodInputArguments"),
        NodeId.parse("ns=0;i=296"),
        1,
        Argument[].class
    );

    Property<Argument[]> LAST_METHOD_OUTPUT_ARGUMENTS = new BasicProperty<>(
        QualifiedName.parse("0:LastMethodOutputArguments"),
        NodeId.parse("ns=0;i=296"),
        1,
        Argument[].class
    );

    Property<DateTime> LAST_METHOD_CALL_TIME = new BasicProperty<>(
        QualifiedName.parse("0:LastMethodCallTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<StatusResult> LAST_METHOD_RETURN_STATUS = new BasicProperty<>(
        QualifiedName.parse("0:LastMethodReturnStatus"),
        NodeId.parse("ns=0;i=299"),
        -1,
        StatusResult.class
    );


    CompletableFuture<? extends PropertyType> createSessionId();

    CompletableFuture<NodeId> getCreateSessionId();

    CompletableFuture<StatusCode> setCreateSessionId(NodeId value);

    CompletableFuture<? extends PropertyType> createClientName();

    CompletableFuture<String> getCreateClientName();

    CompletableFuture<StatusCode> setCreateClientName(String value);

    CompletableFuture<? extends PropertyType> invocationCreationTime();

    CompletableFuture<DateTime> getInvocationCreationTime();

    CompletableFuture<StatusCode> setInvocationCreationTime(DateTime value);

    CompletableFuture<? extends PropertyType> lastTransitionTime();

    CompletableFuture<DateTime> getLastTransitionTime();

    CompletableFuture<StatusCode> setLastTransitionTime(DateTime value);

    CompletableFuture<? extends PropertyType> lastMethodCall();

    CompletableFuture<String> getLastMethodCall();

    CompletableFuture<StatusCode> setLastMethodCall(String value);

    CompletableFuture<? extends PropertyType> lastMethodSessionId();

    CompletableFuture<NodeId> getLastMethodSessionId();

    CompletableFuture<StatusCode> setLastMethodSessionId(NodeId value);

    CompletableFuture<? extends PropertyType> lastMethodInputArguments();

    CompletableFuture<Argument[]> getLastMethodInputArguments();

    CompletableFuture<StatusCode> setLastMethodInputArguments(Argument[] value);

    CompletableFuture<? extends PropertyType> lastMethodOutputArguments();

    CompletableFuture<Argument[]> getLastMethodOutputArguments();

    CompletableFuture<StatusCode> setLastMethodOutputArguments(Argument[] value);

    CompletableFuture<? extends PropertyType> lastMethodCallTime();

    CompletableFuture<DateTime> getLastMethodCallTime();

    CompletableFuture<StatusCode> setLastMethodCallTime(DateTime value);

    CompletableFuture<? extends PropertyType> lastMethodReturnStatus();

    CompletableFuture<StatusResult> getLastMethodReturnStatus();

    CompletableFuture<StatusCode> setLastMethodReturnStatus(StatusResult value);


}