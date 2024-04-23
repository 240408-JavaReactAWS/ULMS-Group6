package com.revature.backend.repos;

import com.revature.backend.models.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementsDAO extends JpaRepository<Announcements, Integer> {
}
