package ch.wisv.password.validator;


import ch.wisv.password.model.ModifyPasswordRequest;
import ch.wisv.password.service.PasswordStrengthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Validator for modify password request model objects
 */
@Component
public class ModifyPasswordRequestValidator implements Validator {

    @Autowired
    private PasswordStrengthService passwordStrengthService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ModifyPasswordRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ModifyPasswordRequest request = (ModifyPasswordRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
        if (!request.getUsername().matches("[a-z]*")) {
            errors.rejectValue("username", "username.notlowercase");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "currentPassword.empty");

        List<String> passwordStrengthErrors = passwordStrengthService.checkPasswordStrength(request.getNewPassword1());
        for (String passwordStrengthError : passwordStrengthErrors) {
            errors.rejectValue("newPassword1", "newPassword1.strength", passwordStrengthError);
        }

        if (!request.getNewPassword1().equals(request.getNewPassword2())) {
            errors.rejectValue("newPassword2", "newPassword2.notmatch");
        }
    }
}
