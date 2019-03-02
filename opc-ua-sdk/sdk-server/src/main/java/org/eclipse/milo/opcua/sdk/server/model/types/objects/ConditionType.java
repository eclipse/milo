/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ConditionVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface ConditionType extends BaseEventType {
    QualifiedProperty<String> CLIENT_USER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientUserId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<LocalizedText> CONDITION_CLASS_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionClassName",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<NodeId> CONDITION_CLASS_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionClassId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Boolean> RETAIN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Retain",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<NodeId> BRANCH_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BranchId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<String> CONDITION_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getClientUserIdNode();

    String getClientUserId();

    void setClientUserId(String value);

    PropertyType getConditionClassNameNode();

    LocalizedText getConditionClassName();

    void setConditionClassName(LocalizedText value);

    PropertyType getConditionClassIdNode();

    NodeId getConditionClassId();

    void setConditionClassId(NodeId value);

    PropertyType getRetainNode();

    Boolean getRetain();

    void setRetain(Boolean value);

    PropertyType getBranchIdNode();

    NodeId getBranchId();

    void setBranchId(NodeId value);

    PropertyType getConditionNameNode();

    String getConditionName();

    void setConditionName(String value);

    UaMethodNode getAddCommentMethodNode();

    UaMethodNode getDisableMethodNode();

    UaMethodNode getEnableMethodNode();

    UaMethodNode getConditionRefreshMethodNode();

    UaMethodNode getConditionRefresh2MethodNode();

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    ConditionVariableType getLastSeverityNode();

    UShort getLastSeverity();

    void setLastSeverity(UShort value);

    ConditionVariableType getCommentNode();

    LocalizedText getComment();

    void setComment(LocalizedText value);

    ConditionVariableType getQualityNode();

    StatusCode getQuality();

    void setQuality(StatusCode value);
}
