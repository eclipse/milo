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

public interface AuditUpdateStateEventType extends AuditUpdateMethodEventType {
    QualifiedProperty<Object> NEW_STATE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewStateId",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> OLD_STATE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldStateId",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    PropertyType getNewStateIdNode();

    Object getNewStateId();

    void setNewStateId(Object value);

    PropertyType getOldStateIdNode();

    Object getOldStateId();

    void setOldStateId(Object value);
}
