package com.revature.backend.Service;


import com.revature.backend.Repo.CoursesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {
    private CoursesDAO coursesDAO;

    @Autowired
    public CoursesService(CoursesDAO coursesDAO) {
        this.coursesDAO = coursesDAO;
    }
}
