package com.unrealconclusion.springboot.thymeleaf.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.unrealconclusion.springboot.thymeleaf.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class StudentController {
    
    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a student object (model)
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);

        // add list of countries to model
        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Brazil");
        countries.add("France");
        countries.add("Germany");
        countries.add("India");
        theModel.addAttribute("countries", countries);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {

        // log the input data
        System.out.println("theStudent: " + theStudent.getFirstName());

        return "student-confirmation";
    }
    
}
