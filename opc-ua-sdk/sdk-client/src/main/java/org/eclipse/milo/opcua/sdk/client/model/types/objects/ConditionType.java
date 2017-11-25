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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.ConditionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface ConditionType extends BaseEventType {
    QualifiedProperty<NodeId> CONDITION_CLASS_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionClassId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<LocalizedText> CONDITION_CLASS_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionClassName",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<String> CONDITION_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConditionName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<NodeId> BRANCH_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BranchId",
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

    QualifiedProperty<String> CLIENT_USER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientUserId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    CompletableFuture<? extends PropertyType> getConditionClassIdNode();

    CompletableFuture<NodeId> getConditionClassId();

    CompletableFuture<StatusCode> setConditionClassId(NodeId value);

    CompletableFuture<? extends PropertyType> getConditionClassNameNode();

    CompletableFuture<LocalizedText> getConditionClassName();

    CompletableFuture<StatusCode> setConditionClassName(LocalizedText value);

    CompletableFuture<? extends PropertyType> getConditionNameNode();

    CompletableFuture<String> getConditionName();

    CompletableFuture<StatusCode> setConditionName(String value);

    CompletableFuture<? extends PropertyType> getBranchIdNode();

    CompletableFuture<NodeId> getBranchId();

    CompletableFuture<StatusCode> setBranchId(NodeId value);

    CompletableFuture<? extends PropertyType> getRetainNode();

    CompletableFuture<Boolean> getRetain();

    CompletableFuture<StatusCode> setRetain(Boolean value);

    CompletableFuture<? extends PropertyType> getClientUserIdNode();

    CompletableFuture<String> getClientUserId();

    CompletableFuture<StatusCode> setClientUserId(String value);

    CompletableFuture<? extends TwoStateVariableType> getEnabledStateNode();

    CompletableFuture<LocalizedText> getEnabledState();

    CompletableFuture<StatusCode> setEnabledState(LocalizedText value);

    CompletableFuture<? extends ConditionVariableType> getQualityNode();

    CompletableFuture<StatusCode> getQuality();

    CompletableFuture<StatusCode> setQuality(StatusCode value);

    CompletableFuture<? extends ConditionVariableType> getLastSeverityNode();

    CompletableFuture<UShort> getLastSeverity();

    CompletableFuture<StatusCode> setLastSeverity(UShort value);

    CompletableFuture<? extends ConditionVariableType> getCommentNode();

    CompletableFuture<LocalizedText> getComment();

    CompletableFuture<StatusCode> setComment(LocalizedText value);
}
