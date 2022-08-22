package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public abstract class UserIdentityToken extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=316");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=318");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=317");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15140");

    private final String policyId;

    public UserIdentityToken(String policyId) {
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

    public String getPolicyId() {
        return policyId;
    }
}
