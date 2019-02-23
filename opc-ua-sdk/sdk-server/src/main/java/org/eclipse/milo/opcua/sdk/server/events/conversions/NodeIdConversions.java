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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

final class NodeIdConversions {

    private NodeIdConversions() {}

    @Nonnull
    static ExpandedNodeId nodeIdToExpandedNodeId(@Nonnull NodeId nodeId) {
        return nodeId.expanded();
    }

    @Nonnull
    static String nodeIdToString(@Nonnull NodeId nodeId) {
        return nodeId.toParseableString();
    }

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof NodeId) {
            NodeId nodeId = (NodeId) o;

            return implicit ?
                implicitConversion(nodeId, targetType) :
                explicitConversion(nodeId, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@Nonnull NodeId nodeId, BuiltinDataType targetType) {
        return implicitConversion(nodeId, targetType);
    }

    @Nullable
    static Object implicitConversion(@Nonnull NodeId nodeId, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case ExpandedNodeId:    return nodeIdToExpandedNodeId(nodeId);
            case String:            return nodeIdToString(nodeId);
            default:                return null;
        }
        //@formatter:on
    }

}
