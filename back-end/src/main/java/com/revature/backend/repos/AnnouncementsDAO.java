package com.revature.backend.repos;

import com.revature.backend.models.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementsDAO extends JpaRepository<Announcements, Integer> {
<<<<<<< Updated upstream
=======
   // As a Student, I can check course Announcements for different courses.
    List<Announcements> findByCourse_Students_UserIdAndCourse_CourseId(Integer studentId, Integer courseId);

    List<Announcements> findByCourse_CourseId(Integer courseId);
>>>>>>> Stashed changes
}
