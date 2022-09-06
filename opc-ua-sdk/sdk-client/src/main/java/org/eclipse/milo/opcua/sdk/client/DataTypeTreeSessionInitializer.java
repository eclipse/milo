/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.sdk.core.DataTypeTree;
import org.eclipse.milo.opcua.sdk.core.DataTypeTree.DataType;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.DynamicStructCodec;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.util.Tree;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.LoggerFactory;

/**
 * Builds a {@link DataTypeTree} and stores it on an {@link OpcUaSession} as an attribute under
 * the key {@link DataTypeTreeSessionInitializer#SESSION_ATTRIBUTE_KEY}.
 */
public class DataTypeTreeSessionInitializer implements SessionFsm.SessionInitializer {

    /**
     * The attribute key that the {@link DataTypeTree} will be stored under in the {@link OpcUaSession}.
     *
     * @see OpcUaSession#getAttribute(String)
     * @see OpcUaSession#setAttribute(String, Object)
     */
    public static final String SESSION_ATTRIBUTE_KEY = "dataTypeTree";

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient stackClient, OpcUaSession session) {
        return DataTypeTreeBuilder.buildAsync(stackClient, session)
            .thenAccept(tree -> {
                session.setAttribute(SESSION_ATTRIBUTE_KEY, tree);

                Tree<DataType> structureNode = tree.getTreeNode(NodeIds.Structure);
                if (structureNode != null) {
                    structureNode.traverse(dataType -> {
                        DataTypeDefinition definition = dataType.getDataTypeDefinition();

                        if (definition instanceof StructureDefinition) {
                            var codec = new DynamicStructCodec((StructureDefinition) definition);
                            if (dataType.getBinaryEncodingId() != null) {
                                stackClient.getDynamicDataTypeManager().registerCodec(
                                    dataType.getBinaryEncodingId(),
                                    codec
                                );
                            }
                            if (dataType.getXmlEncodingId() != null) {
                                stackClient.getDynamicDataTypeManager().registerCodec(
                                    dataType.getXmlEncodingId(),
                                    codec
                                );
                            }
                            if (dataType.getJsonEncodingId() != null) {
                                stackClient.getDynamicDataTypeManager().registerCodec(
                                    dataType.getJsonEncodingId(),
                                    codec
                                );
                            }
                        }
                    });
                } else {
                    LoggerFactory.getLogger(DataTypeTreeSessionInitializer.class)
                        .warn("Tree for NodeIds.Structure not found; is the server DataType hierarchy sane?");
                }

                Tree<DataType> enumerationNode = tree.getTreeNode(NodeIds.Enumeration);
                if (enumerationNode != null) {
                    enumerationNode.traverse(dataType -> {
                        DataTypeDefinition definition = dataType.getDataTypeDefinition();

                        if (definition instanceof EnumDefinition) {
                            // TODO register DynamicEnumCodec
                        }
                    });
                } else {
                    LoggerFactory.getLogger(DataTypeTreeSessionInitializer.class)
                        .warn("Tree for NodeIds.Enumeration not found; is the server DataType hierarchy sane?");
                }
            })
            .thenApply(v -> Unit.VALUE);
    }

}
