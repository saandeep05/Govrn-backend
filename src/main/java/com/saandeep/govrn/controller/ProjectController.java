package com.saandeep.govrn.controller;

import com.saandeep.govrn.dto.EntityDTO;
import com.saandeep.govrn.dto.ProjectDTO;
import com.saandeep.govrn.model.Project;
import com.saandeep.govrn.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        Project project = projectService.getProject(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/{personId}")
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectDTO projectDTO, @PathVariable Long personId) {
        Project project = projectService.createProject(projectDTO, personId);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PostMapping("/assign/{projectId}/{personId}")
    public ResponseEntity<EntityDTO<Project>> assignProject(@PathVariable Long projectId, @PathVariable Long personId) {
        EntityDTO<Project> entityDTO = projectService.assignProjectTo(projectId, personId);
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PostMapping("/nextStage/{projectId}")
    public ResponseEntity<EntityDTO<Project>> gotoNextStage(@PathVariable Long projectId) {
        EntityDTO<Project> entityDTO = projectService.transitionToNextStage(projectId);
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
