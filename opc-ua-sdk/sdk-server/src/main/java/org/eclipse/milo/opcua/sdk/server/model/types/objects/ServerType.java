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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.1</a>
 */
public interface ServerType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<String[]> NAMESPACE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<UInteger> URIS_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UrisVersion",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=20998"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UByte> SERVICE_LEVEL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceLevel",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        -1,
        UByte.class
    );

    QualifiedProperty<Boolean> AUDITING = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Auditing",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<DateTime> ESTIMATED_RETURN_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EstimatedReturnTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=13"),
        -1,
        DateTime.class
    );

    QualifiedProperty<TimeZoneDataType> LOCAL_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocalTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=8912"),
        -1,
        TimeZoneDataType.class
    );

    String[] getServerArray();

    void setServerArray(String[] value);

    PropertyType getServerArrayNode();

    String[] getNamespaceArray();

    void setNamespaceArray(String[] value);

    PropertyType getNamespaceArrayNode();

    UInteger getUrisVersion();

    void setUrisVersion(UInteger value);

    PropertyType getUrisVersionNode();

    UByte getServiceLevel();

    void setServiceLevel(UByte value);

    PropertyType getServiceLevelNode();

    Boolean getAuditing();

    void setAuditing(Boolean value);

    PropertyType getAuditingNode();

    DateTime getEstimatedReturnTime();

    void setEstimatedReturnTime(DateTime value);

    PropertyType getEstimatedReturnTimeNode();

    TimeZoneDataType getLocalTime();

    void setLocalTime(TimeZoneDataType value);

    PropertyType getLocalTimeNode();

    ServerStatusType getServerStatusNode();

    ServerStatusDataType getServerStatus();

    void setServerStatus(ServerStatusDataType value);

    ServerCapabilitiesType getServerCapabilitiesNode();

    ServerDiagnosticsType getServerDiagnosticsNode();

    VendorServerInfoType getVendorServerInfoNode();

    ServerRedundancyType getServerRedundancyNode();

    NamespacesType getNamespacesNode();

    MethodNode getGetMonitoredItemsMethodNode();

    MethodNode getResendDataMethodNode();

    MethodNode getSetSubscriptionDurableMethodNode();

    MethodNode getRequestServerStateChangeMethodNode();
}
