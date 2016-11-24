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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public interface DialogConditionType extends ConditionType {

    Property<LocalizedText> PROMPT = new BasicProperty<>(
        QualifiedName.parse("0:Prompt"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );

    Property<LocalizedText[]> RESPONSE_OPTION_SET = new BasicProperty<>(
        QualifiedName.parse("0:ResponseOptionSet"),
        NodeId.parse("ns=0;i=21"),
        1,
        LocalizedText[].class
    );

    Property<Integer> DEFAULT_RESPONSE = new BasicProperty<>(
        QualifiedName.parse("0:DefaultResponse"),
        NodeId.parse("ns=0;i=6"),
        -1,
        Integer.class
    );

    Property<Integer> OK_RESPONSE = new BasicProperty<>(
        QualifiedName.parse("0:OkResponse"),
        NodeId.parse("ns=0;i=6"),
        -1,
        Integer.class
    );

    Property<Integer> CANCEL_RESPONSE = new BasicProperty<>(
        QualifiedName.parse("0:CancelResponse"),
        NodeId.parse("ns=0;i=6"),
        -1,
        Integer.class
    );

    Property<Integer> LAST_RESPONSE = new BasicProperty<>(
        QualifiedName.parse("0:LastResponse"),
        NodeId.parse("ns=0;i=6"),
        -1,
        Integer.class
    );


    CompletableFuture<? extends PropertyType> prompt();

    CompletableFuture<LocalizedText> getPrompt();

    CompletableFuture<StatusCode> setPrompt(LocalizedText value);

    CompletableFuture<? extends PropertyType> responseOptionSet();

    CompletableFuture<LocalizedText[]> getResponseOptionSet();

    CompletableFuture<StatusCode> setResponseOptionSet(LocalizedText[] value);

    CompletableFuture<? extends PropertyType> defaultResponse();

    CompletableFuture<Integer> getDefaultResponse();

    CompletableFuture<StatusCode> setDefaultResponse(Integer value);

    CompletableFuture<? extends PropertyType> okResponse();

    CompletableFuture<Integer> getOkResponse();

    CompletableFuture<StatusCode> setOkResponse(Integer value);

    CompletableFuture<? extends PropertyType> cancelResponse();

    CompletableFuture<Integer> getCancelResponse();

    CompletableFuture<StatusCode> setCancelResponse(Integer value);

    CompletableFuture<? extends PropertyType> lastResponse();

    CompletableFuture<Integer> getLastResponse();

    CompletableFuture<StatusCode> setLastResponse(Integer value);


    CompletableFuture<? extends TwoStateVariableType> enabledState();

    CompletableFuture<LocalizedText> getEnabledState();

    CompletableFuture<StatusCode> setEnabledState(LocalizedText value);

    CompletableFuture<? extends TwoStateVariableType> dialogState();

    CompletableFuture<LocalizedText> getDialogState();

    CompletableFuture<StatusCode> setDialogState(LocalizedText value);

}