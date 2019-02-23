/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.Collection;
import java.util.Optional;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.util.AttributeReader;
import org.eclipse.milo.opcua.sdk.server.util.AttributeWriter;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public interface UaServerNode extends Node {

    /**
     * @return the {@link UaNodeContext} for this {@link UaServerNode}.
     */
    UaNodeContext getNodeContext();

    /**
     * Add a {@link Reference} to this node.
     *
     * @param reference the {@link Reference} to add.
     */
    void addReference(Reference reference);

    /**
     * Add {@link Reference}s to this node.
     *
     * @param c the {@link Reference}s to add.
     */
    default void addReferences(Collection<Reference> c) {
        c.forEach(this::addReference);
    }

    /**
     * Remove a {@link Reference} from this node.
     *
     * @param reference to remove.
     */
    void removeReference(Reference reference);

    /**
     * Remove {@link Reference}s from this node.
     *
     * @param c the {@link Reference}s to remove.
     */
    default void removeReferences(Collection<Reference> c) {
        c.forEach(this::removeReference);
    }

    /**
     * @return this node's {@link Reference}s.
     */
    ImmutableList<Reference> getReferences();

    /**
     * Read the specified attribute.
     * <p>
     * If the attribute is not specified on this node, a value with status {@link StatusCodes#Bad_AttributeIdInvalid}
     * will be returned.
     *
     * @param attribute the id of the attribute to read.
     * @return the value of the specified attribute.
     */
    default DataValue readAttribute(AttributeContext context, UInteger attribute) {
        return readAttribute(context, attribute, null, null, null);
    }

    /**
     * Read the specified attribute.
     * <p>
     * If the attribute is not specified on this node, a value with status {@link StatusCodes#Bad_AttributeIdInvalid}
     * will be returned.
     *
     * @param attribute    the id of the attribute to read.
     * @param timestamps   the {@link TimestampsToReturn}.
     * @param indexRange   the index range to read. Must be a parseable by {@link NumericRange}.
     * @param dataEncoding the requested data encoding.
     * @return the value of the specified attribute.
     */
    default DataValue readAttribute(
        AttributeContext context,
        UInteger attribute,
        @Nullable TimestampsToReturn timestamps,
        @Nullable String indexRange,
        @Nullable QualifiedName dataEncoding) {

        return AttributeId.from(attribute)
            .map(attributeId -> readAttribute(context, attributeId, timestamps, indexRange, dataEncoding))
            .orElse(new DataValue(StatusCodes.Bad_AttributeIdInvalid));
    }

    /**
     * Read the specified attribute, applying {@code timestamps} and {@code indexRange} if specified.
     * <p>
     * If the attribute is not specified on this node, a value with status {@link StatusCodes#Bad_AttributeIdInvalid}
     * will be returned.
     *
     * @param attributeId  the id of the attribute to read.
     * @param timestamps   the {@link TimestampsToReturn}.
     * @param indexRange   the index range to read. Must be a parseable by {@link NumericRange}.
     * @param dataEncoding the requested data encoding.
     * @return the value of the specified attribute.
     */
    default DataValue readAttribute(
        AttributeContext context,
        AttributeId attributeId,
        @Nullable TimestampsToReturn timestamps,
        @Nullable String indexRange,
        @Nullable QualifiedName dataEncoding) {

        return AttributeReader.readAttribute(context, this, attributeId, timestamps, indexRange, dataEncoding);
    }

    /**
     * Write to the specified attribute.
     *
     * @param context    the {@link NamespaceManager}.
     * @param attribute  the id of the attribute to write.
     * @param value      the {@link DataValue} write.
     * @param indexRange the index range to write. Must be a parseable by {@link NumericRange}.
     * @throws UaException if writing to the attribute fails.
     */
    default void writeAttribute(
        AttributeContext context,
        UInteger attribute,
        DataValue value,
        String indexRange) throws UaException {

        Optional<AttributeId> attributeId = AttributeId.from(attribute);

        if (attributeId.isPresent()) {
            writeAttribute(context, attributeId.get(), value, indexRange);
        } else {
            throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }
    }

    /**
     * Write to the specified attribute.
     *
     * @param context     the {@link NamespaceManager}.
     * @param attributeId the {@link AttributeId} of the attribute to write.
     * @param value       the {@link DataValue} write.
     * @param indexRange  the index range to write. Must be a parseable by {@link NumericRange}.
     * @throws UaException if writing to the attribute fails.
     */
    default void writeAttribute(
        AttributeContext context,
        AttributeId attributeId,
        DataValue value,
        String indexRange) throws UaException {

        AttributeWriter.writeAttribute(context, this, attributeId, value, indexRange);
    }

    /**
     * Get an attribute of this node, taking the {@link AttributeContext} into account and respecting any
     * {@link AttributeDelegate} this node may have.
     *
     * @param context     the {@link AttributeContext} to get the attribute in.
     * @param attributeId the {@link AttributeId} to get.
     * @return a {@link DataValue} containing the attribute value or a {@link StatusCode} describing any failure.
     */
    DataValue getAttribute(AttributeContext context, AttributeId attributeId);

    /**
     * Set an attribute of this node, taking the {@link AttributeContext} into account and respecting any
     * {@link AttributeDelegate} this node may have.
     *
     * @param context     the {@link AttributeContext} to set the attribute in.
     * @param attributeId the {@link AttributeId} to set.
     * @param value       the new {@link DataValue} to set for the attribute.
     * @throws UaException if setting the attribute failed for any reason.
     */
    void setAttribute(AttributeContext context, AttributeId attributeId, DataValue value) throws UaException;

}
