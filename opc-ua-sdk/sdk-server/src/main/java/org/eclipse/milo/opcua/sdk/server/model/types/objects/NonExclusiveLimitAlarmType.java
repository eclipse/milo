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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface NonExclusiveLimitAlarmType extends LimitAlarmType {
    TwoStateVariableType getLowStateNode();

    LocalizedText getLowState();

    void setLowState(LocalizedText value);

    TwoStateVariableType getLowLowStateNode();

    LocalizedText getLowLowState();

    void setLowLowState(LocalizedText value);

    TwoStateVariableType getHighStateNode();

    LocalizedText getHighState();

    void setHighState(LocalizedText value);

    TwoStateVariableType getHighHighStateNode();

    LocalizedText getHighHighState();

    void setHighHighState(LocalizedText value);

    TwoStateVariableType getActiveStateNode();

    LocalizedText getActiveState();

    void setActiveState(LocalizedText value);
}
