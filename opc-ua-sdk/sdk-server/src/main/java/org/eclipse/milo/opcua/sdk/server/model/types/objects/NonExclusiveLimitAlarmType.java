package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface NonExclusiveLimitAlarmType extends LimitAlarmType {
    TwoStateVariableType getActiveStateNode();

    LocalizedText getActiveState();

    void setActiveState(LocalizedText value);

    TwoStateVariableType getHighHighStateNode();

    LocalizedText getHighHighState();

    void setHighHighState(LocalizedText value);

    TwoStateVariableType getHighStateNode();

    LocalizedText getHighState();

    void setHighState(LocalizedText value);

    TwoStateVariableType getLowStateNode();

    LocalizedText getLowState();

    void setLowState(LocalizedText value);

    TwoStateVariableType getLowLowStateNode();

    LocalizedText getLowLowState();

    void setLowLowState(LocalizedText value);
}
