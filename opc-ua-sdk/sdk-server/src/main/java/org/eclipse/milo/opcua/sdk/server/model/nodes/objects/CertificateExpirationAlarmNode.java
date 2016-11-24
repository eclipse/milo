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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.CertificateExpirationAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:CertificateExpirationAlarmType")
public class CertificateExpirationAlarmNode extends SystemOffNormalAlarmNode implements CertificateExpirationAlarmType {

    public CertificateExpirationAlarmNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public DateTime getExpirationDate() {
        Optional<DateTime> property = getProperty(CertificateExpirationAlarmType.EXPIRATION_DATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getExpirationDateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_DATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setExpirationDate(DateTime value) {
        setProperty(CertificateExpirationAlarmType.EXPIRATION_DATE, value);
    }

    @Override
    public NodeId getCertificateType() {
        Optional<NodeId> property = getProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getCertificateTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE_TYPE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setCertificateType(NodeId value) {
        setProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE, value);
    }

    @Override
    public ByteString getCertificate() {
        Optional<ByteString> property = getProperty(CertificateExpirationAlarmType.CERTIFICATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setCertificate(ByteString value) {
        setProperty(CertificateExpirationAlarmType.CERTIFICATE, value);
    }

}
