package com.nchomework.netcrackerhw.controller;

import com.nchomework.netcrackerhw.model.Message;
import com.nchomework.netcrackerhw.parsing.UUIDParser;
import com.nchomework.netcrackerhw.service.api.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("{id}")
    public void sendMessage(@PathVariable String id, @RequestBody Message message) {
        emailService.sendEmailByPersonId(UUIDParser.parseUuidWithError(id), message);
    }

}
