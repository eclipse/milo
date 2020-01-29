/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.filters;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.nodes.DefaultAttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.GetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.SetAttributeContext;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class AttributeFilterChain {

    private final ConcurrentLinkedDeque<AttributeFilter> filters = new ConcurrentLinkedDeque<>();

    /**
     * Create an empty {@link AttributeFilterChain}.
     */
    public AttributeFilterChain() {}

    /**
     * Create an {@link AttributeFilterChain} with {@code filter} added to it.
     *
     * @param filter the filter to add.
     */
    public AttributeFilterChain(AttributeFilter filter) {
        filters.add(filter);
    }

    /**
     * Create an {@link AttributeFilterChain} with {@code filters} added to it.
     *
     * @param filters the filters to add.
     */
    public AttributeFilterChain(List<AttributeFilter> filters) {
        this.filters.addAll(filters);
    }

    public boolean isAsync() {
        return filters.stream().anyMatch(AttributeFilter::isAsync);
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
            DefaultAttributeFilter.INSTANCE;

        GetAttributeContext ctx = new GetAttributeContext(session, node, filterIterator);

        Object value = filter.getAttributeBlocking(ctx, attributeId);

        if (ctx.isObservable()) {
            node.fireAttributeChanged(attributeId, value);
        }

        return value;
    }

    public void getAttributeAsync(
        @Nullable Session session,
        UaNode node,
        AttributeId attributeId,
        Pending<Unit, Object> promise
    ) {

        Iterator<AttributeFilter> filterIterator = filters.iterator();

        AttributeFilter filter = filterIterator.hasNext() ?
            filterIterator.next() :
            DefaultAttributeFilter.INSTANCE;

        GetAttributeContext ctx = new GetAttributeContext(session, node, filterIterator);

        if (ctx.isObservable()) {
            promise.getOutputFuture().thenAccept(
                value ->
                    node.fireAttributeChanged(attributeId, value)
            );
        }

        filter.getAttributeAsync(ctx, attributeId, promise);
    }

    public void setAttributeAsync(
        @Nullable Session session,
        UaNode node,
        AttributeId attributeId,
        Pending<Object, Unit> pending
    ) {
        Iterator<AttributeFilter> filterIterator = filters.iterator();

        AttributeFilter filter = filterIterator.hasNext() ?
            filterIterator.next() :
            DefaultAttributeFilter.INSTANCE;

        SetAttributeContext ctx = new SetAttributeContext(session, node, filterIterator);

        filter.setAttributeAsync(ctx, attributeId, pending);
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
            DefaultAttributeFilter.INSTANCE;

        SetAttributeContext ctx = new SetAttributeContext(session, node, filterIterator);

        filter.setAttributeBlocking(ctx, attributeId, value);
    }

    /**
     * Add {@code attributeFilter} to the front of this filter chain.
     *
     * @param attributeFilter the {@link AttributeFilter} to add.
     * @return this {@link AttributeFilterChain}.
     */
    public AttributeFilterChain addFirst(AttributeFilter attributeFilter) {
        filters.addFirst(attributeFilter);

        return this;
    }

    /**
     * Add {@code attributeFilters} (sequentially) to the front of this filter chain.
     *
     * @param attributeFilters the {@link AttributeFilter}s to add.
     * @return this {@link AttributeFilterChain}.
     */
    public AttributeFilterChain addFirst(AttributeFilter... attributeFilters) {
        Arrays.stream(attributeFilters).forEach(this::addFirst);

        return this;
    }

    /**
     * Add {@code attributeFilter} to the end of this filter chain.
     *
     * @param attributeFilter the {@link AttributeFilter} to add.
     * @return this {@link AttributeFilterChain}.
     */
    public AttributeFilterChain addLast(AttributeFilter attributeFilter) {
        filters.addLast(attributeFilter);

        return this;
    }

    /**
     * Add {@code attributeFilters} (sequentially) to the end of this filter chain.
     *
     * @param attributeFilters the {@link AttributeFilter}s to add.
     * @return this {@link AttributeFilterChain}.
     */
    public AttributeFilterChain addLast(AttributeFilter... attributeFilters) {
        Arrays.stream(attributeFilters).forEach(this::addLast);

        return this;
    }

}
