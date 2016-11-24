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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaObjectTypeNode extends UaNode implements ObjectTypeNode {

    private volatile Boolean isAbstract;

    public UaObjectTypeNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        Boolean isAbstract) {

        super(nodeMap, nodeId, NodeClass.ObjectType,
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

    @UaOptional("NodeVersion")
    public String getNodeVersion() {
        return getProperty(NodeVersion).orElse(null);
    }

    @UaOptional("Icon")
    public ByteString getIcon() {
        return getProperty(Icon).orElse(null);
    }

    public static final Property<String> NodeVersion = new BasicProperty<>(
        new QualifiedName(0, "NodeVersion"),
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    public static final Property<ByteString> Icon = new BasicProperty<>(
        new QualifiedName(0, "Icon"),
        Identifiers.Image,
        ValueRanks.Scalar,
        ByteString.class
    );

}
