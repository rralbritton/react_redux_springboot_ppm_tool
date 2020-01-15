package io.agileintelligence.ppmtool.controllers;

import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.models.ProjectTask;
import io.agileintelligence.ppmtool.services.ProjectTaskService;
import io.agileintelligence.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.security.Principal;


@RestController
@RequestMapping("/api/backlog")
@CrossOrigin //Allows CORS - readup on this for PROD. Should be more defined
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                                     BindingResult result, Principal principal, @PathVariable String backlog_id){

        //Check for errors
        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if(errorMap !=null) return errorMap;

        //Create new project task
        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id.toUpperCase(), projectTask, principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id, Principal principal){
        return projectTaskService.findBacklogById(backlog_id, principal.getName());
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTaskById(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){
     ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id, principal.getName());
     return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               Principal principal, @PathVariable String backlog_id, @PathVariable String pt_id){

        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if(errorMap !=null) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateProjectTaskByPTSequence(projectTask, backlog_id, pt_id, principal.getName());

        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);

    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTaskById (@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){
        projectTaskService.deleteProjectTaskByProjectSequence(backlog_id.toUpperCase(), pt_id.toUpperCase(), principal.getName());
        return new ResponseEntity<String>("Project Task " + pt_id + " was deleted successfully from Project " + backlog_id, HttpStatus.OK);

    }

}
