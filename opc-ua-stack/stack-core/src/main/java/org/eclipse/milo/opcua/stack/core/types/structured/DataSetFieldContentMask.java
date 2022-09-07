package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.4/#6.2.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.4/#6.2.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class DataSetFieldContentMask extends OptionSetUInteger<DataSetFieldContentMask.Field> {
    public DataSetFieldContentMask(UInteger value) {
        super(value);
    }

    public boolean getStatusCode() {
        return get(Field.StatusCode);
    }

    public boolean getSourceTimestamp() {
        return get(Field.SourceTimestamp);
    }

    public boolean getServerTimestamp() {
        return get(Field.ServerTimestamp);
    }

    public boolean getSourcePicoSeconds() {
        return get(Field.SourcePicoSeconds);
    }

    public boolean getServerPicoSeconds() {
        return get(Field.ServerPicoSeconds);
    }

    public boolean getRawData() {
        return get(Field.RawData);
    }

    @Override
    public UInteger getValue() {
        return (UInteger) value;
    }

    @Override
    public Set<DataSetFieldContentMask.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static DataSetFieldContentMask of(DataSetFieldContentMask.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new DataSetFieldContentMask(UInteger.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        StatusCode(0),

        SourceTimestamp(1),

        ServerTimestamp(2),

        SourcePicoSeconds(3),

        ServerPicoSeconds(4),

        RawData(5);

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
