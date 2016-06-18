package ch.wisv.password.service;

import org.passay.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Password strength service
 * <p>
 * Should (also) be validated by the LDAP server
 */
@Service
public class PasswordStrengthService {

    private final PasswordValidator validator;

    public PasswordStrengthService() {
        validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 256),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1)));
    }

    public List<String> checkPasswordStrength(String password) {
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return Collections.emptyList();
        } else {
            return validator.getMessages(result);
        }
    }
}
