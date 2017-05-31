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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ServerDiagnosticsSummaryDataType")
public class ServerDiagnosticsSummaryDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServerDiagnosticsSummaryDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml;

    protected final UInteger _serverViewCount;
    protected final UInteger _currentSessionCount;
    protected final UInteger _cumulatedSessionCount;
    protected final UInteger _securityRejectedSessionCount;
    protected final UInteger _rejectedSessionCount;
    protected final UInteger _sessionTimeoutCount;
    protected final UInteger _sessionAbortCount;
    protected final UInteger _currentSubscriptionCount;
    protected final UInteger _cumulatedSubscriptionCount;
    protected final UInteger _publishingIntervalCount;
    protected final UInteger _securityRejectedRequestsCount;
    protected final UInteger _rejectedRequestsCount;

    public ServerDiagnosticsSummaryDataType() {
        this._serverViewCount = null;
        this._currentSessionCount = null;
        this._cumulatedSessionCount = null;
        this._securityRejectedSessionCount = null;
        this._rejectedSessionCount = null;
        this._sessionTimeoutCount = null;
        this._sessionAbortCount = null;
        this._currentSubscriptionCount = null;
        this._cumulatedSubscriptionCount = null;
        this._publishingIntervalCount = null;
        this._securityRejectedRequestsCount = null;
        this._rejectedRequestsCount = null;
    }

    public ServerDiagnosticsSummaryDataType(UInteger _serverViewCount, UInteger _currentSessionCount, UInteger _cumulatedSessionCount, UInteger _securityRejectedSessionCount, UInteger _rejectedSessionCount, UInteger _sessionTimeoutCount, UInteger _sessionAbortCount, UInteger _currentSubscriptionCount, UInteger _cumulatedSubscriptionCount, UInteger _publishingIntervalCount, UInteger _securityRejectedRequestsCount, UInteger _rejectedRequestsCount) {
        this._serverViewCount = _serverViewCount;
        this._currentSessionCount = _currentSessionCount;
        this._cumulatedSessionCount = _cumulatedSessionCount;
        this._securityRejectedSessionCount = _securityRejectedSessionCount;
        this._rejectedSessionCount = _rejectedSessionCount;
        this._sessionTimeoutCount = _sessionTimeoutCount;
        this._sessionAbortCount = _sessionAbortCount;
        this._currentSubscriptionCount = _currentSubscriptionCount;
        this._cumulatedSubscriptionCount = _cumulatedSubscriptionCount;
        this._publishingIntervalCount = _publishingIntervalCount;
        this._securityRejectedRequestsCount = _securityRejectedRequestsCount;
        this._rejectedRequestsCount = _rejectedRequestsCount;
    }

    public UInteger getServerViewCount() { return _serverViewCount; }

    public UInteger getCurrentSessionCount() { return _currentSessionCount; }

    public UInteger getCumulatedSessionCount() { return _cumulatedSessionCount; }

    public UInteger getSecurityRejectedSessionCount() { return _securityRejectedSessionCount; }

    public UInteger getRejectedSessionCount() { return _rejectedSessionCount; }

    public UInteger getSessionTimeoutCount() { return _sessionTimeoutCount; }

    public UInteger getSessionAbortCount() { return _sessionAbortCount; }

    public UInteger getCurrentSubscriptionCount() { return _currentSubscriptionCount; }

    public UInteger getCumulatedSubscriptionCount() { return _cumulatedSubscriptionCount; }

    public UInteger getPublishingIntervalCount() { return _publishingIntervalCount; }

    public UInteger getSecurityRejectedRequestsCount() { return _securityRejectedRequestsCount; }

    public UInteger getRejectedRequestsCount() { return _rejectedRequestsCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ServerViewCount", _serverViewCount)
            .add("CurrentSessionCount", _currentSessionCount)
            .add("CumulatedSessionCount", _cumulatedSessionCount)
            .add("SecurityRejectedSessionCount", _securityRejectedSessionCount)
            .add("RejectedSessionCount", _rejectedSessionCount)
            .add("SessionTimeoutCount", _sessionTimeoutCount)
            .add("SessionAbortCount", _sessionAbortCount)
            .add("CurrentSubscriptionCount", _currentSubscriptionCount)
            .add("CumulatedSubscriptionCount", _cumulatedSubscriptionCount)
            .add("PublishingIntervalCount", _publishingIntervalCount)
            .add("SecurityRejectedRequestsCount", _securityRejectedRequestsCount)
            .add("RejectedRequestsCount", _rejectedRequestsCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ServerDiagnosticsSummaryDataType> {
        @Override
        public ServerDiagnosticsSummaryDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _serverViewCount = reader.readUInt32();
            UInteger _currentSessionCount = reader.readUInt32();
            UInteger _cumulatedSessionCount = reader.readUInt32();
            UInteger _securityRejectedSessionCount = reader.readUInt32();
            UInteger _rejectedSessionCount = reader.readUInt32();
            UInteger _sessionTimeoutCount = reader.readUInt32();
            UInteger _sessionAbortCount = reader.readUInt32();
            UInteger _currentSubscriptionCount = reader.readUInt32();
            UInteger _cumulatedSubscriptionCount = reader.readUInt32();
            UInteger _publishingIntervalCount = reader.readUInt32();
            UInteger _securityRejectedRequestsCount = reader.readUInt32();
            UInteger _rejectedRequestsCount = reader.readUInt32();

            return new ServerDiagnosticsSummaryDataType(_serverViewCount, _currentSessionCount, _cumulatedSessionCount, _securityRejectedSessionCount, _rejectedSessionCount, _sessionTimeoutCount, _sessionAbortCount, _currentSubscriptionCount, _cumulatedSubscriptionCount, _publishingIntervalCount, _securityRejectedRequestsCount, _rejectedRequestsCount);
        }

        @Override
        public void encode(SerializationContext context, ServerDiagnosticsSummaryDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._serverViewCount);
            writer.writeUInt32(encodable._currentSessionCount);
            writer.writeUInt32(encodable._cumulatedSessionCount);
            writer.writeUInt32(encodable._securityRejectedSessionCount);
            writer.writeUInt32(encodable._rejectedSessionCount);
            writer.writeUInt32(encodable._sessionTimeoutCount);
            writer.writeUInt32(encodable._sessionAbortCount);
            writer.writeUInt32(encodable._currentSubscriptionCount);
            writer.writeUInt32(encodable._cumulatedSubscriptionCount);
            writer.writeUInt32(encodable._publishingIntervalCount);
            writer.writeUInt32(encodable._securityRejectedRequestsCount);
            writer.writeUInt32(encodable._rejectedRequestsCount);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ServerDiagnosticsSummaryDataType> {
        @Override
        public ServerDiagnosticsSummaryDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _serverViewCount = reader.readUInt32("ServerViewCount");
            UInteger _currentSessionCount = reader.readUInt32("CurrentSessionCount");
            UInteger _cumulatedSessionCount = reader.readUInt32("CumulatedSessionCount");
            UInteger _securityRejectedSessionCount = reader.readUInt32("SecurityRejectedSessionCount");
            UInteger _rejectedSessionCount = reader.readUInt32("RejectedSessionCount");
            UInteger _sessionTimeoutCount = reader.readUInt32("SessionTimeoutCount");
            UInteger _sessionAbortCount = reader.readUInt32("SessionAbortCount");
            UInteger _currentSubscriptionCount = reader.readUInt32("CurrentSubscriptionCount");
            UInteger _cumulatedSubscriptionCount = reader.readUInt32("CumulatedSubscriptionCount");
            UInteger _publishingIntervalCount = reader.readUInt32("PublishingIntervalCount");
            UInteger _securityRejectedRequestsCount = reader.readUInt32("SecurityRejectedRequestsCount");
            UInteger _rejectedRequestsCount = reader.readUInt32("RejectedRequestsCount");

            return new ServerDiagnosticsSummaryDataType(_serverViewCount, _currentSessionCount, _cumulatedSessionCount, _securityRejectedSessionCount, _rejectedSessionCount, _sessionTimeoutCount, _sessionAbortCount, _currentSubscriptionCount, _cumulatedSubscriptionCount, _publishingIntervalCount, _securityRejectedRequestsCount, _rejectedRequestsCount);
        }

        @Override
        public void encode(SerializationContext context, ServerDiagnosticsSummaryDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("ServerViewCount", encodable._serverViewCount);
            writer.writeUInt32("CurrentSessionCount", encodable._currentSessionCount);
            writer.writeUInt32("CumulatedSessionCount", encodable._cumulatedSessionCount);
            writer.writeUInt32("SecurityRejectedSessionCount", encodable._securityRejectedSessionCount);
            writer.writeUInt32("RejectedSessionCount", encodable._rejectedSessionCount);
            writer.writeUInt32("SessionTimeoutCount", encodable._sessionTimeoutCount);
            writer.writeUInt32("SessionAbortCount", encodable._sessionAbortCount);
            writer.writeUInt32("CurrentSubscriptionCount", encodable._currentSubscriptionCount);
            writer.writeUInt32("CumulatedSubscriptionCount", encodable._cumulatedSubscriptionCount);
            writer.writeUInt32("PublishingIntervalCount", encodable._publishingIntervalCount);
            writer.writeUInt32("SecurityRejectedRequestsCount", encodable._securityRejectedRequestsCount);
            writer.writeUInt32("RejectedRequestsCount", encodable._rejectedRequestsCount);
        }
    }

}
