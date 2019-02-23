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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.xml.bind.DatatypeConverter;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public final class NodeId {

    public static final NodeId NULL_NUMERIC = new NodeId(ushort(0), uint(0));
    public static final NodeId NULL_STRING = new NodeId(ushort(0), "");
    public static final NodeId NULL_GUID = new NodeId(ushort(0), new UUID(0, 0));
    public static final NodeId NULL_OPAQUE = new NodeId(ushort(0), ByteString.NULL_VALUE);

    public static final NodeId NULL_VALUE = NULL_NUMERIC;

    private final UShort namespaceIndex;
    private final Object identifier;

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(int namespaceIndex, int identifier) {
        this(ushort(namespaceIndex), uint(identifier));
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(int namespaceIndex, UInteger identifier) {
        this(ushort(namespaceIndex), identifier);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(int namespaceIndex, String identifier) {
        this(ushort(namespaceIndex), identifier);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(int namespaceIndex, UUID identifier) {
        this(ushort(namespaceIndex), identifier);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(int namespaceIndex, ByteString identifier) {
        this(ushort(namespaceIndex), identifier);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(UShort namespaceIndex, UInteger identifier) {
        checkNotNull(namespaceIndex);
        checkNotNull(identifier);

        this.namespaceIndex = namespaceIndex;
        this.identifier = identifier;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(UShort namespaceIndex, int identifier) {
        checkNotNull(namespaceIndex);

        this.namespaceIndex = namespaceIndex;
        this.identifier = uint(identifier);
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(UShort namespaceIndex, String identifier) {
        checkNotNull(namespaceIndex);

        if (identifier == null) identifier = "";

        this.namespaceIndex = namespaceIndex;
        this.identifier = identifier;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(UShort namespaceIndex, UUID identifier) {
        checkNotNull(namespaceIndex);
        checkNotNull(identifier);

        this.namespaceIndex = namespaceIndex;
        this.identifier = identifier;
    }

    /**
     * @param namespaceIndex the index for a namespace URI. An index of 0 is used for OPC UA defined NodeIds.
     * @param identifier     the identifier for a node in the address space of an OPC UA Server.
     */
    public NodeId(UShort namespaceIndex, ByteString identifier) {
        checkNotNull(namespaceIndex);
        checkNotNull(identifier);

        this.namespaceIndex = namespaceIndex;
        this.identifier = identifier;
    }

    private NodeId(@Nonnull UShort namespaceIndex, @Nonnull Object identifier) {
        checkNotNull(namespaceIndex);
        checkNotNull(identifier);

        this.namespaceIndex = namespaceIndex;
        this.identifier = identifier;
    }

    public UShort getNamespaceIndex() {
        return namespaceIndex;
    }

    public Object getIdentifier() {
        return identifier;
    }

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

    public ExpandedNodeId expanded() {
        return new ExpandedNodeId(this);
    }

    public boolean isNull() {
        return namespaceIndex.intValue() == 0 &&
            (NULL_NUMERIC.equals(this) ||
                NULL_STRING.equals(this) ||
                NULL_GUID.equals(this) ||
                NULL_OPAQUE.equals(this));
    }

    public boolean isNotNull() {
        return !isNull();
    }

    /**
     * Re-index this {@link NodeId} from its current namespace index to the index for {@code namespaceUri}.
     * <p>
     * If the target namespace URI is not present in the namespace table this {@link NodeId} is returned.
     *
     * @param namespaceTable the {@link NamespaceTable}.
     * @param namespaceUri   the target namespace URI.
     * @return a new {@link NodeId} in the namespace index indicated by {@code namespaceUri}.
     */
    public NodeId reindex(NamespaceTable namespaceTable, String namespaceUri) {
        UShort namespaceIndex = namespaceTable.getIndex(namespaceUri);

        if (namespaceIndex != null && !namespaceIndex.equals(getNamespaceIndex())) {
            return withNamespaceIndex(namespaceIndex);
        } else {
            return this;
        }
    }

    /**
     * Return a new {@link NodeId} with {@code id}.
     *
     * @param id the new identifier.
     * @return a new {@link NodeId} with {@code id}.
     */
    public NodeId withId(UInteger id) {
        return new NodeId(namespaceIndex, id);
    }

    /**
     * Return a new {@link NodeId} with {@code id}.
     *
     * @param id the new identifier.
     * @return a new {@link NodeId} with {@code id}.
     */
    public NodeId withId(String id) {
        return new NodeId(namespaceIndex, id);
    }

    /**
     * Return a new {@link NodeId} with {@code id}.
     *
     * @param id the new identifier.
     * @return a new {@link NodeId} with {@code id}.
     */
    public NodeId withId(UUID id) {
        return new NodeId(namespaceIndex, id);
    }

    /**
     * Return a new {@link NodeId} with {@code id}.
     *
     * @param id the new identifier.
     * @return a new {@link NodeId} with {@code id}.
     */
    public NodeId withId(ByteString id) {
        return new NodeId(namespaceIndex, id);
    }

    /**
     * Return a new {@link NodeId} with {@code namespaceIndex}.
     *
     * @param namespaceIndex the new namespace index.
     * @return a new {@link NodeId} with {@code namespaceIndex}.
     */
    public NodeId withNamespaceIndex(UShort namespaceIndex) {
        return new NodeId(namespaceIndex, this.identifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeId nodeId = (NodeId) o;

        return identifier.equals(nodeId.identifier) &&
            namespaceIndex.equals(nodeId.namespaceIndex);
    }

    @Override
    public int hashCode() {
        int result = namespaceIndex.hashCode();
        result = 31 * result + identifier.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ns", namespaceIndex)
            .add("id", identifier)
            .toString();
    }

    public String toParseableString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ns=").append(namespaceIndex).append(";");

        switch (getType()) {
            case Numeric:
                sb.append("i=").append(identifier);
                break;
            case String:
                sb.append("s=").append(identifier);
                break;
            case Guid:
                sb.append("g=").append(identifier);
                break;
            case Opaque:
                ByteString bs = (ByteString) identifier;
                if (bs.isNull()) sb.append("b=");
                else sb.append("b=").append(DatatypeConverter.printBase64Binary(bs.bytes()));
                break;

            default:
                throw new IllegalStateException("unknown IdType: " + getType());
        }

        return sb.toString();
    }

    private static final Pattern NS_INT = Pattern.compile("ns=(\\d*);i=(\\d*)");

    private static final Pattern NONE_INT = Pattern.compile("i=(\\d*)");

    private static final Pattern NS_STRING = Pattern.compile("ns=(\\d*);s=(.*)");

    private static final Pattern NONE_STRING = Pattern.compile("s=(.*)");

    private static final Pattern NS_GUID = Pattern.compile(
        "ns=(\\d*);g=([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})");

    private static final Pattern NONE_GUID = Pattern.compile(
        "g=([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})");

    private static final Pattern NS_OPAQUE = Pattern.compile("ns=(\\d*);b=([0-9a-zA-Z+/=]*)");

    private static final Pattern NONE_OPAQUE = Pattern.compile("b=([0-9a-zA-Z+/=]*)");

    public static NodeId parse(@Nonnull String s) {
        Matcher m;

        if (s.startsWith("ns=")) {
            m = NS_INT.matcher(s);
            if (m.matches()) {
                Integer nsi = Integer.valueOf(m.group(1));
                Long obj = Long.valueOf(m.group(2));
                return new NodeId(ushort(nsi), uint(obj));
            }

            m = NS_STRING.matcher(s);
            if (m.matches()) {
                Integer nsi = Integer.valueOf(m.group(1));
                String obj = m.group(2);
                return new NodeId(ushort(nsi), obj);
            }

            m = NS_GUID.matcher(s);
            if (m.matches()) {
                Integer nsi = Integer.valueOf(m.group(1));
                UUID obj = UUID.fromString(m.group(2));
                return new NodeId(ushort(nsi), obj);
            }

            m = NS_OPAQUE.matcher(s);
            if (m.matches()) {
                Integer nsi = Integer.valueOf(m.group(1));
                byte[] obj = DatatypeConverter.parseBase64Binary(m.group(2));
                return new NodeId(ushort(nsi), ByteString.of(obj));
            }
        } else {
            m = NONE_STRING.matcher(s);
            if (m.matches()) {
                String obj = m.group(1);
                return new NodeId(ushort(0), obj);
            }

            m = NONE_INT.matcher(s);
            if (m.matches()) {
                Long obj = Long.valueOf(m.group(1));
                return new NodeId(ushort(0), uint(obj));
            }

            m = NONE_GUID.matcher(s);
            if (m.matches()) {
                UUID obj = UUID.fromString(m.group(1));
                return new NodeId(ushort(0), obj);
            }

            m = NONE_OPAQUE.matcher(s);
            if (m.matches()) {
                byte[] obj = DatatypeConverter.parseBase64Binary(m.group(1));
                return new NodeId(ushort(0), ByteString.of(obj));
            }
        }

        throw new UaRuntimeException(StatusCodes.Bad_NodeIdInvalid);
    }

    public static Optional<NodeId> parseSafe(String s) {
        try {
            return Optional.of(parse(s));
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

}
