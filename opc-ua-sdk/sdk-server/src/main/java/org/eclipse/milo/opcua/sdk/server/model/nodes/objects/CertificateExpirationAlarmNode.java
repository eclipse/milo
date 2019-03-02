/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.CertificateExpirationAlarmType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CertificateExpirationAlarmNode extends SystemOffNormalAlarmNode implements CertificateExpirationAlarmType {
    public CertificateExpirationAlarmNode(UaNodeContext context, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public CertificateExpirationAlarmNode(UaNodeContext context, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getExpirationDateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_DATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getExpirationDate() {
        Optional<DateTime> propertyValue = getProperty(CertificateExpirationAlarmType.EXPIRATION_DATE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setExpirationDate(DateTime value) {
        setProperty(CertificateExpirationAlarmType.EXPIRATION_DATE, value);
    }

    @Override
    public PropertyNode getCertificateTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE_TYPE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getCertificateType() {
        Optional<NodeId> propertyValue = getProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setCertificateType(NodeId value) {
        setProperty(CertificateExpirationAlarmType.CERTIFICATE_TYPE, value);
    }

    @Override
    public PropertyNode getExpirationLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.EXPIRATION_LIMIT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Double getExpirationLimit() {
        Optional<Double> propertyValue = getProperty(CertificateExpirationAlarmType.EXPIRATION_LIMIT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setExpirationLimit(Double value) {
        setProperty(CertificateExpirationAlarmType.EXPIRATION_LIMIT, value);
    }

    @Override
    public PropertyNode getCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateExpirationAlarmType.CERTIFICATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public ByteString getCertificate() {
        Optional<ByteString> propertyValue = getProperty(CertificateExpirationAlarmType.CERTIFICATE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setCertificate(ByteString value) {
        setProperty(CertificateExpirationAlarmType.CERTIFICATE, value);
    }
}
