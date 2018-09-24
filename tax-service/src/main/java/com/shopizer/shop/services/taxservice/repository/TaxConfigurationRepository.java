package com.shopizer.shop.services.taxservice.repository;

import com.shopizer.shop.services.taxservice.model.TaxConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaxConfigurationRepository extends JpaRepository<TaxConfigEntity, Integer> {

    @Query("select merchantConfigValue from TaxConfigEntity where taxConfigKey=:taxConfigKey and storeId=:storeId")
    public String getTaxConfigurationForMerchant(@Param("taxConfigKey") String taxConfigKey, @Param("storeId") String storeId);
}
