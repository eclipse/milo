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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface AuditConditionAcknowledgeEventType extends AuditConditionEventType {

    Property<ByteString> EVENT_ID = new BasicProperty<>(
        QualifiedName.parse("0:EventId"),
        NodeId.parse("ns=0;i=15"),
        -1,
        ByteString.class
    );

    Property<LocalizedText> COMMENT = new BasicProperty<>(
        QualifiedName.parse("0:Comment"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );

    ByteString getEventId();

    PropertyType getEventIdNode();

    void setEventId(ByteString value);

    LocalizedText getComment();

    PropertyType getCommentNode();

    void setComment(LocalizedText value);
}
