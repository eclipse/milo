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

import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UNumber;
import org.eclipse.milo.opcua.stack.core.util.Tree;

public class DataTypeTree {

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

        public DataType(QualifiedName browseName, NodeId nodeId, NodeId binaryEncodingId, NodeId xmlEncodingId) {
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

}
