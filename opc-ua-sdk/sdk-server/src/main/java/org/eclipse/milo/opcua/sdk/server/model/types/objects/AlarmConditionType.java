package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AlarmConditionType extends AcknowledgeableConditionType {
    QualifiedProperty<NodeId> INPUT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InputNode",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Boolean> SUPPRESSED_OR_SHELVED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SuppressedOrShelved",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Double> MAX_TIME_SHELVED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTimeShelved",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    PropertyType getInputNodeNode();

    NodeId getInputNode();

    void setInputNode(NodeId value);

    PropertyType getSuppressedOrShelvedNode();

    Boolean getSuppressedOrShelved();

    void setSuppressedOrShelved(Boolean value);

    PropertyType getMaxTimeShelvedNode();

    Double getMaxTimeShelved();

    void setMaxTimeShelved(Double value);

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    TwoStateVariableType getActiveStateNode();

    LocalizedText getActiveState();

    void setActiveState(LocalizedText value);

    TwoStateVariableType getSuppressedStateNode();

    LocalizedText getSuppressedState();

    void setSuppressedState(LocalizedText value);

    ShelvedStateMachineType getShelvingStateNode();
}
