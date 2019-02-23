/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

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

    PropertyType getPromptNode();

    LocalizedText getPrompt();

    void setPrompt(LocalizedText value);

    PropertyType getResponseOptionSetNode();

    LocalizedText[] getResponseOptionSet();

    void setResponseOptionSet(LocalizedText[] value);

    PropertyType getDefaultResponseNode();

    Integer getDefaultResponse();

    void setDefaultResponse(Integer value);

    PropertyType getOkResponseNode();

    Integer getOkResponse();

    void setOkResponse(Integer value);

    PropertyType getCancelResponseNode();

    Integer getCancelResponse();

    void setCancelResponse(Integer value);

    PropertyType getLastResponseNode();

    Integer getLastResponse();

    void setLastResponse(Integer value);

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    TwoStateVariableType getDialogStateNode();

    LocalizedText getDialogState();

    void setDialogState(LocalizedText value);
}
