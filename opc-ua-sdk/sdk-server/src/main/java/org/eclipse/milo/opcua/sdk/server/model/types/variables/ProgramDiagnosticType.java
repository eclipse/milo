/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
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

    PropertyType getCreateSessionIdNode();

    NodeId getCreateSessionId();

    void setCreateSessionId(NodeId value);

    PropertyType getCreateClientNameNode();

    String getCreateClientName();

    void setCreateClientName(String value);

    PropertyType getInvocationCreationTimeNode();

    DateTime getInvocationCreationTime();

    void setInvocationCreationTime(DateTime value);

    PropertyType getLastTransitionTimeNode();

    DateTime getLastTransitionTime();

    void setLastTransitionTime(DateTime value);

    PropertyType getLastMethodCallNode();

    String getLastMethodCall();

    void setLastMethodCall(String value);

    PropertyType getLastMethodSessionIdNode();

    NodeId getLastMethodSessionId();

    void setLastMethodSessionId(NodeId value);

    PropertyType getLastMethodInputArgumentsNode();

    Argument[] getLastMethodInputArguments();

    void setLastMethodInputArguments(Argument[] value);

    PropertyType getLastMethodOutputArgumentsNode();

    Argument[] getLastMethodOutputArguments();

    void setLastMethodOutputArguments(Argument[] value);

    PropertyType getLastMethodCallTimeNode();

    DateTime getLastMethodCallTime();

    void setLastMethodCallTime(DateTime value);

    PropertyType getLastMethodReturnStatusNode();

    StatusResult getLastMethodReturnStatus();

    void setLastMethodReturnStatus(StatusResult value);
}
