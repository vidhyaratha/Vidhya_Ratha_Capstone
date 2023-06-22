package org.vidhyaratha.employeeassetmanagement.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class EmployeeAssetsDTO
{
    private Long id;
    private String assetAssignedDate;
    private String approvedAdminName;
    private String empId;
    private Long assetId;

    public EmployeeAssetsDTO() {
    }

    public EmployeeAssetsDTO(Long id, String assetAssignedDate, String approvedAdminName, String empId, Long assetId) {
        this.id = id;
        this.assetAssignedDate = assetAssignedDate;
        this.approvedAdminName = approvedAdminName;
        this.empId = empId;
        this.assetId = assetId;
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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }
}
