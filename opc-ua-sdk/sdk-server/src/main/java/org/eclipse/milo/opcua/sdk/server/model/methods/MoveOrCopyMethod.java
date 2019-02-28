/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.methods;

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class MoveOrCopyMethod extends AbstractMethodInvocationHandler {
    public static final Argument OBJECT_TO_MOVE_OR_COPY = new Argument(
        "ObjectToMoveOrCopy",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument TARGET_DIRECTORY = new Argument(
        "TargetDirectory",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument CREATE_COPY = new Argument(
        "CreateCopy",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument NEW_NAME = new Argument(
        "NewName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument NEW_NODE_ID = new Argument(
        "NewNodeId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public MoveOrCopyMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{OBJECT_TO_MOVE_OR_COPY, TARGET_DIRECTORY, CREATE_COPY, NEW_NAME};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{NEW_NODE_ID};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        NodeId objectToMoveOrCopy = (NodeId) inputValues[0].getValue();
        NodeId targetDirectory = (NodeId) inputValues[1].getValue();
        Boolean createCopy = (Boolean) inputValues[2].getValue();
        String newName = (String) inputValues[3].getValue();
        AtomicReference<NodeId> newNodeId = new AtomicReference<NodeId>();
        invoke(context, objectToMoveOrCopy, targetDirectory, createCopy, newName, newNodeId);
        return new Variant[]{new Variant(newNodeId.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   NodeId objectToMoveOrCopy, NodeId targetDirectory, Boolean createCopy, String newName,
                                   AtomicReference<NodeId> newNodeId) throws UaException;
}
