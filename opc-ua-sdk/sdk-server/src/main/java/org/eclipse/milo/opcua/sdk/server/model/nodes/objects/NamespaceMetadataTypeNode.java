/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.NamespaceMetadataType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

public class NamespaceMetadataTypeNode extends BaseObjectTypeNode implements NamespaceMetadataType {
    public NamespaceMetadataTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                     LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                     UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public NamespaceMetadataTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                     LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                     UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getNamespaceUriNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.NAMESPACE_URI);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public String getNamespaceUri() {
        Optional<String> propertyValue = getProperty(NamespaceMetadataType.NAMESPACE_URI);
        return propertyValue.orElse(null);
    }

    @Override
    public void setNamespaceUri(String value) {
        setProperty(NamespaceMetadataType.NAMESPACE_URI, value);
    }

    @Override
    public PropertyTypeNode getNamespaceVersionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.NAMESPACE_VERSION);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public String getNamespaceVersion() {
        Optional<String> propertyValue = getProperty(NamespaceMetadataType.NAMESPACE_VERSION);
        return propertyValue.orElse(null);
    }

    @Override
    public void setNamespaceVersion(String value) {
        setProperty(NamespaceMetadataType.NAMESPACE_VERSION, value);
    }

    @Override
    public PropertyTypeNode getNamespacePublicationDateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getNamespacePublicationDate() {
        Optional<DateTime> propertyValue = getProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setNamespacePublicationDate(DateTime value) {
        setProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE, value);
    }

    @Override
    public PropertyTypeNode getIsNamespaceSubsetNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.IS_NAMESPACE_SUBSET);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getIsNamespaceSubset() {
        Optional<Boolean> propertyValue = getProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET);
        return propertyValue.orElse(null);
    }

    @Override
    public void setIsNamespaceSubset(Boolean value) {
        setProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET, value);
    }

    @Override
    public PropertyTypeNode getStaticNodeIdTypesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.STATIC_NODE_ID_TYPES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public IdType[] getStaticNodeIdTypes() {
        Optional<IdType[]> propertyValue = getProperty(NamespaceMetadataType.STATIC_NODE_ID_TYPES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStaticNodeIdTypes(IdType[] value) {
        setProperty(NamespaceMetadataType.STATIC_NODE_ID_TYPES, value);
    }

    @Override
    public PropertyTypeNode getStaticNumericNodeIdRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public String[] getStaticNumericNodeIdRange() {
        Optional<String[]> propertyValue = getProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStaticNumericNodeIdRange(String[] value) {
        setProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE, value);
    }

    @Override
    public PropertyTypeNode getStaticStringNodeIdPatternNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public String getStaticStringNodeIdPattern() {
        Optional<String> propertyValue = getProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStaticStringNodeIdPattern(String value) {
        setProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN, value);
    }

    @Override
    public AddressSpaceFileTypeNode getNamespaceFileNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "NamespaceFile");
        return (AddressSpaceFileTypeNode) component.orElse(null);
    }
}
