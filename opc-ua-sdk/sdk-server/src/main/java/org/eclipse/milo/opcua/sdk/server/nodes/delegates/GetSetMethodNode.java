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

import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

public interface GetSetMethodNode extends GetSetBase {

    default DataValue getMethodAttribute(
        AttributeContext context,
        MethodNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case Executable:
                return dv(isExecutable(context, node));

            case UserExecutable:
                return dv(isUserExecutable(context, node));

            default:
                return getBaseAttribute(context, node, attributeId);
        }
    }

    default void setMethodAttribute(
        AttributeContext context,
        MethodNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Executable:
                setExecutable(context, node, AttributeUtil.extract(value));
                break;
            case UserExecutable:
                setUserExecutable(context, node, AttributeUtil.extract(value));
                break;

            default:
                setBaseAttribute(context, node, attributeId, value);
        }
    }

    default Boolean isExecutable(AttributeContext context, MethodNode node) throws UaException {
        return node.isExecutable();
    }

    default Boolean isUserExecutable(AttributeContext context, MethodNode node) throws UaException {
        return node.isUserExecutable();
    }

    default void setExecutable(AttributeContext context, MethodNode node, Boolean executable) throws UaException {
        node.setExecutable(executable);
    }

    default void setUserExecutable(
        AttributeContext context, MethodNode node, Boolean userExecutable) throws UaException {

        node.setUserExecutable(userExecutable);
    }

}
