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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

public interface NamespaceMetadataType extends BaseObjectType {
    QualifiedProperty<String> NAMESPACE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceUri",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> NAMESPACE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceVersion",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<DateTime> NAMESPACE_PUBLICATION_DATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespacePublicationDate",
        NodeId.parse("ns=0;i=13"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<Boolean> IS_NAMESPACE_SUBSET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IsNamespaceSubset",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<IdType[]> STATIC_NODE_ID_TYPES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticNodeIdTypes",
        NodeId.parse("ns=0;i=256"),
        ValueRanks.OneDimension,
        IdType[].class
    );

    QualifiedProperty<String[]> STATIC_NUMERIC_NODE_ID_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticNumericNodeIdRange",
        NodeId.parse("ns=0;i=291"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String> STATIC_STRING_NODE_ID_PATTERN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticStringNodeIdPattern",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    CompletableFuture<? extends PropertyType> getNamespaceUriNode();

    CompletableFuture<String> getNamespaceUri();

    CompletableFuture<StatusCode> setNamespaceUri(String value);

    CompletableFuture<? extends PropertyType> getNamespaceVersionNode();

    CompletableFuture<String> getNamespaceVersion();

    CompletableFuture<StatusCode> setNamespaceVersion(String value);

    CompletableFuture<? extends PropertyType> getNamespacePublicationDateNode();

    CompletableFuture<DateTime> getNamespacePublicationDate();

    CompletableFuture<StatusCode> setNamespacePublicationDate(DateTime value);

    CompletableFuture<? extends PropertyType> getIsNamespaceSubsetNode();

    CompletableFuture<Boolean> getIsNamespaceSubset();

    CompletableFuture<StatusCode> setIsNamespaceSubset(Boolean value);

    CompletableFuture<? extends PropertyType> getStaticNodeIdTypesNode();

    CompletableFuture<IdType[]> getStaticNodeIdTypes();

    CompletableFuture<StatusCode> setStaticNodeIdTypes(IdType[] value);

    CompletableFuture<? extends PropertyType> getStaticNumericNodeIdRangeNode();

    CompletableFuture<String[]> getStaticNumericNodeIdRange();

    CompletableFuture<StatusCode> setStaticNumericNodeIdRange(String[] value);

    CompletableFuture<? extends PropertyType> getStaticStringNodeIdPatternNode();

    CompletableFuture<String> getStaticStringNodeIdPattern();

    CompletableFuture<StatusCode> setStaticStringNodeIdPattern(String value);

    CompletableFuture<? extends AddressSpaceFileType> getNamespaceFileNode();
}
