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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;

public interface MultiStateValueDiscreteType extends DiscreteItemType {
    QualifiedProperty<EnumValueType[]> ENUM_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EnumValues",
        NodeId.parse("ns=0;i=7594"),
        ValueRanks.OneDimension,
        EnumValueType[].class
    );

    QualifiedProperty<LocalizedText> VALUE_AS_TEXT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ValueAsText",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    CompletableFuture<? extends PropertyType> getEnumValuesNode();

    CompletableFuture<EnumValueType[]> getEnumValues();

    CompletableFuture<StatusCode> setEnumValues(EnumValueType[] value);

    CompletableFuture<? extends PropertyType> getValueAsTextNode();

    CompletableFuture<LocalizedText> getValueAsText();

    CompletableFuture<StatusCode> setValueAsText(LocalizedText value);
}
