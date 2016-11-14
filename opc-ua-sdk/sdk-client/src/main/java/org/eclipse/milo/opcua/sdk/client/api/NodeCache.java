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

package org.eclipse.milo.opcua.sdk.client.api;

import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface NodeCache {

    Optional<DataValue> getAttribute(NodeId nodeId, AttributeId attributeId);

    void putAttribute(NodeId nodeId, AttributeId attributeId, DataValue value);

    void invalidate(NodeId nodeId);

    void invalidate(NodeId nodeId, AttributeId attributeId);

    void invalidateAll();

}
