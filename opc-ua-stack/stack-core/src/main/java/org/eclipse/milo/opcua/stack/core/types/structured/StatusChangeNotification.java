/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.4">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.25.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class StatusChangeNotification extends NotificationData implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=818");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=820");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=819");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15350");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public StatusCode getStatus() {
        return status;
    }

    public DiagnosticInfo getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 820),
            new NodeId(0, 945),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Status", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfo", LocalizedText.NULL_VALUE, new NodeId(0, 25), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StatusChangeNotification> {
        @Override
        public Class<StatusChangeNotification> getType() {
            return StatusChangeNotification.class;
        }

        @Override
        public StatusChangeNotification decodeType(SerializationContext context, UaDecoder decoder) {
            StatusCode status = decoder.readStatusCode("Status");
            DiagnosticInfo diagnosticInfo = decoder.readDiagnosticInfo("DiagnosticInfo");
            return new StatusChangeNotification(status, diagnosticInfo);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               StatusChangeNotification value) {
            encoder.writeStatusCode("Status", value.getStatus());
            encoder.writeDiagnosticInfo("DiagnosticInfo", value.getDiagnosticInfo());
        }
    }
}
