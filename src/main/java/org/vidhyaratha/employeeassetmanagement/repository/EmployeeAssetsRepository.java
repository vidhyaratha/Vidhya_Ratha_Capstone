package org.vidhyaratha.employeeassetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;


import java.util.List;

@Repository
public interface EmployeeAssetsRepository extends JpaRepository<EmployeeAssets, Long> {

    List<EmployeeAssets> findByEmployee(Employee employee);

    //public EmployeeAssets findByAssetId(String assetId);

//    @Modifying
//    @Query(value = "delete from employeeassets e where e.assetId =: assetId", nativeQuery = true)
    public void deleteById(Long id);

    public EmployeeAssets findByAsset(Asset asset);

    //public String save(EmployeeAssets employeeAssets);

    //public EmployeeAssets findByEmployeeIdAndAssetId(String empId, String assetId);

   // public void deleteByAssetId(String assetId);

   //public List<EmployeeAssets> findAllAssetsByEmpId(String empId);

    //public Employee findemployeeByEmail(String email);
}
