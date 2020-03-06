package io.agileintelligence.ppmtool.controllers;

import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.models.ProjectFileAttachment;
import io.agileintelligence.ppmtool.services.ProjectService;
import io.agileintelligence.ppmtool.services.ValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectFileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private ProjectFileAttachmentService attachmentService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){
        //Principle is part of the java security package. Comes from the user that is logged in

        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Project project1 = projectService.saveOrUpdateProject(project, principal.getName());
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @PostMapping("/attachment/{projectId}")
    public ResponseEntity<Void> uploadFile(@NotNull @PathVariable Long projectId, @RequestParam("file[]") MultipartFile multipartFile) throws IOException {
        ProjectFileAttachment file = new ProjectFileAttachment(
                multipartFile.getOriginalFilename(),
                multipartFile.getSize(),
                multipartFile.getContentType(),
                multipartFile.getBytes());
        attachmentService.uploadFile(projectId, file);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal){
        Project project = projectService.findByProjectIdentifier(projectId.toUpperCase(), principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal){ return projectService.findAllProjects(principal.getName());}

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, Principal principal){
        projectService.deleteProjectById(projectId, principal.getName());
        return new ResponseEntity<String>("Project with ID '" + projectId + "' was deleted successfully", HttpStatus.OK);
    }

}
