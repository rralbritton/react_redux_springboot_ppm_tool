package io.agileintelligence.ppmtool.services.utilitiesServices;

import io.agileintelligence.ppmtool.models.utilities.MenuDevelopersList;
import io.agileintelligence.ppmtool.models.utilities.MenuStatusList;
import io.agileintelligence.ppmtool.models.utilities.MenuTaskPriority;
import io.agileintelligence.ppmtool.repositories.utilitiesRepository.MenuDeveloperRepository;
import io.agileintelligence.ppmtool.repositories.utilitiesRepository.MenuStatusRepository;
import io.agileintelligence.ppmtool.repositories.utilitiesRepository.MenuTaskPriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilitiesServices {

    @Autowired
    private MenuDeveloperRepository developerRepository;

    @Autowired
    private MenuTaskPriorityRepository taskPriorityRepository;

    @Autowired
    private MenuStatusRepository statusRepository;

    public List<MenuDevelopersList> getDeveloperList(){
        return developerRepository.findAllBy();
    }

    public List<MenuTaskPriority> getTaskPriorityList(){
        return taskPriorityRepository.findAllBy();
    }

    public List<MenuStatusList> getStatusList(){
        return statusRepository.findAllBy();
    }

}
