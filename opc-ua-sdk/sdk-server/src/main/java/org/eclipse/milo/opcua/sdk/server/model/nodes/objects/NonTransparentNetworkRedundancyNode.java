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

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.NonTransparentNetworkRedundancyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.NetworkGroupDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:NonTransparentNetworkRedundancyType")
public class NonTransparentNetworkRedundancyNode extends NonTransparentRedundancyNode implements NonTransparentNetworkRedundancyType {

    public NonTransparentNetworkRedundancyNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NetworkGroupDataType[] getServerNetworkGroups() {
        Optional<NetworkGroupDataType[]> property = getProperty(NonTransparentNetworkRedundancyType.SERVER_NETWORK_GROUPS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getServerNetworkGroupsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NonTransparentNetworkRedundancyType.SERVER_NETWORK_GROUPS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setServerNetworkGroups(NetworkGroupDataType[] value) {
        setProperty(NonTransparentNetworkRedundancyType.SERVER_NETWORK_GROUPS, value);
    }

}
