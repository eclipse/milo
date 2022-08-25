package org.eclipse.milo.opcua.sdk.server.model.variables;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.24">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.24</a>
 */
public interface ThreeDCartesianCoordinatesType extends CartesianCoordinatesType {
    BaseDataVariableType getXNode();

    Double getX();

    void setX(Double value);

    BaseDataVariableType getYNode();

    Double getY();

    void setY(Double value);

    BaseDataVariableType getZNode();

    Double getZ();

    void setZ(Double value);
}
