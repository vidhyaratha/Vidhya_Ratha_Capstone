package org.vidhyaratha.employeeassetmanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public String handleError() {
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
