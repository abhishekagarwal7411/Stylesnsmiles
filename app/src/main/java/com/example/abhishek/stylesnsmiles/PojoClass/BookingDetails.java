package com.example.abhishek.stylesnsmiles.PojoClass;

public class BookingDetails {
    private String username;
    private String usercontactNumber;
    private String parlourname;
    private String useremailId;
    private String parlourEmployeename;
    private String parlourEmployeeContactDetail;
    private String date;
    private String time;
    private  String customermobile;
    private String status;
    public BookingDetails() {

    }

    public BookingDetails(String username,String customermobile,String parlourname,String parlourEmployeename,String parlourEmployeeContactDetail,String date,String time,String status) {
        this.username = username;
        this.parlourname = parlourname;
        this.parlourEmployeename = parlourEmployeename;
        this.parlourEmployeeContactDetail = parlourEmployeeContactDetail;
        this.date = date;
        this.time = time;
        this.customermobile = customermobile;
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomermobile() {
        return customermobile;
    }

    public void setCustomermobile(String customermobile) {
        this.customermobile = customermobile;
    }

    public String getUsercontactNumber() {
        return usercontactNumber;
    }

    public void setUsercontactNumber(String usercontactNumber) {
        this.usercontactNumber = usercontactNumber;
    }

    public String getUseremailId() {
        return useremailId;
    }

    public void setUseremailId(String useremailId) {
        this.useremailId = useremailId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getParlourname() {
        return parlourname;
    }

    public void setParlourname(String parlourname) {
        this.parlourname = parlourname;
    }



    public String getParlourEmployeename() {
        return parlourEmployeename;
    }

    public void setParlourEmployeename(String parlourEmployeename) {
        this.parlourEmployeename = parlourEmployeename;
    }

    public String getParlourEmployeeContactDetail() {
        return parlourEmployeeContactDetail;
    }

    public void setParlourEmployeeContactDetail(String parlourEmployeeContactDetail) {
        this.parlourEmployeeContactDetail = parlourEmployeeContactDetail;
    }
}