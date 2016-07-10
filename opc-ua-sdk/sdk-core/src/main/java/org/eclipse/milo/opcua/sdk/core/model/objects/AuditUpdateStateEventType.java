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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface AuditUpdateStateEventType extends AuditUpdateMethodEventType {

    Property<Object> OLD_STATE_ID = new BasicProperty<>(
        QualifiedName.parse("0:OldStateId"),
        NodeId.parse("ns=0;i=24"),
        -1,
        Object.class
    );

    Property<Object> NEW_STATE_ID = new BasicProperty<>(
        QualifiedName.parse("0:NewStateId"),
        NodeId.parse("ns=0;i=24"),
        -1,
        Object.class
    );

    Object getOldStateId();

    PropertyType getOldStateIdNode();

    void setOldStateId(Object value);

    Object getNewStateId();

    PropertyType getNewStateIdNode();

    void setNewStateId(Object value);
}
