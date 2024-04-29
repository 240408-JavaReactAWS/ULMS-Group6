import React, { useState, useEffect, FormEvent } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import User from '../../Interfaces/UserInterface';

function CourseManage() {
  const { courseId } = useParams<{ courseId: string }>();
  const courseIdNumber = Number(courseId);
  const [teacher, setTeacher] = useState<User | null>(null);
  const [students, setStudents] = useState<User[]>([]);
  const [teacherId, setTeacherId] = useState<number>(0);
  const [studentId, setStudentId] = useState<number>(0);
  const [showAddStudentForm, setShowAddStudentForm] = useState<boolean>(false);

  useEffect(() => {
    axios.get<User>(`http://localhost:8080/courses/${courseIdNumber}/teacher`)
      .then(response => {
        if (response.data) {
          setTeacher(response.data);
        }
      });

    axios.get<User[]>(`http://localhost:8080/courses/${courseIdNumber}/students`)
      .then(response => {
        setStudents(response.data);
      });
  }, [courseId]);

  const assignTeacher = async (event: FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.put<User>(`http://localhost:8080/courses/assignTeacher/${courseIdNumber}/${teacherId}`);
      setTeacher(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  
  const assignStudent = async (event: FormEvent) => {
    event.preventDefault();
    if (students.some(student => student.userId === studentId)) {
      alert('Student is already in the course');
    } else {
      try {
        const response = await axios.put<User>(`http://localhost:8080/courses/assignStudent/${courseIdNumber}/${studentId}`);
        setStudents(prevStudents => [...prevStudents, response.data]);
      } catch (error) {
        console.error(error);
      }
    }
   
  };
  const deleteStudent = (id: number) => {
    axios.delete(`http://localhost:8080/courses/removeStudent/${courseIdNumber}/${id}`)
      .then(() => {
        setStudents(prevStudents => prevStudents.filter(student => student.userId !== id));
      });
  };

  return (
    <div>
      <h1>Course Manage</h1>
      {teacher ? (
        <div>{teacher.firstName} {teacher.lastName}</div>
      ) : (
        <form onSubmit={assignTeacher}>
          <input type="text" value={teacherId} onChange={e => setTeacherId(Number(e.target.value))} />
          <button type="submit">Assign Teacher</button>
        </form>
      )}
      <button onClick={() => setShowAddStudentForm(!showAddStudentForm)}>
        {showAddStudentForm ? 'Hide Add Student Form' : 'Show Add Student Form'}
      </button>
      {showAddStudentForm && (
        <form onSubmit={assignStudent}>
          <input type="text" value={studentId} onChange={e => setStudentId(Number(e.target.value))} />
          <button type="submit">Add Student</button>
        </form>
      )}
      <table>
        <thead>
            <tr>
            <th>Student ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>Action</th>
            </tr>
        </thead>
        <tbody>
            {students.map((student, index) => (
              <tr key={student.userId}>
                <td>{student.userId}</td>
                <td>{student.firstName}</td>
                <td>{student.lastName}</td>
                <td>{student.username}</td>
                <td><button onClick={() => deleteStudent(student.userId)}>Delete</button></td>
              </tr>
            ))}
        </tbody>
        </table>
    </div>
  );
}

export default CourseManage;