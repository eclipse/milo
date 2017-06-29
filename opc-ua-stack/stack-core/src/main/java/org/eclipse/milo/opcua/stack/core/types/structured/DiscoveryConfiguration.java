/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DiscoveryConfiguration implements UaStructure {

    public static final NodeId TypeId = Identifiers.DiscoveryConfiguration;
    public static final NodeId BinaryEncodingId = Identifiers.DiscoveryConfiguration_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DiscoveryConfiguration_Encoding_DefaultXml;


    public DiscoveryConfiguration() {
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

    public static class Codec extends BuiltinDataTypeCodec<DiscoveryConfiguration> {

        @Override
        public Class<DiscoveryConfiguration> getType() {
            return DiscoveryConfiguration.class;
        }

        @Override
        public DiscoveryConfiguration decode(UaDecoder decoder) throws UaSerializationException {

            return new DiscoveryConfiguration();
        }

        @Override
        public void encode(DiscoveryConfiguration value, UaEncoder encoder) throws UaSerializationException {
        }
    }

}
