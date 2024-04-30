package com.revature.backend.services;

import com.revature.backend.repos.CourseStudentDAO;
import org.springframework.stereotype.Service;

@Service
public class CourseStudentService {
    private CourseStudentDAO courseStudentDAO;

    public CourseStudentService(CourseStudentDAO courseStudentDAO) {
        this.courseStudentDAO = courseStudentDAO;
    }

    public void deleteCourseStudent(Integer id) {
        courseStudentDAO.deleteById(id);
    }
}
