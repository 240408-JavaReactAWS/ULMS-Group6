
import Role from './RoleInterface';

interface User {
    userId: number ;
    firstName: string;
    lastName: string;
    username: string;
    role: Role;
    password: string;
}

export default User;