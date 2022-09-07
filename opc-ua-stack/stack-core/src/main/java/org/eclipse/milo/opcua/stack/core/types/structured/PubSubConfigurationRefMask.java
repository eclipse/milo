package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.7.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class PubSubConfigurationRefMask extends OptionSetUInteger<PubSubConfigurationRefMask.Field> {
    public PubSubConfigurationRefMask(UInteger value) {
        super(value);
    }

    public boolean getElementAdd() {
        return get(Field.ElementAdd);
    }

    public boolean getElementMatch() {
        return get(Field.ElementMatch);
    }

    public boolean getElementModify() {
        return get(Field.ElementModify);
    }

    public boolean getElementRemove() {
        return get(Field.ElementRemove);
    }

    public boolean getReferenceWriter() {
        return get(Field.ReferenceWriter);
    }

    public boolean getReferenceReader() {
        return get(Field.ReferenceReader);
    }

    public boolean getReferenceWriterGroup() {
        return get(Field.ReferenceWriterGroup);
    }

    public boolean getReferenceReaderGroup() {
        return get(Field.ReferenceReaderGroup);
    }

    public boolean getReferenceConnection() {
        return get(Field.ReferenceConnection);
    }

    public boolean getReferencePubDataset() {
        return get(Field.ReferencePubDataset);
    }

    public boolean getReferenceSubDataset() {
        return get(Field.ReferenceSubDataset);
    }

    public boolean getReferenceSecurityGroup() {
        return get(Field.ReferenceSecurityGroup);
    }

    public boolean getReferencePushTarget() {
        return get(Field.ReferencePushTarget);
    }

    @Override
    public UInteger getValue() {
        return (UInteger) value;
    }

    @Override
    public Set<PubSubConfigurationRefMask.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static PubSubConfigurationRefMask of(PubSubConfigurationRefMask.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new PubSubConfigurationRefMask(UInteger.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        ElementAdd(0),

        ElementMatch(1),

        ElementModify(2),

        ElementRemove(3),

        ReferenceWriter(4),

        ReferenceReader(5),

        ReferenceWriterGroup(6),

        ReferenceReaderGroup(7),

        ReferenceConnection(8),

        ReferencePubDataset(9),

        ReferenceSubDataset(10),

        ReferenceSecurityGroup(11),

        ReferencePushTarget(12);

        private final int bitIndex;

        Field(int bitIndex) {
            this.bitIndex = bitIndex;
        }

        @Override
        public int getBitIndex() {
            return bitIndex;
        }
    }
}
