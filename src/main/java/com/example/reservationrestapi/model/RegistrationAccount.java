package com.example.reservationrestapi.model;

public class RegistrationAccount {

    private String username;
    private String email;
    private boolean useEmail;
    private String password;

    public RegistrationAccount(String username, String email, boolean useEmail, String password) {
        this.username = username;
        this.email = email;
        this.useEmail = useEmail;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUseEmail() {
        return useEmail;
    }

    public void setUseEmail(boolean useEmail) {
        this.useEmail = useEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
