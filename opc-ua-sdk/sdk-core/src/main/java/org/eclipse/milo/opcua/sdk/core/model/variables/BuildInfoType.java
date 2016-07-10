/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;


public interface BuildInfoType extends BaseDataVariableType {


    String getProductUri();

    BaseDataVariableType getProductUriNode();

    void setProductUri(String value);

    String getManufacturerName();

    BaseDataVariableType getManufacturerNameNode();

    void setManufacturerName(String value);

    String getProductName();

    BaseDataVariableType getProductNameNode();

    void setProductName(String value);

    String getSoftwareVersion();

    BaseDataVariableType getSoftwareVersionNode();

    void setSoftwareVersion(String value);

    String getBuildNumber();

    BaseDataVariableType getBuildNumberNode();

    void setBuildNumber(String value);

    DateTime getBuildDate();

    BaseDataVariableType getBuildDateNode();

    void setBuildDate(DateTime value);
}
