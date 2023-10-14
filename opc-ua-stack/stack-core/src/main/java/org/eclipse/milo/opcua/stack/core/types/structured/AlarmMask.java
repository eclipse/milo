/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUI16;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/8.3">https://reference.opcfoundation.org/v105/Core/docs/Part9/8.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class AlarmMask extends OptionSetUI16<AlarmMask.Field> {
    public AlarmMask(UShort value) {
        super(value);
    }

    public boolean getActive() {
        return get(Field.Active);
    }

    public boolean getUnacknowledged() {
        return get(Field.Unacknowledged);
    }

    public boolean getUnconfirmed() {
        return get(Field.Unconfirmed);
    }

    @Override
    public UShort getValue() {
        return (UShort) value;
    }

    @Override
    public Set<Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static AlarmMask of(Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new AlarmMask(UShort.valueOf(bits));
    }

    public enum Field implements BitIndex {
        Active(0),

        Unacknowledged(1),

        Unconfirmed(2);

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
