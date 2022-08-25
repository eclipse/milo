package org.eclipse.milo.opcua.sdk.server.model.variables;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.26">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.26</a>
 */
public interface ThreeDOrientationType extends OrientationType {
    BaseDataVariableType getANode();

    Double getA();

    void setA(Double value);

    BaseDataVariableType getBNode();

    Double getB();

    void setB(Double value);

    BaseDataVariableType getCNode();

    Double getC();

    void setC(Double value);
}
