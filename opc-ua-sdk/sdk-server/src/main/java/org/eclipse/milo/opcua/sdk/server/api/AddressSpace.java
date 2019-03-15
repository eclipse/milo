/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AddressSpace extends AddressSpaceServices {

    /**
     * Return {@code true} if {@code nodeId} belongs to this {@link AddressSpace}.
     * <p>
     * This is not an indication that a Node for {@code nodeId} exists, rather, it's solely an indication that this
     * AddressSpace would be responsible for the Node if it does exist.
     *
     * @param nodeId a {@link NodeId}.
     * @return {@code true} if {@code nodeId} belongs to this {@link AddressSpace}.
     */
    boolean filter(NodeId nodeId);

    //region Default ViewServices Implementations

//    /**
//     * Get all References from {@code nodeId} in this AddressSpace.
//     * <p>
//     * The Node identified by {@code nodeId} belongs to this AddressSpace according {@link AddressSpace#filter(NodeId)}.
//     * <p>
//     * If a Node instance for {@code nodeId} does not exist then {@link BrowseContext#failure(StatusCode)} should be
//     * invoked with {@link StatusCodes#Bad_NodeIdUnknown}.
//     *
//     * @param context         TODO
//     * @param viewDescription TODO
//     * @param nodeId          TODO
//     */
//    @Override
//    default void browse(BrowseContext context, ViewDescription viewDescription, NodeId nodeId) {
//        try {
//            NodeManager<UaNode> nodeManager = getNodeManager()
//                .orElseThrow(() -> new UaException(StatusCodes.Bad_NodeIdUnknown));
//
//            if (nodeManager.containsNode(nodeId)) {
//                context.success(nodeManager.getReferences(nodeId));
//            } else {
//                context.failure(StatusCodes.Bad_NodeIdUnknown);
//            }
//        } catch (UaException e) {
//            context.failure(e);
//        }
//    }
//
//    /**
//     * References from {@code sourceNodeId} are being collected from all AddressSpace instances. Return any References
//     * from {@code sourceNodeId} this AddressSpace may have to contribute.
//     * <p>
//     * The Node identified by {@code sourceNodeId} is managed by another AddressSpace.
//     *
//     * @param context         TODO
//     * @param viewDescription TODO
//     * @param sourceNodeId    TODO
//     */
//    @Override
//    default void getReferences(BrowseContext context, ViewDescription viewDescription, NodeId sourceNodeId) {
//        List<Reference> references = getNodeManager()
//            .map(n -> n.getReferences(sourceNodeId))
//            .orElse(Collections.emptyList());
//
//        context.success(references);
//    }

    //endregion

}
