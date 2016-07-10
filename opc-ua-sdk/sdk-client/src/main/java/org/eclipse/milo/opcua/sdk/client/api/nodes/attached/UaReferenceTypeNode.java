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

package org.eclipse.milo.opcua.sdk.client.api.nodes.attached;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public interface UaReferenceTypeNode {

    /**
     * Read the IsAbstract {@link DataValue}.
     *
     * @return the IsAbstract {@link DataValue}.
     */
    CompletableFuture<DataValue> readIsAbstract();

    /**
     * Read the IsAbstract attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readIsAbstract()}.
     *
     * @return the IsAbstract attribute.
     */
    default CompletableFuture<Boolean> readIsAbstractAttribute() {
        return readIsAbstract().thenApply(v -> (Boolean) v.getValue().getValue());
    }

    /**
     * Read the Symmetric attribute {@link DataValue}.
     *
     * @return the Symmetric attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readSymmetric();

    /**
     * Read the Symmetric attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readSymmetric()}.
     *
     * @return the Symmetric attribute.
     */
    default CompletableFuture<Boolean> readSymmetricAttribute() {
        return readSymmetric().thenApply(v -> (Boolean) v.getValue().getValue());
    }

    /**
     * Read the InverseName attribute {@link DataValue}.
     *
     * @return the InverseName attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readInverseName();

    /**
     * Read the InverseName attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readInverseName()}.
     *
     * @return the InverseName attribute.
     */
    default CompletableFuture<Optional<LocalizedText>> readInverseNameAttribute() {
        return readInverseName().thenApply(v -> {
            StatusCode statusCode = v.getStatusCode();

            if (statusCode.getValue() == StatusCodes.Bad_AttributeIdInvalid) {
                return Optional.empty();
            } else {
                return Optional.ofNullable((LocalizedText) v.getValue().getValue());
            }
        });
    }

    /**
     * Write a {@link DataValue} to the IsAbstract attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeIsAbstract(DataValue value);

    /**
     * Write a {@link Boolean} to the IsAbstract attribute.
     *
     * @param isAbstract the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeIsAbstractAttribute(boolean isAbstract) {
        return writeIsAbstract(new DataValue(new Variant(isAbstract)));
    }

    /**
     * Write a {@link DataValue} to the Symmetric attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeSymmetric(DataValue value);

    /**
     * Write a {@link Boolean} to the Symmetric attribute.
     *
     * @param symmetric the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeSymmetricAttribute(boolean symmetric) {
        return writeSymmetric(new DataValue(new Variant(symmetric)));
    }

    /**
     * Write a {@link DataValue} to the InverseName attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeInverseName(DataValue value);

    /**
     * Write a {@link LocalizedText} to the InverseName attribute.
     *
     * @param inverseName the {@link LocalizedText} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    default CompletableFuture<StatusCode> writeInverseNameAttribute(LocalizedText inverseName) {
        return writeInverseName(new DataValue(new Variant(inverseName)));
    }

}
