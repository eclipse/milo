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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface TwoStateVariableType extends StateVariableType {
    QualifiedProperty<Boolean> ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Id",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> TRANSITION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TransitionTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> EFFECTIVE_TRANSITION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EffectiveTransitionTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<LocalizedText> TRUE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TrueState",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText> FALSE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "FalseState",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    CompletableFuture<? extends PropertyType> getIdNode();

    CompletableFuture<Boolean> getId();

    CompletableFuture<StatusCode> setId(Boolean value);

    CompletableFuture<? extends PropertyType> getTransitionTimeNode();

    CompletableFuture<DateTime> getTransitionTime();

    CompletableFuture<StatusCode> setTransitionTime(DateTime value);

    CompletableFuture<? extends PropertyType> getEffectiveTransitionTimeNode();

    CompletableFuture<DateTime> getEffectiveTransitionTime();

    CompletableFuture<StatusCode> setEffectiveTransitionTime(DateTime value);

    CompletableFuture<? extends PropertyType> getTrueStateNode();

    CompletableFuture<LocalizedText> getTrueState();

    CompletableFuture<StatusCode> setTrueState(LocalizedText value);

    CompletableFuture<? extends PropertyType> getFalseStateNode();

    CompletableFuture<LocalizedText> getFalseState();

    CompletableFuture<StatusCode> setFalseState(LocalizedText value);
}
