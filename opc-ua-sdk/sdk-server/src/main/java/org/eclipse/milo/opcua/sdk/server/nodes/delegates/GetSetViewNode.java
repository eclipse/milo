/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaViewNode;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

public interface GetSetViewNode extends GetSetBase {

    default DataValue getViewAttribute(
        AttributeContext context,
        UaViewNode node,
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
        UaViewNode node,
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

    default Boolean getContainsNoLoops(AttributeContext context, UaViewNode node) throws UaException {
        return (Boolean) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.ContainsNoLoops
        );
    }

    default UByte getEventNotifier(AttributeContext context, UaViewNode node) throws UaException {
        return (UByte) node.getFilterChain().getAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.EventNotifier
        );
    }

    default void setContainsNoLoops(
        AttributeContext context,
        UaViewNode node,
        Boolean containsNoLoops
    ) throws UaException {

        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.ContainsNoLoops,
            containsNoLoops
        );
    }

    default void setEventNotifier(AttributeContext context, UaViewNode node, UByte eventNotifier) throws UaException {
        node.getFilterChain().setAttribute(
            context.getSession().orElse(null),
            node,
            AttributeId.EventNotifier,
            eventNotifier
        );
    }

}
