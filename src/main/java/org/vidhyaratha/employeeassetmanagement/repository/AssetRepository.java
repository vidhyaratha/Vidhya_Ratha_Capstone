package org.vidhyaratha.employeeassetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Asset;


@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    public Asset findByAssetId(Long assetId);
    //public void updateAssetStatus(Long assetId, String status);
}
