/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespaceMetadataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

public class NamespaceMetadataNode extends BaseObjectNode implements NamespaceMetadataType {
    public NamespaceMetadataNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getNamespaceUriNode() {
        return getPropertyNode(NamespaceMetadataType.NAMESPACE_URI);
    }

    public CompletableFuture<String> getNamespaceUri() {
        return getProperty(NamespaceMetadataType.NAMESPACE_URI);
    }

    public CompletableFuture<StatusCode> setNamespaceUri(String value) {
        return setProperty(NamespaceMetadataType.NAMESPACE_URI, value);
    }

    public CompletableFuture<PropertyNode> getNamespaceVersionNode() {
        return getPropertyNode(NamespaceMetadataType.NAMESPACE_VERSION);
    }

    public CompletableFuture<String> getNamespaceVersion() {
        return getProperty(NamespaceMetadataType.NAMESPACE_VERSION);
    }

    public CompletableFuture<StatusCode> setNamespaceVersion(String value) {
        return setProperty(NamespaceMetadataType.NAMESPACE_VERSION, value);
    }

    public CompletableFuture<PropertyNode> getNamespacePublicationDateNode() {
        return getPropertyNode(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE);
    }

    public CompletableFuture<DateTime> getNamespacePublicationDate() {
        return getProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE);
    }

    public CompletableFuture<StatusCode> setNamespacePublicationDate(DateTime value) {
        return setProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE, value);
    }

    public CompletableFuture<PropertyNode> getIsNamespaceSubsetNode() {
        return getPropertyNode(NamespaceMetadataType.IS_NAMESPACE_SUBSET);
    }

    public CompletableFuture<Boolean> getIsNamespaceSubset() {
        return getProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET);
    }

    public CompletableFuture<StatusCode> setIsNamespaceSubset(Boolean value) {
        return setProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET, value);
    }

    public CompletableFuture<PropertyNode> getStaticNodeIdTypesNode() {
        return getPropertyNode(NamespaceMetadataType.STATIC_NODE_ID_TYPES);
    }

    public CompletableFuture<IdType[]> getStaticNodeIdTypes() {
        return getProperty(NamespaceMetadataType.STATIC_NODE_ID_TYPES);
    }

    public CompletableFuture<StatusCode> setStaticNodeIdTypes(IdType[] value) {
        return setProperty(NamespaceMetadataType.STATIC_NODE_ID_TYPES, value);
    }

    public CompletableFuture<PropertyNode> getStaticNumericNodeIdRangeNode() {
        return getPropertyNode(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE);
    }

    public CompletableFuture<String[]> getStaticNumericNodeIdRange() {
        return getProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE);
    }

    public CompletableFuture<StatusCode> setStaticNumericNodeIdRange(String[] value) {
        return setProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE, value);
    }

    public CompletableFuture<PropertyNode> getStaticStringNodeIdPatternNode() {
        return getPropertyNode(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN);
    }

    public CompletableFuture<String> getStaticStringNodeIdPattern() {
        return getProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN);
    }

    public CompletableFuture<StatusCode> setStaticStringNodeIdPattern(String value) {
        return setProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN, value);
    }

    public CompletableFuture<AddressSpaceFileNode> getNamespaceFileNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "NamespaceFile").thenApply(AddressSpaceFileNode.class::cast);
    }
}
