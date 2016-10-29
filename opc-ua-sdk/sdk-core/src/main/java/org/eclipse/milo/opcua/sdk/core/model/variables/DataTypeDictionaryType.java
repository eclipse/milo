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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;


public interface DataTypeDictionaryType extends BaseDataVariableType {

    Property<String> DATA_TYPE_VERSION = new BasicProperty<>(
        QualifiedName.parse("0:DataTypeVersion"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> NAMESPACE_URI = new BasicProperty<>(
        QualifiedName.parse("0:NamespaceUri"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );


    String getDataTypeVersion();

    PropertyType getDataTypeVersionNode();

    void setDataTypeVersion(String value);

    String getNamespaceUri();

    PropertyType getNamespaceUriNode();

    void setNamespaceUri(String value);

}
