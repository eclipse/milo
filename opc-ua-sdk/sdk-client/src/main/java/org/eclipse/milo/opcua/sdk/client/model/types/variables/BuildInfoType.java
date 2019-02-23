/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface BuildInfoType extends BaseDataVariableType {
    CompletableFuture<? extends BaseDataVariableType> getProductUriNode();

    CompletableFuture<String> getProductUri();

    CompletableFuture<StatusCode> setProductUri(String value);

    CompletableFuture<? extends BaseDataVariableType> getManufacturerNameNode();

    CompletableFuture<String> getManufacturerName();

    CompletableFuture<StatusCode> setManufacturerName(String value);

    CompletableFuture<? extends BaseDataVariableType> getProductNameNode();

    CompletableFuture<String> getProductName();

    CompletableFuture<StatusCode> setProductName(String value);

    CompletableFuture<? extends BaseDataVariableType> getSoftwareVersionNode();

    CompletableFuture<String> getSoftwareVersion();

    CompletableFuture<StatusCode> setSoftwareVersion(String value);

    CompletableFuture<? extends BaseDataVariableType> getBuildNumberNode();

    CompletableFuture<String> getBuildNumber();

    CompletableFuture<StatusCode> setBuildNumber(String value);

    CompletableFuture<? extends BaseDataVariableType> getBuildDateNode();

    CompletableFuture<DateTime> getBuildDate();

    CompletableFuture<StatusCode> setBuildDate(DateTime value);
}
