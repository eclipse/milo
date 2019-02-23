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

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

public interface GetSetObjectNode extends GetSetBase {

    default DataValue getObjectAttribute(
        AttributeContext context,
        ObjectNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case EventNotifier:
                return dv(getEventNotifier(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setObjectAttribute(
        AttributeContext context,
        ObjectNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case EventNotifier:
                setEventNotifier(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default UByte getEventNotifier(AttributeContext context, ObjectNode node) throws UaException {
        return node.getEventNotifier();
    }

    default void setEventNotifier(AttributeContext context, ObjectNode node, UByte eventNotifier) throws UaException {
        node.setEventNotifier(eventNotifier);
    }

}
