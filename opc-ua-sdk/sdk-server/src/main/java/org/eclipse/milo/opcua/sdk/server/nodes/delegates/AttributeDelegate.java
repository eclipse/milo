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

import org.eclipse.milo.opcua.sdk.server.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ViewNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;

public interface AttributeDelegate extends
    GetSetBase,
    GetSetDataTypeNode,
    GetSetMethodNode,
    GetSetObjectNode,
    GetSetObjectTypeNode,
    GetSetReferenceTypeNode,
    GetSetVariableNode,
    GetSetVariableTypeNode,
    GetSetViewNode {

    /**
     * A default implementation of {@link AttributeDelegate} in which all calls are simply delegate to the
     * appropriate getter or setter for the Node.
     */
    AttributeDelegate DEFAULT = new AttributeDelegate() {};

    default DataValue getAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId) {

        try {
            switch (node.getNodeClass()) {
                case DataType:
                    return getDataTypeAttribute(context, (DataTypeNode) node, attributeId);

                case Method:
                    return getMethodAttribute(context, (MethodNode) node, attributeId);

                case Object:
                    return getObjectAttribute(context, (ObjectNode) node, attributeId);

                case ObjectType:
                    return getObjectTypeAttribute(context, (ObjectTypeNode) node, attributeId);

                case ReferenceType:
                    return getReferenceTypeAttribute(context, (ReferenceTypeNode) node, attributeId);

                case Variable:
                    return getVariableAttribute(context, (VariableNode) node, attributeId);

                case VariableType:
                    return getVariableTypeAttribute(context, (VariableTypeNode) node, attributeId);

                case View:
                    return getViewAttribute(context, (ViewNode) node, attributeId);

                default:
                    throw new UaException(StatusCodes.Bad_NodeClassInvalid);
            }
        } catch (UaException e) {
            return new DataValue(e.getStatusCode());
        }
    }

    default void setAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId,
        DataValue value) throws UaException {

        switch (node.getNodeClass()) {
            case DataType:
                setDataTypeAttribute(context, (DataTypeNode) node, attributeId, value);
                break;
            case Method:
                setMethodAttribute(context, (MethodNode) node, attributeId, value);
                break;
            case Object:
                setObjectAttribute(context, (ObjectNode) node, attributeId, value);
                break;
            case ObjectType:
                setObjectTypeAttribute(context, (ObjectTypeNode) node, attributeId, value);
                break;
            case ReferenceType:
                setReferenceTypeAttribute(context, (ReferenceTypeNode) node, attributeId, value);
                break;
            case Variable:
                setVariableAttribute(context, (VariableNode) node, attributeId, value);
                break;
            case VariableType:
                setVariableTypeAttribute(context, (VariableTypeNode) node, attributeId, value);
                break;
            case View:
                setViewAttribute(context, (ViewNode) node, attributeId, value);
                break;

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }
    }

}
