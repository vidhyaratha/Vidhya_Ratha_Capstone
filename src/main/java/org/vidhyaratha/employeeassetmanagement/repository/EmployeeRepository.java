package org.vidhyaratha.employeeassetmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>
{
    public Employee findEmployeeByEmail(String email);

    public Employee findEmployeeByEmpId(String empId);

}
