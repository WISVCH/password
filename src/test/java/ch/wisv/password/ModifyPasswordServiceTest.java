package ch.wisv.password;

import ch.wisv.password.service.ModifyPasswordService;
import org.junit.Ignore;
import org.junit.Test;

import static ch.wisv.password.service.ModifyPasswordService.Result.SUCCESS;
import static org.junit.Assert.assertEquals;

/**
 * Modify password service test
 */
public class ModifyPasswordServiceTest {
    @Test
    @Ignore("requires user password")
    public void modifyPassword() throws Exception {
        ModifyPasswordService modifyPasswordService = new ModifyPasswordService();
        ModifyPasswordService.Result result = modifyPasswordService.modifyPassword("username", "Welkom01", "Welkom01");
        assertEquals("Modify password successful", SUCCESS, result);
    }

}
