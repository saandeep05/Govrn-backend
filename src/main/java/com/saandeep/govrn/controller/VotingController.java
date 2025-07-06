package com.saandeep.govrn.controller;

import com.saandeep.govrn.dto.EntityDTO;
import com.saandeep.govrn.dto.VotingPublishDTO;
import com.saandeep.govrn.model.Project;
import com.saandeep.govrn.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voting")
public class VotingController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/start")
    public ResponseEntity<EntityDTO<List<Project>>> startVoting(@Valid @RequestBody VotingPublishDTO votingPublishDTO) {
        EntityDTO<List<Project>> projectList = projectService.startVoting(votingPublishDTO.getStartDate(), votingPublishDTO.getProjectIdList());
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
