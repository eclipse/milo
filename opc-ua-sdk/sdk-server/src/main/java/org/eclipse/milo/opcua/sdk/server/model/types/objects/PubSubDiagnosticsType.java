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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PubSubDiagnosticsCounterType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DiagnosticsLevel;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.2</a>
 */
public interface PubSubDiagnosticsType extends BaseObjectType {
    BaseDataVariableType getDiagnosticsLevelNode();

    DiagnosticsLevel getDiagnosticsLevel();

    void setDiagnosticsLevel(DiagnosticsLevel value);

    PubSubDiagnosticsCounterType getTotalInformationNode();

    UInteger getTotalInformation();

    void setTotalInformation(UInteger value);

    PubSubDiagnosticsCounterType getTotalErrorNode();

    UInteger getTotalError();

    void setTotalError(UInteger value);

    MethodNode getResetMethodNode();

    BaseDataVariableType getSubErrorNode();

    Boolean getSubError();

    void setSubError(Boolean value);

    BaseObjectType getCountersNode();

    BaseObjectType getLiveValuesNode();
}
