package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.methods.Out;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.util.Lazy;

public interface ServerConfigurationType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_CAPABILITIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerCapabilities",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<String[]> SUPPORTED_PRIVATE_KEY_FORMATS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SupportedPrivateKeyFormats",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<UInteger> MAX_TRUST_LIST_SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTrustListSize",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<Boolean> MULTICAST_DNS_ENABLED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MulticastDnsEnabled",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    String[] getServerCapabilities();

    void setServerCapabilities(String[] value);

    PropertyType getServerCapabilitiesNode();

    String[] getSupportedPrivateKeyFormats();

    void setSupportedPrivateKeyFormats(String[] value);

    PropertyType getSupportedPrivateKeyFormatsNode();

    UInteger getMaxTrustListSize();

    void setMaxTrustListSize(UInteger value);

    PropertyType getMaxTrustListSizeNode();

    Boolean getMulticastDnsEnabled();

    void setMulticastDnsEnabled(Boolean value);

    PropertyType getMulticastDnsEnabledNode();

    CertificateGroupFolderType getCertificateGroupsNode();

    MethodNode getUpdateCertificateMethodNode();

    MethodNode getApplyChangesMethodNode();

    MethodNode getCreateSigningRequestMethodNode();

    MethodNode getGetRejectedListMethodNode();

    abstract class UpdateCertificateMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        private final Lazy<Argument[]> outputArguments = new Lazy<>();

        public UpdateCertificateMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("CertificateGroupId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("CertificateTypeId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Certificate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("IssuerCertificates", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), 1, new UInteger[]{UInteger.valueOf(0)}, new LocalizedText("", "")),
                    new Argument("PrivateKeyFormat", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("PrivateKey", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        public Argument[] getOutputArguments() {
            return outputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("ApplyChangesRequired", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        protected Variant[] invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            NodeId certificateGroupId = (NodeId) inputValues[0].getValue();
            NodeId certificateTypeId = (NodeId) inputValues[1].getValue();
            ByteString certificate = (ByteString) inputValues[2].getValue();
            ByteString[] issuerCertificates = (ByteString[]) inputValues[3].getValue();
            String privateKeyFormat = (String) inputValues[4].getValue();
            ByteString privateKey = (ByteString) inputValues[5].getValue();
            Out<Boolean> applyChangesRequired = new Out<>();
            invoke(context, certificateGroupId, certificateTypeId, certificate, issuerCertificates, privateKeyFormat, privateKey, applyChangesRequired);
            return new Variant[]{new Variant(applyChangesRequired.get())};
        }

        protected abstract void invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                       NodeId certificateGroupId, NodeId certificateTypeId, ByteString certificate,
                                       ByteString[] issuerCertificates, String privateKeyFormat, ByteString privateKey,
                                       Out<Boolean> applyChangesRequired) throws UaException;
    }

    abstract class ApplyChangesMethod extends AbstractMethodInvocationHandler {
        public ApplyChangesMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return new Argument[]{};
        }

        @Override
        public Argument[] getOutputArguments() {
            return new Argument[]{};
        }

        @Override
        protected Variant[] invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            invoke(context);
            return new Variant[]{};
        }

        protected abstract void invoke(AbstractMethodInvocationHandler.InvocationContext context) throws
            UaException;
    }

    abstract class CreateSigningRequestMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        private final Lazy<Argument[]> outputArguments = new Lazy<>();

        public CreateSigningRequestMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("CertificateGroupId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("CertificateTypeId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("SubjectName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("RegeneratePrivateKey", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Nonce", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        public Argument[] getOutputArguments() {
            return outputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("CertificateRequest", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        protected Variant[] invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            NodeId certificateGroupId = (NodeId) inputValues[0].getValue();
            NodeId certificateTypeId = (NodeId) inputValues[1].getValue();
            String subjectName = (String) inputValues[2].getValue();
            Boolean regeneratePrivateKey = (Boolean) inputValues[3].getValue();
            ByteString nonce = (ByteString) inputValues[4].getValue();
            Out<ByteString> certificateRequest = new Out<>();
            invoke(context, certificateGroupId, certificateTypeId, subjectName, regeneratePrivateKey, nonce, certificateRequest);
            return new Variant[]{new Variant(certificateRequest.get())};
        }

        protected abstract void invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                       NodeId certificateGroupId, NodeId certificateTypeId, String subjectName,
                                       Boolean regeneratePrivateKey, ByteString nonce, Out<ByteString> certificateRequest) throws
            UaException;
    }

    abstract class GetRejectedListMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> outputArguments = new Lazy<>();

        public GetRejectedListMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return new Argument[]{};
        }

        @Override
        public Argument[] getOutputArguments() {
            return outputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("Certificates", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), 1, new UInteger[]{UInteger.valueOf(0)}, new LocalizedText("", ""))
                };
            });
        }

        @Override
        protected Variant[] invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            Out<ByteString[]> certificates = new Out<>();
            invoke(context, certificates);
            return new Variant[]{new Variant(certificates.get())};
        }

        protected abstract void invoke(AbstractMethodInvocationHandler.InvocationContext context,
                                       Out<ByteString[]> certificates) throws UaException;
    }
}
