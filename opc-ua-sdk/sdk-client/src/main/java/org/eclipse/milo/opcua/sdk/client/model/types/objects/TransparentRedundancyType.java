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

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
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
