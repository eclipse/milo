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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerDiagnosticsSummaryDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServerDiagnosticsSummaryDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml;

    protected final UInteger serverViewCount;
    protected final UInteger currentSessionCount;
    protected final UInteger cumulatedSessionCount;
    protected final UInteger securityRejectedSessionCount;
    protected final UInteger rejectedSessionCount;
    protected final UInteger sessionTimeoutCount;
    protected final UInteger sessionAbortCount;
    protected final UInteger currentSubscriptionCount;
    protected final UInteger cumulatedSubscriptionCount;
    protected final UInteger publishingIntervalCount;
    protected final UInteger securityRejectedRequestsCount;
    protected final UInteger rejectedRequestsCount;

    public ServerDiagnosticsSummaryDataType() {
        this.serverViewCount = null;
        this.currentSessionCount = null;
        this.cumulatedSessionCount = null;
        this.securityRejectedSessionCount = null;
        this.rejectedSessionCount = null;
        this.sessionTimeoutCount = null;
        this.sessionAbortCount = null;
        this.currentSubscriptionCount = null;
        this.cumulatedSubscriptionCount = null;
        this.publishingIntervalCount = null;
        this.securityRejectedRequestsCount = null;
        this.rejectedRequestsCount = null;
    }

    public ServerDiagnosticsSummaryDataType(UInteger serverViewCount, UInteger currentSessionCount, UInteger cumulatedSessionCount, UInteger securityRejectedSessionCount, UInteger rejectedSessionCount, UInteger sessionTimeoutCount, UInteger sessionAbortCount, UInteger currentSubscriptionCount, UInteger cumulatedSubscriptionCount, UInteger publishingIntervalCount, UInteger securityRejectedRequestsCount, UInteger rejectedRequestsCount) {
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

    public UInteger getServerViewCount() { return serverViewCount; }

    public UInteger getCurrentSessionCount() { return currentSessionCount; }

    public UInteger getCumulatedSessionCount() { return cumulatedSessionCount; }

    public UInteger getSecurityRejectedSessionCount() { return securityRejectedSessionCount; }

    public UInteger getRejectedSessionCount() { return rejectedSessionCount; }

    public UInteger getSessionTimeoutCount() { return sessionTimeoutCount; }

    public UInteger getSessionAbortCount() { return sessionAbortCount; }

    public UInteger getCurrentSubscriptionCount() { return currentSubscriptionCount; }

    public UInteger getCumulatedSubscriptionCount() { return cumulatedSubscriptionCount; }

    public UInteger getPublishingIntervalCount() { return publishingIntervalCount; }

    public UInteger getSecurityRejectedRequestsCount() { return securityRejectedRequestsCount; }

    public UInteger getRejectedRequestsCount() { return rejectedRequestsCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ServerViewCount", serverViewCount)
            .add("CurrentSessionCount", currentSessionCount)
            .add("CumulatedSessionCount", cumulatedSessionCount)
            .add("SecurityRejectedSessionCount", securityRejectedSessionCount)
            .add("RejectedSessionCount", rejectedSessionCount)
            .add("SessionTimeoutCount", sessionTimeoutCount)
            .add("SessionAbortCount", sessionAbortCount)
            .add("CurrentSubscriptionCount", currentSubscriptionCount)
            .add("CumulatedSubscriptionCount", cumulatedSubscriptionCount)
            .add("PublishingIntervalCount", publishingIntervalCount)
            .add("SecurityRejectedRequestsCount", securityRejectedRequestsCount)
            .add("RejectedRequestsCount", rejectedRequestsCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ServerDiagnosticsSummaryDataType> {

        @Override
        public Class<ServerDiagnosticsSummaryDataType> getType() {
            return ServerDiagnosticsSummaryDataType.class;
        }

        @Override
        public ServerDiagnosticsSummaryDataType decode(UaDecoder decoder) throws UaSerializationException {
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
        public void encode(ServerDiagnosticsSummaryDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("ServerViewCount", value.serverViewCount);
            encoder.writeUInt32("CurrentSessionCount", value.currentSessionCount);
            encoder.writeUInt32("CumulatedSessionCount", value.cumulatedSessionCount);
            encoder.writeUInt32("SecurityRejectedSessionCount", value.securityRejectedSessionCount);
            encoder.writeUInt32("RejectedSessionCount", value.rejectedSessionCount);
            encoder.writeUInt32("SessionTimeoutCount", value.sessionTimeoutCount);
            encoder.writeUInt32("SessionAbortCount", value.sessionAbortCount);
            encoder.writeUInt32("CurrentSubscriptionCount", value.currentSubscriptionCount);
            encoder.writeUInt32("CumulatedSubscriptionCount", value.cumulatedSubscriptionCount);
            encoder.writeUInt32("PublishingIntervalCount", value.publishingIntervalCount);
            encoder.writeUInt32("SecurityRejectedRequestsCount", value.securityRejectedRequestsCount);
            encoder.writeUInt32("RejectedRequestsCount", value.rejectedRequestsCount);
        }
    }

}
