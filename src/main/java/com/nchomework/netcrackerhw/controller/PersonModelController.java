package com.nchomework.netcrackerhw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nchomework.netcrackerhw.model.ModelMessage;
import com.nchomework.netcrackerhw.model.Person;
import com.nchomework.netcrackerhw.service.api.EmailService;
import com.nchomework.netcrackerhw.service.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PersonModelController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailService emailService;

    @GetMapping("/profile")
    public String profileForm(Model model) {
        model.addAttribute("person", new Person());
        return "profile";
    }

    @PostMapping("/persons")
    public String profileSubmit(@ModelAttribute @Valid Person person) {
        personService.create(person);
        return "result";
    }

    @GetMapping("search")
    public String getPerson(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("persons", new ArrayList<Person>());
        return "search";
    }

    @PostMapping("search")
    public String findPersonResult(@ModelAttribute Person person, Model model) {
        List<Person> persons = personService.getByFirstNameAndLastName(person.getName(), person.getLastname());
        if (persons.isEmpty()) {
            return "unsuccessfulsearch";
        }
        model.addAttribute("persons", persons);
        return "successfulsearch";
    }


    @GetMapping("upload")
    public String uploadFile() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file");
            return "redirect:/upload";
        }
        try (InputStream is = file.getInputStream()) {
            Person[] persons = objectMapper.readValue(is, Person[].class);
            personService.createAll(List.of(persons));
            attributes.addFlashAttribute("message", "File:  " + file.getOriginalFilename() + "has been uploaded!");
            return "redirect:/upload";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/sendmail")
    public String mailSender(Model model) {
        model.addAttribute("messageMail", new ModelMessage());
        return "sendmail";
    }

    @PostMapping("/sendmail")
    public String mailSenderResult(@ModelAttribute @Valid ModelMessage messageMail, RedirectAttributes attributes) {

        try {
            emailService.sendEmail(messageMail.getEmail(), messageMail);
        } catch (Exception ex) {
            attributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/sendmail";
        }


        return "mailsuccess";
    }

}
