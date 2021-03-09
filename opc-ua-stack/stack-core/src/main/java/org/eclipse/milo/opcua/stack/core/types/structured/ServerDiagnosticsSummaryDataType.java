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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ServerDiagnosticsSummaryDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=859");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=860");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=861");

    private final UInteger serverViewCount;

    private final UInteger currentSessionCount;

    private final UInteger cumulatedSessionCount;

    private final UInteger securityRejectedSessionCount;

    private final UInteger rejectedSessionCount;

    private final UInteger sessionTimeoutCount;

    private final UInteger sessionAbortCount;

    private final UInteger currentSubscriptionCount;

    private final UInteger cumulatedSubscriptionCount;

    private final UInteger publishingIntervalCount;

    private final UInteger securityRejectedRequestsCount;

    private final UInteger rejectedRequestsCount;

    public ServerDiagnosticsSummaryDataType(UInteger serverViewCount, UInteger currentSessionCount,
                                            UInteger cumulatedSessionCount, UInteger securityRejectedSessionCount,
                                            UInteger rejectedSessionCount, UInteger sessionTimeoutCount, UInteger sessionAbortCount,
                                            UInteger currentSubscriptionCount, UInteger cumulatedSubscriptionCount,
                                            UInteger publishingIntervalCount, UInteger securityRejectedRequestsCount,
                                            UInteger rejectedRequestsCount) {
        this.serverViewCount = serverViewCount;
        this.currentSessionCount = currentSessionCount;
        this.cumulatedSessionCount = cumulatedSessionCount;
        this.securityRejectedSessionCount = securityRejectedSessionCount;
        this.rejectedSessionCount = rejectedSessionCount;
        this.sessionTimeoutCount = sessionTimeoutCount;
        this.sessionAbortCount = sessionAbortCount;
        this.currentSubscriptionCount = currentSubscriptionCount;
        this.cumulatedSubscriptionCount = cumulatedSubscriptionCount;
        this.publishingIntervalCount = publishingIntervalCount;
        this.securityRejectedRequestsCount = securityRejectedRequestsCount;
        this.rejectedRequestsCount = rejectedRequestsCount;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public UInteger getServerViewCount() {
        return serverViewCount;
    }

    public UInteger getCurrentSessionCount() {
        return currentSessionCount;
    }

    public UInteger getCumulatedSessionCount() {
        return cumulatedSessionCount;
    }

    public UInteger getSecurityRejectedSessionCount() {
        return securityRejectedSessionCount;
    }

    public UInteger getRejectedSessionCount() {
        return rejectedSessionCount;
    }

    public UInteger getSessionTimeoutCount() {
        return sessionTimeoutCount;
    }

    public UInteger getSessionAbortCount() {
        return sessionAbortCount;
    }

    public UInteger getCurrentSubscriptionCount() {
        return currentSubscriptionCount;
    }

    public UInteger getCumulatedSubscriptionCount() {
        return cumulatedSubscriptionCount;
    }

    public UInteger getPublishingIntervalCount() {
        return publishingIntervalCount;
    }

    public UInteger getSecurityRejectedRequestsCount() {
        return securityRejectedRequestsCount;
    }

    public UInteger getRejectedRequestsCount() {
        return rejectedRequestsCount;
    }

    public static final class Codec extends GenericDataTypeCodec<ServerDiagnosticsSummaryDataType> {
        @Override
        public Class<ServerDiagnosticsSummaryDataType> getType() {
            return ServerDiagnosticsSummaryDataType.class;
        }

        @Override
        public ServerDiagnosticsSummaryDataType decode(SerializationContext context,
                                                       UaDecoder decoder) {
            UInteger serverViewCount = decoder.readUInt32("ServerViewCount");
            UInteger currentSessionCount = decoder.readUInt32("CurrentSessionCount");
            UInteger cumulatedSessionCount = decoder.readUInt32("CumulatedSessionCount");
            UInteger securityRejectedSessionCount = decoder.readUInt32("SecurityRejectedSessionCount");
            UInteger rejectedSessionCount = decoder.readUInt32("RejectedSessionCount");
            UInteger sessionTimeoutCount = decoder.readUInt32("SessionTimeoutCount");
            UInteger sessionAbortCount = decoder.readUInt32("SessionAbortCount");
            UInteger currentSubscriptionCount = decoder.readUInt32("CurrentSubscriptionCount");
            UInteger cumulatedSubscriptionCount = decoder.readUInt32("CumulatedSubscriptionCount");
            UInteger publishingIntervalCount = decoder.readUInt32("PublishingIntervalCount");
            UInteger securityRejectedRequestsCount = decoder.readUInt32("SecurityRejectedRequestsCount");
            UInteger rejectedRequestsCount = decoder.readUInt32("RejectedRequestsCount");
            return new ServerDiagnosticsSummaryDataType(serverViewCount, currentSessionCount, cumulatedSessionCount, securityRejectedSessionCount, rejectedSessionCount, sessionTimeoutCount, sessionAbortCount, currentSubscriptionCount, cumulatedSubscriptionCount, publishingIntervalCount, securityRejectedRequestsCount, rejectedRequestsCount);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ServerDiagnosticsSummaryDataType value) {
            encoder.writeUInt32("ServerViewCount", value.getServerViewCount());
            encoder.writeUInt32("CurrentSessionCount", value.getCurrentSessionCount());
            encoder.writeUInt32("CumulatedSessionCount", value.getCumulatedSessionCount());
            encoder.writeUInt32("SecurityRejectedSessionCount", value.getSecurityRejectedSessionCount());
            encoder.writeUInt32("RejectedSessionCount", value.getRejectedSessionCount());
            encoder.writeUInt32("SessionTimeoutCount", value.getSessionTimeoutCount());
            encoder.writeUInt32("SessionAbortCount", value.getSessionAbortCount());
            encoder.writeUInt32("CurrentSubscriptionCount", value.getCurrentSubscriptionCount());
            encoder.writeUInt32("CumulatedSubscriptionCount", value.getCumulatedSubscriptionCount());
            encoder.writeUInt32("PublishingIntervalCount", value.getPublishingIntervalCount());
            encoder.writeUInt32("SecurityRejectedRequestsCount", value.getSecurityRejectedRequestsCount());
            encoder.writeUInt32("RejectedRequestsCount", value.getRejectedRequestsCount());
        }
    }
}
