/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;

public interface AuditAddNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<AddNodesItem[]> NODES_TO_ADD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToAdd",
        NodeId.parse("ns=0;i=376"),
        ValueRanks.OneDimension,
        AddNodesItem[].class
    );

    PropertyType getNodesToAddNode();

    AddNodesItem[] getNodesToAdd();

    void setNodesToAdd(AddNodesItem[] value);
}
