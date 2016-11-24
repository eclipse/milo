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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.ConditionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
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


    CompletableFuture<? extends PropertyType> conditionClassId();

    CompletableFuture<NodeId> getConditionClassId();

    CompletableFuture<StatusCode> setConditionClassId(NodeId value);

    CompletableFuture<? extends PropertyType> conditionClassName();

    CompletableFuture<LocalizedText> getConditionClassName();

    CompletableFuture<StatusCode> setConditionClassName(LocalizedText value);

    CompletableFuture<? extends PropertyType> conditionName();

    CompletableFuture<String> getConditionName();

    CompletableFuture<StatusCode> setConditionName(String value);

    CompletableFuture<? extends PropertyType> branchId();

    CompletableFuture<NodeId> getBranchId();

    CompletableFuture<StatusCode> setBranchId(NodeId value);

    CompletableFuture<? extends PropertyType> retain();

    CompletableFuture<Boolean> getRetain();

    CompletableFuture<StatusCode> setRetain(Boolean value);

    CompletableFuture<? extends PropertyType> clientUserId();

    CompletableFuture<String> getClientUserId();

    CompletableFuture<StatusCode> setClientUserId(String value);


    CompletableFuture<? extends TwoStateVariableType> enabledState();

    CompletableFuture<LocalizedText> getEnabledState();

    CompletableFuture<StatusCode> setEnabledState(LocalizedText value);

    CompletableFuture<? extends ConditionVariableType> quality();

    CompletableFuture<StatusCode> getQuality();

    CompletableFuture<StatusCode> setQuality(StatusCode value);

    CompletableFuture<? extends ConditionVariableType> lastSeverity();

    CompletableFuture<UShort> getLastSeverity();

    CompletableFuture<StatusCode> setLastSeverity(UShort value);

    CompletableFuture<? extends ConditionVariableType> comment();

    CompletableFuture<LocalizedText> getComment();

    CompletableFuture<StatusCode> setComment(LocalizedText value);

}