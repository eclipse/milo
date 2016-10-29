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
import org.eclipse.milo.opcua.sdk.core.model.variables.ServerStatusType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public interface ServerType extends BaseObjectType {

    Property<String[]> SERVER_ARRAY = new BasicProperty<>(
        QualifiedName.parse("0:ServerArray"),
        NodeId.parse("ns=0;i=12"),
        1,
        String[].class
    );

    Property<String[]> NAMESPACE_ARRAY = new BasicProperty<>(
        QualifiedName.parse("0:NamespaceArray"),
        NodeId.parse("ns=0;i=12"),
        1,
        String[].class
    );

    Property<UByte> SERVICE_LEVEL = new BasicProperty<>(
        QualifiedName.parse("0:ServiceLevel"),
        NodeId.parse("ns=0;i=3"),
        -1,
        UByte.class
    );

    Property<Boolean> AUDITING = new BasicProperty<>(
        QualifiedName.parse("0:Auditing"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<DateTime> ESTIMATED_RETURN_TIME = new BasicProperty<>(
        QualifiedName.parse("0:EstimatedReturnTime"),
        NodeId.parse("ns=0;i=13"),
        -1,
        DateTime.class
    );

    String[] getServerArray();

    PropertyType getServerArrayNode();

    void setServerArray(String[] value);

    String[] getNamespaceArray();

    PropertyType getNamespaceArrayNode();

    void setNamespaceArray(String[] value);

    UByte getServiceLevel();

    PropertyType getServiceLevelNode();

    void setServiceLevel(UByte value);

    Boolean getAuditing();

    PropertyType getAuditingNode();

    void setAuditing(Boolean value);

    DateTime getEstimatedReturnTime();

    PropertyType getEstimatedReturnTimeNode();

    void setEstimatedReturnTime(DateTime value);

    ServerCapabilitiesType getServerCapabilitiesNode();

    ServerDiagnosticsType getServerDiagnosticsNode();

    VendorServerInfoType getVendorServerInfoNode();

    ServerRedundancyType getServerRedundancyNode();

    NamespacesType getNamespacesNode();

    ServerStatusDataType getServerStatus();

    ServerStatusType getServerStatusNode();

    void setServerStatus(ServerStatusDataType value);
}
