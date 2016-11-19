package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import org.eclipse.milo.opcua.sdk.server.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;

import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.dv;

public interface GetSetDataTypeNode extends GetSetBase {

    default DataValue getDataTypeAttribute(
        AttributeContext context,
        DataTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                return dv(getIsAbstract(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setDataTypeAttribute(
        AttributeContext context,
        DataTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                setIsAbstract(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default Boolean getIsAbstract(AttributeContext context, DataTypeNode node) throws UaException {
        return node.getIsAbstract();
    }

    default void setIsAbstract(AttributeContext context, DataTypeNode node, Boolean isAbstract) throws UaException {
        node.setIsAbstract(isAbstract);
    }

}
