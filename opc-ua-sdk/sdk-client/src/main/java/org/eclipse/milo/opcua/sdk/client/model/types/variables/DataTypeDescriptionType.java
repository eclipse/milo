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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface DataTypeDescriptionType extends BaseDataVariableType {
    QualifiedProperty<String> DATA_TYPE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataTypeVersion",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<ByteString> DICTIONARY_FRAGMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DictionaryFragment",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    CompletableFuture<? extends PropertyType> getDataTypeVersionNode();

    CompletableFuture<String> getDataTypeVersion();

    CompletableFuture<StatusCode> setDataTypeVersion(String value);

    CompletableFuture<? extends PropertyType> getDictionaryFragmentNode();

    CompletableFuture<ByteString> getDictionaryFragment();

    CompletableFuture<StatusCode> setDictionaryFragment(ByteString value);
}
