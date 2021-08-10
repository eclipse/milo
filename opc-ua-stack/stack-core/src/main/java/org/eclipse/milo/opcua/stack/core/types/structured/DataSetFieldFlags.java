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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class DataSetFieldFlags extends OptionSetUInteger<DataSetFieldFlags.Field> {
    public DataSetFieldFlags(UShort value) {
        super(value);
    }

    public boolean getPromotedField() {
        return get(Field.PromotedField);
    }

    @Override
    public UShort getValue() {
        return (UShort) value;
    }

    @Override
    public Set<DataSetFieldFlags.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static DataSetFieldFlags of(DataSetFieldFlags.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new DataSetFieldFlags(UShort.valueOf(bits));
    }

    public static DataSetFieldFlags of(Collection<DataSetFieldFlags.Field> fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new DataSetFieldFlags(UShort.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        PromotedField(0);

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
