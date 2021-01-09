package com.addusername.pmddmm_p1.model;

/**
 * Esta clase representa el objeto fromulario de MainView y MainModel
 */
public class FormPojo {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private String comments;
    private String mode;


    public FormPojo(){}

    public FormPojo(String name, String surname, String phone, String email, String comments, String mode) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
        this.mode = mode;
    }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
