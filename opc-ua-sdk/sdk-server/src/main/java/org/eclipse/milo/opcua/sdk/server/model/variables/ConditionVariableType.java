package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.3">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.3</a>
 */
public interface ConditionVariableType extends BaseDataVariableType {
    QualifiedProperty<DateTime> SOURCE_TIMESTAMP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceTimestamp",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        -1,
        DateTime.class
    );

    DateTime getSourceTimestamp();

    void setSourceTimestamp(DateTime value);

    PropertyType getSourceTimestampNode();
}
