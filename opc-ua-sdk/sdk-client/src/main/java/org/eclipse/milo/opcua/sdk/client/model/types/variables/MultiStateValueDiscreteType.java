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
