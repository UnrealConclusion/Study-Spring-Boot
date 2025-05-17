package com.unrealconclusion.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {
    // show the HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }
    // process the HTML form
    @RequestMapping("/processForm")
    public String processForm(HttpServletRequest request, Model model) {

        // read request parameter from the HTTP form
        String theName = request.getParameter("studentName");

        // convert the string to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        model.addAttribute("message", result);
        return "hello";
    } 
}
