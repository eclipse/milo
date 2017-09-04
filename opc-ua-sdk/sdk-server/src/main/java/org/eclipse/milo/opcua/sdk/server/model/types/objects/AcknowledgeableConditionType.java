package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface AcknowledgeableConditionType extends ConditionType {
    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    TwoStateVariableType getAckedStateNode();

    LocalizedText getAckedState();

    void setAckedState(LocalizedText value);

    TwoStateVariableType getConfirmedStateNode();

    LocalizedText getConfirmedState();

    void setConfirmedState(LocalizedText value);
}
