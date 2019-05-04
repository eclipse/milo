package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;


@SuperBuilder(
    toBuilder = true
)
@ToString
public abstract class Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=22");

    public Structure() {
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }
}
