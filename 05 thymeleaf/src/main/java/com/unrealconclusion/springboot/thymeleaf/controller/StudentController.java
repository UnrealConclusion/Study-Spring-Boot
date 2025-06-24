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

        // add list of countries to the model
        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Brazil");
        countries.add("France");
        countries.add("Germany");
        countries.add("India");
        countries.add("Mexico");
        countries.add("Spain");
        countries.add("United States");
        theModel.addAttribute("countries", countries);

        // add a list of programming languages to the model
        ArrayList<String> programmingLanguages = new ArrayList<String>();
        programmingLanguages.add("Go");
        programmingLanguages.add("Java");
        programmingLanguages.add("Python");
        programmingLanguages.add("Rust");
        programmingLanguages.add("TypeScript");
        theModel.addAttribute("programmingLanguages", programmingLanguages);

        // add a list of operating systems to the model
        ArrayList<String> operatingSystems = new ArrayList<String>();
        operatingSystems.add("Linux");
        operatingSystems.add("MacOS");
        operatingSystems.add("Microsoft Windows");
        operatingSystems.add("Android");
        operatingSystems.add("iOS");
        theModel.addAttribute("operatingSystems", operatingSystems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {

        // log the input data
        System.out.println("theStudent: " + theStudent.getFirstName() + theStudent.getLastName());

        return "student-confirmation";
    }
    
}
