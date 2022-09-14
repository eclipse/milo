/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public interface DataTypeManager {

    void registerType(
        NodeId dataTypeId,
        DataTypeCodec codec,
        @Nullable NodeId binaryEncodingId,
        @Nullable NodeId xmlEncodingId,
        @Nullable NodeId jsonEncodingId
    );

    @Nullable DataTypeCodec getCodec(NodeId encodingId);

    @Nullable DataTypeCodec getCodec(QualifiedName encodingName, NodeId dataTypeId);

    @Nullable NodeId getBinaryEncodingId(NodeId dataTypeId);

    @Nullable NodeId getXmlEncodingId(NodeId dataTypeId);

    @Nullable NodeId getJsonEncodingId(NodeId dataTypeId);

    @Nullable DataTypeDictionary2 getTypeDictionary(String namespaceUri);

    void registerTypeDictionary(DataTypeDictionary2 dictionary);

}
