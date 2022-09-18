/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.typetree.DataTypeTreeBuilder;
import org.eclipse.milo.opcua.sdk.core.types.DynamicCodecFactory;
import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.sdk.core.typetree.DataTypeTree;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.encoding.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.util.Tree;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTypeCodecSessionInitializer implements SessionFsm.SessionInitializer {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(DataTypeCodecSessionInitializer.class);

    private final CodecFactory codecFactory;

    /**
     * Create a {@link DataTypeCodecSessionInitializer} that the default {@link CodecFactory} that
     * uses {@link DynamicCodecFactory}.
     */
    public DataTypeCodecSessionInitializer() {
        this(DynamicCodecFactory::create);
    }

    /**
     * Create a {@link DataTypeCodecSessionInitializer} with a custom {@link CodecFactory}.
     *
     * @param codecFactory the custom {@link CodecFactory} that will create {@link DataTypeCodec}s.
     */
    public DataTypeCodecSessionInitializer(CodecFactory codecFactory) {
        this.codecFactory = codecFactory;
    }

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient stackClient, OpcUaSession session) {
        String treeKey = DataTypeTreeSessionInitializer.SESSION_ATTRIBUTE_KEY;

        Object dataTypeTree = session.getAttribute(treeKey);

        if (dataTypeTree instanceof DataTypeTree) {
            registerCodecs(stackClient, (DataTypeTree) dataTypeTree);

            return CompletableFuture.completedFuture(Unit.VALUE);
        } else {
            return DataTypeTreeBuilder.buildAsync(stackClient, session)
                .thenAccept(tree -> {
                    session.setAttribute(treeKey, tree);

                    registerCodecs(stackClient, tree);
                })
                .thenApply(v -> Unit.VALUE);
        }
    }

    private void registerCodecs(UaStackClient stackClient, DataTypeTree dataTypeTree) {
        Tree<DataType> structureNode = dataTypeTree.getTreeNode(NodeIds.Structure);

        if (structureNode != null) {
            structureNode.traverse(dataType -> {
                if (dataType.getDataTypeDefinition() != null) {
                    LOGGER.debug(
                        "Registering type: name={}, dataTypeId={}",
                        dataType.getBrowseName(), dataType.getNodeId()
                    );

                    stackClient.getDynamicDataTypeManager().registerType(
                        dataType.getNodeId(),
                        codecFactory.create(dataType, dataTypeTree),
                        dataType.getBinaryEncodingId(),
                        dataType.getXmlEncodingId(),
                        dataType.getJsonEncodingId()
                    );
                }
            });
        } else {
            LoggerFactory.getLogger(DataTypeTreeSessionInitializer.class)
                .warn("Tree for NodeIds.Structure not found; is the server DataType hierarchy sane?");
        }
    }

    public interface CodecFactory {

        /**
         * Create a {@link DataTypeCodec} instance for {@code dataType}.
         *
         * @param dataType     the {@link DataType} to create the codec for.
         * @param dataTypeTree the {@link DataTypeTree}.
         * @return a {@link DataTypeCodec} for {@code dataType}.
         */
        DataTypeCodec create(DataType dataType, DataTypeTree dataTypeTree);

    }

}
