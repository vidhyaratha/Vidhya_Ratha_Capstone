package org.vidhyaratha.employeeassetmanagement.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.service.UserService;

@Slf4j
@Controller
@SessionAttributes("userDTO")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    private  UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("userDTO")
    public UserDTO setUpEmployee() {
        return new UserDTO();
    }


    @GetMapping("/home")
    public String home() {
        logger.info("Home Page Displayed");
        return "index";
    }


    @RequestMapping("/signin")
    public String showSigninForm(Model model) {
        logger.info("Show Sign In page Displayed");
        return "signin";
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        logger.info("Show Sign Up page Displayed");
        return "signup";
    }


    @GetMapping("/logout")
    public String logout() {
        logger.info("Logged out successfully");
        return "redirect:/signin?logout";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("userDTO") UserDTO userDTO,@RequestParam("gender") String gender,
                               BindingResult result, Model model) {

        User existingEmployeeI = userService.findUserByEmpId(userDTO.getEmpId());
        User existingEmployeeE = userService.findUserByEmail(userDTO.getEmail());

        if ((existingEmployeeI != null) || (existingEmployeeE != null)) {
            if (existingEmployeeE.getEmail().equals(userDTO.getEmail()) || existingEmployeeI.getEmpId().equals(userDTO.getEmpId())) {
                model.addAttribute("employee", userDTO);
                logger.info("Sign up error page displayed");
                return "redirect:/signup?error";
            }
        }

     userService.saveUser(userDTO);
        logger.info("Registration successful");
        return "redirect:/signup?success";

    }


    @GetMapping("/faq")
    public String questions() {
        logger.info("FAQ page Displayed");
        return "faq";
    }



    @GetMapping("/editEmployeeInformation")
    public String showupdateEmployee(@ModelAttribute("userDTO") UserDTO userDTO) {
        logger.info("Employee Edit Profile Displayed");
        return "redirect:/editEmployee";
    }


    @GetMapping("/editEmployee")
    public String updateEmployee(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

       User existingEmployee = userService.findUserByEmail(username);
        model.addAttribute("employee",existingEmployee);
        return "editprofile";
    }


    @PostMapping("/processEditProfile")
    public String processEditProfile(@ModelAttribute("userDTO") UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);
        userDTO.setEmpId(existingEmployee.getEmpId());
        userDTO.setGender(existingEmployee.getGender());
        userDTO.setLocation(existingEmployee.getLocation());
        userService.saveUser(userDTO);
        logger.info("Employee Profile Updated successfully");
        return "redirect:/getEmployeeAssets" ;

    }

}
