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

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.DataTypeDescriptionType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:DataTypeDescriptionType")
public class DataTypeDescriptionNode extends BaseDataVariableNode implements DataTypeDescriptionType {

    public DataTypeDescriptionNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public DataTypeDescriptionNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        boolean historizing) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }


    @Override
    public String getDataTypeVersion() {
        Optional<String> property = getProperty(DataTypeDescriptionType.DATA_TYPE_VERSION);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDataTypeVersionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DataTypeDescriptionType.DATA_TYPE_VERSION.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDataTypeVersion(String value) {
        setProperty(DataTypeDescriptionType.DATA_TYPE_VERSION, value);
    }

    @Override
    public ByteString getDictionaryFragment() {
        Optional<ByteString> property = getProperty(DataTypeDescriptionType.DICTIONARY_FRAGMENT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDictionaryFragmentNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DataTypeDescriptionType.DICTIONARY_FRAGMENT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDictionaryFragment(ByteString value) {
        setProperty(DataTypeDescriptionType.DICTIONARY_FRAGMENT, value);
    }

}
