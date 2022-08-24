/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SelectionListType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.KeyValuePair;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.5/#9.1.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.5/#9.1.5.2</a>
 */
public interface PubSubConnectionType extends BaseObjectType {
    QualifiedProperty<Object> PUBLISHER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PublisherId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        -1,
        Object.class
    );

    QualifiedProperty<KeyValuePair[]> CONNECTION_PROPERTIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConnectionProperties",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14533"),
        1,
        KeyValuePair[].class
    );

    Object getPublisherId();

    void setPublisherId(Object value);

    PropertyType getPublisherIdNode();

    KeyValuePair[] getConnectionProperties();

    void setConnectionProperties(KeyValuePair[] value);

    PropertyType getConnectionPropertiesNode();

    SelectionListType getTransportProfileUriNode();

    String getTransportProfileUri();

    void setTransportProfileUri(String value);

    NetworkAddressType getAddressNode();

    ConnectionTransportType getTransportSettingsNode();

    PubSubStatusType getStatusNode();

    PubSubDiagnosticsConnectionType getDiagnosticsNode();

    MethodNode getAddWriterGroupMethodNode();

    MethodNode getAddReaderGroupMethodNode();

    MethodNode getRemoveGroupMethodNode();
}
