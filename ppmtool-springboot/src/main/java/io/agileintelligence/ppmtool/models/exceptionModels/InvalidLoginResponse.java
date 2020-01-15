package io.agileintelligence.ppmtool.models.exceptionModels;

public class InvalidLoginResponse {

    private String username;
    private String password;

    /*Constructor*/
    public InvalidLoginResponse(){
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }

    /*Getters & Setters*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
