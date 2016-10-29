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

package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.model.objects.CertificateGroupFolderType;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaObjectNode(typeName = "0:CertificateGroupFolderType")
public class CertificateGroupFolderNode extends FolderNode implements CertificateGroupFolderType {

    public CertificateGroupFolderNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        Optional<LocalizedText> description,
        Optional<UInteger> writeMask,
        Optional<UInteger> userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public CertificateGroupNode getDefaultApplicationGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("DefaultApplicationGroup");

        return component.map(node -> (CertificateGroupNode) node).orElse(null);
    }

    @Override
    public CertificateGroupNode getDefaultHttpsGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("DefaultHttpsGroup");

        return component.map(node -> (CertificateGroupNode) node).orElse(null);
    }

    @Override
    public CertificateGroupNode getDefaultUserTokenGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("DefaultUserTokenGroup");

        return component.map(node -> (CertificateGroupNode) node).orElse(null);
    }

}
