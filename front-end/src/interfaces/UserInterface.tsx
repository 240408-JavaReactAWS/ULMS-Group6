// Type: Interface

import Role from "./RoleInterface";

export default interface User {
    userId: number ;
    firstName: string;
    lastName: string;
    username: string;
    role: Role;
    password: string;
    enrolledCourses: number[];
    taughtCourses: number[];
}