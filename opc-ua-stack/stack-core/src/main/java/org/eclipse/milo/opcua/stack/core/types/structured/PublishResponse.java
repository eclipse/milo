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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PublishResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=827");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=829");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=828");

    private final ResponseHeader responseHeader;

    private final UInteger subscriptionId;

    private final UInteger[] availableSequenceNumbers;

    private final Boolean moreNotifications;

    private final NotificationMessage notificationMessage;

    private final StatusCode[] results;

    private final DiagnosticInfo[] diagnosticInfos;

    public PublishResponse(ResponseHeader responseHeader, UInteger subscriptionId,
                           UInteger[] availableSequenceNumbers, Boolean moreNotifications,
                           NotificationMessage notificationMessage, StatusCode[] results,
                           DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.subscriptionId = subscriptionId;
        this.availableSequenceNumbers = availableSequenceNumbers;
        this.moreNotifications = moreNotifications;
        this.notificationMessage = notificationMessage;
        this.results = results;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public UInteger[] getAvailableSequenceNumbers() {
        return availableSequenceNumbers;
    }

    public Boolean getMoreNotifications() {
        return moreNotifications;
    }

    public NotificationMessage getNotificationMessage() {
        return notificationMessage;
    }

    public StatusCode[] getResults() {
        return results;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<PublishResponse> {
        @Override
        public Class<PublishResponse> getType() {
            return PublishResponse.class;
        }

        @Override
        public PublishResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger[] availableSequenceNumbers = decoder.readUInt32Array("AvailableSequenceNumbers");
            Boolean moreNotifications = decoder.readBoolean("MoreNotifications");
            NotificationMessage notificationMessage = (NotificationMessage) decoder.readStruct("NotificationMessage", NotificationMessage.TYPE_ID);
            StatusCode[] results = decoder.readStatusCodeArray("Results");
            DiagnosticInfo[] diagnosticInfos = decoder.readDiagnosticInfoArray("DiagnosticInfos");
            return new PublishResponse(responseHeader, subscriptionId, availableSequenceNumbers, moreNotifications, notificationMessage, results, diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PublishResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeUInt32Array("AvailableSequenceNumbers", value.getAvailableSequenceNumbers());
            encoder.writeBoolean("MoreNotifications", value.getMoreNotifications());
            encoder.writeStruct("NotificationMessage", value.getNotificationMessage(), NotificationMessage.TYPE_ID);
            encoder.writeStatusCodeArray("Results", value.getResults());
            encoder.writeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
        }
    }
}
