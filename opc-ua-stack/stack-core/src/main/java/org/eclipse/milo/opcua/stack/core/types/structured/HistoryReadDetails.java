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

public class HistoryReadDetails implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadDetails;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadDetails_Encoding_DefaultXml;


    public HistoryReadDetails() {
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

    public static class Codec extends BuiltinDataTypeCodec<HistoryReadDetails> {

        @Override
        public Class<HistoryReadDetails> getType() {
            return HistoryReadDetails.class;
        }

        @Override
        public HistoryReadDetails decode(UaDecoder decoder) throws UaSerializationException {

            return new HistoryReadDetails();
        }

        @Override
        public void encode(HistoryReadDetails value, UaEncoder encoder) throws UaSerializationException {
        }
    }

}
