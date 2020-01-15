package io.agileintelligence.ppmtool.security;

import com.google.gson.Gson;
import io.agileintelligence.ppmtool.models.exceptionModels.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*The authentication entry point is an interface that provides the implementation for a method called commence.
* This method is called whenever an exception is thrown because someone is trying to access a resource
* that requires authentication. For this project, users should be logged in to view/create/update/delete projects.
* Also user should only be able to view their personal projects.
* The purpose of this class is to customize the invalid login response.
* */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override //Overriding this method allows for a customized message to be presented to the user if login fails
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);
        
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(jsonLoginResponse);

    }
}
