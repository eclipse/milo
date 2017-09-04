package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface ExclusiveLimitAlarmType extends LimitAlarmType {
    TwoStateVariableType getActiveStateNode();

    LocalizedText getActiveState();

    void setActiveState(LocalizedText value);

    ExclusiveLimitStateMachineType getLimitStateNode();
}
