package com.booktrain.exachangeseat.dto;

public class UserDTO {

    private String name;

    private String mobileNumber;

    private String emailId;

    private Byte age;

    private String gender;

    public UserDTO() {
    }

    public UserDTO(String name, String mobileNumber, String emailId, Byte age, String gender) {
        this.name = name;
        this.mobileNumber=mobileNumber;
        this.emailId = emailId;
        this.age = age;
        this.gender = gender;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
