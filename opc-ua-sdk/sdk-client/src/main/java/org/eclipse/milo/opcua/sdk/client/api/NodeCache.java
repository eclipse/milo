/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
