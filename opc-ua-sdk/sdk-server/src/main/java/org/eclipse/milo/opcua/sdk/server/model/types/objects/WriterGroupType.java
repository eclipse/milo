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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.6/#9.1.6.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.6/#9.1.6.3</a>
 */
public interface WriterGroupType extends PubSubGroupType {
    QualifiedProperty<UShort> WRITER_GROUP_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "WriterGroupId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        -1,
        UShort.class
    );

    QualifiedProperty<Double> PUBLISHING_INTERVAL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PublishingInterval",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    QualifiedProperty<Double> KEEP_ALIVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "KeepAliveTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    QualifiedProperty<UByte> PRIORITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Priority",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        -1,
        UByte.class
    );

    QualifiedProperty<String[]> LOCALE_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocaleIds",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=295"),
        1,
        String[].class
    );

    QualifiedProperty<String> HEADER_LAYOUT_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HeaderLayoutUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    UShort getWriterGroupId();

    void setWriterGroupId(UShort value);

    PropertyType getWriterGroupIdNode();

    Double getPublishingInterval();

    void setPublishingInterval(Double value);

    PropertyType getPublishingIntervalNode();

    Double getKeepAliveTime();

    void setKeepAliveTime(Double value);

    PropertyType getKeepAliveTimeNode();

    UByte getPriority();

    void setPriority(UByte value);

    PropertyType getPriorityNode();

    String[] getLocaleIds();

    void setLocaleIds(String[] value);

    PropertyType getLocaleIdsNode();

    String getHeaderLayoutUri();

    void setHeaderLayoutUri(String value);

    PropertyType getHeaderLayoutUriNode();

    WriterGroupTransportType getTransportSettingsNode();

    WriterGroupMessageType getMessageSettingsNode();

    PubSubDiagnosticsWriterGroupType getDiagnosticsNode();

    MethodNode getAddDataSetWriterMethodNode();

    MethodNode getRemoveDataSetWriterMethodNode();
}
