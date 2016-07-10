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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface ServerConfigurationType extends BaseObjectType {

    Property<String[]> SERVER_CAPABILITIES = new BasicProperty<>(
        QualifiedName.parse("0:ServerCapabilities"),
        NodeId.parse("ns=0;i=12"),
        1,
        String[].class
    );

    Property<String[]> SUPPORTED_PRIVATE_KEY_FORMATS = new BasicProperty<>(
        QualifiedName.parse("0:SupportedPrivateKeyFormats"),
        NodeId.parse("ns=0;i=12"),
        1,
        String[].class
    );

    Property<UInteger> MAX_TRUST_LIST_SIZE = new BasicProperty<>(
        QualifiedName.parse("0:MaxTrustListSize"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<Boolean> MULTICAST_DNS_ENABLED = new BasicProperty<>(
        QualifiedName.parse("0:MulticastDnsEnabled"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    String[] getServerCapabilities();

    PropertyType getServerCapabilitiesNode();

    void setServerCapabilities(String[] value);

    String[] getSupportedPrivateKeyFormats();

    PropertyType getSupportedPrivateKeyFormatsNode();

    void setSupportedPrivateKeyFormats(String[] value);

    UInteger getMaxTrustListSize();

    PropertyType getMaxTrustListSizeNode();

    void setMaxTrustListSize(UInteger value);

    Boolean getMulticastDnsEnabled();

    PropertyType getMulticastDnsEnabledNode();

    void setMulticastDnsEnabled(Boolean value);

    CertificateGroupFolderType getCertificateGroupsNode();

}
