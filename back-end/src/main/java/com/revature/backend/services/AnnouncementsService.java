package com.revature.backend.services;


import com.revature.backend.repos.AnnouncementsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementsService {
    private AnnouncementsDAO announcementsDAO;

    @Autowired
    public AnnouncementsService(AnnouncementsDAO announcementsDAO) {
        this.announcementsDAO = announcementsDAO;
    }
}
