package com.shopizer.shop.services.taxservice.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.shopizer.shop.services.taxservice.model.TaxConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final static String TAX_CONFIGURATION = "TAX_CONFIG";

    @Autowired
    private MerchantConfigurationService merchantConfigurationService;

    public TaxConfiguration getTaxConfiguration(MerchantStore store) throws ServiceException {
        String merchantConfigValue = null;
        TaxConfiguration taxConfiguration = null;
        try {
            MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(TAX_CONFIGURATION, store);
            if(configuration != null) {
                merchantConfigValue = configuration.getValue();
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
