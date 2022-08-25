/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part20/4.4.1">https://reference.opcfoundation.org/v105/Core/docs/Part20/4.4.1</a>
 */
public interface TemporaryFileTransferType extends BaseObjectType {
    QualifiedProperty<Double> CLIENT_PROCESSING_TIMEOUT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientProcessingTimeout",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    Double getClientProcessingTimeout();

    void setClientProcessingTimeout(Double value);

    PropertyType getClientProcessingTimeoutNode();

    MethodNode getGenerateFileForReadMethodNode();

    MethodNode getGenerateFileForWriteMethodNode();

    MethodNode getCloseAndCommitMethodNode();
}