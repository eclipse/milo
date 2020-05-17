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

import org.eclipse.milo.opcua.sdk.core.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaViewNode;
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
        UaNode node,
        AttributeId attributeId) {

        try {
            switch (node.getNodeClass()) {
                case DataType:
                    return getDataTypeAttribute(context, (UaDataTypeNode) node, attributeId);

                case Method:
                    return getMethodAttribute(context, (UaMethodNode) node, attributeId);

                case Object:
                    return getObjectAttribute(context, (UaObjectNode) node, attributeId);

                case ObjectType:
                    return getObjectTypeAttribute(context, (UaObjectTypeNode) node, attributeId);

                case ReferenceType:
                    return getReferenceTypeAttribute(context, (UaReferenceTypeNode) node, attributeId);

                case Variable:
                    return getVariableAttribute(context, (UaVariableNode) node, attributeId);

                case VariableType:
                    return getVariableTypeAttribute(context, (UaVariableTypeNode) node, attributeId);

                case View:
                    return getViewAttribute(context, (UaViewNode) node, attributeId);

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
                setDataTypeAttribute(context, (UaDataTypeNode) node, attributeId, value);
                break;
            case Method:
                setMethodAttribute(context, (UaMethodNode) node, attributeId, value);
                break;
            case Object:
                setObjectAttribute(context, (UaObjectNode) node, attributeId, value);
                break;
            case ObjectType:
                setObjectTypeAttribute(context, (UaObjectTypeNode) node, attributeId, value);
                break;
            case ReferenceType:
                setReferenceTypeAttribute(context, (UaReferenceTypeNode) node, attributeId, value);
                break;
            case Variable:
                setVariableAttribute(context, (UaVariableNode) node, attributeId, value);
                break;
            case VariableType:
                setVariableTypeAttribute(context, (UaVariableTypeNode) node, attributeId, value);
                break;
            case View:
                setViewAttribute(context, (UaViewNode) node, attributeId, value);
                break;

            default:
                throw new UaException(StatusCodes.Bad_NodeClassInvalid);
        }
    }

}
