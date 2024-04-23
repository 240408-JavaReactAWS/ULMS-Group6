package com.revature.backend.Service;


import com.revature.backend.Repo.GradesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradesService {
    private GradesDAO gradesDAO;

    @Autowired
    public GradesService(GradesDAO gradesDAO) {
        this.gradesDAO = gradesDAO;
    }
}
