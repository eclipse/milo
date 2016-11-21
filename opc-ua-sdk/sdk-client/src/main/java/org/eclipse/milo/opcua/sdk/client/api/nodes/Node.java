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

package org.eclipse.milo.opcua.sdk.client.api.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public interface Node {

    /**
     * Get the NodeId attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readNodeId()}.
     *
     * @return the NodeId attribute.
     * @see #readNodeId()
     */
    CompletableFuture<NodeId> getNodeId();

    /**
     * Get the NodeClass attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readNodeClass()}.
     *
     * @return the NodeClass attribute.
     * @see #readNodeClass()
     */
    CompletableFuture<NodeClass> getNodeClass();

    /**
     * Get the BrowseName attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readBrowseName()}.
     *
     * @return the BrowseName attribute.
     */
    CompletableFuture<QualifiedName> getBrowseName();

    /**
     * Get the DisplayName attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readDisplayName()}.
     *
     * @return the DisplayName attribute.
     */
    CompletableFuture<LocalizedText> getDisplayName();

    /**
     * Get the Description attribute.
     * <p>
     * If quality and timestamp are required, see {@link #readDescription()}.
     *
     * @return the Description attribute, if present.
     */
    CompletableFuture<LocalizedText> getDescription();

    /**
     * Get the WriteMask attribute.
     * <p>
     * If quality and timestamp are required, see {@link #readWriteMask()}.
     *
     * @return the WriteMask attribute, if present.
     */
    CompletableFuture<UInteger> getWriteMask();

    /**
     * Get the WriteMask attribute.
     * <p>
     * If quality and timestamp are required, see {@link #readWriteMask()}.
     *
     * @return the WriteMask attribute, if present.
     */
    CompletableFuture<UInteger> getUserWriteMask();

    /**
     * Set the NodeId attribute.
     *
     * @param nodeId the {@link NodeId} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setNodeId(NodeId nodeId);

    /**
     * Set the NodeClass attribute.
     *
     * @param nodeClass the {@link NodeClass} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setNodeClass(NodeClass nodeClass);

    /**
     * Set the BrowseName attribute.
     *
     * @param browseName the {@link QualifiedName} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setBrowseName(QualifiedName browseName);

    /**
     * Set the DisplayName attribute.
     *
     * @param displayName the {@link LocalizedText} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setDisplayName(LocalizedText displayName);

    /**
     * Set the Description attribute.
     *
     * @param description the {@link LocalizedText} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setDescription(LocalizedText description);

    /**
     * Set the WriteMask attribute.
     *
     * @param writeMask the {@link UInteger} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setWriteMask(UInteger writeMask);

    /**
     * Set the UserWriteMask attribute.
     *
     * @param userWriteMask the {@link UInteger} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setUserWriteMask(UInteger userWriteMask);

    /**
     * Read the NodeId attribute {@link DataValue}.
     *
     * @return the NodeId attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readNodeId();

    /**
     * Read the NodeClass attribute {@link DataValue}.
     *
     * @return the NodeClass attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readNodeClass();

    /**
     * Read the BrowseName attribute {@link DataValue}.
     *
     * @return the BrowseName attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readBrowseName();

    /**
     * Read the DisplayName attribute {@link DataValue}.
     *
     * @return the DisplayName attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readDisplayName();

    /**
     * Read the Description attribute {@link DataValue}.
     *
     * @return the Description attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readDescription();

    /**
     * Read the WriteMask attribute {@link DataValue}.
     *
     * @return the WriteMask attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readWriteMask();

    /**
     * Read the UserWriteMask attribute {@link DataValue}.
     *
     * @return the UserWriteMask attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readUserWriteMask();

    /**
     * Write a {@link DataValue} to the NodeId attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeNodeId(DataValue value);

    /**
     * Write a {@link DataValue} to the NodeClass attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeNodeClass(DataValue value);

    /**
     * Write a {@link DataValue} to the BrowseName attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeBrowseName(DataValue value);

    /**
     * Write a {@link DataValue} to the DisplayName attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeDisplayName(DataValue value);

    /**
     * Write a {@link DataValue} to the Description attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeDescription(DataValue value);

    /**
     * Write a {@link DataValue} to the WriteMask attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeWriteMask(DataValue value);

    /**
     * Write a {@link DataValue} to the UserWriteMask attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeUserWriteMask(DataValue value);

}
