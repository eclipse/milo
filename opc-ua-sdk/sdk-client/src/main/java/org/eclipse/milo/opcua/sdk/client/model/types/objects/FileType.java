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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface FileType extends BaseObjectType {
    QualifiedProperty<ULong> SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Size",
        NodeId.parse("ns=0;i=9"),
        ValueRanks.Scalar,
        ULong.class
    );

    QualifiedProperty<Boolean> WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Writable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> USER_WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserWritable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UShort> OPEN_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OpenCount",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<String> MIME_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MimeType",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    CompletableFuture<? extends PropertyType> getSizeNode();

    CompletableFuture<ULong> getSize();

    CompletableFuture<StatusCode> setSize(ULong value);

    CompletableFuture<? extends PropertyType> getWritableNode();

    CompletableFuture<Boolean> getWritable();

    CompletableFuture<StatusCode> setWritable(Boolean value);

    CompletableFuture<? extends PropertyType> getUserWritableNode();

    CompletableFuture<Boolean> getUserWritable();

    CompletableFuture<StatusCode> setUserWritable(Boolean value);

    CompletableFuture<? extends PropertyType> getOpenCountNode();

    CompletableFuture<UShort> getOpenCount();

    CompletableFuture<StatusCode> setOpenCount(UShort value);

    CompletableFuture<? extends PropertyType> getMimeTypeNode();

    CompletableFuture<String> getMimeType();

    CompletableFuture<StatusCode> setMimeType(String value);
}
