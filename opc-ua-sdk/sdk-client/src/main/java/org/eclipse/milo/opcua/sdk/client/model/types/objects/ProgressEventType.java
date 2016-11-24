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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;


public interface ProgressEventType extends BaseEventType {

    Property<Object> CONTEXT = new BasicProperty<>(
        QualifiedName.parse("0:Context"),
        NodeId.parse("ns=0;i=24"),
        -1,
        Object.class
    );

    Property<UShort> PROGRESS = new BasicProperty<>(
        QualifiedName.parse("0:Progress"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );


    CompletableFuture<? extends PropertyType> context();

    CompletableFuture<? extends Object> getContext();

    CompletableFuture<StatusCode> setContext(Object value);

    CompletableFuture<? extends PropertyType> progress();

    CompletableFuture<UShort> getProgress();

    CompletableFuture<StatusCode> setProgress(UShort value);


}