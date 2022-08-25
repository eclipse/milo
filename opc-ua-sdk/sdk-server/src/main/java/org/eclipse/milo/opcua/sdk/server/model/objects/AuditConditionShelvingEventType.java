package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.10.8">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.10.8</a>
 */
public interface AuditConditionShelvingEventType extends AuditConditionEventType {
    QualifiedProperty<Double> SHELVING_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ShelvingTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    Double getShelvingTime();

    void setShelvingTime(Double value);

    PropertyType getShelvingTimeNode();
}
