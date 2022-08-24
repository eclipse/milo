/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ConditionVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.5.2</a>
 */
public interface ConditionType extends BaseEventType {
    QualifiedProperty<NodeId> CONDITION_CLASS_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionClassId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<LocalizedText> CONDITION_CLASS_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionClassName",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    QualifiedProperty<NodeId[]> CONDITION_SUB_CLASS_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionSubClassId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        1,
        NodeId[].class
    );

    QualifiedProperty<LocalizedText[]> CONDITION_SUB_CLASS_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionSubClassName",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        1,
        LocalizedText[].class
    );

    QualifiedProperty<String> CONDITION_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionName",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<NodeId> BRANCH_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BranchId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<Boolean> RETAIN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Retain",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<String> CLIENT_USER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientUserId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    NodeId getConditionClassId();

    void setConditionClassId(NodeId value);

    PropertyType getConditionClassIdNode();

    LocalizedText getConditionClassName();

    void setConditionClassName(LocalizedText value);

    PropertyType getConditionClassNameNode();

    NodeId[] getConditionSubClassId();

    void setConditionSubClassId(NodeId[] value);

    PropertyType getConditionSubClassIdNode();

    LocalizedText[] getConditionSubClassName();

    void setConditionSubClassName(LocalizedText[] value);

    PropertyType getConditionSubClassNameNode();

    String getConditionName();

    void setConditionName(String value);

    PropertyType getConditionNameNode();

    NodeId getBranchId();

    void setBranchId(NodeId value);

    PropertyType getBranchIdNode();

    Boolean getRetain();

    void setRetain(Boolean value);

    PropertyType getRetainNode();

    String getClientUserId();

    void setClientUserId(String value);

    PropertyType getClientUserIdNode();

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    ConditionVariableType getQualityNode();

    StatusCode getQuality();

    void setQuality(StatusCode value);

    ConditionVariableType getLastSeverityNode();

    UShort getLastSeverity();

    void setLastSeverity(UShort value);

    ConditionVariableType getCommentNode();

    LocalizedText getComment();

    void setComment(LocalizedText value);

    MethodNode getDisableMethodNode();

    MethodNode getEnableMethodNode();

    MethodNode getAddCommentMethodNode();

    MethodNode getConditionRefreshMethodNode();

    MethodNode getConditionRefresh2MethodNode();
}
