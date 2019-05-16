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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.AttributeId;

public class AttributeFilterChain {

    private final ConcurrentLinkedDeque<AttributeFilter> filters = new ConcurrentLinkedDeque<>();

    public AttributeFilterChain() {}

    public AttributeFilterChain(AttributeFilter filter) {
        filters.add(filter);
    }

    public AttributeFilterChain(List<AttributeFilter> filters) {
        Preconditions.checkArgument(!filters.isEmpty(), "filters must be non-empty");

        this.filters.addAll(filters);
    }

    /**
     * Get the value for the attribute identified by {@code attributeId} from {@code node}.
     *
     * @param node        the {@link UaNode} to get the attribute value from.
     * @param attributeId the {@link AttributeId} of the attribute to get the value of.
     * @return the value for the attribute identified by {@code attributeId} from {@code node}.
     */
    public Object getAttribute(UaNode node, AttributeId attributeId) {
        return getAttribute(null, node, attributeId);
    }

    /**
     * Get the value for the attribute identified by {@code attributeId} from {@code node}.
     *
     * @param session     the {@link Session} the attribute request is originating from, if there is one.
     * @param node        the {@link UaNode} to get the attribute value from.
     * @param attributeId the {@link AttributeId} of the attribute to get the value of.
     * @return the value for the attribute identified by {@code attributeId} from {@code node}.
     */
    public Object getAttribute(@Nullable Session session, UaNode node, AttributeId attributeId) {
        Iterator<AttributeFilter> filterIterator = filters.iterator();

        AttributeFilter filter = filterIterator.hasNext() ?
            filterIterator.next() :
            AttributeFilter.DEFAULT_INSTANCE;

        AttributeFilterContext ctx = new AttributeFilterContext(session, node, filterIterator);

        return filter.getAttribute(ctx, attributeId);
    }

    /**
     * Set the value for the attribute identified by {@code attributeId}.
     *
     * @param node        the {@link UaNode} to set the attribute value on.
     * @param attributeId the {@link AttributeId} of the attribute to set the value of.
     * @param value       the value to set.
     */
    public void setAttribute(UaNode node, AttributeId attributeId, Object value) {
        setAttribute(null, node, attributeId, value);
    }

    /**
     * Set the value for the attribute identified by {@code attributeId}.
     *
     * @param session     the {@link Session} the attribute request is originating from, if there is one.
     * @param node        the {@link UaNode} to set the attribute value on.
     * @param attributeId the {@link AttributeId} of the attribute to set the value of.
     * @param value       the value to set.
     */
    public void setAttribute(@Nullable Session session, UaNode node, AttributeId attributeId, Object value) {
        Iterator<AttributeFilter> filterIterator = filters.iterator();

        AttributeFilter filter = filterIterator.hasNext() ?
            filterIterator.next() :
            AttributeFilter.DEFAULT_INSTANCE;

        AttributeFilterContext ctx = new AttributeFilterContext(session, node, filterIterator);

        filter.setAttribute(ctx, attributeId, value);
    }

    public AttributeFilterChain addFirst(AttributeFilter attributeFilter) {
        filters.addFirst(attributeFilter);

        return this;
    }

    public AttributeFilterChain addFirst(AttributeFilter... attributeFilters) {
        Arrays.stream(attributeFilters).forEach(filters::addFirst);

        return this;
    }

    public AttributeFilterChain addLast(AttributeFilter attributeFilter) {
        filters.addLast(attributeFilter);

        return this;
    }

    public AttributeFilterChain addLast(AttributeFilter... attributeFilters) {
        Arrays.stream(attributeFilters).forEach(filters::addLast);

        return this;
    }

}
