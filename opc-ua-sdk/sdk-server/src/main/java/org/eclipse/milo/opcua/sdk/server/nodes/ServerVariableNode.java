package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.extract;

public interface ServerVariableNode extends ServerNode, VariableNode {

    default DataValue getValue(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.Value));
    }

    default NodeId getDataType(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.DataType));
    }

    default Integer getValueRank(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.ValueRank));
    }

    default Optional<UInteger[]> getArrayDimensions(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.ArrayDimensions));
    }

    default UByte getAccessLevel(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.AccessLevel));
    }

    default UByte getUserAccessLevel(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.UserAccessLevel));
    }

    default Optional<Double> getMinimumSamplingInterval(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.MinimumSamplingInterval));
    }

    default Boolean getHistorizing(AttributeContext context) throws UaException {
        return extract(getAttribute(context, AttributeId.Historizing));
    }

}
