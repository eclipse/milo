package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.32">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.32</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public abstract class DataTypeDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=14525");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=125");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14796");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15057");

    private final NodeId dataTypeId;

    private final QualifiedName name;

    public DataTypeDescription(NodeId dataTypeId, QualifiedName name) {
        this.dataTypeId = dataTypeId;
        this.name = name;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public NodeId getDataTypeId() {
        return dataTypeId;
    }

    public QualifiedName getName() {
        return name;
    }
}
