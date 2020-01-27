package io.agileintelligence.ppmtool.validators;

import io.agileintelligence.ppmtool.models.security.PpmUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PpmUser.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        PpmUser ppmUser = (PpmUser) object;

        if(ppmUser.getPassword().length() <6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

        if(!ppmUser.getPassword().equals(ppmUser.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match", "Passwords must match");

        }
    }
}