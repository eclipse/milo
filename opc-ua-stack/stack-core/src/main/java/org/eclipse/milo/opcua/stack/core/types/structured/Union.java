/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class Union implements UaStructure {

    public static final NodeId TypeId = Identifiers.Union;
    public static final NodeId BinaryEncodingId = Identifiers.Union_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Union_Encoding_DefaultXml;


    public Union() {
    }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<Union> {

        @Override
        public Class<Union> getType() {
            return Union.class;
        }

        @Override
        public Union decode(UaDecoder decoder) throws UaSerializationException {

            return new Union();
        }

        @Override
        public void encode(Union value, UaEncoder encoder) throws UaSerializationException {
        }
    }

}
