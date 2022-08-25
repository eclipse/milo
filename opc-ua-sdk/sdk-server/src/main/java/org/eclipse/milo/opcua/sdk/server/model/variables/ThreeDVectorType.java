package org.eclipse.milo.opcua.sdk.server.model.variables;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.22">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.22</a>
 */
public interface ThreeDVectorType extends VectorType {
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
