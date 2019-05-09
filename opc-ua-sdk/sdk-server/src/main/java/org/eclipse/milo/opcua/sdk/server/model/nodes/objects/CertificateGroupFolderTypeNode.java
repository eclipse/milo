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

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.CertificateGroupFolderType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CertificateGroupFolderTypeNode extends FolderTypeNode implements CertificateGroupFolderType {
    public CertificateGroupFolderTypeNode(UaNodeContext context, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public CertificateGroupFolderTypeNode(UaNodeContext context, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public CertificateGroupTypeNode getDefaultApplicationGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "DefaultApplicationGroup");
        return (CertificateGroupTypeNode) component.orElse(null);
    }

    @Override
    public CertificateGroupTypeNode getDefaultHttpsGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "DefaultHttpsGroup");
        return (CertificateGroupTypeNode) component.orElse(null);
    }

    @Override
    public CertificateGroupTypeNode getDefaultUserTokenGroupNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "DefaultUserTokenGroup");
        return (CertificateGroupTypeNode) component.orElse(null);
    }
}
