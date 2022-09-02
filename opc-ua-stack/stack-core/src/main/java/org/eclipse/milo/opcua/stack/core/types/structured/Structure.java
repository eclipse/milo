package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12</a>
 */
@EqualsAndHashCode
@SuperBuilder
@ToString
public abstract class Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=22");

    public Structure() {
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }
}
