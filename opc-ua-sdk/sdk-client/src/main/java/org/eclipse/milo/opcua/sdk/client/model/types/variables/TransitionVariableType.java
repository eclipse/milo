/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface TransitionVariableType extends BaseDataVariableType {
    QualifiedProperty<Object> ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Id",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<QualifiedName> NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Name",
        NodeId.parse("ns=0;i=20"),
        ValueRanks.Scalar,
        QualifiedName.class
    );

    QualifiedProperty<UInteger> NUMBER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Number",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
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

    CompletableFuture<? extends PropertyType> getIdNode();

    CompletableFuture<?> getId();

    CompletableFuture<StatusCode> setId(Object value);

    CompletableFuture<? extends PropertyType> getNameNode();

    CompletableFuture<QualifiedName> getName();

    CompletableFuture<StatusCode> setName(QualifiedName value);

    CompletableFuture<? extends PropertyType> getNumberNode();

    CompletableFuture<UInteger> getNumber();

    CompletableFuture<StatusCode> setNumber(UInteger value);

    CompletableFuture<? extends PropertyType> getTransitionTimeNode();

    CompletableFuture<DateTime> getTransitionTime();

    CompletableFuture<StatusCode> setTransitionTime(DateTime value);

    CompletableFuture<? extends PropertyType> getEffectiveTransitionTimeNode();

    CompletableFuture<DateTime> getEffectiveTransitionTime();

    CompletableFuture<StatusCode> setEffectiveTransitionTime(DateTime value);
}
