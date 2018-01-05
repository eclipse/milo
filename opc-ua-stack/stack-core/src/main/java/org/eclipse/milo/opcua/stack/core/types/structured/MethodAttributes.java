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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class MethodAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.MethodAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.MethodAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MethodAttributes_Encoding_DefaultXml;

    protected final Boolean executable;
    protected final Boolean userExecutable;

    public MethodAttributes() {
        super(null, null, null, null, null);
        this.executable = null;
        this.userExecutable = null;
    }

    public MethodAttributes(UInteger specifiedAttributes, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, Boolean executable, Boolean userExecutable) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.executable = executable;
        this.userExecutable = userExecutable;
    }

    public Boolean getExecutable() { return executable; }

    public Boolean getUserExecutable() { return userExecutable; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SpecifiedAttributes", specifiedAttributes)
            .add("DisplayName", displayName)
            .add("Description", description)
            .add("WriteMask", writeMask)
            .add("UserWriteMask", userWriteMask)
            .add("Executable", executable)
            .add("UserExecutable", userExecutable)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<MethodAttributes> {

        @Override
        public Class<MethodAttributes> getType() {
            return MethodAttributes.class;
        }

        @Override
        public MethodAttributes decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            Boolean executable = decoder.readBoolean("Executable");
            Boolean userExecutable = decoder.readBoolean("UserExecutable");

            return new MethodAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, executable, userExecutable);
        }

        @Override
        public void encode(MethodAttributes value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedAttributes", value.specifiedAttributes);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
            encoder.writeBoolean("Executable", value.executable);
            encoder.writeBoolean("UserExecutable", value.userExecutable);
        }
    }

}
