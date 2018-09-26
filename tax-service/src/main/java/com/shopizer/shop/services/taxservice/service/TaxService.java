package com.shopizer.shop.services.taxservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.shopizer.shop.services.taxservice.model.MerchantConfiguration;
import com.shopizer.shop.services.taxservice.model.MerchantStore;
import com.shopizer.shop.services.taxservice.model.TaxConfiguration;
import com.shopizer.shop.services.taxservice.repository.TaxConfigurationRepository;
import com.shopizer.shop.services.taxservice.repository.TaxMerchantConfigRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final static String TAX_CONFIGURATION = "TAX_CONFIG";

    @Autowired
    private TaxConfigurationRepository taxConfigurationRepository;

    @Autowired
    private TaxMerchantConfigRepository taxMerchantConfigRepository;

    public TaxConfiguration getTaxConfiguration(Integer storeId) throws ServiceException {
        MerchantConfiguration merchantConfiguration = null;
        TaxConfiguration taxConfiguration = null;
        try {
            merchantConfiguration = taxMerchantConfigRepository.findMerchantConfiguration(storeId, TAX_CONFIGURATION);
            if(merchantConfiguration != null) {
                ObjectMapper mapper = new ObjectMapper();
                taxConfiguration = mapper.readValue(merchantConfiguration.getValue(), TaxConfiguration.class);
            }

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception ex) {
            throw new ServiceException("Cannot parse json string " + merchantConfiguration.getValue());
        }
        return taxConfiguration;
    }


    public MerchantConfiguration saveTaxConfiguration(TaxConfiguration shippingConfiguration, MerchantStore store) throws ServiceException {

        MerchantConfiguration merchantConfiguration = taxMerchantConfigRepository.findMerchantConfiguration(store.getId(), TAX_CONFIGURATION);

        if(merchantConfiguration == null) {
            merchantConfiguration = new MerchantConfiguration();
            merchantConfiguration.setMerchantStore(store);
            merchantConfiguration.setKey(TAX_CONFIGURATION); //mapped
        }

        String value = shippingConfiguration.toJSONString();
        merchantConfiguration.setValue(value); //maped
        
        MerchantConfiguration createdConfig = taxMerchantConfigRepository.saveAndFlush(merchantConfiguration);
        return createdConfig;

    }

}
