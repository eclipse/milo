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
