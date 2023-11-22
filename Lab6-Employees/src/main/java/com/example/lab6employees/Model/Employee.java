package com.example.lab6employees.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "id should not be Empty")
    @Size(min = 2,message = "id must be more than 2 letters")
    private String id;
    @NotEmpty(message = "name should not be Empty")
    @Size(min = 4,message = "name must be more than 4 letters")
    @Pattern(regexp = "[A-Za-z]+",message = "name accept only characters")
    private String name;
    @jakarta.validation.constraints.Email(message = "invalid Email")
    private String Email;
    @Pattern(regexp = "^05.[0-9]+",message = "phone number must starts with 05")
    @Size(min = 10,max = 10 ,message = "phone number must be 10 digits")
    private String phoneNumber;
    @NotNull(message = "age should be Empty")
    @Min(25)
    private int age;
    @NotEmpty(message = "position should not be Empty")
    @Pattern(regexp = "supervisor|coordinator",message = "position must be supervisor or coordinator")
    private String position;
    @AssertFalse(message = "must be false")
    private boolean onLeave;
    @NotNull(message = "hire date cannot be null")
    @PastOrPresent(message = "the hire date must be in the past or present")
    private Date hireDate;
    @NotNull(message = "annual leave cannot be null")
    @Positive(message = "annual leave must be a positive number")
    private int annualLeave;
}
