package org.eclipse.milo.opcua.sdk.server.model.objects;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.7">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.7</a>
 */
public interface PubSubDiagnosticsRootType extends PubSubDiagnosticsType {
    BaseObjectType getLiveValuesNode();
}
