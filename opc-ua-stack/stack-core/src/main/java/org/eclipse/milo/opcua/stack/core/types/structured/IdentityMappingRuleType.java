/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdentityCriteriaType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class IdentityMappingRuleType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15634");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15736");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15728");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15042");

    private final IdentityCriteriaType criteriaType;

    private final String criteria;

    public IdentityMappingRuleType(IdentityCriteriaType criteriaType, String criteria) {
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

    public String getCriteria() {
        return criteria;
    }

    public static final class Codec extends GenericDataTypeCodec<IdentityMappingRuleType> {
        @Override
        public Class<IdentityMappingRuleType> getType() {
            return IdentityMappingRuleType.class;
        }

        @Override
        public IdentityMappingRuleType decode(SerializationContext context, UaDecoder decoder) {
            IdentityCriteriaType criteriaType = decoder.readEnum("CriteriaType", IdentityCriteriaType.class);
            String criteria = decoder.readString("Criteria");
            return new IdentityMappingRuleType(criteriaType, criteria);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           IdentityMappingRuleType value) {
            encoder.writeEnum("CriteriaType", value.getCriteriaType());
            encoder.writeString("Criteria", value.getCriteria());
        }
    }
}
