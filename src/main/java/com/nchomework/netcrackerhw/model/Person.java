package com.nchomework.netcrackerhw.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    private UUID id;

    @Pattern(regexp = "^([a-zA-Z]){2,1024}$")
    private String name;
    @Pattern(regexp = "^([a-zA-Z]){2,1024}$")
    private String lastname;
    @Pattern(regexp = "^[a-zA-Z]{0,1024}$")
    private String patronymic;

    @Min(10) //It's ok for Asia
    @Max(127)
    private byte age;

    @DecimalMin("0")
    @Digits(integer = Short.MAX_VALUE, fraction = 2) //Integer.MAX_VALUE, Yes, so many zeros, do you know about Zimbabwe or Venezuela currencies?
    private BigDecimal salary;

    @Email
    private String email;

    @Pattern(regexp = "^[a-zA-Z\\d./']{0,2048}$") //My Service - My Rules
    private String work;

    public Person() {
    }

    public Person(UUID id, String name, String lastname, String patronymic, byte age, BigDecimal salary, String email, String work) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", work='" + work + '\'' +
                '}';
    }
}
