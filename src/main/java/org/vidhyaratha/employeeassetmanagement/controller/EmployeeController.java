package org.vidhyaratha.employeeassetmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;


@Controller
@SessionAttributes("employeeDTO")
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @ModelAttribute("employeeDTO")
    public EmployeeDTO setUpEmployee() {
        return new EmployeeDTO();
    }


    @GetMapping("/home")
    public String home() {
        return "index";
    }


    @GetMapping("/signin")
    public String showSigninForm(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employeeDTO", employeeDTO);
        return "signin";
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employeeDTO", employeeDTO);
        return "signup";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/signin?logout";
    }

    @PostMapping("/processSignin")
    public String signinEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                 BindingResult result, Model model) {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());

        if (existingEmployee != null && existingEmployee.getPassword().equals(employeeDTO.getPassword())) {
            return "redirect:/getEmployeeAssets/" + employeeDTO.getEmpId();
        } else {
            return "redirect:/signin?error";
        }
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,@RequestParam("gender") String gender,
                               BindingResult result, Model model) {

        Employee existingEmployeeI = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
        Employee existingEmployeeE = employeeService.findEmployeeByEmail(employeeDTO.getEmail());

        if ((existingEmployeeI != null) || (existingEmployeeE != null)) {
            if (existingEmployeeE.getEmail().equals(employeeDTO.getEmail()) || existingEmployeeI.getEmpId().equals(employeeDTO.getEmpId())) {
                model.addAttribute("employee", employeeDTO);
                return "redirect:/signup?error";
            }
        }

        employeeService.saveEmployee(employeeDTO);
        return "redirect:/signup?success";

    }


    @GetMapping("/faq")
    public String questions() {
        return "faq";
    }

}
