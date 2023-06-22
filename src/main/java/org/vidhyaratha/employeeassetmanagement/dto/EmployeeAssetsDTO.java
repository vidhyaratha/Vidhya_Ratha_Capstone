package org.vidhyaratha.employeeassetmanagement.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;

import java.util.Date;

public class EmployeeAssetsDTO
{
    private Long id;
    private String assetAssignedDate;
    private String approvedAdminName;
    //private String empId;
   // private String assetId;
    private Employee employee;
    private Asset asset;

    public EmployeeAssetsDTO() {
    }

    public EmployeeAssetsDTO(Long id, String assetAssignedDate, String approvedAdminName, Employee employee, Asset asset) {
        this.id = id;
        this.assetAssignedDate = assetAssignedDate;
        this.approvedAdminName = approvedAdminName;
        this.employee = employee;
        this.asset = asset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetAssignedDate() {
        return assetAssignedDate;
    }

    public void setAssetAssignedDate(String assetAssignedDate) {
        this.assetAssignedDate = assetAssignedDate;
    }

    public String getApprovedAdminName() {
        return approvedAdminName;
    }

    public void setApprovedAdminName(String approvedAdminName) {
        this.approvedAdminName = approvedAdminName;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
