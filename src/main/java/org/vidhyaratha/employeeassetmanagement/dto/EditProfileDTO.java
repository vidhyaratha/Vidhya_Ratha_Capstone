package org.vidhyaratha.employeeassetmanagement.dto;

public class EditProfileDTO {

   private String empName;
   private String email;
   private String password;
   private String confirmPassword;


   public EditProfileDTO() {
   }

   public EditProfileDTO(String empName, String email, String password, String confirmPassword) {
      this.empName = empName;
      this.email = email;
      this.password = password;
      this.confirmPassword = confirmPassword;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getConfirmPassword() {
      return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword) {
      this.confirmPassword = confirmPassword;
   }

   public String getEmpName() {
      return empName;
   }

   public void setEmpName(String empName) {
      this.empName = empName;
   }
}
