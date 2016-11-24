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

package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.UaOptional;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaVariableTypeNode extends UaNode implements VariableTypeNode {

    private volatile DataValue value;
    private volatile NodeId dataType;
    private volatile Integer valueRank;
    private volatile UInteger[] arrayDimensions;
    private volatile Boolean isAbstract;

    public UaVariableTypeNode(
        ServerNodeMap nodeMap,
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
        Boolean isAbstract) {

        super(nodeMap, nodeId, NodeClass.VariableType,
            browseName, displayName, description, writeMask, userWriteMask);

        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.isAbstract = isAbstract;
    }

    @Override
    public DataValue getValue() {
        return value;
    }

    @Override
    public NodeId getDataType() {
        return dataType;
    }

    @Override
    public Integer getValueRank() {
        return valueRank;
    }

    @Override
    public UInteger[] getArrayDimensions() {
        return arrayDimensions;
    }

    @Override
    public Boolean getIsAbstract() {
        return isAbstract;
    }

    @Override
    public synchronized void setValue(DataValue value) {
        this.value = value;

        fireAttributeChanged(AttributeId.Value, value);
    }

    @Override
    public synchronized void setDataType(NodeId dataType) {
        this.dataType = dataType;

        fireAttributeChanged(AttributeId.Value, dataType);
    }

    @Override
    public synchronized void setValueRank(Integer valueRank) {
        this.valueRank = valueRank;

        fireAttributeChanged(AttributeId.ValueRank, valueRank);
    }

    @Override
    public synchronized void setArrayDimensions(UInteger[] arrayDimensions) {
        this.arrayDimensions = arrayDimensions;

        fireAttributeChanged(AttributeId.ArrayDimensions, arrayDimensions);
    }

    @Override
    public synchronized void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;

        fireAttributeChanged(AttributeId.IsAbstract, isAbstract);
    }

    @UaOptional("NodeVersion")
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    public void setNodeVersion(String nodeVersion) {
        setProperty(NodeVersion, nodeVersion);
    }

    public static final Property<String> NodeVersion = new BasicProperty<>(
        new QualifiedName(0, "NodeVersion"),
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

}
