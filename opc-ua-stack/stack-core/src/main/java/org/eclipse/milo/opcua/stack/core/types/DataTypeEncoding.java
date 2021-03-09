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

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface DataTypeEncoding {

    QualifiedName getName();

    ExtensionObject.BodyType getBodyType();
    
    Object encode(
        SerializationContext context,
        Object decodedBody,
        NodeId encodingId
    );

    Object decode(
        SerializationContext context,
        Object encodedBody,
        NodeId encodingId
    );

    default Object encode(
        SerializationContext context,
        Object decodedBody,
        ExpandedNodeId xEncodingId
    ) {

        NodeId encodingId = xEncodingId.toNodeId(context.getNamespaceTable())
            .orElseThrow(
                () ->
                    new UaSerializationException(
                        StatusCodes.Bad_EncodingError,
                        "namespace not registered: " +
                            xEncodingId.getNamespaceUri())
            );

        return encode(context, decodedBody, encodingId);
    }

    default Object decode(
        SerializationContext context,
        Object encodedBody,
        ExpandedNodeId xEncodingId
    ) {

        NodeId encodingId = xEncodingId.toNodeId(context.getNamespaceTable())
            .orElseThrow(
                () ->
                    new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "namespace not registered: " +
                            xEncodingId.getNamespaceUri())
            );

        return decode(context, encodedBody, encodingId);
    }

}
