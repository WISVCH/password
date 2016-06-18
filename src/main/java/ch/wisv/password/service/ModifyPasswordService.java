package ch.wisv.password.service;

import org.ldaptive.*;
import org.ldaptive.extended.PasswordModifyOperation;
import org.ldaptive.extended.PasswordModifyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static ch.wisv.password.service.ModifyPasswordService.Result.ERROR;
import static ch.wisv.password.service.ModifyPasswordService.Result.SUCCESS;

/**
 * Service to modify a user's password in LDAP
 */
@Service
public class ModifyPasswordService {
    private final Logger logger = LoggerFactory.getLogger(ModifyPasswordService.class);
    private final ConnectionFactory connectionFactory;

    public ModifyPasswordService() {
        connectionFactory = new DefaultConnectionFactory("ldaps://ank.chnet");
    }

    public Result modifyPassword(String username, String currentPassword, String newPassword) {
        if (!username.matches("[a-z]+")) {
            return ERROR;
        }
        String dn = String.format("uid=%s,ou=People,dc=ank,dc=chnet", username);

        try (Connection connection = connectionFactory.getConnection()) {
            connection.open();
            BindOperation bind = new BindOperation(connection);
            Credential currentCredential = new Credential(currentPassword);
            bind.execute(new BindRequest(dn, currentCredential));
            PasswordModifyOperation modify = new PasswordModifyOperation(connection);
            Response<Credential> response = modify.execute(new PasswordModifyRequest(dn, currentCredential, new
                    Credential(newPassword)));
            if (response.getResultCode() == ResultCode.SUCCESS) {
                logger.info("Password modify success for {}", username);
                return SUCCESS;
            } else {
                logger.error("Password modify failed for {}: {}", username, response.getResultCode());
                return ERROR;
            }
        } catch (LdapException e) {
            if (e.getResultCode() == ResultCode.INVALID_CREDENTIALS) {
                logger.info("Invalid credentials for {}", username);
                return Result.INVALID_CREDENTIALS;
            } else {
                logger.error("Password modify password error for {}", username, e);
                return ERROR;
            }
        }
    }

    public enum Result {
        SUCCESS,
        INVALID_CREDENTIALS,
        ERROR
    }
}
