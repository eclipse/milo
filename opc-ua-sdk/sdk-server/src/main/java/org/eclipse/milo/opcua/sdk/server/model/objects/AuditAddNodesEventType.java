/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.20">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.20</a>
 */
public interface AuditAddNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<AddNodesItem[]> NODES_TO_ADD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToAdd",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=376"),
        1,
        AddNodesItem[].class
    );

    AddNodesItem[] getNodesToAdd();

    void setNodesToAdd(AddNodesItem[] value);

    PropertyType getNodesToAddNode();
}