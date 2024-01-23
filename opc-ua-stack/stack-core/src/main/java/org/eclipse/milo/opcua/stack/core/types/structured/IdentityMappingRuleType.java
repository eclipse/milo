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
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdentityCriteriaType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part18/4.4.3">https://reference.opcfoundation.org/v105/Core/docs/Part18/4.4.3</a>
 */
public abstract class IdentityMappingRuleType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15634");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15736");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15728");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15042");

    private final IdentityCriteriaType criteriaType;

    private final @Nullable String criteria;

    public IdentityMappingRuleType(IdentityCriteriaType criteriaType, @Nullable String criteria) {
        this.criteriaType = criteriaType;
        this.criteria = criteria;
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

    public IdentityCriteriaType getCriteriaType() {
        return criteriaType;
    }

    public @Nullable String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", IdentityMappingRuleType.class.getSimpleName() + "[", "]");
        joiner.add("criteriaType=" + getCriteriaType());
        joiner.add("criteria='" + getCriteria() + "'");
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15736),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("CriteriaType", LocalizedText.NULL_VALUE, new NodeId(0, 15632), -1, null, UInteger.valueOf(0), false),
                new StructureField("Criteria", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }
}
