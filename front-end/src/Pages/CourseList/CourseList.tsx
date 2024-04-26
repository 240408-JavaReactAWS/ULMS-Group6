import React, { useEffect, useState } from "react";
import axios from 'axios';
import Course from "../../interfaces/CourseInterface";
import CourseForm from "../../interfaces/CourseFormInterface";
import { Link } from 'react-router-dom';


function CourseList() {
    const [courses, setCourses] = useState<Course[]>([]);
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
  
    useEffect(() => {
      axios.get('http://localhost:8080/courses')
        .then(response => {
          console.log(response.data);
          setCourses(response.data);
        })
        .catch(error => {
          console.error('There was an error!', error);
        });
    }, []);
  
    const deleteCourse = (courseId : number) => {
      axios.delete(`http://localhost:8080/courses/deleteCourse/${courseId}`)
        .then(response => {
          setCourses(courses.filter((course: {courseId : number}) => course.courseId !== courseId));
        })
        .catch(error => {
          console.error('There was an error!', error);
        });
    };

    const addCourse = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const form = event.currentTarget;
        const newCourse: CourseForm = {
          courseName: (form.elements.namedItem('name') as HTMLInputElement).value,
          courseCapacity: parseInt((form.elements.namedItem('capacity') as HTMLInputElement).value),
        };
        console.log(newCourse);
        axios.post<Course>('http://localhost:8080/courses/createCourse', newCourse)
          .then(response => {
            setCourses((prevCourses: Course[]) => [...prevCourses, response.data]);
            handleClose();
          })
          .catch(error => {
            console.error('There was an error!', error);
          });
      };
  return (
    <div>
      <h1>Course List</h1>
      <button onClick={handleShow}>Add Course</button>
      {show && (
        <form onSubmit={addCourse}>
        <h2>Add Course</h2>
        <label> 
          Course Name
          <input type="text" name="name" placeholder="Name" required />  
        </label>
        <label> 
          Course Capacity
          <input type="number" name="capacity" placeholder="Capacity" required />  
        </label>
        <button type="submit">Add Course</button>
      </form>
      )}
      <table>
        <thead>
          <tr>
            <th>Course Id</th>
            <th>Course Name</th>
            <th>Capacity</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {courses.map((course : Course) => (
            <tr key={course.courseId}>
              <td>{course.courseId}</td>
              <td>{course.courseName}</td>
              <td>{course.courseCapacity}</td>
              <td>
                <Link to={`/manage-course/${course.courseId}`}>Manage</Link>
              </td>
              <td>
              <button onClick={() => deleteCourse(course.courseId)}>Delete</button>
            </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CourseList;