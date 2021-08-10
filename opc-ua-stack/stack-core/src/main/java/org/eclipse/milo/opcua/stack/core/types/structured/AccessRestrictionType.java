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
public class AccessRestrictionType extends OptionSetUInteger<AccessRestrictionType.Field> {
    public AccessRestrictionType(UInteger value) {
        super(value);
    }

    public boolean getSigningRequired() {
        return get(Field.SigningRequired);
    }

    public boolean getEncryptionRequired() {
        return get(Field.EncryptionRequired);
    }

    public boolean getSessionRequired() {
        return get(Field.SessionRequired);
    }

    @Override
    public UInteger getValue() {
        return (UInteger) value;
    }

    @Override
    public Set<AccessRestrictionType.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static AccessRestrictionType of(AccessRestrictionType.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new AccessRestrictionType(UInteger.valueOf(bits));
    }

    public static AccessRestrictionType of(Collection<AccessRestrictionType.Field> fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new AccessRestrictionType(UInteger.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        SigningRequired(0),

        EncryptionRequired(1),

        SessionRequired(2);

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
