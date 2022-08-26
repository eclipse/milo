package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.PasswordOptionsMask;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.UserConfigurationMask;
import org.eclipse.milo.opcua.stack.core.types.structured.UserManagementDataType;
import org.eclipse.milo.opcua.stack.core.util.Lazy;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part18/5.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part18/5.2.1</a>
 */
public interface UserManagementType extends BaseObjectType {
    QualifiedProperty<UserManagementDataType[]> USERS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Users",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24281"),
        1,
        UserManagementDataType[].class
    );

    QualifiedProperty<Range> PASSWORD_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PasswordLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=884"),
        -1,
        Range.class
    );

    QualifiedProperty<PasswordOptionsMask> PASSWORD_OPTIONS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PasswordOptions",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24277"),
        -1,
        PasswordOptionsMask.class
    );

    QualifiedProperty<LocalizedText> PASSWORD_RESTRICTIONS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PasswordRestrictions",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    UserManagementDataType[] getUsers();

    void setUsers(UserManagementDataType[] value);

    PropertyType getUsersNode();

    Range getPasswordLength();

    void setPasswordLength(Range value);

    PropertyType getPasswordLengthNode();

    PasswordOptionsMask getPasswordOptions();

    void setPasswordOptions(PasswordOptionsMask value);

    PropertyType getPasswordOptionsNode();

    LocalizedText getPasswordRestrictions();

    void setPasswordRestrictions(LocalizedText value);

    PropertyType getPasswordRestrictionsNode();

    MethodNode getAddUserMethodNode();

    MethodNode getModifyUserMethodNode();

    MethodNode getRemoveUserMethodNode();

    MethodNode getChangePasswordMethodNode();

    abstract class AddUserMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        public AddUserMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("UserName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Password", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("UserConfiguration", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24279").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Description", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
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
            String userName = (String) inputValues[0].getValue();
            String password = (String) inputValues[1].getValue();
            UserConfigurationMask userConfiguration = (UserConfigurationMask) inputValues[2].getValue();
            String description = (String) inputValues[3].getValue();
            invoke(context, userName, password, userConfiguration, description);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context,
                                       String userName, String password, UserConfigurationMask userConfiguration,
                                       String description) throws UaException;
    }

    abstract class ModifyUserMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        public ModifyUserMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("UserName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Password", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("UserConfiguration", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24279").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Description", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
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
            String userName = (String) inputValues[0].getValue();
            String password = (String) inputValues[1].getValue();
            UserConfigurationMask userConfiguration = (UserConfigurationMask) inputValues[2].getValue();
            String description = (String) inputValues[3].getValue();
            invoke(context, userName, password, userConfiguration, description);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context,
                                       String userName, String password, UserConfigurationMask userConfiguration,
                                       String description) throws UaException;
    }

    abstract class RemoveUserMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        public RemoveUserMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("UserName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Password", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("UserConfiguration", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24279").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Description", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
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
            String userName = (String) inputValues[0].getValue();
            String password = (String) inputValues[1].getValue();
            UserConfigurationMask userConfiguration = (UserConfigurationMask) inputValues[2].getValue();
            String description = (String) inputValues[3].getValue();
            invoke(context, userName, password, userConfiguration, description);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context,
                                       String userName, String password, UserConfigurationMask userConfiguration,
                                       String description) throws UaException;
    }

    abstract class ChangePasswordMethod extends AbstractMethodInvocationHandler {
        private final Lazy<Argument[]> inputArguments = new Lazy<>();

        public ChangePasswordMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return inputArguments.getOrCompute(() -> {
                NamespaceTable namespaceTable = getNode().getNodeContext().getNamespaceTable();

                return new Argument[]{
                    new Argument("UserName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Password", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("UserConfiguration", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24279").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", "")),
                    new Argument("Description", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12").toNodeId(namespaceTable).orElseThrow(), -1, null, new LocalizedText("", ""))
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
            String userName = (String) inputValues[0].getValue();
            String password = (String) inputValues[1].getValue();
            UserConfigurationMask userConfiguration = (UserConfigurationMask) inputValues[2].getValue();
            String description = (String) inputValues[3].getValue();
            invoke(context, userName, password, userConfiguration, description);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context,
                                       String userName, String password, UserConfigurationMask userConfiguration,
                                       String description) throws UaException;
    }
}
