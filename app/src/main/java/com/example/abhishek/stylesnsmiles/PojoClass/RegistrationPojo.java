package com.example.abhishek.stylesnsmiles.PojoClass;

public class RegistrationPojo {
    private String username;
    private String mobilenumber;
    private String emailId;
    private String confirmpassword;

    public RegistrationPojo() {

    }

    public RegistrationPojo(String username, String mobilenumber, String emailId, String confirmpassword) {
        this.username = username;
        this.mobilenumber = mobilenumber;
        this.confirmpassword = confirmpassword;
        this.emailId = emailId;

    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}