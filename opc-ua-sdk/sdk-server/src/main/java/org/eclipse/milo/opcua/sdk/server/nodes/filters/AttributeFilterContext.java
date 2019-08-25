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

import java.util.Iterator;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeObserver;
import org.eclipse.milo.opcua.sdk.server.nodes.DefaultAttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.util.Pending;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public abstract class AttributeFilterContext {

    private volatile boolean observable = false;

    private final UaNode node;
    private final Session session;

    private AttributeFilterContext(Session session, UaNode node) {
        this.session = session;
        this.node = node;
    }

    /**
     * Get the {@link UaNode} an attribute is being get from or set on.
     *
     * @return the {@link UaNode} an attribute is being get from or set on.
     */
    public UaNode getNode() {
        return node;
    }

    /**
     * Get the {@link Session} this the attribute get or set request is originating from, if there is one.
     * <p>
     * No session indicates the call is internal and should not fail for access / authentication reasons.
     *
     * @return the {@link Session} this the attribute get or set request is originating from, if there is one.
     */
    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

    /**
     * Indicate that an attribute get should be observed by {@link AttributeObserver}s.
     *
     * @param observable {@code true} if the attribute get should be observed by {@link AttributeObserver}s.
     */
    public void setObservable(boolean observable) {
        this.observable = observable;
    }

    /**
     * @return {@code true} if the attribute get should be observed by {@link AttributeObserver}s.
     */
    public boolean isObservable() {
        return observable;
    }


    public static final class GetAttributeContext extends AttributeFilterContext {

        private final Iterator<AttributeFilter> filterIterator;

        GetAttributeContext(Session session, UaNode node, Iterator<AttributeFilter> filterIterator) {
            super(session, node);

            this.filterIterator = filterIterator;
        }

        /**
         * Get the value for the attribute identified by {@code attributeId} using the next filter in the chain.
         *
         * @param attributeId the {@link AttributeId} of the attribute to get the value of.
         * @return the value for the attribute identified by {@code attributeId} from the next filter in the chain.
         */
        public Object getAttribute(AttributeId attributeId) {
            AttributeFilter next = filterIterator.hasNext() ?
                filterIterator.next() :
                DefaultAttributeFilter.INSTANCE;

            return next.getAttribute(this, attributeId);
        }

        public void getAttributeAsync(AttributeId attributeId, Pending<Unit, Object> pending) {
            AttributeFilter next = filterIterator.hasNext() ?
                filterIterator.next() :
                DefaultAttributeFilter.INSTANCE;

            next.getAttributeAsync(this, attributeId, pending);
        }

    }

    public static final class SetAttributeContext extends AttributeFilterContext {

        private final Iterator<AttributeFilter> filterIterator;

        SetAttributeContext(Session session, UaNode node, Iterator<AttributeFilter> filterIterator) {
            super(session, node);

            this.filterIterator = filterIterator;
        }

        /**
         * Set the value for the attribute identified by {@code attributeId} using the next filter in the chain.
         *
         * @param attributeId the {@link AttributeId} of the attribute to set the value of.
         * @param value       the value to set.
         */
        public void setAttribute(AttributeId attributeId, Object value) {
            AttributeFilter next = filterIterator.hasNext() ?
                filterIterator.next() :
                DefaultAttributeFilter.INSTANCE;

            next.setAttribute(this, attributeId, value);
        }

        public void setAttributeAsync(AttributeId attributeId, Pending<Object, Unit> pending) {
            AttributeFilter next = filterIterator.hasNext() ?
                filterIterator.next() :
                DefaultAttributeFilter.INSTANCE;

            next.setAttributeAsync(this, attributeId, pending);
        }

    }

}
