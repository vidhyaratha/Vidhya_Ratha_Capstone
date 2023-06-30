package org.vidhyaratha.employeeassetmanagement.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;

@Slf4j
@Controller
@SessionAttributes("employeeDTO")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    private  EmployeeService employeeService;

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
        logger.info("Home Page Displayed");
        return "index";
    }


    @RequestMapping("/signin")
    public String showSigninForm() {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        model.addAttribute("employeeDTO", employeeDTO);
        logger.info("Show Sign In page Displayed");
        return "signin";
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employeeDTO", employeeDTO);
        logger.info("Show Sign Up page Displayed");
        return "signup";
    }


    @GetMapping("/logout")
    public String logout() {
        logger.info("Logged out successfully");
        return "redirect:/signin?logout";
    }

//    @PostMapping("/processSignin")
//    public String signinEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
//                                 Model model) {
//
//        logger.info("Show Sign In page Entered");
//        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
//
//        if (existingEmployee != null && existingEmployee.getPassword().equals(employeeDTO.getPassword())) {
//                logger.info("Employee Information Page Displayed");
//            return "redirect:/getEmployeeAssets" ;
//        }
//        else {
//            logger.info("Sign In Error page Displayed");
//            return "redirect:/signin?error";
//        }
//    }



    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,@RequestParam("gender") String gender,
                               BindingResult result, Model model) {

        Employee existingEmployeeI = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
        Employee existingEmployeeE = employeeService.findEmployeeByEmail(employeeDTO.getEmail());

        if ((existingEmployeeI != null) || (existingEmployeeE != null)) {
            if (existingEmployeeE.getEmail().equals(employeeDTO.getEmail()) || existingEmployeeI.getEmpId().equals(employeeDTO.getEmpId())) {
                model.addAttribute("employee", employeeDTO);
                logger.info("Sign up error page displayed");
                return "redirect:/signup?error";
            }
        }

        employeeService.saveEmployee(employeeDTO);
        logger.info("Registration successful");
        return "redirect:/signup?success";

    }


    @GetMapping("/faq")
    public String questions() {
        logger.info("FAQ page Displayed");
        return "faq";
    }








    @GetMapping("/editEmployeeInformation")
    public String showupdateEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
        return "redirect:/" + employeeDTO.getEmpId() + "/editEmployee";
    }


    @GetMapping("/editEmployee")
    public String updateEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, Model model) {
       Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
        model.addAttribute("employee",existingEmployee);
        return "editprofile";
    }


    @PostMapping("/processEditProfile")
    public String processEditProfile(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
        employeeDTO.setEmpId(existingEmployee.getEmpId());
        employeeDTO.setGender(existingEmployee.getGender());
        employeeDTO.setLocation(existingEmployee.getLocation());
        employeeService.saveEmployee(employeeDTO);
        logger.info("Employee Profile Updated successfully");
        return "redirect:/getEmployeeAssets" ;

    }

}
