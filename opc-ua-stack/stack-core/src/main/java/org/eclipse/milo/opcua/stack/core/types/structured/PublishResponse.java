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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class PublishResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.PublishResponse;
    public static final NodeId BinaryEncodingId = Identifiers.PublishResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.PublishResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final UInteger subscriptionId;
    protected final UInteger[] availableSequenceNumbers;
    protected final Boolean moreNotifications;
    protected final NotificationMessage notificationMessage;
    protected final StatusCode[] results;
    protected final DiagnosticInfo[] diagnosticInfos;

    public PublishResponse() {
        this.responseHeader = null;
        this.subscriptionId = null;
        this.availableSequenceNumbers = null;
        this.moreNotifications = null;
        this.notificationMessage = null;
        this.results = null;
        this.diagnosticInfos = null;
    }

    public PublishResponse(ResponseHeader responseHeader, UInteger subscriptionId, UInteger[] availableSequenceNumbers, Boolean moreNotifications, NotificationMessage notificationMessage, StatusCode[] results, DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.subscriptionId = subscriptionId;
        this.availableSequenceNumbers = availableSequenceNumbers;
        this.moreNotifications = moreNotifications;
        this.notificationMessage = notificationMessage;
        this.results = results;
        this.diagnosticInfos = diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    @Nullable
    public UInteger[] getAvailableSequenceNumbers() { return availableSequenceNumbers; }

    public Boolean getMoreNotifications() { return moreNotifications; }

    public NotificationMessage getNotificationMessage() { return notificationMessage; }

    @Nullable
    public StatusCode[] getResults() { return results; }

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
            .add("ResponseHeader", responseHeader)
            .add("SubscriptionId", subscriptionId)
            .add("AvailableSequenceNumbers", availableSequenceNumbers)
            .add("MoreNotifications", moreNotifications)
            .add("NotificationMessage", notificationMessage)
            .add("Results", results)
            .add("DiagnosticInfos", diagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<PublishResponse> {

        @Override
        public Class<PublishResponse> getType() {
            return PublishResponse.class;
        }

        @Override
        public PublishResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger[] availableSequenceNumbers = decoder.readArray("AvailableSequenceNumbers", decoder::readUInt32, UInteger.class);
            Boolean moreNotifications = decoder.readBoolean("MoreNotifications");
            NotificationMessage notificationMessage = (NotificationMessage) decoder.readBuiltinStruct("NotificationMessage", NotificationMessage.class);
            StatusCode[] results = decoder.readArray("Results", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] diagnosticInfos = decoder.readArray("DiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new PublishResponse(responseHeader, subscriptionId, availableSequenceNumbers, moreNotifications, notificationMessage, results, diagnosticInfos);
        }

        @Override
        public void encode(PublishResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeArray("AvailableSequenceNumbers", value.availableSequenceNumbers, encoder::writeUInt32);
            encoder.writeBoolean("MoreNotifications", value.moreNotifications);
            encoder.writeBuiltinStruct("NotificationMessage", value.notificationMessage, NotificationMessage.class);
            encoder.writeArray("Results", value.results, encoder::writeStatusCode);
            encoder.writeArray("DiagnosticInfos", value.diagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
