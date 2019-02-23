/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
