/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

final class NodeIdConversions {

    private NodeIdConversions() {}

    static ExpandedNodeId nodeIdToExpandedNodeId(NodeId nodeId) {
        return nodeId.expanded();
    }

    static String nodeIdToString(NodeId nodeId) {
        return nodeId.toParseableString();
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {
        
        if (value instanceof NodeId) {
            NodeId nodeId = (NodeId) value;

            return implicit ?
                implicitConversion(nodeId, targetType) :
                explicitConversion(nodeId, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(NodeId nodeId, BuiltinDataType targetType) throws ConversionNotDefinedException {
        return implicitConversion(nodeId, targetType);
    }

    static Object implicitConversion(NodeId nodeId, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case ExpandedNodeId:    return nodeIdToExpandedNodeId(nodeId);
            case String:            return nodeIdToString(nodeId);
            default:                throw new ConversionNotDefinedException(BuiltinDataType.NodeId, targetType);
        }
        //@formatter:on
    }

}
