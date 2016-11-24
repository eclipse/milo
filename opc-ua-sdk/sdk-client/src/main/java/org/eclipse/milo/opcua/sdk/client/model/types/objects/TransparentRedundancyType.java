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

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.RedundantServerDataType;


public interface TransparentRedundancyType extends ServerRedundancyType {

    Property<String> CURRENT_SERVER_ID = new BasicProperty<>(
        QualifiedName.parse("0:CurrentServerId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<RedundantServerDataType[]> REDUNDANT_SERVER_ARRAY = new BasicProperty<>(
        QualifiedName.parse("0:RedundantServerArray"),
        NodeId.parse("ns=0;i=853"),
        1,
        RedundantServerDataType[].class
    );


    CompletableFuture<? extends PropertyType> currentServerId();

    CompletableFuture<String> getCurrentServerId();

    CompletableFuture<StatusCode> setCurrentServerId(String value);

    CompletableFuture<? extends PropertyType> redundantServerArray();

    CompletableFuture<RedundantServerDataType[]> getRedundantServerArray();

    CompletableFuture<StatusCode> setRedundantServerArray(RedundantServerDataType[] value);


}