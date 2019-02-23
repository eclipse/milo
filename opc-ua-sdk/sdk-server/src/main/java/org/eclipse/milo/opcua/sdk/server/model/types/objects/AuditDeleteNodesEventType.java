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
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;

public interface AuditDeleteNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteNodesItem[]> NODES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToDelete",
        NodeId.parse("ns=0;i=382"),
        ValueRanks.OneDimension,
        DeleteNodesItem[].class
    );

    PropertyType getNodesToDeleteNode();

    DeleteNodesItem[] getNodesToDelete();

    void setNodesToDelete(DeleteNodesItem[] value);
}
