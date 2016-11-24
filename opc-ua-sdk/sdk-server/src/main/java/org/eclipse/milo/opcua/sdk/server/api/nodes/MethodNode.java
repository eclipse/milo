/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.api.nodes;

public interface MethodNode extends Node {

    /**
     * The Executable attribute indicates whether the Method is executable, not taking user access rights into account.
     * If the OPC UA Server cannot get the Executable information from the underlying system, it should state that it
     * is executable.
     * <p>
     * See OPC-UA Part 3, Section 5.7.
     *
     * @return {@code true} if this method is executable, not taking user access rights into account.
     */
    Boolean isExecutable();

    /**
     * The UserExecutable attribute indicates whether the Method is executable, taking user access rights into account.
     * If the OPC UA Server cannot get any user rights related information from the underlying system, it should use
     * the same value as used in the Executable attribute.
     * <p>
     * See OPC-UA Part 3, Section 5.7.
     *
     * @return {@code true} if this method is executable, taking user access rights into account.
     */
    Boolean isUserExecutable();

    /**
     * Set the Executable attribute of this Method.
     *
     * @param executable {@code true} if the method is executable.
     */
    void setExecutable(Boolean executable);

    /**
     * Set the UserExecutable attribute of this Method.
     *
     * @param userExecutable {@code true} if the method is executable, taking access rights into account.
     */
    void setUserExecutable(Boolean userExecutable);

}
