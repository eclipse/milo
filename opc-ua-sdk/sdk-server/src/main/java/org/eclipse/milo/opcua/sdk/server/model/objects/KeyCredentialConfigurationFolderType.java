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

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.methods.Out;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.util.Lazy;

public interface KeyCredentialConfigurationFolderType extends FolderType {
    MethodNode getCreateCredentialMethodNode();

    abstract class CreateCredentialMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        private final Lazy<Argument[]> outputArguments = new Lazy<>();

        public CreateCredentialMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("ResourceUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("ProfileUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("EndpointUrls", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), 1, new UInteger[]{UInteger.valueOf(0)}, new LocalizedText("", ""))
                };
            });
        }

        @Override
        public Argument[] getOutputArguments() {
            return outputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("CredentialNodeId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        protected Variant[] invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            String resourceUri = (String) inputValues[0].getValue();
            String profileUri = (String) inputValues[1].getValue();
            String[] endpointUrls = (String[]) inputValues[2].getValue();
            Out<NodeId> credentialNodeId = new Out<>();
            invoke(context, resourceUri, profileUri, endpointUrls, credentialNodeId);
            return new Variant[]{new Variant(credentialNodeId.get())};
        }

        protected abstract void invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                       String resourceUri, String profileUri, String[] endpointUrls, Out<NodeId> credentialNodeId)
            throws UaException;
    }
}
