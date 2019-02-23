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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DataChangeNotification extends NotificationData {

    public static final NodeId TypeId = Identifiers.DataChangeNotification;
    public static final NodeId BinaryEncodingId = Identifiers.DataChangeNotification_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DataChangeNotification_Encoding_DefaultXml;

    protected final MonitoredItemNotification[] monitoredItems;
    protected final DiagnosticInfo[] diagnosticInfos;

    public DataChangeNotification() {
        super();
        this.monitoredItems = null;
        this.diagnosticInfos = null;
    }

    public DataChangeNotification(MonitoredItemNotification[] monitoredItems, DiagnosticInfo[] diagnosticInfos) {
        super();
        this.monitoredItems = monitoredItems;
        this.diagnosticInfos = diagnosticInfos;
    }

    @Nullable
    public MonitoredItemNotification[] getMonitoredItems() { return monitoredItems; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("MonitoredItems", monitoredItems)
            .add("DiagnosticInfos", diagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DataChangeNotification> {

        @Override
        public Class<DataChangeNotification> getType() {
            return DataChangeNotification.class;
        }

        @Override
        public DataChangeNotification decode(UaDecoder decoder) throws UaSerializationException {
            MonitoredItemNotification[] monitoredItems =
                decoder.readBuiltinStructArray(
                    "MonitoredItems",
                    MonitoredItemNotification.class
                );
            DiagnosticInfo[] diagnosticInfos = decoder.readArray("DiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new DataChangeNotification(monitoredItems, diagnosticInfos);
        }

        @Override
        public void encode(DataChangeNotification value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStructArray(
                "MonitoredItems",
                value.monitoredItems,
                MonitoredItemNotification.class
            );
            encoder.writeArray("DiagnosticInfos", value.diagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
