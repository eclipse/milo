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

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class JsonDataSetMessageContentMask extends OptionSetUInteger<JsonDataSetMessageContentMask.Field> {
    public JsonDataSetMessageContentMask(UInteger value) {
        super(value);
    }

    public boolean getDataSetWriterId() {
        return get(Field.DataSetWriterId);
    }

    public boolean getMetaDataVersion() {
        return get(Field.MetaDataVersion);
    }

    public boolean getSequenceNumber() {
        return get(Field.SequenceNumber);
    }

    public boolean getTimestamp() {
        return get(Field.Timestamp);
    }

    public boolean getStatus() {
        return get(Field.Status);
    }

    @Override
    public UInteger getValue() {
        return (UInteger) value;
    }

    @Override
    public Set<JsonDataSetMessageContentMask.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static JsonDataSetMessageContentMask of(JsonDataSetMessageContentMask.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new JsonDataSetMessageContentMask(UInteger.valueOf(bits));
    }

    public static JsonDataSetMessageContentMask of(
        Collection<JsonDataSetMessageContentMask.Field> fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new JsonDataSetMessageContentMask(UInteger.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        DataSetWriterId(0),

        MetaDataVersion(1),

        SequenceNumber(2),

        Timestamp(3),

        Status(4);

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
