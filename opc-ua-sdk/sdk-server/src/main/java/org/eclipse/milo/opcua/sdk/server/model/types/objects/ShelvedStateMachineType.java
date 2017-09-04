package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ShelvedStateMachineType extends FiniteStateMachineType {
    QualifiedProperty<Double> UNSHELVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UnshelveTime",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    PropertyType getUnshelveTimeNode();

    Double getUnshelveTime();

    void setUnshelveTime(Double value);

    StateType getUnshelvedNode();

    StateType getTimedShelvedNode();

    StateType getOneShotShelvedNode();

    TransitionType getUnshelvedToTimedShelvedNode();

    TransitionType getUnshelvedToOneShotShelvedNode();

    TransitionType getTimedShelvedToUnshelvedNode();

    TransitionType getTimedShelvedToOneShotShelvedNode();

    TransitionType getOneShotShelvedToUnshelvedNode();

    TransitionType getOneShotShelvedToTimedShelvedNode();
}
