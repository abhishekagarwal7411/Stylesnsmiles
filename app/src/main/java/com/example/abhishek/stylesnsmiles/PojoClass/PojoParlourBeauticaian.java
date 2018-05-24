package com.example.abhishek.stylesnsmiles.PojoClass;

public class PojoParlourBeauticaian {
    private String username;
    private String mobilenumber;
    private String emailId;
    private String confirmpassword;
    private String title;
    private String status;

    public PojoParlourBeauticaian() {

    }

    public PojoParlourBeauticaian(String username, String mobilenumber, String emailId, String title, String status) {
        this.username = username;
        this.mobilenumber = mobilenumber;
        this.emailId = emailId;
        this.status = status;
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
