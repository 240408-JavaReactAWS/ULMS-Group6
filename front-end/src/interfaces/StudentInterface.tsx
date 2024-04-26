import Grade from "./GradeInterface";
import User from "./UserInterface";

interface Student {
    user: User;
    grades: Grade[];
}

export default Student;