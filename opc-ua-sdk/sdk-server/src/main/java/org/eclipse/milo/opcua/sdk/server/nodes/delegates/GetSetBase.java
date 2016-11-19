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

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

import static org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeUtil.dv;

public interface GetSetBase {

    default DataValue getBaseAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId) throws UaException {

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
                return getDescription(context, node)
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case WriteMask:
                return getWriteMask(context, node)
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            case UserWriteMask:
                return getUserWriteMask(context, node)
                    .map(AttributeUtil::dv)
                    .orElseThrow(AttributeUtil.ATTRIBUTE_ID_INVALID_EXCEPTION);

            default:
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    default void setBaseAttribute(
        AttributeContext context,
        Node node,
        AttributeId attributeId,
        DataValue value) throws UaException {

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
        return node.getNodeId();
    }

    default NodeClass getNodeClass(AttributeContext context, Node node) throws UaException {
        return node.getNodeClass();
    }

    default QualifiedName getBrowseName(AttributeContext context, Node node) throws UaException {
        return node.getBrowseName();
    }

    default LocalizedText getDisplayName(AttributeContext context, Node node) throws UaException {
        return node.getDisplayName();
    }

    default Optional<LocalizedText> getDescription(AttributeContext context, Node node) throws UaException {
        return node.getDescription();
    }

    default Optional<UInteger> getWriteMask(AttributeContext context, Node node) throws UaException {
        return node.getWriteMask();
    }

    default Optional<UInteger> getUserWriteMask(AttributeContext context, Node node) throws UaException {
        return node.getUserWriteMask();
    }

    default void setNodeId(AttributeContext context, Node node, NodeId nodeId) throws UaException {
        node.setNodeId(nodeId);
    }

    default void setNodeClass(AttributeContext context, Node node, NodeClass nodeClass) throws UaException {
        node.setNodeClass(nodeClass);
    }

    default void setBrowseName(AttributeContext context, Node node, QualifiedName browseName) throws UaException {
        node.setBrowseName(browseName);
    }

    default void setDisplayName(AttributeContext context, Node node, LocalizedText displayName) throws UaException {
        node.setDisplayName(displayName);
    }

    default void setDescription(AttributeContext context, Node node, LocalizedText description) throws UaException {
        node.setDescription(Optional.ofNullable(description));
    }

    default void setWriteMask(AttributeContext context, Node node, UInteger writeMask) throws UaException {
        node.setWriteMask(Optional.ofNullable(writeMask));
    }

    default void setUserWriteMask(AttributeContext context, Node node, UInteger userWriteMask) throws UaException {
        node.setUserWriteMask(Optional.ofNullable(userWriteMask));
    }

}
