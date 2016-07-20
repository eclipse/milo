package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;

/**
 * Base type for the UA unsigned types.
 */
public abstract class UnsignedNumber extends Number {

    /**
     * @return the value this {@link UnsignedNumber} as a {@link BigInteger}.
     */
    public abstract BigInteger bigIntegerValue();

}
