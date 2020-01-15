package io.agileintelligence.ppmtool.models.exceptionModels;

public class BacklogIdException {

    private String projectIdentifier;

    /*Getters and Setters*/
    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    /*Constructor*/
    public BacklogIdException(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
