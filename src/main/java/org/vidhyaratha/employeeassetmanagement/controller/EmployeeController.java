package org.vidhyaratha.employeeassetmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeAssetsDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.service.AssetService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeAssetsService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("employeeDTO")
public class EmployeeController
{
    //@Autowired
    private final EmployeeService employeeService;

    //@Autowired
    private final AssetService assetService;

    //@Autowired
    private final EmployeeAssetsService employeeAssetsService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AssetService assetService, EmployeeAssetsService employeeAssetsService) {
        this.employeeService = employeeService;
        this.assetService = assetService;
        this.employeeAssetsService = employeeAssetsService;
    }

    @ModelAttribute("employeeDTO")
    public EmployeeDTO setUpEmployee() {
        return new EmployeeDTO();
    }


//    @ModelAttribute("employeeAssetsDTO")
//    public EmployeeAssetsDTO setUpEmployeeAssets()
//    {
//        return new EmployeeAssetsDTO();
//    }



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



    @GetMapping("/logout")
    public String logout()
    {
        return "redirect:/signin?logout";
    }



    @PostMapping("/processSignin")
    public String signinEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                                                  BindingResult result,Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());

        if(existingEmployee != null && existingEmployee.getPassword().equals(employeeDTO.getPassword()))
        {

            return "redirect:/getEmployeeAssets/"+ employeeDTO.getEmpId();
            //return "success";
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





//    @GetMapping("/getEmployees")
//        public String getEmployees(Model model)
//        {
//            List<EmployeeDTO> employeesList = employeeService.getAllEmployees();
//            model.addAttribute("employees",employeesList);
//            return "employees";
//
//        }






    @GetMapping("/getEmployeeAssets/{employeeId}")
    public String getEmployeeAssets(@PathVariable String employeeId, Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);

        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);
        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);

        return "userpage";
    }





    @GetMapping("/returnDevice")
    public String showReturnDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        return "redirect:/"+ employeeDTO.getEmpId() +"/returnDevice";
    }







    @GetMapping("/{employeeId}/returnDevice")
    public String returnDevice(@PathVariable String employeeId, Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);
        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);

        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);
        return "returnDevice";
    }




    // To update the status of the asset
    @PostMapping("/processReturnDevice")
    public String processReturnDevice(@RequestParam("selectedAsset") String selectedAssetId,
                                      @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        assetService.updateAssetStatus(selectedAssetId,"Unassigned");
        return "redirect:/getEmployeeAssets/" + employeeDTO.getEmpId();
    }



    @GetMapping("/requestDevice")
    public String showRequestDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        return "redirect:/"+ employeeDTO.getEmpId() + "/requestDevice";
    }




    @GetMapping("/{employeeId}/requestDevice")
    public String requestDevice(@PathVariable String employeeId, Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);
        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);

        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);
        return "requestdevice";
    }





//    @PostMapping("/processRequestDevice")
//    public String processRequestDevice(@RequestParam("selectedType") String selectedAssetType,
//                                       @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
//    {
//       Asset asset = assetService.assignAssetToAssignedStatus(selectedAssetType, "Unassigned");
//        employeeAssetsService.assignAsset(employeeDTO.getEmpId(), asset.getAssetId(), asset.getAssetName());
//
//        return "redirect:/getEmployeeAssets/" + employeeDTO.getEmpId();
//    }







//    @DeleteMapping("/deleteDevice/{assetId}")
//    public String deleteDeviceById(@PathVariable("assetId") String assetId,
//                                     @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
//    {
//        assetService.updateAssetStatus(assetId,"unassigned");
//        return "userpage";
//    }





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
