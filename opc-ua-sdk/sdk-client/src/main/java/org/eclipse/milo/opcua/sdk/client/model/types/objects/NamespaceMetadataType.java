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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;


public interface NamespaceMetadataType extends BaseObjectType {

    Property<String> NAMESPACE_URI = new BasicProperty<>(
        QualifiedName.parse("0:NamespaceUri"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> NAMESPACE_VERSION = new BasicProperty<>(
        QualifiedName.parse("0:NamespaceVersion"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<DateTime> NAMESPACE_PUBLICATION_DATE = new BasicProperty<>(
        QualifiedName.parse("0:NamespacePublicationDate"),
        NodeId.parse("ns=0;i=13"),
        -1,
        DateTime.class
    );

    Property<Boolean> IS_NAMESPACE_SUBSET = new BasicProperty<>(
        QualifiedName.parse("0:IsNamespaceSubset"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<IdType[]> STATIC_NODE_ID_IDENTIFIER_TYPES = new BasicProperty<>(
        QualifiedName.parse("0:StaticNodeIdIdentifierTypes"),
        NodeId.parse("ns=0;i=256"),
        1,
        IdType[].class
    );

    Property<String[]> STATIC_NUMERIC_NODE_ID_RANGE = new BasicProperty<>(
        QualifiedName.parse("0:StaticNumericNodeIdRange"),
        NodeId.parse("ns=0;i=291"),
        1,
        String[].class
    );

    Property<String[]> STATIC_STRING_NODE_ID_PATTERN = new BasicProperty<>(
        QualifiedName.parse("0:StaticStringNodeIdPattern"),
        NodeId.parse("ns=0;i=12"),
        1,
        String[].class
    );


    CompletableFuture<? extends PropertyType> namespaceUri();

    CompletableFuture<String> getNamespaceUri();

    CompletableFuture<StatusCode> setNamespaceUri(String value);

    CompletableFuture<? extends PropertyType> namespaceVersion();

    CompletableFuture<String> getNamespaceVersion();

    CompletableFuture<StatusCode> setNamespaceVersion(String value);

    CompletableFuture<? extends PropertyType> namespacePublicationDate();

    CompletableFuture<DateTime> getNamespacePublicationDate();

    CompletableFuture<StatusCode> setNamespacePublicationDate(DateTime value);

    CompletableFuture<? extends PropertyType> isNamespaceSubset();

    CompletableFuture<Boolean> getIsNamespaceSubset();

    CompletableFuture<StatusCode> setIsNamespaceSubset(Boolean value);

    CompletableFuture<? extends PropertyType> staticNodeIdIdentifierTypes();

    CompletableFuture<IdType[]> getStaticNodeIdIdentifierTypes();

    CompletableFuture<StatusCode> setStaticNodeIdIdentifierTypes(IdType[] value);

    CompletableFuture<? extends PropertyType> staticNumericNodeIdRange();

    CompletableFuture<String[]> getStaticNumericNodeIdRange();

    CompletableFuture<StatusCode> setStaticNumericNodeIdRange(String[] value);

    CompletableFuture<? extends PropertyType> staticStringNodeIdPattern();

    CompletableFuture<String[]> getStaticStringNodeIdPattern();

    CompletableFuture<StatusCode> setStaticStringNodeIdPattern(String[] value);

    CompletableFuture<? extends AddressSpaceFileType> namespaceFile();


}