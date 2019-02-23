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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface StateVariableType extends BaseDataVariableType {
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

    QualifiedProperty<LocalizedText> EFFECTIVE_DISPLAY_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EffectiveDisplayName",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
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

    CompletableFuture<? extends PropertyType> getEffectiveDisplayNameNode();

    CompletableFuture<LocalizedText> getEffectiveDisplayName();

    CompletableFuture<StatusCode> setEffectiveDisplayName(LocalizedText value);
}
