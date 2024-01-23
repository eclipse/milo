/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.lang.Override;
import java.lang.String;
import java.util.StringJoiner;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15</a>
 */
public abstract class UserIdentityToken extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=316");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=318");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=317");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15140");

    private final @Nullable String policyId;

    public UserIdentityToken(@Nullable String policyId) {
        this.policyId = policyId;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public @Nullable String getPolicyId() {
        return policyId;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", UserIdentityToken.class.getSimpleName() + "[", "]");
        joiner.add("policyId='" + getPolicyId() + "'");
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 318),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PolicyId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }
}
