package com.unrealconclusion.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {
    // show the HTML form
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // using the @RequestParam() annotation Spring will automatically read the parameter from the request and bind it to a variable 
    @PostMapping("/processForm")
    public String processForm(@RequestParam("studentName") String theName, Model model) {
        
        // convert the string to all caps
        theName = theName.toUpperCase();
        
        // create the message
        String result = "Yo! " + theName;
        
        // add message to the model
        model.addAttribute("message", result);
        return "hello";
    } 

    /* manually retrieve the data from the HTTP request
    @RequestMapping("/processForm")
    public String processForm(HttpServletRequest request, Model model) {

        // read request parameter from the HTTP form
        String theName = request.getParameter("studentName");

        // convert the string to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        model.addAttribute("message", result);
        return "hello";
    } */
}
