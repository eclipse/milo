package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;

public interface BuildInfoType extends BaseDataVariableType {
    BaseDataVariableType getProductUriNode();

    String getProductUri();

    void setProductUri(String value);

    BaseDataVariableType getManufacturerNameNode();

    String getManufacturerName();

    void setManufacturerName(String value);

    BaseDataVariableType getProductNameNode();

    String getProductName();

    void setProductName(String value);

    BaseDataVariableType getSoftwareVersionNode();

    String getSoftwareVersion();

    void setSoftwareVersion(String value);

    BaseDataVariableType getBuildNumberNode();

    String getBuildNumber();

    void setBuildNumber(String value);

    BaseDataVariableType getBuildDateNode();

    DateTime getBuildDate();

    void setBuildDate(DateTime value);
}
