/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.util.UnknownNamespace;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class NamespaceManager {

    private static final Namespace UNKNOWN_NAMESPACE = new UnknownNamespace();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NamespaceTable namespaceTable = new NamespaceTable();

    private final Map<UShort, Namespace> namespaces = Maps.newConcurrentMap();

    public NamespaceManager() {
        namespaceTable.putUri(NamespaceTable.OPC_UA_NAMESPACE, ushort(0));
    }

    /**
     * Register a namespace URI.
     *
     * @param namespaceUri the namespace URI to register.
     * @return The index assigned to the given namespace URI.
     * @see #registerAndAdd(String, Function)
     */
    public UShort registerUri(String namespaceUri) {
        UShort index = namespaceTable.addUri(namespaceUri);

        logger.info("registered namespace index={}, uri={}", index, namespaceUri);

        return index;
    }

    /**
     * Add a {@link Namespace}.
     * <p>
     * The URI must already be registered.
     *
     * @param namespace The {@link Namespace} to add.
     * @see #registerUri(String)
     * @see #registerAndAdd(String, Function)
     */
    public void addNamespace(Namespace namespace) {
        Preconditions.checkNotNull(
            namespaceTable.getIndex(namespace.getNamespaceUri()),
            "namespace must be registered prior to adding");

        namespaces.put(namespace.getNamespaceIndex(), namespace);

        logger.info("added namespace index={}, uri={}",
            namespace.getNamespaceIndex(), namespace.getNamespaceUri());
    }

    /**
     * Register a namespace URI and add the corresponding {@link Namespace}.
     * <p>
     * This is a convenience method equivalent to calling {@link #registerUri(String)} followed by
     * {@link #addNamespace(Namespace)}.
     *
     * @param namespaceUri      The namespace URI to register.
     * @param namespaceFunction A function that returns a {@link Namespace} for the supplied namespace index.
     * @return The {@link Namespace} returned by {@code namespaceFunction}.
     */
    public <T extends Namespace> T registerAndAdd(String namespaceUri, Function<UShort, T> namespaceFunction) {
        UShort namespaceIndex = namespaceTable.addUri(namespaceUri);
        T namespace = namespaceFunction.apply(namespaceIndex);
        namespaces.put(namespaceIndex, namespace);

        logger.info("registered and added namespace index={}, uri={}",
            namespace.getNamespaceIndex(), namespace.getNamespaceUri());

        return namespace;
    }

    public Namespace getNamespace(int index) {
        return getNamespace(ushort(index));
    }

    public Namespace getNamespace(UShort index) {
        Namespace namespace = namespaces.get(index);

        return namespace != null ? namespace : UNKNOWN_NAMESPACE;
    }

    public NamespaceTable getNamespaceTable() {
        return namespaceTable;
    }

    public Optional<NodeId> toNodeId(ExpandedNodeId expandedNodeId) {
        if (!expandedNodeId.isLocal()) return Optional.empty();

        String namespaceUri = expandedNodeId.getNamespaceUri();

        Object identifier = expandedNodeId.getIdentifier();
        IdType type = expandedNodeId.getType();

        if (namespaceUri == null || namespaceUri.isEmpty()) {
            return Optional.of(createNodeId(expandedNodeId.getNamespaceIndex(), identifier, type));
        } else {
            UShort index = namespaceTable.getIndex(namespaceUri);

            if (index == null) return Optional.empty();
            else return Optional.of(createNodeId(index, identifier, type));
        }
    }

    private NodeId createNodeId(UShort namespaceIndex, Object identifier, IdType type) {
        switch (type) {
            case Numeric:
                return new NodeId(namespaceIndex, (UInteger) identifier);
            case String:
                return new NodeId(namespaceIndex, (String) identifier);
            case Guid:
                return new NodeId(namespaceIndex, (UUID) identifier);
            case Opaque:
                return new NodeId(namespaceIndex, (ByteString) identifier);
            default:
                throw new UaRuntimeException(StatusCodes.Bad_InternalError, "unhandled type: " + type);
        }
    }

}
