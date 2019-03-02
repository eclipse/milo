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
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface FileType extends BaseObjectType {
    QualifiedProperty<Boolean> USER_WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserWritable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Writable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<ULong> SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Size",
        NodeId.parse("ns=0;i=9"),
        ValueRanks.Scalar,
        ULong.class
    );

    QualifiedProperty<String> MIME_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MimeType",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<UShort> OPEN_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OpenCount",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    PropertyType getUserWritableNode();

    Boolean getUserWritable();

    void setUserWritable(Boolean value);

    PropertyType getWritableNode();

    Boolean getWritable();

    void setWritable(Boolean value);

    PropertyType getSizeNode();

    ULong getSize();

    void setSize(ULong value);

    PropertyType getMimeTypeNode();

    String getMimeType();

    void setMimeType(String value);

    PropertyType getOpenCountNode();

    UShort getOpenCount();

    void setOpenCount(UShort value);

    UaMethodNode getReadMethodNode();

    UaMethodNode getWriteMethodNode();

    UaMethodNode getGetPositionMethodNode();

    UaMethodNode getCloseMethodNode();

    UaMethodNode getOpenMethodNode();

    UaMethodNode getSetPositionMethodNode();
}
