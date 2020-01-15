package io.agileintelligence.ppmtool.payload;

public class JwtLoginSuccessResponse {

    private Boolean success;
    private String token;

    /*Constructor*/
    public JwtLoginSuccessResponse(Boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    /*Getters and Setters*/
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtLoginSuccessResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
