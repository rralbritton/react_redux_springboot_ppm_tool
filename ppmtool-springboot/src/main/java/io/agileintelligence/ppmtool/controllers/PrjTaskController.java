package io.agileintelligence.ppmtool.controllers;

import io.agileintelligence.ppmtool.models.PrjTask;
import io.agileintelligence.ppmtool.services.PrjTaskService;
import io.agileintelligence.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/project/tasks")
public class PrjTaskController {

    @Autowired
    PrjTaskService taskService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping("/{prjId}")
    public ResponseEntity<?> getAllPrjTasks (@PathVariable Long prjId, Principal principal){
        List<PrjTask> prjTasks = taskService.getAllPrjTasks(prjId, principal.getName());
        return new ResponseEntity<>(prjTasks, HttpStatus.OK);
    }

    @GetMapping("/{prjId}/{taskId}")
    public ResponseEntity<?> getPrjTaskById (@PathVariable Long prjId, @PathVariable Long taskId, Principal principal){
        PrjTask task = taskService.getPrjTaskById(prjId, taskId, principal.getName());
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/{prjId}")
    public ResponseEntity<?> createNewPrjTask (@Valid @RequestBody PrjTask prjTask, @PathVariable Long prjId, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if (errorMap != null) {
            return errorMap;
        }

        PrjTask newTask = taskService.addProjectTask(prjId, prjTask, principal.getName());
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{prjId}/{taskId}")
    public ResponseEntity<?> updatePrjTask (@Valid @RequestBody PrjTask prjTask,
                                            @PathVariable Long prjId, @PathVariable Long taskId, BindingResult result,
                                            Principal principal){
        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if (errorMap != null) {
            return errorMap;
        }
        taskService.updatePrjTask(prjId, taskId,prjTask, principal.getName());
        return new ResponseEntity<>(prjTask, HttpStatus.OK);
    }

    @DeleteMapping("/{prjId}/{taskId}")
    public ResponseEntity<?> deletePrjTask( @PathVariable Long prjId, @PathVariable Long taskId, Principal principal){
        taskService.deleteTask(prjId, taskId, principal.getName());
        return new ResponseEntity<String>("Task deleted successfully", HttpStatus.OK);
    }
}
