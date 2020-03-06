package io.agileintelligence.ppmtool.controllers;

import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.models.utilities.MenuDevelopersList;
import io.agileintelligence.ppmtool.models.utilities.MenuStatusList;
import io.agileintelligence.ppmtool.models.utilities.MenuTaskPriority;
import io.agileintelligence.ppmtool.services.utilitiesServices.UtilitiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/utilities")
public class UtilitiesController {

    @Autowired
    private UtilitiesServices utilitiesService;

    @GetMapping("/developers")
    public ResponseEntity<?> getDeveloperList() {
        List<MenuDevelopersList> developers = utilitiesService.getDeveloperList();
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @GetMapping("/statuslist")
    public ResponseEntity<?> getStatusList() {
    List<MenuStatusList> statusList = utilitiesService.getStatusList();
        return new ResponseEntity<>(statusList,HttpStatus.OK);
}
    @GetMapping("/taskpriorities")
    public ResponseEntity<?> getTaskPrioritiesList() {
    List<MenuTaskPriority> priorities = utilitiesService.getTaskPriorityList();
        return new ResponseEntity<>(priorities, HttpStatus.OK);
}
        }
