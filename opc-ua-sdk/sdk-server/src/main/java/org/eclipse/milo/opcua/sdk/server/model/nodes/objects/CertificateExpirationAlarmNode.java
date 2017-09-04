/*
 * Copyright (c) 2017 Kevin Herron
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

public class CertificateExpirationAlarmNode extends SystemOffNormalAlarmNode implements CertificateExpirationAlarmType {
    public CertificateExpirationAlarmNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public CertificateExpirationAlarmNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getExpirationDateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_DATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DateTime getExpirationDate() {
        Optional<DateTime> propertyValue = getProperty(CertificateExpirationAlarmType.EXPIRATION_DATE);
        return propertyValue.orElse(null);
    }

    public void setExpirationDate(DateTime value) {
        setProperty(CertificateExpirationAlarmType.EXPIRATION_DATE, value);
    }

    public PropertyNode getExpirationLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_LIMIT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getExpirationLimit() {
        Optional<Double> propertyValue = getProperty(CertificateExpirationAlarmType.EXPIRATION_LIMIT);
        return propertyValue.orElse(null);
    }

    public void setExpirationLimit(Double value) {
        setProperty(CertificateExpirationAlarmType.EXPIRATION_LIMIT, value);
    }

    public PropertyNode getCertificateTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE_TYPE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public NodeId getCertificateType() {
        Optional<NodeId> propertyValue = getProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE);
        return propertyValue.orElse(null);
    }

    public void setCertificateType(NodeId value) {
        setProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE, value);
    }

    public PropertyNode getCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public ByteString getCertificate() {
        Optional<ByteString> propertyValue = getProperty(CertificateExpirationAlarmType.CERTIFICATE);
        return propertyValue.orElse(null);
    }

    public void setCertificate(ByteString value) {
        setProperty(CertificateExpirationAlarmType.CERTIFICATE, value);
    }
}
