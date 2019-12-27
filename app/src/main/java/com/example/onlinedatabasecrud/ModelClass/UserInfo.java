package com.example.onlinedatabasecrud.ModelClass;

public class UserInfo {

    private String uAuthID;
    private String uName;
    private String uPhone;
    private String uEmail;
    private String uAddress;
    private String uGender;

    public UserInfo() {
    }

    public UserInfo(String uAuthID, String uName, String uPhone, String uEmail, String uAddress, String uGender) {
        this.uAuthID = uAuthID;
        this.uName = uName;
        this.uPhone = uPhone;
        this.uEmail = uEmail;
        this.uAddress = uAddress;
        this.uGender = uGender;
    }

    public String getuAuthID() {
        return uAuthID;
    }

    public void setuAuthID(String uAuthID) {
        this.uAuthID = uAuthID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uAuthID='" + uAuthID + '\'' +
                ", uName='" + uName + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uAddress='" + uAddress + '\'' +
                ", uGender='" + uGender + '\'' +
                '}';
    }
}
