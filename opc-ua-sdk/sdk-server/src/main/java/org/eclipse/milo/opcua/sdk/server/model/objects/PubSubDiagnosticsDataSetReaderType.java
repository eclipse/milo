package org.eclipse.milo.opcua.sdk.server.model.objects;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.12">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.12</a>
 */
public interface PubSubDiagnosticsDataSetReaderType extends PubSubDiagnosticsType {
    BaseObjectType getCountersNode();

    BaseObjectType getLiveValuesNode();
}
