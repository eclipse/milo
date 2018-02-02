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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface DialogConditionType extends ConditionType {
    QualifiedProperty<LocalizedText> PROMPT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Prompt",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText[]> RESPONSE_OPTION_SET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ResponseOptionSet",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.OneDimension,
        LocalizedText[].class
    );

    QualifiedProperty<Integer> DEFAULT_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DefaultResponse",
        NodeId.parse("ns=0;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<Integer> OK_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OkResponse",
        NodeId.parse("ns=0;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<Integer> CANCEL_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CancelResponse",
        NodeId.parse("ns=0;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<Integer> LAST_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastResponse",
        NodeId.parse("ns=0;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    CompletableFuture<? extends PropertyType> getPromptNode();

    CompletableFuture<LocalizedText> getPrompt();

    CompletableFuture<StatusCode> setPrompt(LocalizedText value);

    CompletableFuture<? extends PropertyType> getResponseOptionSetNode();

    CompletableFuture<LocalizedText[]> getResponseOptionSet();

    CompletableFuture<StatusCode> setResponseOptionSet(LocalizedText[] value);

    CompletableFuture<? extends PropertyType> getDefaultResponseNode();

    CompletableFuture<Integer> getDefaultResponse();

    CompletableFuture<StatusCode> setDefaultResponse(Integer value);

    CompletableFuture<? extends PropertyType> getOkResponseNode();

    CompletableFuture<Integer> getOkResponse();

    CompletableFuture<StatusCode> setOkResponse(Integer value);

    CompletableFuture<? extends PropertyType> getCancelResponseNode();

    CompletableFuture<Integer> getCancelResponse();

    CompletableFuture<StatusCode> setCancelResponse(Integer value);

    CompletableFuture<? extends PropertyType> getLastResponseNode();

    CompletableFuture<Integer> getLastResponse();

    CompletableFuture<StatusCode> setLastResponse(Integer value);

    CompletableFuture<? extends TwoStateVariableType> getEnabledStateNode();

    CompletableFuture<LocalizedText> getEnabledState();

    CompletableFuture<StatusCode> setEnabledState(LocalizedText value);

    CompletableFuture<? extends TwoStateVariableType> getDialogStateNode();

    CompletableFuture<LocalizedText> getDialogState();

    CompletableFuture<StatusCode> setDialogState(LocalizedText value);
}
