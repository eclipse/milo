/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.CertificateGroupFolderType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CertificateGroupFolderNode extends FolderNode implements CertificateGroupFolderType {
    public CertificateGroupFolderNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public CertificateGroupFolderNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public CertificateGroupNode getDefaultApplicationGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "DefaultApplicationGroup");
        return component.map(node -> (CertificateGroupNode) node).orElse(null);
    }

    public CertificateGroupNode getDefaultHttpsGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "DefaultHttpsGroup");
        return component.map(node -> (CertificateGroupNode) node).orElse(null);
    }

    public CertificateGroupNode getDefaultUserTokenGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "DefaultUserTokenGroup");
        return component.map(node -> (CertificateGroupNode) node).orElse(null);
    }
}
