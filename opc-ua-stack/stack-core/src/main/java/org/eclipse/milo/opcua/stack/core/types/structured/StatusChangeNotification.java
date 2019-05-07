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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class StatusChangeNotification extends NotificationData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=818");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=820");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=819");

    private final StatusCode status;

    private final DiagnosticInfo diagnosticInfo;

    public StatusChangeNotification(StatusCode status, DiagnosticInfo diagnosticInfo) {
        this.status = status;
        this.diagnosticInfo = diagnosticInfo;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public StatusCode getStatus() {
        return status;
    }

    public DiagnosticInfo getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public static final class Codec extends GenericDataTypeCodec<StatusChangeNotification> {
        @Override
        public Class<StatusChangeNotification> getType() {
            return StatusChangeNotification.class;
        }

        @Override
        public StatusChangeNotification decode(SerializationContext context, UaDecoder decoder) {
            StatusCode status = decoder.readStatusCode("Status");
            DiagnosticInfo diagnosticInfo = decoder.readDiagnosticInfo("DiagnosticInfo");
            return new StatusChangeNotification(status, diagnosticInfo);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           StatusChangeNotification value) {
            encoder.writeStatusCode("Status", value.getStatus());
            encoder.writeDiagnosticInfo("DiagnosticInfo", value.getDiagnosticInfo());
        }
    }
}
