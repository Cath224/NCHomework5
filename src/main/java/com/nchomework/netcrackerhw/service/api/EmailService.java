package com.nchomework.netcrackerhw.service.api;

import com.nchomework.netcrackerhw.model.Message;

import java.util.UUID;

public interface EmailService {

    void sendEmailByPersonId(UUID personId, Message message);

    void sendEmail(String email, Message message);

}
