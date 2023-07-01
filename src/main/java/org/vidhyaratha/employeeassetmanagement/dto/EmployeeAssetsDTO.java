package org.vidhyaratha.employeeassetmanagement.dto;


import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.User;


public class EmployeeAssetsDTO {
    private Long id;
    private String assetAssignedDate;
    private String approvedAdminName;
    private User user;
    private Asset asset;

    public EmployeeAssetsDTO() {
    }

    public EmployeeAssetsDTO(Long id, String assetAssignedDate, String approvedAdminName, User user, Asset asset) {
        this.id = id;
        this.assetAssignedDate = assetAssignedDate;
        this.approvedAdminName = approvedAdminName;
        this.user = user;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
