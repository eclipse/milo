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

package org.eclipse.milo.opcua.sdk.core.model;

import java.util.Objects;
import java.util.Optional;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class QualifiedProperty<T> {

    private final String namespaceUri;
    private final String browseName;
    private final NodeId dataType;
    private final Integer valueRank;
    private final Class<T> javaType;

    public QualifiedProperty(
        String namespaceUri,
        String browseName,
        NodeId dataType,
        Integer valueRank,
        Class<T> javaType) {

        this.namespaceUri = namespaceUri;
        this.browseName = browseName;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.javaType = javaType;
    }

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public String getBrowseName() {
        return browseName;
    }

    public NodeId getDataType() {
        return dataType;
    }

    public Integer getValueRank() {
        return valueRank;
    }

    public Class<T> getJavaType() {
        return javaType;
    }

    /**
     * Get the {@link QualifiedName} of this property.
     * <p>
     * The {@code namespaceUri} must be present in {@code namespaceTable} or an empty {@link Optional} will be returned.
     *
     * @param namespaceTable the {@link NamespaceTable} containing the namespace URIs and their indices.
     * @return the {@link QualifiedName} of this property, or an empty {@link Optional} if the namespace URI is not
     * present in {@code namespaceTable}.
     */
    public Optional<QualifiedName> getQualifiedName(NamespaceTable namespaceTable) {
        UShort namespaceIndex = namespaceTable.getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return Optional.of(new QualifiedName(namespaceIndex, browseName));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("namespaceUri", namespaceUri)
            .add("browseName", browseName)
            .add("dataType", dataType)
            .add("valueRank", valueRank)
            .add("javaType", javaType)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualifiedProperty<?> that = (QualifiedProperty<?>) o;

        return Objects.equals(namespaceUri, that.namespaceUri) &&
            Objects.equals(browseName, that.browseName) &&
            Objects.equals(dataType, that.dataType) &&
            Objects.equals(valueRank, that.valueRank) &&
            Objects.equals(javaType, that.javaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespaceUri, browseName, dataType, valueRank, javaType);
    }

}
