package org.vidhyaratha.employeeassetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Asset;

import java.util.List;
import java.util.Set;


@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    public Asset findByAssetId(String assetId);
    public List<Asset> findByAssetType(String assetType);

    @Query(value = "Select DISTINCT assetType from assets where assetType is not null", nativeQuery = true)
    public List<String> findDistinctAssetTypes();

   // public void updateAssetStatus(String assetId, String status);
}
