package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.PasswordOptionsMask;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.UserManagementDataType;

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
}
