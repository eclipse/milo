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

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaFolderNode extends UaObjectNode {

    public UaFolderNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName) {

        super(nodeMap, nodeId, browseName, displayName);

        addReference(new Reference(
            getNodeId(),
            Identifiers.HasTypeDefinition,
            Identifiers.FolderType.expanded(),
            NodeClass.ObjectType,
            true
        ));
    }

    /**
     * Add an 'Organizes' reference from this folder to {@code node} and an inverse 'Organized By' reference from
     * {@code node} back to this folder.
     *
     * @param node the node to be organized by this folder.
     */
    public void addOrganizes(UaNode node) {
        addReference(new Reference(
            getNodeId(),
            Identifiers.Organizes,
            node.getNodeId().expanded(),
            node.getNodeClass(),
            true
        ));

        node.addReference(new Reference(
            node.getNodeId(),
            Identifiers.Organizes,
            getNodeId().expanded(),
            getNodeClass(),
            false
        ));
    }

    /**
     * Remove the 'Organizes' reference from this folder to {@code node} and the inverse 'Organized By' reference from
     * {@code node} back to this folder.
     *
     * @param node the node to be organized by this folder.
     */
    public void removeOrganizes(UaNode node) {
        removeReference(new Reference(
            getNodeId(),
            Identifiers.Organizes,
            node.getNodeId().expanded(),
            node.getNodeClass(),
            true
        ));

        node.removeReference(new Reference(
            node.getNodeId(),
            Identifiers.Organizes,
            getNodeId().expanded(),
            getNodeClass(),
            false
        ));
    }

}
