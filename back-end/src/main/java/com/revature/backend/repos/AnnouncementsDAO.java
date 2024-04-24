package com.revature.backend.repos;

import com.revature.backend.models.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementsDAO extends JpaRepository<Announcements, Integer> {
   // As a Student, I can check course Announcements for different courses.
    List<Announcements> findByCourse_Students_UserIdAndCourse_CourseId(Integer studentId, Integer courseId);
}
