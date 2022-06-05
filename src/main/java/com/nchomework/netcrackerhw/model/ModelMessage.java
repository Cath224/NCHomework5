package com.nchomework.netcrackerhw.model;

import javax.validation.constraints.Email;

public class ModelMessage  extends Message{

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}