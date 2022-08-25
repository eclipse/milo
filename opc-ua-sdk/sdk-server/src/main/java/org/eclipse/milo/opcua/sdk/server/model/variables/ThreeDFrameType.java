package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.ThreeDCartesianCoordinates;
import org.eclipse.milo.opcua.stack.core.types.structured.ThreeDOrientation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.28">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.28</a>
 */
public interface ThreeDFrameType extends FrameType {
    ThreeDCartesianCoordinatesType getCartesianCoordinatesNode();

    ThreeDCartesianCoordinates getCartesianCoordinates();

    void setCartesianCoordinates(ThreeDCartesianCoordinates value);

    ThreeDOrientationType getOrientationNode();

    ThreeDOrientation getOrientation();

    void setOrientation(ThreeDOrientation value);
}
