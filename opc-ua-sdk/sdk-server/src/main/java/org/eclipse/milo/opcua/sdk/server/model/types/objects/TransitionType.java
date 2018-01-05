package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface TransitionType extends BaseObjectType {
    QualifiedProperty<UInteger> TRANSITION_NUMBER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TransitionNumber",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    PropertyType getTransitionNumberNode();

    UInteger getTransitionNumber();

    void setTransitionNumber(UInteger value);
}
