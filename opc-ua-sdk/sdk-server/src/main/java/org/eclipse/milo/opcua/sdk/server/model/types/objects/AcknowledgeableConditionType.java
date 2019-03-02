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

import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface AcknowledgeableConditionType extends ConditionType {
    TwoStateVariableType getAckedStateNode();

    LocalizedText getAckedState();

    void setAckedState(LocalizedText value);

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    UaMethodNode getAcknowledgeMethodNode();

    UaMethodNode getConfirmMethodNode();

    TwoStateVariableType getConfirmedStateNode();

    LocalizedText getConfirmedState();

    void setConfirmedState(LocalizedText value);
}
