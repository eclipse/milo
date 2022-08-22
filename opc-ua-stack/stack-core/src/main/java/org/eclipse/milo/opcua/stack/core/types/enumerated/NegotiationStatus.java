package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.4">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.4</a>
 */
public enum NegotiationStatus implements UaEnumeration {
    /**
     * The auto-negotiation protocol is running and negotiation is currently in-progress.
     */
    InProgress(0),

    /**
     * The auto-negotiation protocol has completed successfully.
     */
    Complete(1),

    /**
     * The auto-negotiation protocol has failed.
     */
    Failed(2),

    /**
     * The auto-negotiation status is not currently known, this could be because it is still negotiating or the protocol cannot run (e.g., if no medium is present).
     */
    Unknown(3),

    /**
     * No auto-negotiation is executed. The auto-negotiation function is either not supported on this interface or has not been enabled.
     */
    NoNegotiation(4);

    private final int value;

    NegotiationStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=24216");
    }

    public static @Nullable NegotiationStatus from(int value) {
        switch (value) {
            case 0:
                return InProgress;
            case 1:
                return Complete;
            case 2:
                return Failed;
            case 3:
                return Unknown;
            case 4:
                return NoNegotiation;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, new LocalizedText("", "The auto-negotiation protocol is running and negotiation is currently in-progress."), "InProgress"),
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "The auto-negotiation protocol has completed successfully."), "Complete"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "The auto-negotiation protocol has failed."), "Failed"),
            new EnumField(3L, LocalizedText.NULL_VALUE, new LocalizedText("", "The auto-negotiation status is not currently known, this could be because it is still negotiating or the protocol cannot run (e.g., if no medium is present)."), "Unknown"),
            new EnumField(4L, LocalizedText.NULL_VALUE, new LocalizedText("", "No auto-negotiation is executed. The auto-negotiation function is either not supported on this interface or has not been enabled."), "NoNegotiation")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<NegotiationStatus> {
        @Override
        public Class<NegotiationStatus> getType() {
            return NegotiationStatus.class;
        }

        @Override
        public NegotiationStatus decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.NegotiationStatus.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NegotiationStatus value) {
            encoder.writeEnum(null, value);
        }
    }
}
