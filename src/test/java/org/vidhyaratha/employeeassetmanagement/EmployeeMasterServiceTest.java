//package org.vidhyaratha.employeeassetmanagement;
//
//
//import org.junit.Assert;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.vidhyaratha.employeeassetmanagement.model.EmployeeMaster;
//import org.vidhyaratha.employeeassetmanagement.repository.EmployeeMasterRepository;
//
//@SpringBootTest(classes = DemoApplication.class)
//@DisplayName("EmployeeMasterService Test Class")
//public class EmployeeMasterServiceTest
//{
//
//    private final EmployeeMasterRepository employeeMasterRepository;
//
//   @Autowired
//   public EmployeeMasterServiceTest(EmployeeMasterRepository employeeMasterRepository) {
//       this.employeeMasterRepository = employeeMasterRepository;
//   }
//
//
//    @Test
//    @DisplayName("Test Case : Validate Employee Id ")
//    public void getEmployeeByEmpIdTest()
//    {
//        EmployeeMaster employeeMaster = employeeMasterRepository.findEmployeeByempId("EID123");
//        Assert.assertNotNull(employeeMaster);
//    }
//
//
//}
