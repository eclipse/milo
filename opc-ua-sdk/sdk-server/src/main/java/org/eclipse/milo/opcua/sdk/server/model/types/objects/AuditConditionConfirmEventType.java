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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditConditionConfirmEventType extends AuditConditionEventType {
    QualifiedProperty<ByteString> EVENT_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventId",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    QualifiedProperty<LocalizedText> COMMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Comment",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    PropertyType getEventIdNode();

    ByteString getEventId();

    void setEventId(ByteString value);

    PropertyType getCommentNode();

    LocalizedText getComment();

    void setComment(LocalizedText value);
}
