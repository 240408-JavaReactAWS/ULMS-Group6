package com.revature.backend.Controller;

import com.revature.backend.Service.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemporaryController {
    AnnouncementsService announcementsService;
    AssignmentsService assignmentsService;
    CoursesService coursesService;
    EnrollmentsService enrollmentsService;
    GradesService gradesService;
    UsersService usersService;

    public TemporaryController(AnnouncementsService announcementsService, AssignmentsService assignmentsService, CoursesService coursesService, EnrollmentsService enrollmentsService, GradesService gradesService, UsersService usersService) {
        this.announcementsService = announcementsService;
        this.assignmentsService = assignmentsService;
        this.coursesService = coursesService;
        this.enrollmentsService = enrollmentsService;
        this.gradesService = gradesService;
        this.usersService = usersService;
    }
}
