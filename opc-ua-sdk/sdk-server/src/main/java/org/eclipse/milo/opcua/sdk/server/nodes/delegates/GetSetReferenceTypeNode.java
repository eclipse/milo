package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.dv;

public interface GetSetReferenceTypeNode extends GetSetBase {

    default DataValue getReferenceTypeAttribute(
        AttributeContext context,
        ReferenceTypeNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                return dv(getIsAbstract(context, node));

            case Symmetric:
                return dv(getSymmetric(context, node));

            case InverseName:
                return getInverseName(context, node)
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setReferenceTypeAttribute(
        AttributeContext context,
        ReferenceTypeNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case IsAbstract:
                setIsAbstract(context, node, AttributeUtil.extract(value));
                break;
            case Symmetric:
                setSymmetric(context, node, AttributeUtil.extract(value));
                break;
            case InverseName:
                setInverseName(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default Boolean getIsAbstract(AttributeContext context, ReferenceTypeNode node) throws UaException {
        return node.getIsAbstract();
    }

    default Boolean getSymmetric(AttributeContext context, ReferenceTypeNode node) throws UaException {
        return node.getSymmetric();
    }

    default Optional<LocalizedText> getInverseName(
        AttributeContext context, ReferenceTypeNode node) throws UaException {

        return node.getInverseName();
    }

    default void setIsAbstract(
        AttributeContext context, ReferenceTypeNode node, Boolean isAbstract) throws UaException {

        node.setIsAbstract(isAbstract);
    }

    default void setSymmetric(
        AttributeContext context, ReferenceTypeNode node, Boolean symmetric) throws UaException {

        node.setSymmetric(symmetric);
    }

    default void setInverseName(
        AttributeContext context, ReferenceTypeNode node, LocalizedText inverseName) throws UaException {

        node.setInverseName(Optional.ofNullable(inverseName));
    }

}
