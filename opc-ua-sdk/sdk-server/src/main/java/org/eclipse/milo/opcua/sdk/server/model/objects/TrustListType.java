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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.util.Lazy;

public interface TrustListType extends FileType {
    QualifiedProperty<DateTime> LAST_UPDATE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastUpdateTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        -1,
        DateTime.class
    );

    QualifiedProperty<Double> UPDATE_FREQUENCY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdateFrequency",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    DateTime getLastUpdateTime();

    void setLastUpdateTime(DateTime value);

    PropertyType getLastUpdateTimeNode();

    Double getUpdateFrequency();

    void setUpdateFrequency(Double value);

    PropertyType getUpdateFrequencyNode();

    MethodNode getOpenWithMasksMethodNode();

    MethodNode getCloseAndUpdateMethodNode();

    MethodNode getAddCertificateMethodNode();

    MethodNode getRemoveCertificateMethodNode();

    abstract class OpenWithMasksMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        private final Lazy<Argument[]> outputArguments = new Lazy<>();

        public OpenWithMasksMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("Masks", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        public Argument[] getOutputArguments() {
            return outputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("FileHandle", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        protected Variant[] invoke(InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            UInteger masks = (UInteger) inputValues[0].getValue();
            Out<UInteger> fileHandle = new Out<>();
            invoke(context, masks, fileHandle);
            return new Variant[]{new Variant(fileHandle.get())};
        }

        protected abstract void invoke(InvocationContext context,
                                       UInteger masks, Out<UInteger> fileHandle) throws UaException;
    }

    abstract class CloseAndUpdateMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        private final Lazy<Argument[]> outputArguments = new Lazy<>();

        public CloseAndUpdateMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("FileHandle", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
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
        protected Variant[] invoke(InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            UInteger fileHandle = (UInteger) inputValues[0].getValue();
            Out<Boolean> applyChangesRequired = new Out<>();
            invoke(context, fileHandle, applyChangesRequired);
            return new Variant[]{new Variant(applyChangesRequired.get())};
        }

        protected abstract void invoke(InvocationContext context,
                                       UInteger fileHandle, Out<Boolean> applyChangesRequired) throws UaException;
    }

    abstract class AddCertificateMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        public AddCertificateMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("Certificate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("IsTrustedCertificate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        public Argument[] getOutputArguments() {
            return new Argument[]{};
        }

        @Override
        protected Variant[] invoke(InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            ByteString certificate = (ByteString) inputValues[0].getValue();
            Boolean isTrustedCertificate = (Boolean) inputValues[1].getValue();
            invoke(context, certificate, isTrustedCertificate);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context,
                                       ByteString certificate, Boolean isTrustedCertificate) throws UaException;
    }

    abstract class RemoveCertificateMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        public RemoveCertificateMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("Thumbprint", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("IsTrustedCertificate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
                };
            });
        }

        @Override
        public Argument[] getOutputArguments() {
            return new Argument[]{};
        }

        @Override
        protected Variant[] invoke(InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            String thumbprint = (String) inputValues[0].getValue();
            Boolean isTrustedCertificate = (Boolean) inputValues[1].getValue();
            invoke(context, thumbprint, isTrustedCertificate);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context,
                                       String thumbprint, Boolean isTrustedCertificate) throws UaException;
    }
}
