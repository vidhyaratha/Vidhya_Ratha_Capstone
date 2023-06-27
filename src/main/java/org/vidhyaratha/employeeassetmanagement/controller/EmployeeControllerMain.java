package org.vidhyaratha.employeeassetmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeAssetsDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeMasterDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeMaster;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.service.AssetService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeAssetsService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeMasterService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//
//@Controller
//@SessionAttributes({"employeeDTO","employeeMaster"})
public class EmployeeControllerMain
{

    private final EmployeeService employeeService;
    private final AssetService assetService;
    private final EmployeeAssetsService employeeAssetsService;
    private final EmployeeMasterService employeeMasterService;

    @Autowired
    public EmployeeControllerMain(EmployeeService employeeService, AssetService assetService, EmployeeAssetsService employeeAssetsService, EmployeeMasterService employeeMasterService) {
        this.employeeService = employeeService;
        this.assetService = assetService;
        this.employeeAssetsService = employeeAssetsService;
        this.employeeMasterService = employeeMasterService;
    }






    @ModelAttribute("employeeDTO")
    public EmployeeDTO setUpEmployee() {
        return new EmployeeDTO();
    }


    @ModelAttribute("employeeMaster")
    public EmployeeMaster setUpMasterEmployee() {
        return new EmployeeMaster();
    }




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
       // EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeDTO.getEmpId());

       // if(masterEmployee != null && masterEmployee.getEmail() !=null && !masterEmployee.getEmail().isEmpty()) {


            Employee existingEmployeeI = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
            Employee existingEmployeeE = employeeService.findEmployeeByEmail(employeeDTO.getEmail());

//            if ((existingEmployee != null && existingEmployee.getEmail() != null && !existingEmployee.getEmail().isEmpty())
//                    || (existingEmployee != null && existingEmployee.getEmpId() != null && !existingEmployee.getEmpId().isEmpty()))

        if((existingEmployeeI!=null) || (existingEmployeeE!=null)) {
            if (existingEmployeeE.getEmail().equals(employeeDTO.getEmail()) || existingEmployeeI.getEmpId().equals(employeeDTO.getEmpId())) {
                //result.rejectValue("email", "error.email", "Account already exists");
                // }

                // if (result.hasErrors()) {
                model.addAttribute("employee", employeeDTO);
                return "redirect:/signup?error";
            }
        }
            //  else {
            employeeService.saveEmployee(employeeDTO);
            return "redirect:/signup?success";
            // }

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
        EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeId);

        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);
        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);
        model.addAttribute("employeeMaster",masterEmployee);

        return "userpage";
    }




    @GetMapping("/showDevice")
    public String showAssignedDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        return "redirect:/getEmployeeAssets/"+ employeeDTO.getEmpId();
    }




    @GetMapping("/returnDevice")
    public String showReturnDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        return "redirect:/"+ employeeDTO.getEmpId() +"/returnDevice";
    }







    @GetMapping("/{employeeId}/returnDevice")
    public String returnDevice(@PathVariable String employeeId, Model model)
    {
        //Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);
        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);

        EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeId);

        model.addAttribute("employeeAssets",employeeAssets);
       // model.addAttribute("employee",existingEmployee);
        model.addAttribute("employeeMaster",masterEmployee);

        return "returnDevice";
    }




    // To update the status of the asset
    @PostMapping("/processReturnDevice")
    public String processReturnDevice(@RequestParam("selectedAsset") String selectedAssetId,
                                      @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        assetService.updateAssetStatus(selectedAssetId,"Unassigned");

        employeeAssetsService.deleteByAssetId(selectedAssetId);

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
       // Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);
       //List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);
        List<String> assetTypeList = assetService.getAllAssetTypes();
        EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeId);


        model.addAttribute("assetTypes",assetTypeList);
        //model.addAttribute("employeeAssets",employeeAssets);
        //model.addAttribute("employee",existingEmployee);
        model.addAttribute("employeeMaster",masterEmployee);

        return "requestdevice";
    }





    @PostMapping("/processRequestDevice")
    public String processRequestDevice(@RequestParam("selectedType") String selectedAssetType,
                                       @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, Model model)
    {
        Asset asset = assetService.assignAssetToAssignedStatus(selectedAssetType, "Unassigned");
        if(asset.getAssetType()==null)
        {
            throw new RuntimeException("Please contact Admin");

        }

        employeeAssetsService.assignAsset(employeeDTO.getEmpId(), asset.getAssetId(), asset.getAssetName());
        return "redirect:/getEmployeeAssets/" + employeeDTO.getEmpId();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex,@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO ,Model model)
    {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("employeeDTO0", employeeDTO);
        return "error";
    }







    @GetMapping("/editEmployeeInformation")
    public String showupdateEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
       return "redirect:/"+ employeeDTO.getEmpId()+ "/editEmployee";
    }



    @GetMapping("/{employeeId}/editEmployee")
    public String updateEmployee(@PathVariable String employeeId, Model model)
    {
        EmployeeMaster employeeMaster = employeeMasterService.getEmployeeByEmpId(employeeId);
        model.addAttribute("employeeMaster",employeeMaster);
        return "editprofile";
    }




    @PostMapping("/processEditProfile")
    public String processEditProfile(@Valid @ModelAttribute("employeeMaster") EmployeeMaster employeeMaster)
    {
        employeeMasterService.saveEmployeeMaster(employeeMaster);
        return "/getEmployeeAssets/"+ employeeMaster.getEmpId();

    }





    @GetMapping("/faq")
    public String questions()
    {
        return "faq";
    }


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
