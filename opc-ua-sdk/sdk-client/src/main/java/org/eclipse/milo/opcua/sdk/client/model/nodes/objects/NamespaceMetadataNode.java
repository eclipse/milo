/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespaceMetadataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;


public class NamespaceMetadataNode extends BaseObjectNode implements NamespaceMetadataType {

    public NamespaceMetadataNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> namespaceUri() {
        return getPropertyNode(NamespaceMetadataType.NAMESPACE_URI.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getNamespaceUri() {
        return getProperty(NamespaceMetadataType.NAMESPACE_URI);
    }

    @Override
    public CompletableFuture<StatusCode> setNamespaceUri(String value) {
        return setProperty(NamespaceMetadataType.NAMESPACE_URI, value);
    }

    @Override
    public CompletableFuture<PropertyNode> namespaceVersion() {
        return getPropertyNode(NamespaceMetadataType.NAMESPACE_VERSION.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getNamespaceVersion() {
        return getProperty(NamespaceMetadataType.NAMESPACE_VERSION);
    }

    @Override
    public CompletableFuture<StatusCode> setNamespaceVersion(String value) {
        return setProperty(NamespaceMetadataType.NAMESPACE_VERSION, value);
    }

    @Override
    public CompletableFuture<PropertyNode> namespacePublicationDate() {
        return getPropertyNode(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getNamespacePublicationDate() {
        return getProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE);
    }

    @Override
    public CompletableFuture<StatusCode> setNamespacePublicationDate(DateTime value) {
        return setProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> isNamespaceSubset() {
        return getPropertyNode(NamespaceMetadataType.IS_NAMESPACE_SUBSET.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getIsNamespaceSubset() {
        return getProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET);
    }

    @Override
    public CompletableFuture<StatusCode> setIsNamespaceSubset(Boolean value) {
        return setProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET, value);
    }

    @Override
    public CompletableFuture<PropertyNode> staticNodeIdIdentifierTypes() {
        return getPropertyNode(NamespaceMetadataType.STATIC_NODE_ID_IDENTIFIER_TYPES.getBrowseName());
    }

    @Override
    public CompletableFuture<IdType[]> getStaticNodeIdIdentifierTypes() {
        return getProperty(NamespaceMetadataType.STATIC_NODE_ID_IDENTIFIER_TYPES);
    }

    @Override
    public CompletableFuture<StatusCode> setStaticNodeIdIdentifierTypes(IdType[] value) {
        return setProperty(NamespaceMetadataType.STATIC_NODE_ID_IDENTIFIER_TYPES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> staticNumericNodeIdRange() {
        return getPropertyNode(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getStaticNumericNodeIdRange() {
        return getProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE);
    }

    @Override
    public CompletableFuture<StatusCode> setStaticNumericNodeIdRange(String[] value) {
        return setProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> staticStringNodeIdPattern() {
        return getPropertyNode(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getStaticStringNodeIdPattern() {
        return getProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN);
    }

    @Override
    public CompletableFuture<StatusCode> setStaticStringNodeIdPattern(String[] value) {
        return setProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN, value);
    }


    @Override
    public CompletableFuture<AddressSpaceFileNode> namespaceFile() {
        return getObjectComponent(QualifiedName.parse("0:NamespaceFile"))
            .thenApply(AddressSpaceFileNode.class::cast);
    }

}