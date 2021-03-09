/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;

public class SamplingIntervalDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SamplingIntervalDiagnosticsArrayType {
    public SamplingIntervalDiagnosticsArrayTypeNode(UaNodeContext context, NodeId nodeId,
                                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                    UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SamplingIntervalDiagnosticsArrayTypeNode(UaNodeContext context, NodeId nodeId,
                                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                    UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                                    Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                                    double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public SamplingIntervalDiagnosticsTypeNode getSamplingIntervalDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SamplingIntervalDiagnostics");
        return (SamplingIntervalDiagnosticsTypeNode) component.orElse(null);
    }

    @Override
    public SamplingIntervalDiagnosticsDataType getSamplingIntervalDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SamplingIntervalDiagnostics");
        return component.map(node -> (SamplingIntervalDiagnosticsDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSamplingIntervalDiagnostics(SamplingIntervalDiagnosticsDataType value) {
        getVariableComponent("http://opcfoundation.org/UA/", "SamplingIntervalDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
