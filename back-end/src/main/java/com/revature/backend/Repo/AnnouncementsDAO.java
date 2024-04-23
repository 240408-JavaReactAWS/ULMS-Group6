package com.revature.backend.Repo;

import com.revature.backend.Model.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementsDAO extends JpaRepository<Announcements, Integer> {
}
