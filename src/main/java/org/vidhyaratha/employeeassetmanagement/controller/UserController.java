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
import org.vidhyaratha.employeeassetmanagement.Exception.PasswordMatchException;
import org.vidhyaratha.employeeassetmanagement.dto.EditProfileDTO;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.Role;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.service.RoleService;
import org.vidhyaratha.employeeassetmanagement.service.UserService;

@Slf4j
@Controller
@SessionAttributes("userDTO")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private  UserService userService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @ModelAttribute("userDTO")
    public UserDTO setUpEmployee() {
        return new UserDTO();
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // Get request handler method to display the home page
    @GetMapping("/home")
    public String home() {
        logger.info("Home Page Displayed");
        return "index";
    }

    // Get request handler method to display sign in page
    @RequestMapping("/signin")
    public String showSigninForm(Model model) {
        logger.info("Show Sign In page Displayed");
        return "signin";
    }

    // Get Request handler method to display sign up page
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        logger.info("Show Sign Up page Displayed");
        return "signup";
    }

    // Get request handler method to redirect to the signin  page when logged out
    @GetMapping("/logout")
    public String logout() {
        logger.info("Logged out successfully");
        return "redirect:/signin?logout";
    }

    // Post request handler method to redirect to the sign up page to show success message
    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("userDTO") UserDTO userDTO,@RequestParam("gender") String gender,
                               @RequestParam("role") String role,BindingResult result, Model model) {

        User existingEmployeeI = userService.findUserByEmpId(userDTO.getEmpId());
        User existingEmployeeE = userService.findUserByEmail(userDTO.getEmail());

        if ((existingEmployeeI != null) || (existingEmployeeE != null)) {
            if (existingEmployeeE.getEmail().equals(userDTO.getEmail()) || existingEmployeeI.getEmpId().equals(userDTO.getEmpId())) {
                model.addAttribute("employee", userDTO);
                logger.info("Sign up error page displayed");
                return "redirect:/signup?error";
            }
        }

     userService.saveUser(userDTO, role);
        logger.info("Registration successful");
        return "redirect:/signup?success";

    }

    // Get Request handler method to display the faq page
    @GetMapping("/faq")
    public String questions() {
        logger.info("FAQ page Displayed");
        return "faq";
    }


    // Get request handler method to redirect to the edit employee page
    @GetMapping("/editEmployeeInformation")
    public String showupdateEmployee(@ModelAttribute("userDTO") UserDTO userDTO) {
        logger.info("Employee Edit Profile Displayed");
        return "redirect:/editEmployee";
    }

    // Get request handler method to display the edit employee page
    @GetMapping("/editEmployee")
    public String updateEmployee(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

       User existingEmployee = userService.findUserByEmail(username);
        model.addAttribute("employee",existingEmployee);
        return "editprofile";
    }

    // Post request handler method to edit the employee information
    @PostMapping("/processEditProfile")
    public String processEditProfile(@ModelAttribute("userDTO") UserDTO userDTO,
                                    @RequestParam("confirmPassword") String confirmPassword,
                                     @RequestParam("password") String password,
                                     @ModelAttribute("editProfileDTO") EditProfileDTO editProfileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);

        Role role = roleService.getRolesByUser(existingEmployee.getEmpId());


        if (password.equals(confirmPassword)) {
            userDTO.setEmail(editProfileDTO.getEmail());
            userDTO.setEmpName(editProfileDTO.getEmpName());
            userDTO.setPassword(password);
            userDTO.setEmpId(existingEmployee.getEmpId());
            userDTO.setGender(existingEmployee.getGender());
            userDTO.setLocation(existingEmployee.getLocation());
            userService.saveUser(userDTO, role.getName());
            logger.info("Employee Profile Updated successfully");
            return "redirect:/getEmployeeAssets";
        }
        else
        {
            throw new PasswordMatchException("Passwords doesn't match");
        }

    }

}
