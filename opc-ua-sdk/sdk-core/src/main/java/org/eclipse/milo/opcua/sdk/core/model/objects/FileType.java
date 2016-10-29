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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface FileType extends BaseObjectType {

    Property<ULong> SIZE = new BasicProperty<>(
        QualifiedName.parse("0:Size"),
        NodeId.parse("ns=0;i=9"),
        -1,
        ULong.class
    );

    Property<Boolean> WRITABLE = new BasicProperty<>(
        QualifiedName.parse("0:Writable"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> USER_WRITABLE = new BasicProperty<>(
        QualifiedName.parse("0:UserWritable"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<UShort> OPEN_COUNT = new BasicProperty<>(
        QualifiedName.parse("0:OpenCount"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );

    Property<String> MIME_TYPE = new BasicProperty<>(
        QualifiedName.parse("0:MimeType"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    ULong getSize();

    PropertyType getSizeNode();

    void setSize(ULong value);

    Boolean getWritable();

    PropertyType getWritableNode();

    void setWritable(Boolean value);

    Boolean getUserWritable();

    PropertyType getUserWritableNode();

    void setUserWritable(Boolean value);

    UShort getOpenCount();

    PropertyType getOpenCountNode();

    void setOpenCount(UShort value);

    String getMimeType();

    PropertyType getMimeTypeNode();

    void setMimeType(String value);
}
