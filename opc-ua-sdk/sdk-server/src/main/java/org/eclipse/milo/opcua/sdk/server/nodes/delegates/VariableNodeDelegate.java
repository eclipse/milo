/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public class VariableNodeDelegate extends AttributeDelegateAdapter {

    @Override
    protected DataValue getVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId) throws UaException {

        switch (attributeId) {
            case AccessLevel:
                return dv(getAccessLevel(context, node));

            case UserAccessLevel:
                return dv(getUserAccessLevel(context, node));

            case Value:
                DataValue value = getValue(context, node);

                return new DataValue(
                    value.getValue(),
                    value.getStatusCode(),
                    value.getSourceTime(),
                    DateTime.now()
                );

            default:
                return super.getVariableAttribute(context, node, attributeId);
        }
    }


    protected UByte getAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return node.getAccessLevel();
    }

    protected UByte getUserAccessLevel(AttributeContext context, VariableNode node) throws UaException {
        return node.getUserAccessLevel();
    }

    protected DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
        return node.getValue();
    }

    @Override
    protected void setVariableAttribute(
        AttributeContext context,
        VariableNode node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (attributeId) {
            case Value:
                setValue(context, node, value);
                break;

            default:
                super.setVariableAttribute(context, node, attributeId, value);
        }
    }

    protected void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {
        node.setValue(value);
    }

}
