/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.RedundantServerDataType;

public interface TransparentRedundancyType extends ServerRedundancyType {
    QualifiedProperty<String> CURRENT_SERVER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CurrentServerId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<RedundantServerDataType[]> REDUNDANT_SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RedundantServerArray",
        NodeId.parse("ns=0;i=853"),
        ValueRanks.OneDimension,
        RedundantServerDataType[].class
    );

    CompletableFuture<? extends PropertyType> getCurrentServerIdNode();

    CompletableFuture<String> getCurrentServerId();

    CompletableFuture<StatusCode> setCurrentServerId(String value);

    CompletableFuture<? extends PropertyType> getRedundantServerArrayNode();

    CompletableFuture<RedundantServerDataType[]> getRedundantServerArray();

    CompletableFuture<StatusCode> setRedundantServerArray(RedundantServerDataType[] value);
}
