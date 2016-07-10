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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.ConditionVariableType;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface ConditionType extends BaseEventType {

    Property<NodeId> CONDITION_CLASS_ID = new BasicProperty<>(
        QualifiedName.parse("0:ConditionClassId"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<LocalizedText> CONDITION_CLASS_NAME = new BasicProperty<>(
        QualifiedName.parse("0:ConditionClassName"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );

    Property<String> CONDITION_NAME = new BasicProperty<>(
        QualifiedName.parse("0:ConditionName"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<NodeId> BRANCH_ID = new BasicProperty<>(
        QualifiedName.parse("0:BranchId"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<Boolean> RETAIN = new BasicProperty<>(
        QualifiedName.parse("0:Retain"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<String> CLIENT_USER_ID = new BasicProperty<>(
        QualifiedName.parse("0:ClientUserId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    NodeId getConditionClassId();

    PropertyType getConditionClassIdNode();

    void setConditionClassId(NodeId value);

    LocalizedText getConditionClassName();

    PropertyType getConditionClassNameNode();

    void setConditionClassName(LocalizedText value);

    String getConditionName();

    PropertyType getConditionNameNode();

    void setConditionName(String value);

    NodeId getBranchId();

    PropertyType getBranchIdNode();

    void setBranchId(NodeId value);

    Boolean getRetain();

    PropertyType getRetainNode();

    void setRetain(Boolean value);

    String getClientUserId();

    PropertyType getClientUserIdNode();

    void setClientUserId(String value);

    LocalizedText getEnabledState();

    TwoStateVariableType getEnabledStateNode();

    void setEnabledState(LocalizedText value);

    StatusCode getQuality();

    ConditionVariableType getQualityNode();

    void setQuality(StatusCode value);

    UShort getLastSeverity();

    ConditionVariableType getLastSeverityNode();

    void setLastSeverity(UShort value);

    LocalizedText getComment();

    ConditionVariableType getCommentNode();

    void setComment(LocalizedText value);
}
