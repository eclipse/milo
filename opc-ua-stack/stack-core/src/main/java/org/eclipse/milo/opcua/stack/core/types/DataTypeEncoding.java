/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface DataTypeEncoding {

    QualifiedName getName();

    ExtensionObject.BodyType getBodyType();

    Object encode(
        Object decodedBody,
        NodeId encodingId,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager);

    Object decode(
        Object encodedBody,
        NodeId encodingId,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager);

}
