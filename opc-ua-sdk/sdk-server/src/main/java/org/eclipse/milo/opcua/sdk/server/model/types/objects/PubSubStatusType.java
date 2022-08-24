/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PubSubState;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.10/#9.1.10.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.10/#9.1.10.1</a>
 */
public interface PubSubStatusType extends BaseObjectType {
    BaseDataVariableType getStateNode();

    PubSubState getState();

    void setState(PubSubState value);

    MethodNode getEnableMethodNode();

    MethodNode getDisableMethodNode();
}
