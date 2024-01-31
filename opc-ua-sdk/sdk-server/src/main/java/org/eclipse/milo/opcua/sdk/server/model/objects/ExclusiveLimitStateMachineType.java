/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.objects;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.19/#5.8.19.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.19/#5.8.19.2</a>
 */
public interface ExclusiveLimitStateMachineType extends FiniteStateMachineType {
    StateType getHighHighNode();

    StateType getHighNode();

    StateType getLowNode();

    StateType getLowLowNode();

    TransitionType getLowLowToLowNode();

    TransitionType getLowToLowLowNode();

    TransitionType getHighHighToHighNode();

    TransitionType getHighToHighHighNode();
}