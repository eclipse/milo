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

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.DatatypeConverter;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public final class ExpandedNodeId {

    public static final ExpandedNodeId NULL_VALUE = new ExpandedNodeId(
        UShort.MIN,
        null,
        UInteger.MIN,
        UInteger.MIN
    );

    private final UShort namespaceIndex;
    private final Object identifier;
    private final String namespaceUri;
    private final UInteger serverIndex;

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        UInteger identifier
    ) {

        this(namespaceIndex, namespaceUri, identifier, UInteger.MIN);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        UInteger identifier,
        UInteger serverIndex
    ) {

        this(namespaceIndex, namespaceUri, (Object) identifier, serverIndex);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        String identifier
    ) {

        this(namespaceIndex, namespaceUri, identifier, UInteger.MIN);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        String identifier,
        UInteger serverIndex
    ) {

        this(namespaceIndex, namespaceUri, (Object) identifier, serverIndex);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        ByteString identifier
    ) {

        this(namespaceIndex, namespaceUri, identifier, UInteger.MIN);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        ByteString identifier,
        UInteger serverIndex
    ) {

        this(namespaceIndex, namespaceUri, (Object) identifier, serverIndex);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        UUID identifier
    ) {

        this(namespaceIndex, namespaceUri, identifier, UInteger.MIN);
    }

    public ExpandedNodeId(
        UShort namespaceIndex,
        String namespaceUri,
        UUID identifier,
        UInteger serverIndex
    ) {

        this(namespaceIndex, namespaceUri, (Object) identifier, serverIndex);
    }

    public ExpandedNodeId(
        @Nonnull UShort namespaceIndex,
        @Nullable String namespaceUri,
        @Nullable Object identifier,
        @Nonnull UInteger serverIndex
    ) {

        Preconditions.checkNotNull(namespaceIndex);
        Preconditions.checkNotNull(serverIndex);

        this.namespaceIndex = namespaceIndex;
        this.namespaceUri = namespaceUri;
        this.identifier = identifier;
        this.serverIndex = serverIndex;
    }

    /**
     * Get the identifier object of this {@link ExpandedNodeId}.
     *
     * @return the identifier object of this {@link ExpandedNodeId}.
     */
    public Object getIdentifier() {
        return identifier;
    }

    /**
     * Get the {@link IdType} of this {@link ExpandedNodeId}.
     *
     * @return the {@link IdType} of this {@link ExpandedNodeId}.
     */
    public IdType getType() {
        if (identifier instanceof UInteger) {
            return IdType.Numeric;
        } else if (identifier instanceof String) {
            return IdType.String;
        } else if (identifier instanceof UUID) {
            return IdType.Guid;
        } else {
            return IdType.Opaque;
        }
    }

    /**
     * Get the namespace index of this {@link ExpandedNodeId}.
     * <p>
     * This value shall be ignored if {@link #getNamespaceUri()} is non-null.
     *
     * @return the namespace index of this {@link ExpandedNodeId}.
     */
    public UShort getNamespaceIndex() {
        return namespaceIndex;
    }

    /**
     * Get the namespace URI of this {@link ExpandedNodeId}.
     *
     * @return the namespace URI of this {@link ExpandedNodeId}.
     */
    @Nullable
    public String getNamespaceUri() {
        return namespaceUri;
    }

    /**
     * Get the server index of this {@link ExpandedNodeId}.
     *
     * @return the server index of this {@link ExpandedNodeId}.
     */
    public UInteger getServerIndex() {
        return serverIndex;
    }

    /**
     * @return {@code true} if this {@link ExpandedNodeId} is absolute, i.e. it specifies a namespace URI instead of a
     * namespace index.
     */
    public boolean isAbsolute() {
        return namespaceUri != null;
    }

    /**
     * @return {@code true} if this {@link ExpandedNodeId} is relative, i.e. it specifies a namespace index instead of a
     * namespace URI, and is therefore defined relative to some {@link NamespaceTable}.
     */
    public boolean isRelative() {
        return !isAbsolute();
    }

    /**
     * @return {@code true} if this {@link ExpandedNodeId} is local (serverIndex == 0).
     */
    public boolean isLocal() {
        return serverIndex.longValue() == 0;
    }

    /**
     * @return {@code true} if this {@link ExpandedNodeId} is null.
     */
    public boolean isNull() {
        if (namespaceIndex.intValue() > 0) {
            return false;
        }

        switch (getType()) {
            case Numeric: {
                UInteger id = (UInteger) this.identifier;
                return id.intValue() == 0;
            }
            case String: {
                String id = (String) this.identifier;
                return Strings.isNullOrEmpty(id);
            }
            case Guid: {
                UUID id = (UUID) this.identifier;
                return id.getLeastSignificantBits() == 0 && id.getMostSignificantBits() == 0;
            }
            case Opaque: {
                ByteString id = (ByteString) this.identifier;
                return id == null || id.isNullOrEmpty();
            }

            default:
                return true;
        }
    }

    public boolean isNotNull() {
        return !isNull();
    }

    /**
     * If this {@link ExpandedNodeId} resides on the local server ({@code serverIndex == 0}), return its representation
     * as a local {@link NodeId}.
     *
     * @return a local {@link NodeId}, if {@code serverIndex == 0}.
     * @deprecated use {@link #local(NamespaceTable)}, which correctly handles a namespace URI being specified.
     */
    @Deprecated
    public Optional<NodeId> local() {
        return isLocal() ? Optional.of(new NodeId(namespaceIndex, identifier)) : Optional.empty();
    }

    /**
     * If this {@link ExpandedNodeId} resides on the local server ({@code serverIndex == 0}), return its representation
     * as a local {@link NodeId}.
     * <p>
     * If this ExpandedNodeId specifies a namespace URI instead of a namespace index then the URI must exist in
     * {@code namespaceTable} or {@link Optional#empty()} is returned.
     *
     * @param namespaceTable the {@link NamespaceTable}.
     * @return a local {@link NodeId}, if {@code serverIndex == 0} and the namespace index can be determined.
     */
    public Optional<NodeId> local(NamespaceTable namespaceTable) {
        if (isLocal()) {
            if (namespaceUri == null || namespaceUri.isEmpty()) {
                NodeId nodeId = new NodeId(namespaceIndex, identifier);

                return Optional.of(nodeId);
            } else {
                UShort namespaceIndex = namespaceTable.getIndex(namespaceUri);

                if (namespaceIndex == null) {
                    return Optional.empty();
                } else {
                    NodeId nodeId = new NodeId(namespaceIndex, identifier);

                    return Optional.of(nodeId);
                }
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Like {@link #local(NamespaceTable)}, but throws if the node is not local or the namespace is not registered.
     *
     * @param namespaceTable the {@link NamespaceTable}.
     * @return a local {@link NodeId}.
     * @throws Exception if the node is not local or the namespace is not registered.
     */
    public NodeId localOrThrow(NamespaceTable namespaceTable) throws Exception {
        if (isLocal()) {
            return local(namespaceTable)
                .orElseThrow(() -> new Exception("namespace not registered: " + namespaceUri));
        } else {
            throw new Exception("not a local node (serverIndex = " + serverIndex + ")");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpandedNodeId that = (ExpandedNodeId) o;
        return Objects.equals(namespaceIndex, that.namespaceIndex) &&
            Objects.equals(identifier, that.identifier) &&
            Objects.equals(namespaceUri, that.namespaceUri) &&
            serverIndex.equals(that.serverIndex);
    }

    /**
     * Check if this {@link ExpandedNodeId} is equal to {@code nodeId}.
     * <p>
     * To be considered equal this ExpandedNodeId must be in serverIndex == 0, have the same namespace index as
     * {@code nodeId} or have a namespace URI at the same index in the default namespace table, and an equal
     * identifier.
     *
     * @param nodeId the {@link NodeId} to check equality against.
     * @return {@code true} if this {@link ExpandedNodeId} is equal to {@code nodeId}.
     */
    public boolean equals(NodeId nodeId) {
        return nodeId.equals(this);
    }

    /**
     * Check if this {@link ExpandedNodeId} is equal to {@code nodeId}.
     * <p>
     * To be considered equal this ExpandedNodeId must be in serverIndex == 0, have the same namespace index as
     * {@code nodeId} or have a namespace URI at the same index in the default namespace table, and an equal
     * identifier.
     *
     * @param nodeId         the {@link NodeId} to check equality against.
     * @param namespaceTable the {@link NamespaceTable} used to look up the index of a namespace URI.
     * @return {@code true} if this {@link ExpandedNodeId} is equal to {@code nodeId}.
     */
    public boolean equals(NodeId nodeId, NamespaceTable namespaceTable) {
        return nodeId.equals(this, namespaceTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespaceIndex, identifier, namespaceUri, serverIndex);
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
                throw new IllegalStateException("IdType " + getType());
        }

        return sb.toString();
    }

    public static ExpandedNodeId parse(String s) {
        try {
            String[] parts = s.split(";");

            NodeId nodeId = NodeId.parse(parts[parts.length - 1]);

            UInteger serverIndex = UInteger.MIN;
            UShort namespaceIndex = UShort.MIN;
            String namespaceUri = null;
            Object identifier = nodeId.getIdentifier();

            for (String part : parts) {
                String[] ss = part.split("=");
                if ("svr".equals(ss[0])) {
                    serverIndex = uint(Integer.parseInt(ss[1]));
                } else if ("ns".equals(ss[0])) {
                    namespaceIndex = ushort(Integer.parseInt(ss[1]));
                } else if ("nsu".equals(ss[0])) {
                    namespaceUri = ss[1];
                }
            }

            return new ExpandedNodeId(namespaceIndex, namespaceUri, identifier, serverIndex);
        } catch (Throwable t) {
            throw new UaRuntimeException(StatusCodes.Bad_NodeIdInvalid, t);
        }
    }

    /**
     * Return a new {@link ExpandedNodeId.Builder}.
     *
     * @return a new {@link ExpandedNodeId.Builder}.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private UShort namespaceIndex = UShort.MIN;
        private String namespaceUri = null;
        private Object identifier = null;
        private UInteger serverIndex = UInteger.MIN;

        public Builder setNamespaceIndex(int namespaceIndex) {
            return setNamespaceIndex(ushort(namespaceIndex));
        }

        public Builder setNamespaceIndex(UShort namespaceIndex) {
            this.namespaceIndex = namespaceIndex;
            return this;
        }

        public Builder setNamespaceUri(String namespaceUri) {
            this.namespaceUri = namespaceUri;
            return this;
        }

        public Builder setIdentifier(UInteger identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setIdentifier(UUID identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setIdentifier(ByteString identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setServerIndex(long serverIndex) {
            return setServerIndex(uint(serverIndex));
        }

        public Builder setServerIndex(UInteger serverIndex) {
            this.serverIndex = serverIndex;
            return this;
        }

        public ExpandedNodeId build() {
            return new ExpandedNodeId(namespaceIndex, namespaceUri, identifier, serverIndex);
        }

    }

}
