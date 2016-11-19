package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import org.eclipse.milo.opcua.sdk.server.api.nodes.ViewNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.dv;

public interface GetSetViewNode extends GetSetBase {

    default DataValue getViewAttribute(
        AttributeContext context,
        ViewNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case ContainsNoLoops:
                return dv(getContainsNoLoops(context, node));

            case EventNotifier:
                return dv(getEventNotifier(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setViewAttribute(
        AttributeContext context,
        ViewNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case ContainsNoLoops:
                setContainsNoLoops(context, node, AttributeUtil.extract(value));
                break;
            case EventNotifier:
                setEventNotifier(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default Boolean getContainsNoLoops(AttributeContext context, ViewNode node) throws UaException {
        return node.getContainsNoLoops();
    }

    default UByte getEventNotifier(AttributeContext context, ViewNode node) throws UaException {
        return node.getEventNotifier();
    }

    default void setContainsNoLoops(
        AttributeContext context, ViewNode node, Boolean containsNoLoops) throws UaException {

        node.setContainsNoLoops(containsNoLoops);
    }

    default void setEventNotifier(AttributeContext context, ViewNode node, UByte eventNotifier) throws UaException {
        node.setEventNotifier(eventNotifier);
    }

}
