package org.vidhyaratha.employeeassetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;


import java.util.List;

@Repository
public interface EmployeeAssetsRepository extends JpaRepository<EmployeeAssets, Long> {

    List<EmployeeAssets> findByEmployee(Employee employee);

    public void deleteById(Long id);

    public EmployeeAssets findByAsset(Asset asset);


}
