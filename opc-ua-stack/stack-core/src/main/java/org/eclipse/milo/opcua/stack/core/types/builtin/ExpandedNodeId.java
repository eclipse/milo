/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.util.Optional;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Objects;
import com.google.common.primitives.UnsignedInteger;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public final class ExpandedNodeId {

    public static final ExpandedNodeId NULL_VALUE = new ExpandedNodeId(NodeId.NULL_VALUE, null, 0);

    private final NodeId nodeId;
    private final String namespaceUri;
    private final long serverIndex;

    public ExpandedNodeId(NodeId nodeId) {
        this(nodeId, null, 0);
    }

    public ExpandedNodeId(NodeId nodeId, String namespaceUri, long serverIndex) {
        this.nodeId = nodeId;
        this.namespaceUri = namespaceUri;
        this.serverIndex = serverIndex;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(UShort namespaceIndex, UInteger identifier, String namespaceUri, long serverIndex) {
        checkArgument(identifier.longValue() >= 0 && identifier.longValue() <= UnsignedInteger.MAX_VALUE.longValue());

        this.nodeId = new NodeId(namespaceIndex, identifier);
        this.namespaceUri = namespaceUri;
        this.serverIndex = serverIndex;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(int namespaceIndex, int identifier, String namespaceUri, long serverIndex) {
        this(Unsigned.ushort(namespaceIndex), Unsigned.uint(identifier), namespaceUri, serverIndex);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(UShort namespaceIndex, String identifier, String namespaceUri, long serverIndex) {
        checkNotNull(identifier);

        this.nodeId = new NodeId(namespaceIndex, identifier);
        this.namespaceUri = namespaceUri;
        this.serverIndex = serverIndex;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(int namespaceIndex, String identifier, String namespaceUri, long serverIndex) {
        this(Unsigned.ushort(namespaceIndex), identifier, namespaceUri, serverIndex);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(UShort namespaceIndex, UUID identifier, String namespaceUri, long serverIndex) {
        checkNotNull(identifier);

        this.nodeId = new NodeId(namespaceIndex, identifier);
        this.namespaceUri = namespaceUri;
        this.serverIndex = serverIndex;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(int namespaceIndex, UUID identifier, String namespaceUri, long serverIndex) {
        this(Unsigned.ushort(namespaceIndex), identifier, namespaceUri, serverIndex);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(UShort namespaceIndex, ByteString identifier, String namespaceUri, long serverIndex) {
        checkNotNull(identifier);

        this.nodeId = new NodeId(namespaceIndex, identifier);
        this.namespaceUri = namespaceUri;
        this.serverIndex = serverIndex;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public ExpandedNodeId(int namespaceIndex, ByteString identifier, String namespaceUri, long serverIndex) {
        this(Unsigned.ushort(namespaceIndex), identifier, namespaceUri, serverIndex);
    }

    public UShort getNamespaceIndex() {
        return nodeId.getNamespaceIndex();
    }

    public Object getIdentifier() {
        return nodeId.getIdentifier();
    }

    public IdType getType() {
        return nodeId.getType();
    }

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public long getServerIndex() {
        return serverIndex;
    }

    public boolean isLocal() {
        return serverIndex == 0;
    }

    public boolean isNull() {
        return nodeId.isNull();
    }

    public boolean isNotNull() {
        return !isNull();
    }

    /**
     * If this {@link ExpandedNodeId} resides on the local server ({@code serverIndex == 0}), return its representation
     * as a local {@link NodeId}.
     *
     * @return a local {@link NodeId}, if {@code serverIndex == 0}.
     */
    public Optional<NodeId> local() {
        return isLocal() ? Optional.of(nodeId) : Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpandedNodeId that = (ExpandedNodeId) o;

        return Objects.equal(serverIndex, that.serverIndex) &&
            Objects.equal(nodeId.getIdentifier(), that.nodeId.getIdentifier()) &&
            (Objects.equal(namespaceUri, that.namespaceUri) ||
                Objects.equal(nodeId.getNamespaceIndex(), that.nodeId.getNamespaceIndex()));
    }

    @Override
    public int hashCode() {
        int result = nodeId != null ? nodeId.hashCode() : 0;
        result = 31 * result + (namespaceUri != null ? namespaceUri.hashCode() : 0);
        result = 31 * result + (int) (serverIndex ^ (serverIndex >>> 32));
        return result;
    }

    @Override
    public String toString() {
        ToStringHelper helper = MoreObjects.toStringHelper(this);

        if (namespaceUri != null && namespaceUri.length() > 0) {
            helper.add("ns", namespaceUri);
        } else {
            helper.add("ns", getNamespaceIndex());
        }

        helper.add("id", getIdentifier());
        helper.add("serverIndex", getServerIndex());

        return helper.toString();
    }

    public String toParseableString() {
        StringBuilder sb = new StringBuilder();

        sb.append("svr=").append(serverIndex).append(";");

        if (namespaceUri != null) {
            sb.append("nsu=").append(namespaceUri).append(";");
        } else {
            int namespaceIndex = getNamespaceIndex().intValue();
            if (namespaceIndex > 0) {
                sb.append("ns=").append(getNamespaceIndex().intValue()).append(";");
            }
        }

        switch (getType()) {
            case Numeric:
                sb.append("i=").append(getIdentifier());
                break;
            case String:
                sb.append("s=").append(getIdentifier());
                break;
            case Guid:
                sb.append("g=").append(getIdentifier());
                break;
            case Opaque:
                ByteString bs = (ByteString) getIdentifier();
                if (bs.isNull()) sb.append("b=");
                else sb.append("b=").append(DatatypeConverter.printBase64Binary(bs.bytes()));
                break;

            default:
                throw new IllegalStateException("IdType " + nodeId.getType());
        }

        return sb.toString();
    }

    public static ExpandedNodeId parse(String s) {
        try {
            String[] parts = s.split(";");

            NodeId nodeId = NodeId.parse(parts[parts.length - 1]);

            int serverIndex = 0;
            int namespaceIndex = 0;
            String namespaceUri = null;
            Object identifier = nodeId.getIdentifier();

            for (String part : parts) {
                String[] ss = part.split("=");
                if ("svr".equals(ss[0])) {
                    serverIndex = Integer.parseInt(ss[1]);
                } else if ("ns".equals(ss[0])) {
                    namespaceIndex = Integer.parseInt(ss[1]);
                } else if ("nsu".equals(ss[0])) {
                    namespaceUri = ss[1];
                }
            }

            switch (nodeId.getType()) {
                case Guid:
                    return new ExpandedNodeId(
                        namespaceIndex, (UUID) identifier, namespaceUri, serverIndex);
                case Numeric:
                    return new ExpandedNodeId(
                        namespaceIndex, ((UInteger) identifier).intValue(), namespaceUri, serverIndex);
                case Opaque:
                    return new ExpandedNodeId(
                        namespaceIndex, (ByteString) identifier, namespaceUri, serverIndex);
                case String:
                    return new ExpandedNodeId(
                        namespaceIndex, (String) identifier, namespaceUri, serverIndex);

                default:
                    throw new IllegalStateException("IdType " + nodeId.getType());
            }
        } catch (Throwable t) {
            throw new UaRuntimeException(StatusCodes.Bad_NodeIdInvalid, t);
        }
    }

}
