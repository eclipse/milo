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

import java.util.List;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ManagedAddressSpaceServices extends AbstractLifecycle implements AddressSpaceServices {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaNodeManager nodeManager = new UaNodeManager();

    private final UaNodeContext nodeContext;
    private final NodeFactory nodeFactory;

    private final OpcUaServer server;

    public ManagedAddressSpaceServices(OpcUaServer server) {
        this.server = server;

        nodeContext = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }
        };

        nodeFactory = new NodeFactory(nodeContext);
    }

    protected OpcUaServer getServer() {
        return server;
    }

    protected UaNodeContext getNodeContext() {
        return nodeContext;
    }

    protected NodeFactory getNodeFactory() {
        return nodeFactory;
    }

    protected UaNodeManager getNodeManager() {
        return nodeManager;
    }

    @Override
    protected void onStartup() {
        server.getAddressSpaceManager().register(nodeManager);
    }

    @Override
    protected void onShutdown() {
        server.getAddressSpaceManager().unregister(nodeManager);
    }

    @Override
    public void browse(BrowseContext context, ViewDescription viewDescription, NodeId nodeId) {
        if (nodeManager.containsNode(nodeId)) {
            List<Reference> references = nodeManager.getReferences(nodeId);

            logger.debug("Browsed {} references for {}", references.size(), nodeId);

            context.success(references);
        } else {
            context.failure(StatusCodes.Bad_NodeIdUnknown);
        }
    }

    @Override
    public void getReferences(BrowseContext context, ViewDescription viewDescription, NodeId nodeId) {
        List<Reference> references = nodeManager.getReferences(nodeId);

        logger.debug("Got {} references for {}", references.size(), nodeId);

        context.success(references);
    }

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds
    ) {

        List<DataValue> results = Lists.newArrayListWithCapacity(readValueIds.size());

        for (ReadValueId readValueId : readValueIds) {
            UaServerNode node = nodeManager.get(readValueId.getNodeId());

            if (node != null) {
                DataValue value = node.readAttribute(
                    new AttributeContext(context),
                    readValueId.getAttributeId(),
                    timestamps,
                    readValueId.getIndexRange(),
                    readValueId.getDataEncoding()
                );

                logger.debug("Read value {} from attribute {} of {}",
                    value.getValue().getValue(),
                    AttributeId.from(readValueId.getAttributeId())
                        .map(Object::toString).orElse("unknown"),
                    node.getNodeId()
                );

                results.add(value);
            } else {
                results.add(new DataValue(StatusCodes.Bad_NodeIdUnknown));
            }
        }

        context.complete(results);
    }

    @Override
    public void write(
        WriteContext context,
        List<WriteValue> writeValues
    ) {

        List<StatusCode> results = Lists.newArrayListWithCapacity(writeValues.size());

        for (WriteValue writeValue : writeValues) {
            UaServerNode node = nodeManager.get(writeValue.getNodeId());

            if (node != null) {
                try {
                    node.writeAttribute(
                        new AttributeContext(context),
                        writeValue.getAttributeId(),
                        writeValue.getValue(),
                        writeValue.getIndexRange()
                    );

                    results.add(StatusCode.GOOD);

                    logger.debug(
                        "Wrote value {} to {} attribute of {}",
                        writeValue.getValue().getValue(),
                        AttributeId.from(writeValue.getAttributeId())
                            .map(Object::toString).orElse("unknown"),
                        node.getNodeId()
                    );
                } catch (UaException e) {
                    logger.error("Unable to write value={}", writeValue.getValue(), e);
                    results.add(e.getStatusCode());
                }
            } else {
                results.add(new StatusCode(StatusCodes.Bad_NodeIdUnknown));
            }
        }

        context.complete(results);
    }

}
