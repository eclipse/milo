/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultXmlEncoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UNumber;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Tree;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class DataTypeTree {

    public static final String SESSION_ATTRIBUTE_KEY = "dataTypeTree";

    private final Map<NodeId, Tree<DataType>> dataTypes = Maps.newConcurrentMap();

    private final Tree<DataType> tree;

    public DataTypeTree(Tree<DataType> tree) {
        this.tree = tree;

        tree.traverseNodes(
            treeNode ->
                dataTypes.put(treeNode.getValue().getNodeId(), treeNode)
        );
    }

    /**
     * Get the backing Class a value of DataType {@code dataTypeId} would have.
     * <p>
     * Builtin DataTypes are backed by their intrinsic backing class.
     * <p>
     * Abstract types {@link Identifiers#Number}, {@link Identifiers#Integer}, and {@link Identifiers#UInteger}
     * are backed by {@link Number}, {@link Integer}, and {@link UInteger} respectively.
     * <p>
     * Enumerations are backed by {@link Integer}.
     * <p>
     * Structures are backed by {@link ExtensionObject}.
     *
     * @param dataTypeId the {@link NodeId} of a DataType Node.
     * @return the backing Class a value of DataType {@code dataTypeId} would have.
     * @see BuiltinDataType
     * @see BuiltinDataType#getBackingClass()
     */
    public Class<?> getBackingClass(NodeId dataTypeId) {
        if (BuiltinDataType.isBuiltin(dataTypeId)) {
            return BuiltinDataType.getBackingClass(dataTypeId);
        } else {
            if (Identifiers.Enumeration.equals(dataTypeId)) {
                return Integer.class;
            } else if (Identifiers.Number.equals(dataTypeId)) {
                return Number.class;
            } else if (Identifiers.Integer.equals(dataTypeId)) {
                return Number.class;
            } else if (Identifiers.UInteger.equals(dataTypeId)) {
                return UNumber.class;
            } else {
                Tree<DataType> node = dataTypes.get(dataTypeId);
                Tree<DataType> parent = node != null ? node.getParent() : null;

                if (parent != null) {
                    return getBackingClass(parent.getValue().getNodeId());
                } else {
                    return Object.class;
                }
            }
        }
    }

    /**
     * Get the {@link BuiltinDataType} {@code dataTypeId} inherits from, following references to the parent
     * as necessary until a {@link BuiltinDataType} is found.
     *
     * @param dataTypeId the {@link NodeId} of a DataType Node.
     * @return the {@link BuiltinDataType} this DataType inherits from.
     */
    public BuiltinDataType getBuiltinType(NodeId dataTypeId) {
        if (BuiltinDataType.isBuiltin(dataTypeId)) {
            return BuiltinDataType.fromNodeId(dataTypeId);
        } else {
            Tree<DataType> node = dataTypes.get(dataTypeId);
            Tree<DataType> parent = node != null ? node.getParent() : null;

            if (parent != null) {
                return getBuiltinType(parent.getValue().getNodeId());
            } else {
                return BuiltinDataType.Variant;
            }
        }
    }

    /**
     * Get the {@link DataType} info for the DataType identified by {@code dataTypeId}, if it exists.
     *
     * @param dataTypeId the {@link NodeId} of a DataType Node.
     * @return the {@link DataType} info for the DataType identified by {@code dataTypeId}, if it exists.
     */
    @Nullable
    public DataType getDataType(NodeId dataTypeId) {
        Tree<DataType> node = dataTypes.get(dataTypeId);
        return node != null ? node.getValue() : null;
    }

    /**
     * Check if a value of type {@code clazz} is assignable to a value of DataType {@code dataTypeId}, i.e. it is
     * equal to or a subtype of the backing class for {@code dataTypeId}.
     *
     * @param dataTypeId the {@link NodeId} of a DataType Node.
     * @param clazz      the backing Class to check.
     * @return {@code true} if {@code clazz} is equal to or a subtype of the backing class for {@code dataTypeId}.
     */
    public boolean isAssignable(NodeId dataTypeId, Class<?> clazz) {
        Class<?> backingClass = getBackingClass(dataTypeId);

        if (Identifiers.Integer.equals(dataTypeId)) {
            // Can't just check that it's assignable from Number.class because
            // UNumber extends Number rather than the two sharing a common
            // superclass.
            return clazz == byte.class || clazz == Byte.class ||
                clazz == short.class || clazz == Short.class ||
                clazz == int.class || clazz == Integer.class ||
                clazz == long.class || clazz == Long.class;
        } else if (Identifiers.UInteger.equals(dataTypeId)) {
            return UNumber.class.isAssignableFrom(clazz);
        } else {
            return backingClass.isAssignableFrom(clazz);
        }
    }

    public Tree<DataType> getTree() {
        return tree;
    }

    public static CompletableFuture<DataTypeTree> create(UaStackClient client, OpcUaSession session) {
        Tree<DataType> root = new Tree<>(
            null,
            new DataType(
                QualifiedName.parse("0:BaseDataType"), Identifiers.BaseDataType,
                null,
                null
            )
        );

        return readNamespaceTable(client, session)
            .thenCompose(namespaceTable -> addChildren(root, client, session, namespaceTable))
            .thenApply(u -> new DataTypeTree(root));
    }

    private static CompletableFuture<NamespaceTable> readNamespaceTable(UaStackClient client, OpcUaSession session) {
        RequestHeader requestHeader = client.newRequestHeader(
            session.getAuthenticationToken(),
            client.getConfig().getRequestTimeout()
        );

        CompletableFuture<UaResponseMessage> readFuture = client.sendRequest(
            new ReadRequest(
                requestHeader,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        Identifiers.Server_NamespaceArray,
                        AttributeId.Value.uid(),
                        null,
                        QualifiedName.NULL_VALUE)}
            )
        );

        return readFuture.thenApply(ReadResponse.class::cast).thenApply(response -> {
            DataValue dataValue = response.getResults()[0];
            String[] namespaceUris = (String[]) dataValue.getValue().getValue();
            NamespaceTable namespaceTable = new NamespaceTable();
            for (String namespaceUri : namespaceUris) {
                namespaceTable.addUri(namespaceUri);
            }
            return namespaceTable;
        });
    }

    private static CompletableFuture<Unit> addChildren(
        Tree<DataType> tree,
        UaStackClient client,
        OpcUaSession session,
        NamespaceTable namespaceTable
    ) {

        CompletableFuture<List<ReferenceDescription>> subtypes = browseSafe(
            client,
            session,
            new BrowseDescription(
                tree.getValue().nodeId,
                BrowseDirection.Forward,
                Identifiers.HasSubtype,
                false,
                uint(NodeClass.DataType.getValue()),
                uint(BrowseResultMask.All.getValue())
            )
        );

        CompletableFuture<List<DataType>> dataTypesFuture = subtypes.thenCompose(references -> {
            Stream<CompletableFuture<DataType>> dataTypeFutures = references.stream().map(dataTypeReference -> {
                NodeId dataTypeId = dataTypeReference.getNodeId()
                    .local(namespaceTable)
                    .orElse(NodeId.NULL_VALUE);

                CompletableFuture<List<ReferenceDescription>> encodings = browseSafe(
                    client,
                    session,
                    new BrowseDescription(
                        dataTypeId,
                        BrowseDirection.Forward,
                        Identifiers.HasEncoding,
                        false,
                        uint(NodeClass.Object.getValue()),
                        uint(BrowseResultMask.All.getValue())
                    )
                );

                return encodings.thenApply(encodingReferences -> {
                    NodeId binaryEncodingId = null;
                    NodeId xmlEncodingId = null;

                    for (ReferenceDescription r : encodingReferences) {
                        if (r.getBrowseName().equals(OpcUaDefaultBinaryEncoding.ENCODING_NAME)) {
                            binaryEncodingId = r.getNodeId().local(namespaceTable).orElse(null);
                        } else if (r.getBrowseName().equals(OpcUaDefaultXmlEncoding.ENCODING_NAME)) {
                            xmlEncodingId = r.getNodeId().local(namespaceTable).orElse(null);
                        }
                    }

                    return new DataType(
                        dataTypeReference.getBrowseName(),
                        dataTypeId,
                        binaryEncodingId,
                        xmlEncodingId
                    );
                });
            });

            return FutureUtils.sequence(dataTypeFutures);
        });

        return dataTypesFuture
            .thenCompose(dataTypes -> {
                Stream<CompletableFuture<Unit>> futures = dataTypes.stream()
                    .map(tree::addChild)
                    .map(childNode -> addChildren(childNode, client, session, namespaceTable));

                return FutureUtils.sequence(futures);
            })
            .thenApply(v -> Unit.VALUE);
    }

    private static CompletableFuture<List<ReferenceDescription>> browseSafe(
        UaStackClient client,
        OpcUaSession session,
        BrowseDescription browseDescription
    ) {

        BrowseRequest browseRequest = new BrowseRequest(
            client.newRequestHeader(
                session.getAuthenticationToken(),
                client.getConfig().getRequestTimeout()
            ),
            new ViewDescription(
                NodeId.NULL_VALUE,
                DateTime.MIN_VALUE,
                uint(0)
            ),
            uint(0),
            new BrowseDescription[]{browseDescription}
        );

        return client.sendRequest(browseRequest).thenApply(BrowseResponse.class::cast).thenCompose(response -> {
            BrowseResult result = response.getResults()[0];

            List<ReferenceDescription> references =
                Collections.synchronizedList(new ArrayList<>());

            if (result.getStatusCode().isGood()) {
                Collections.addAll(references, result.getReferences());

                ByteString continuationPoint = result.getContinuationPoint();

                if (continuationPoint == null || continuationPoint.isNull()) {
                    return CompletableFuture.completedFuture(references);
                } else {
                    return browseNextSafe(client, session, continuationPoint, references);
                }
            } else {
                return CompletableFuture.completedFuture(references);
            }
        });
    }

    private static CompletableFuture<List<ReferenceDescription>> browseNextSafe(
        UaStackClient client,
        OpcUaSession session,
        ByteString continuationPoint,
        List<ReferenceDescription> references
    ) {

        BrowseNextRequest browseNextRequest = new BrowseNextRequest(
            client.newRequestHeader(
                session.getAuthenticationToken(),
                client.getConfig().getRequestTimeout()
            ),
            false,
            new ByteString[]{continuationPoint}
        );

        return client.sendRequest(browseNextRequest).thenApply(BrowseNextResponse.class::cast).thenCompose(response -> {
            BrowseResult result = response.getResults()[0];

            if (result.getStatusCode().isGood()) {
                Collections.addAll(references, result.getReferences());

                ByteString nextContinuationPoint = result.getContinuationPoint();

                if (nextContinuationPoint == null || nextContinuationPoint.isNull()) {
                    return CompletableFuture.completedFuture(references);
                } else {
                    return browseNextSafe(client, session, nextContinuationPoint, references);
                }
            } else {
                return CompletableFuture.completedFuture(references);
            }
        });
    }

    /**
     * Data object that holds details of a DataType:
     * <ul>
     *     <li>Browse Name of the DataType Node</li>
     *     <li>NodeId of the DataType Node</li>
     *     <li>NodeId of the Binary Encoding Node</li>
     *     <li>NodeId of the XML Encoding Node</li>
     * </ul>
     */
    public static class DataType {

        private final QualifiedName browseName;
        private final NodeId nodeId;
        private final NodeId binaryEncodingId;
        private final NodeId xmlEncodingId;

        private DataType(QualifiedName browseName, NodeId nodeId, NodeId binaryEncodingId, NodeId xmlEncodingId) {
            this.browseName = browseName;
            this.nodeId = nodeId;
            this.binaryEncodingId = binaryEncodingId;
            this.xmlEncodingId = xmlEncodingId;
        }

        /**
         * Get the Browse Name of this DataType.
         *
         * @return the Browse Name of this DataType.
         */
        public QualifiedName getBrowseName() {
            return browseName;
        }

        /**
         * Get the {@link NodeId} of this DataType.
         *
         * @return the {@link NodeId} of this DataType.
         */
        public NodeId getNodeId() {
            return nodeId;
        }

        /**
         * Get the {@link NodeId} of the Binary Encoding Node for this DataType, if it exists.
         * <p>
         * Only Structured DataTypes have encoding ids.
         *
         * @return the NodeId of the Binary Encoding Node for this DataType, if it exists.
         */
        @Nullable
        public NodeId getBinaryEncodingId() {
            return binaryEncodingId;
        }

        /**
         * Get the {@link NodeId} of the XML Encoding Node for this DataType, if it exists.
         * <p>
         * Only Structured DataTypes have encoding ids.
         *
         * @return the NodeId of the XML Encoding Node for this DataType, if it exists.
         */
        @Nullable
        public NodeId getXmlEncodingId() {
            return xmlEncodingId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataType dataType = (DataType) o;
            return browseName.equals(dataType.browseName) &&
                nodeId.equals(dataType.nodeId) &&
                Objects.equals(binaryEncodingId, dataType.binaryEncodingId) &&
                Objects.equals(xmlEncodingId, dataType.xmlEncodingId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(browseName, nodeId, binaryEncodingId, xmlEncodingId);
        }

        @Override
        public String toString() {
            return "DataType{" +
                "browseName=" + browseName +
                ", nodeId=" + nodeId +
                ", binaryEncodingId=" + binaryEncodingId +
                ", xmlEncodingId=" + xmlEncodingId +
                '}';
        }

    }

    public static class DataTypeTreeSessionInitializer implements SessionFsm.SessionInitializer {

        @Override
        public CompletableFuture<Unit> initialize(UaStackClient stackClient, OpcUaSession session) {
            return DataTypeTree.create(stackClient, session)
                .thenAccept(tree -> session.setAttribute(SESSION_ATTRIBUTE_KEY, tree))
                .thenApply(v -> Unit.VALUE);
        }

    }

}
