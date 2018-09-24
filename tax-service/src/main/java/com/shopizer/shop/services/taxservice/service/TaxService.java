package com.shopizer.shop.services.taxservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopizer.shop.services.taxservice.model.TaxConfiguration;
import com.shopizer.shop.services.taxservice.repository.TaxConfigurationRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final static String TAX_CONFIGURATION = "TAX_CONFIG";

    private TaxConfigurationRepository taxConfigurationRepository;

    public TaxService(TaxConfigurationRepository taxConfigurationRepository) {
        this.taxConfigurationRepository = taxConfigurationRepository;
    }

    public TaxConfiguration getTaxConfiguration(String storeId) throws ServiceException {
        String merchantConfigValue = null;
        TaxConfiguration taxConfiguration = null;
        try {
            merchantConfigValue = taxConfigurationRepository.getTaxConfigurationForMerchant(TAX_CONFIGURATION, storeId);
            if(merchantConfigValue != null && !merchantConfigValue.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                taxConfiguration = mapper.readValue(merchantConfigValue, TaxConfiguration.class);
            }

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception ex) {
            throw new ServiceException("Cannot parse json string " + merchantConfigValue);
        }
        return taxConfiguration;
    }
}
