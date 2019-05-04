/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

public class BuiltinDataTypeDictionary {

    public static OpcUaBinaryDataTypeDictionary getBinaryInstance() {
        return InstanceHolder.BINARY_INSTANCE;
    }

    public static OpcUaXmlDataTypeDictionary getXmlInstance() {
        return InstanceHolder.XML_INSTANCE;
    }

    private static class InstanceHolder {

        private static final OpcUaBinaryDataTypeDictionary BINARY_INSTANCE =
            new OpcUaBinaryDataTypeDictionary(Namespaces.OPC_UA);

        private static final OpcUaXmlDataTypeDictionary XML_INSTANCE =
            new OpcUaXmlDataTypeDictionary(Namespaces.OPC_UA_XSD);

        static {
            TypeInitializer.initialize(
                new NamespaceTable(),
                BINARY_INSTANCE,
                XML_INSTANCE
            );
        }

    }

}
