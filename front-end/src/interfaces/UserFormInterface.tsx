import Role from './RoleInterface';
interface UserForm {
    firstName: string;
    lastName: string;
    username: string;
    role: Role;
    password: string;
}

export default UserForm;