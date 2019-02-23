/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * Identifies a structured type as one of the built-in structured types.
 * <p>
 * Built-in structures can be serialized and de-serialized by any of the transport encodings regardless of whether or
 * not they have a registered DataTypeCodec belonging to a DataTypeDictionary because the stack has special knowledge
 * of these types.
 * <p>
 * This implies that custom user-defined structures should not implement this interface.
 */
public interface UaStructure extends UaSerializable {

    NodeId getTypeId();

    NodeId getBinaryEncodingId();

    NodeId getXmlEncodingId();

}
