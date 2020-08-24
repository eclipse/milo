/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;

class Util {

    private Util() {}

    public static String buildBrowseNamePath(UaNode node) {
        return buildBrowseNamePath(node, new ArrayList<>());
    }

    private static String buildBrowseNamePath(UaNode node, List<String> browseNames) {
        if (node == null || node.getNodeId().equals(Identifiers.ObjectsFolder)) {
            Collections.reverse(browseNames);

            return String.join(".", browseNames);
        }

        browseNames.add(node.getBrowseName().toParseableString());

        Optional<Reference> referenceToParent = node.getReferences().stream()
            .filter(r -> r.isInverse() && r.subtypeOf(Identifiers.HierarchicalReferences))
            .findFirst();

        Optional<UaNode> parentNode = referenceToParent
            .flatMap(r ->
                node.getNodeContext()
                    .getServer()
                    .getAddressSpaceManager()
                    .getManagedNode(r.getTargetNodeId())
            );

        return buildBrowseNamePath(parentNode.orElse(null), browseNames);
    }

}
