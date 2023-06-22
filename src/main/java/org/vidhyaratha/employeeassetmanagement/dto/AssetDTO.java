package org.vidhyaratha.employeeassetmanagement.dto;

public class AssetDTO
{
    //private Long id;
    private Long assetId;
    private String assetType;
    private String assetName;
    private String status;
    private String assetCreatedDate;

    public AssetDTO() {
    }

    public AssetDTO( Long assetId, String assetType, String assetName, String status, String assetCreatedDate) {

        this.assetId = assetId;
        this.assetType = assetType;
        this.assetName = assetName;
        this.status = status;
        this.assetCreatedDate = assetCreatedDate;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssetCreatedDate() {
        return assetCreatedDate;
    }

    public void setAssetCreatedDate(String assetCreatedDate) {
        this.assetCreatedDate = assetCreatedDate;
    }
}

