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

import java.util.List;


@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/home")
    public String home()
    {
        return "index";
    }




    @GetMapping("/signin")
    public String showSigninForm(Model model)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employeeDTO",employeeDTO);
        return "signin";
    }



    @GetMapping("/signup")
    public String showSignupForm(Model model)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employeeDTO",employeeDTO);
        return "signup";
    }





    @PostMapping("/processSignin")
    public String signinEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                 BindingResult result,Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmail(employeeDTO.getEmail());

        if(existingEmployee != null && existingEmployee.getPassword().equals(employeeDTO.getPassword()))
        {
            return "success";
        }
        else
        {
            return "redirect:/signin?error";
        }
    }





    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                               BindingResult result,Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmail(employeeDTO.getEmail());

        if ((existingEmployee != null && existingEmployee.getEmail() != null && !existingEmployee.getEmail().isEmpty())
          ||(existingEmployee != null && existingEmployee.getEmpId() != null && !existingEmployee.getEmpId().isEmpty()))
        {
            result.rejectValue("email", "error.email", "Account already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("employee", employeeDTO);

            return "redirect:/signup?error";
        }

        employeeService.saveEmployee(employeeDTO);
        return "redirect:/signup?success";
    }





    @GetMapping("/getEmployees")
        public String getEmployees(Model model)
        {
            List<EmployeeDTO> employeesList = employeeService.getAllEmployees();
            model.addAttribute("employees",employeesList);
            return "employees";

        }








  /*  @PutMapping("/employees/{empId}")
    public String updateEmployee(@PathVariable("empId") int empId, ModelAttribute("employee") EmployeeDTO employeeDTO)
    {
        if(!employeeService.findEmployeeByEmail(employeeDTO.getEmail()) == null)
        {
            employeeService.addEmployee(employee);
        }

        if (!employeeService.doesEmployeeExist(employee.getEmpId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee does not exist");
        }

    }




     @PutMapping("/updateGuest")
            public Guest updateGuest(@RequestParam(name = "id") String id,
                                     @RequestBody GuestDTO guestDTO) throws ChangeSetPersister.NotFoundException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Guest guest = modelMapper.map(guestDTO, Guest.class);
        guest.setId(Integer.parseInt(id));

        //Return updated guest
        return guestService.updateGuest(guest);
    }

   */




//    @DeleteMapping("/deleteEmployees")
//    public void deleteEmployees()
//    {
//        employeeService.deleteEmployees();
//    }





//    @GetMapping("/employees/{empId}")
//    public Optional<Employee> getEmployeeById(@PathVariable("empId") int empId) {
//        return  employeeService.getEmployeeById(empId);
//    }



//    @PostMapping("/createEmployee")
//    public void createEmployee(@RequestBody Employee employee) {
//        employeeService.addEmployee(employee);
//    }





//    @PutMapping("/employees/{id}")
//    public void updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee) {
//        Optional<Employee> employeeData = employeeService.getEmployeeById(empId);
//
//        if (employeeData.isPresent()) {
//            Employee employeeUpdate = employeeData.get();
//            employeeUpdate.setEmailId(employee.getEmailId());
//            employeeUpdate.setPassword(employee.getPassword());
//            employeeUpdate.setEmpType(employee.getEmpType());
//            employeeService.addEmployee(employeeUpdate);
//        }
//    }




//    @DeleteMapping("/deleteEmployee/{empId}")
//    public void deleteEmployeeById(@PathVariable("empId") int empId)
//    {
//        employeeService.deleteEmployeeById(empId);
//    }

/*
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("empId") int empId,Employee employee) {
        if (!employeeService.doesEmployeeExist(employee.getEmpId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee does not exist");
        }
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }


*/

}
