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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.NamespaceMetadataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:NamespaceMetadataType")
public class NamespaceMetadataNode extends BaseObjectNode implements NamespaceMetadataType {

    public NamespaceMetadataNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String getNamespaceUri() {
        Optional<String> property = getProperty(NamespaceMetadataType.NAMESPACE_URI);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNamespaceUriNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.NAMESPACE_URI.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNamespaceUri(String value) {
        setProperty(NamespaceMetadataType.NAMESPACE_URI, value);
    }

    @Override
    public String getNamespaceVersion() {
        Optional<String> property = getProperty(NamespaceMetadataType.NAMESPACE_VERSION);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNamespaceVersionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.NAMESPACE_VERSION.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNamespaceVersion(String value) {
        setProperty(NamespaceMetadataType.NAMESPACE_VERSION, value);
    }

    @Override
    public DateTime getNamespacePublicationDate() {
        Optional<DateTime> property = getProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNamespacePublicationDateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNamespacePublicationDate(DateTime value) {
        setProperty(NamespaceMetadataType.NAMESPACE_PUBLICATION_DATE, value);
    }

    @Override
    public Boolean getIsNamespaceSubset() {
        Optional<Boolean> property = getProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getIsNamespaceSubsetNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.IS_NAMESPACE_SUBSET.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setIsNamespaceSubset(Boolean value) {
        setProperty(NamespaceMetadataType.IS_NAMESPACE_SUBSET, value);
    }

    @Override
    public IdType[] getStaticNodeIdIdentifierTypes() {
        Optional<IdType[]> property = getProperty(NamespaceMetadataType.STATIC_NODE_ID_IDENTIFIER_TYPES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStaticNodeIdIdentifierTypesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.STATIC_NODE_ID_IDENTIFIER_TYPES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStaticNodeIdIdentifierTypes(IdType[] value) {
        setProperty(NamespaceMetadataType.STATIC_NODE_ID_IDENTIFIER_TYPES, value);
    }

    @Override
    public String[] getStaticNumericNodeIdRange() {
        Optional<String[]> property = getProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStaticNumericNodeIdRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStaticNumericNodeIdRange(String[] value) {
        setProperty(NamespaceMetadataType.STATIC_NUMERIC_NODE_ID_RANGE, value);
    }

    @Override
    public String[] getStaticStringNodeIdPattern() {
        Optional<String[]> property = getProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStaticStringNodeIdPatternNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStaticStringNodeIdPattern(String[] value) {
        setProperty(NamespaceMetadataType.STATIC_STRING_NODE_ID_PATTERN, value);
    }

    @Override
    public AddressSpaceFileNode getNamespaceFileNode() {
        Optional<ObjectNode> component = getObjectComponent("NamespaceFile");

        return component.map(node -> (AddressSpaceFileNode) node).orElse(null);
    }

}
