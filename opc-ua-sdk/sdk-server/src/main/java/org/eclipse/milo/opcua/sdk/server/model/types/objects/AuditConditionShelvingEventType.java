package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditConditionShelvingEventType extends AuditConditionEventType {
    QualifiedProperty<Double> SHELVING_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ShelvingTime",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    PropertyType getShelvingTimeNode();

    Double getShelvingTime();

    void setShelvingTime(Double value);
}
