package com.revature.backend.Service;


import com.revature.backend.Repo.AssignmentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentsService {
    private AssignmentsDAO assignmentsDAO;

    @Autowired
    public AssignmentsService(AssignmentsDAO assignmentsDAO) {
        this.assignmentsDAO = assignmentsDAO;
    }
}
