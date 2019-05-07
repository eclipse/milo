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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DataChangeNotification extends NotificationData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=809");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=811");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=810");

    private final MonitoredItemNotification[] monitoredItems;

    private final DiagnosticInfo[] diagnosticInfos;

    public DataChangeNotification(MonitoredItemNotification[] monitoredItems,
                                  DiagnosticInfo[] diagnosticInfos) {
        this.monitoredItems = monitoredItems;
        this.diagnosticInfos = diagnosticInfos;
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

    public MonitoredItemNotification[] getMonitoredItems() {
        return monitoredItems;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<DataChangeNotification> {
        @Override
        public Class<DataChangeNotification> getType() {
            return DataChangeNotification.class;
        }

        @Override
        public DataChangeNotification decode(SerializationContext context, UaDecoder decoder) {
            MonitoredItemNotification[] monitoredItems = (MonitoredItemNotification[]) decoder.readStructArray("MonitoredItems", MonitoredItemNotification.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.readDiagnosticInfoArray("DiagnosticInfos");
            return new DataChangeNotification(monitoredItems, diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DataChangeNotification value) {
            encoder.writeStructArray("MonitoredItems", value.getMonitoredItems(), MonitoredItemNotification.TYPE_ID);
            encoder.writeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
        }
    }
}
