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

public interface OptionSetType extends BaseDataVariableType {
    QualifiedProperty<LocalizedText[]> OPTION_SET_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OptionSetValues",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.OneDimension,
        LocalizedText[].class
    );

    QualifiedProperty<Boolean[]> BIT_MASK = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BitMask",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.OneDimension,
        Boolean[].class
    );

    CompletableFuture<? extends PropertyType> getOptionSetValuesNode();

    CompletableFuture<LocalizedText[]> getOptionSetValues();

    CompletableFuture<StatusCode> setOptionSetValues(LocalizedText[] value);

    CompletableFuture<? extends PropertyType> getBitMaskNode();

    CompletableFuture<Boolean[]> getBitMask();

    CompletableFuture<StatusCode> setBitMask(Boolean[] value);
}
