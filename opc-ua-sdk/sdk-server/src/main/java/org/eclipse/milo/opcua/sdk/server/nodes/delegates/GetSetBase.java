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
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.util.AttributeUtil;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

import static org.eclipse.milo.opcua.sdk.server.util.AttributeUtil.dv;

public interface GetSetBase {

    default DataValue getBaseAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId
    ) throws UaException {

        switch (attributeId) {
            case NodeId:
                return dv(getNodeId(context, node));

            case NodeClass:
                return dv(getNodeClass(context, node));

            case BrowseName:
                return dv(getBrowseName(context, node));

            case DisplayName:
                return dv(getDisplayName(context, node));

            case Description:
                return dv(getDescription(context, node));

            case WriteMask:
                return dv(getWriteMask(context, node));

            case UserWriteMask:
                return dv(getUserWriteMask(context, node));

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    default void setBaseAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId,
        DataValue value
    ) throws UaException {

        switch (attributeId) {
            case NodeId:
                setNodeId(context, node, AttributeUtil.extract(value));
                break;
            case NodeClass:
                setNodeClass(context, node, AttributeUtil.extract(value));
                break;
            case BrowseName:
                setBrowseName(context, node, AttributeUtil.extract(value));
                break;
            case DisplayName:
                setDisplayName(context, node, AttributeUtil.extract(value));
                break;
            case Description:
                setDescription(context, node, AttributeUtil.extract(value));
                break;
            case WriteMask:
                setWriteMask(context, node, AttributeUtil.extract(value));
                break;
            case UserWriteMask:
                setUserWriteMask(context, node, AttributeUtil.extract(value));
                break;

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    default NodeId getNodeId(AttributeContext context, Node node) throws UaException {
        return (NodeId) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.NodeId
        );
    }

    default NodeClass getNodeClass(AttributeContext context, Node node) throws UaException {
        return (NodeClass) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.NodeClass
        );
    }

    default QualifiedName getBrowseName(AttributeContext context, Node node) throws UaException {
        return (QualifiedName) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.BrowseName
        );
    }

    default LocalizedText getDisplayName(AttributeContext context, Node node) throws UaException {
        return (LocalizedText) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.DisplayName
        );
    }

    default LocalizedText getDescription(AttributeContext context, Node node) throws UaException {
        return (LocalizedText) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.Description
        );
    }

    default UInteger getWriteMask(AttributeContext context, Node node) throws UaException {
        return (UInteger) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.WriteMask
        );
    }

    default UInteger getUserWriteMask(AttributeContext context, Node node) throws UaException {
        return (UInteger) ((UaNode) node).getFilterChain().getAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.UserWriteMask
        );
    }

    default void setNodeId(AttributeContext context, Node node, NodeId nodeId) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.NodeId,
            nodeId
        );
    }

    default void setNodeClass(AttributeContext context, Node node, NodeClass nodeClass) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.NodeClass,
            nodeClass
        );
    }

    default void setBrowseName(AttributeContext context, Node node, QualifiedName browseName) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.BrowseName,
            browseName
        );
    }

    default void setDisplayName(AttributeContext context, Node node, LocalizedText displayName) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.DisplayName,
            displayName
        );
    }

    default void setDescription(AttributeContext context, Node node, LocalizedText description) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.Description,
            description
        );
    }

    default void setWriteMask(AttributeContext context, Node node, UInteger writeMask) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.WriteMask,
            writeMask
        );
    }

    default void setUserWriteMask(AttributeContext context, Node node, UInteger userWriteMask) throws UaException {
        ((UaNode) node).getFilterChain().setAttribute(
            context.getSession().orElse(null),
            (UaNode) node,
            AttributeId.UserWriteMask,
            userWriteMask
        );
    }

}
