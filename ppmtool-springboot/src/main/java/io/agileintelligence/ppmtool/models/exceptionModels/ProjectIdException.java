package io.agileintelligence.ppmtool.models.exceptionModels;

public class ProjectIdException {
    private String projectIdentifier;

    /*Getters and Setters*/
    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    /*Constructor*/
    public ProjectIdException(String projectIdentifier){
        this.projectIdentifier = projectIdentifier;
    }
}
