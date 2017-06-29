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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class BrowsePathResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePathResult;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePathResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePathResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final BrowsePathTarget[] targets;

    public BrowsePathResult() {
        this.statusCode = null;
        this.targets = null;
    }

    public BrowsePathResult(StatusCode statusCode, BrowsePathTarget[] targets) {
        this.statusCode = statusCode;
        this.targets = targets;
    }

    public StatusCode getStatusCode() { return statusCode; }

    @Nullable
    public BrowsePathTarget[] getTargets() { return targets; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("Targets", targets)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowsePathResult> {

        @Override
        public Class<BrowsePathResult> getType() {
            return BrowsePathResult.class;
        }

        @Override
        public BrowsePathResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            BrowsePathTarget[] targets =
                decoder.readBuiltinStructArray(
                    "Targets",
                    BrowsePathTarget.class
                );

            return new BrowsePathResult(statusCode, targets);
        }

        @Override
        public void encode(BrowsePathResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeBuiltinStructArray(
                "Targets",
                value.targets,
                BrowsePathTarget.class
            );
        }
    }

}
