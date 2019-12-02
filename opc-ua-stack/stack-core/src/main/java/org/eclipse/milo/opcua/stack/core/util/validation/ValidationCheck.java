/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util.validation;

import java.util.EnumSet;

import com.google.common.collect.ImmutableSet;

/**
 * Validation checks that are allowed to be suppressed (i.e. they are optional) according to the spec.
 * <p>
 * See OPC UA (v1.03) Part 4, Section 6.1.3.
 */
public enum ValidationCheck {

    /**
     * Check that the end-entity certificate contains a particular hostname or IP address in its SANs.
     * <p>
     * This check is only done in clients, against the server they are connecting to.
     */
    HOSTNAME,

    /**
     * Certificate validity and expiration is checked.
     */
    VALIDITY,

    /**
     * The KeyUsage extension must be present and checked for end-entity certificates.
     */
    KEY_USAGE_END_ENTITY,

    /**
     * The ExtendedKeyUsage extension must be present and checked for end-entity certificates.
     */
    EXTENDED_KEY_USAGE_END_ENTITY,

    /**
     * The KeyUsage extension must be presented and checked for CA certificates.
     * <p>
     * This check does not apply to self-signed end-entity certificates.
     */
    KEY_USAGE_ISSUER,

    /**
     * Revocation checking must happen.
     */
    REVOCATION_CHECK,

    /**
     * CRLs for every non-end-entity must CA must be found.
     */
    REVOCATION_LIST_FOUND;

    /**
     * An empty set of {@link ValidationCheck}s.
     */
    public static final ImmutableSet<ValidationCheck> NO_OPTIONAL_CHECKS = ImmutableSet.of();

    /**
     * A set the includes all optional {@link ValidationCheck}s.
     */
    public static final ImmutableSet<ValidationCheck> ALL_OPTIONAL_CHECKS =
        ImmutableSet.copyOf(EnumSet.allOf(ValidationCheck.class));

}
