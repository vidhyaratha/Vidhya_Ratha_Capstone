package org.vidhyaratha.employeeassetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;


import java.util.List;

@Repository
public interface EmployeeAssetsRepository extends JpaRepository<EmployeeAssets, Long> {

    List<EmployeeAssets> findByEmployee(Employee employee);

    //public String save(EmployeeAssets employeeAssets);

    //public EmployeeAssets findByEmployeeIdAndAssetId(String empId, Long assetId);

    //public void deleteByAssetId(Long assetId);

   // public List<EmployeeAssets> findAllAssetsByEmpId(String empId);

    //public Employee findemployeeByEmail(String email);
}
