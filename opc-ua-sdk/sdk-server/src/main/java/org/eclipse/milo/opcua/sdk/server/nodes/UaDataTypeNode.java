/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

public class UaDataTypeNode extends UaNode implements DataTypeNode {

    private volatile Boolean isAbstract;

    public UaDataTypeNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        boolean isAbstract) {

        super(context, nodeId, NodeClass.DataType,
            browseName, displayName, description, writeMask, userWriteMask);

        this.isAbstract = isAbstract;
    }

    @Override
    public Boolean getIsAbstract() {
        return isAbstract;
    }

    @Override
    public synchronized void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;

        fireAttributeChanged(AttributeId.IsAbstract, isAbstract);
    }

    @Nullable
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    @Nullable
    public LocalizedText[] getEnumStrings() {
        return getProperty(EnumStrings).orElse(null);
    }

    @Nullable
    public EnumValueType[] getEnumValues() {
        return getProperty(EnumValues).orElse(null);
    }

    public void setNodeVersion(String nodeVersion) {
        setProperty(NodeVersion, nodeVersion);
    }

    public void setEnumStrings(LocalizedText[] enumStrings) {
        setProperty(EnumStrings, enumStrings);
    }

    public void setEnumValues(EnumValueType[] enumValues) {
        setProperty(EnumValues, enumValues);
    }

    public static final QualifiedProperty<String> NodeVersion = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "NodeVersion",
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    public static final QualifiedProperty<LocalizedText[]> EnumStrings = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "EnumStrings",
        Identifiers.LocalizedText,
        ValueRanks.OneDimension,
        LocalizedText[].class
    );

    public static final QualifiedProperty<EnumValueType[]> EnumValues = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "EnumValues",
        Identifiers.EnumValueType,
        ValueRanks.OneDimension,
        EnumValueType[].class
    );

}
