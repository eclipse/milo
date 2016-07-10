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

package org.eclipse.milo.opcua.sdk.core.model;

import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public final class BasicProperty<T> implements Property<T> {

    private final QualifiedName browseName;
    private final NodeId dataType;
    private final Integer valueRank;
    private final Class<T> javaType;

    public BasicProperty(QualifiedName browseName, NodeId dataType, ValueRank valueRank, Class<T> javaType) {
        this(browseName, dataType, valueRank.getValue(), javaType);
    }

    public BasicProperty(QualifiedName browseName, NodeId dataType, Integer valueRank, Class<T> javaType) {
        this.browseName = browseName;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.javaType = javaType;
    }

    @Override
    public QualifiedName getBrowseName() {
        return browseName;
    }

    @Override
    public NodeId getDataType() {
        return dataType;
    }

    @Override
    public Integer getValueRank() {
        return valueRank;
    }

    @Override
    public Class<T> getJavaType() {
        return javaType;
    }

}
