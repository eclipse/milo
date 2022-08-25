package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NegotiationStatus;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.3">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.3</a>
 */
public interface IIeeeAutoNegotiationStatusType extends BaseInterfaceType {
    BaseDataVariableType getNegotiationStatusNode();

    NegotiationStatus getNegotiationStatus();

    void setNegotiationStatus(NegotiationStatus value);
}
