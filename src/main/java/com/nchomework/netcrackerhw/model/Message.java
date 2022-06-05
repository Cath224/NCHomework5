package com.nchomework.netcrackerhw.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @Pattern(regexp = "^[A-Za-z\\d]{2,1024}$")
    private String subject;
    private String content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
