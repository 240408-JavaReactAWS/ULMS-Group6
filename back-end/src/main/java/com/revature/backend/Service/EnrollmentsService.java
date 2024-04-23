package com.revature.backend.Service;


import com.revature.backend.Repo.EnrollmentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentsService {
    private EnrollmentsDAO enrollmentsDAO;

    @Autowired
    public EnrollmentsService(EnrollmentsDAO enrollmentsDAO) {
        this.enrollmentsDAO = enrollmentsDAO;
    }
}
