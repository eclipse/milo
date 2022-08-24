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

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part20/4.4.6">https://reference.opcfoundation.org/v105/Core/docs/Part20/4.4.6</a>
 */
public interface FileTransferStateMachineType extends FiniteStateMachineType {
    InitialStateType getIdleNode();

    StateType getReadPrepareNode();

    StateType getReadTransferNode();

    StateType getApplyWriteNode();

    StateType getErrorNode();

    TransitionType getIdleToReadPrepareNode();

    TransitionType getReadPrepareToReadTransferNode();

    TransitionType getReadTransferToIdleNode();

    TransitionType getIdleToApplyWriteNode();

    TransitionType getApplyWriteToIdleNode();

    TransitionType getReadPrepareToErrorNode();

    TransitionType getReadTransferToErrorNode();

    TransitionType getApplyWriteToErrorNode();

    TransitionType getErrorToIdleNode();

    MethodNode getResetMethodNode();
}
