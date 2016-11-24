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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;


public interface FileType extends BaseObjectType {

    Property<ULong> SIZE = new BasicProperty<>(
        QualifiedName.parse("0:Size"),
        NodeId.parse("ns=0;i=9"),
        -1,
        ULong.class
    );

    Property<Boolean> WRITABLE = new BasicProperty<>(
        QualifiedName.parse("0:Writable"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> USER_WRITABLE = new BasicProperty<>(
        QualifiedName.parse("0:UserWritable"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<UShort> OPEN_COUNT = new BasicProperty<>(
        QualifiedName.parse("0:OpenCount"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );

    Property<String> MIME_TYPE = new BasicProperty<>(
        QualifiedName.parse("0:MimeType"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );


    CompletableFuture<? extends PropertyType> size();

    CompletableFuture<ULong> getSize();

    CompletableFuture<StatusCode> setSize(ULong value);

    CompletableFuture<? extends PropertyType> writable();

    CompletableFuture<Boolean> getWritable();

    CompletableFuture<StatusCode> setWritable(Boolean value);

    CompletableFuture<? extends PropertyType> userWritable();

    CompletableFuture<Boolean> getUserWritable();

    CompletableFuture<StatusCode> setUserWritable(Boolean value);

    CompletableFuture<? extends PropertyType> openCount();

    CompletableFuture<UShort> getOpenCount();

    CompletableFuture<StatusCode> setOpenCount(UShort value);

    CompletableFuture<? extends PropertyType> mimeType();

    CompletableFuture<String> getMimeType();

    CompletableFuture<StatusCode> setMimeType(String value);


}