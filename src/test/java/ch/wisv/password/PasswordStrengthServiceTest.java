package ch.wisv.password;

import ch.wisv.password.service.PasswordStrengthService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Password strength service test
 */
public class PasswordStrengthServiceTest {
    @Test
    public void validPassword() throws Exception {
        PasswordStrengthService passwordStrengthService = new PasswordStrengthService();
        List<String> result = passwordStrengthService.checkPasswordStrength("ASDFasdf12");
        assertEquals("Password is valid", 0, result.size());
    }

    @Test
    public void invalidPassword() throws Exception {
        PasswordStrengthService passwordStrengthService = new PasswordStrengthService();
        List<String> result = passwordStrengthService.checkPasswordStrength("asdf");
        assertEquals("Password is not valid", 3, result.size());
    }
}
