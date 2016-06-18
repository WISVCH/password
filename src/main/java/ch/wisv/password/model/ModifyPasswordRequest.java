package ch.wisv.password.model;

/**
 * Modify password request model, used in web forms
 */
public class ModifyPasswordRequest {
    private String username;
    private String currentPassword;
    private String newPassword1;
    private String newPassword2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    @Override
    public String toString() {
        return "ModifyPasswordRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
