/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.types;

import java.util.Objects;
import java.util.StringJoiner;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.jetbrains.annotations.Nullable;

/**
 * Data object that holds details of a DataType:
 * <ul>
 *     <li>Browse Name of the DataType Node</li>
 *     <li>NodeId of the DataType Node</li>
 *     <li>NodeId of the Binary Encoding Node</li>
 *     <li>NodeId of the XML Encoding Node</li>
 *     <li>NodeId of the JSON Encoding Node</li>
 *     <li>{@link DataTypeDefinition} attribute value</li>>
 * </ul>
 */
public class DataType {

    private final QualifiedName browseName;
    private final NodeId nodeId;
    private final NodeId binaryEncodingId;
    private final NodeId xmlEncodingId;
    private final NodeId jsonEncodingId;
    private final DataTypeDefinition dataTypeDefinition;

    public DataType(
        QualifiedName browseName,
        NodeId nodeId,
        NodeId binaryEncodingId,
        NodeId xmlEncodingId,
        NodeId jsonEncodingId,
        DataTypeDefinition dataTypeDefinition
    ) {

        this.browseName = browseName;
        this.nodeId = nodeId;
        this.binaryEncodingId = binaryEncodingId;
        this.xmlEncodingId = xmlEncodingId;
        this.jsonEncodingId = jsonEncodingId;
        this.dataTypeDefinition = dataTypeDefinition;
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
    public @Nullable NodeId getBinaryEncodingId() {
        return binaryEncodingId;
    }

    /**
     * Get the {@link NodeId} of the XML Encoding Node for this DataType, if it exists.
     * <p>
     * Only Structured DataTypes have encoding ids.
     *
     * @return the NodeId of the XML Encoding Node for this DataType, if it exists.
     */
    public @Nullable NodeId getXmlEncodingId() {
        return xmlEncodingId;
    }

    /**
     * Get the {@link NodeId} of the JSON Encoding Node for this DataType, if it exists.
     * <p>
     * Only Structured DataTypes have encoding ids.
     *
     * @return the {@link NodeId} of the JSON Encoding Node for this DataType, if it exists.
     */
    public @Nullable NodeId getJsonEncodingId() {
        return jsonEncodingId;
    }

    /**
     * Get the {@link DataTypeDefinition} of this DataType.
     * <p>
     * Only Structured and Enumerated DataTypes have a {@link DataTypeDefinition}.
     *
     * @return the {@link DataTypeDefinition} of this DataType.
     */
    public @Nullable DataTypeDefinition getDataTypeDefinition() {
        return dataTypeDefinition;
    }

    public boolean isEnum() {
        return dataTypeDefinition instanceof EnumDefinition;
    }

    public boolean isStruct() {
        return dataTypeDefinition instanceof StructureDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataType dataType = (DataType) o;
        return browseName.equals(dataType.browseName) &&
            nodeId.equals(dataType.nodeId) &&
            Objects.equals(binaryEncodingId, dataType.binaryEncodingId) &&
            Objects.equals(xmlEncodingId, dataType.xmlEncodingId) &&
            Objects.equals(jsonEncodingId, dataType.jsonEncodingId) &&
            Objects.equals(dataTypeDefinition, dataType.dataTypeDefinition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            browseName,
            nodeId,
            binaryEncodingId,
            xmlEncodingId,
            jsonEncodingId,
            dataTypeDefinition
        );
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataType.class.getSimpleName() + "[", "]")
            .add("browseName=" + browseName)
            .add("nodeId=" + nodeId)
            .add("binaryEncodingId=" + binaryEncodingId)
            .add("xmlEncodingId=" + xmlEncodingId)
            .add("jsonEncodingId=" + jsonEncodingId)
            .add("dataTypeDefinition=" + dataTypeDefinition)
            .toString();
    }
}
