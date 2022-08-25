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

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.11">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.11</a>
 */
public interface PubSubDiagnosticsDataSetWriterType extends PubSubDiagnosticsType {
    BaseObjectType getCountersNode();

    BaseObjectType getLiveValuesNode();
}