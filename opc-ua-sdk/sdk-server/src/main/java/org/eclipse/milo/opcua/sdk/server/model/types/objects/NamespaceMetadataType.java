/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

public interface NamespaceMetadataType extends BaseObjectType {
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

    QualifiedProperty<Boolean> IS_NAMESPACE_SUBSET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IsNamespaceSubset",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

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

    QualifiedProperty<String> STATIC_STRING_NODE_ID_PATTERN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticStringNodeIdPattern",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getStaticNodeIdTypesNode();

    IdType[] getStaticNodeIdTypes();

    void setStaticNodeIdTypes(IdType[] value);

    PropertyType getStaticNumericNodeIdRangeNode();

    String[] getStaticNumericNodeIdRange();

    void setStaticNumericNodeIdRange(String[] value);

    PropertyType getIsNamespaceSubsetNode();

    Boolean getIsNamespaceSubset();

    void setIsNamespaceSubset(Boolean value);

    PropertyType getNamespaceUriNode();

    String getNamespaceUri();

    void setNamespaceUri(String value);

    PropertyType getNamespaceVersionNode();

    String getNamespaceVersion();

    void setNamespaceVersion(String value);

    PropertyType getNamespacePublicationDateNode();

    DateTime getNamespacePublicationDate();

    void setNamespacePublicationDate(DateTime value);

    PropertyType getStaticStringNodeIdPatternNode();

    String getStaticStringNodeIdPattern();

    void setStaticStringNodeIdPattern(String value);

    AddressSpaceFileType getNamespaceFileNode();
}
