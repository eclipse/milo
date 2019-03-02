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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public interface ServerType extends BaseObjectType {
    QualifiedProperty<Boolean> AUDITING = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Auditing",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> ESTIMATED_RETURN_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EstimatedReturnTime",
        NodeId.parse("ns=0;i=13"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<String[]> NAMESPACE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceArray",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerArray",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<UByte> SERVICE_LEVEL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceLevel",
        NodeId.parse("ns=0;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    PropertyType getAuditingNode();

    Boolean getAuditing();

    void setAuditing(Boolean value);

    PropertyType getEstimatedReturnTimeNode();

    DateTime getEstimatedReturnTime();

    void setEstimatedReturnTime(DateTime value);

    PropertyType getNamespaceArrayNode();

    String[] getNamespaceArray();

    void setNamespaceArray(String[] value);

    PropertyType getServerArrayNode();

    String[] getServerArray();

    void setServerArray(String[] value);

    PropertyType getServiceLevelNode();

    UByte getServiceLevel();

    void setServiceLevel(UByte value);

    ServerRedundancyType getServerRedundancyNode();

    NamespacesType getNamespacesNode();

    UaMethodNode getResendDataMethodNode();

    UaMethodNode getRequestServerStateChangeMethodNode();

    ServerStatusType getServerStatusNode();

    ServerStatusDataType getServerStatus();

    void setServerStatus(ServerStatusDataType value);

    UaMethodNode getGetMonitoredItemsMethodNode();

    ServerCapabilitiesType getServerCapabilitiesNode();

    VendorServerInfoType getVendorServerInfoNode();

    ServerDiagnosticsType getServerDiagnosticsNode();

    UaMethodNode getSetSubscriptionDurableMethodNode();
}
