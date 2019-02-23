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

import org.eclipse.milo.opcua.sdk.server.api.nodes.ViewNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

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
