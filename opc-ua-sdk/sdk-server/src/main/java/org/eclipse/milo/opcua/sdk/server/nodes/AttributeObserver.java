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

package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.stack.core.AttributeId;

public interface AttributeObserver {

    /**
     * The Attribute indicated by {@code attributeId} on {@code node} changed.
     *
     * @param node        the {@link UaNode} the change originated from.
     * @param attributeId the {@link AttributeId} that changed.
     * @param value       the new value of the attribute.
     */
    void attributeChanged(UaNode node, AttributeId attributeId, Object value);

}
