package com.shopizer.shop.services.taxservice.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopizer.shop.services.taxservice.model.MerchantConfiguration;
import com.shopizer.shop.services.taxservice.model.MerchantStore;
import com.shopizer.shop.services.taxservice.model.TaxConfiguration;
import com.shopizer.shop.services.taxservice.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/rest/admin/tax/taxconfiguration")
public class TaxConfigurationRestController {

    @Autowired
    private TaxService taxService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/edit")
    public TaxConfiguration getTaxConfiguration(@RequestBody Integer storeId) {
        TaxConfiguration taxConfiguration = taxService.getTaxConfiguration(storeId);
        if(taxConfiguration == null) {
            return new TaxConfiguration();
        }
        return taxConfiguration;
    }

    @PostMapping("/save")
    public String saveTaxConfiguration(@RequestBody Map<String, String> reqBody) {

        TaxConfiguration taxConfiguration = (TaxConfiguration) convertJsonToObject(reqBody.get("taxConfiguration").trim(), TaxConfiguration.class);
        MerchantStore merchantStore = (MerchantStore) convertJsonToObject(reqBody.get("merchantStore").trim(), MerchantStore.class);

        if(taxConfiguration != null && merchantStore != null) {
            MerchantConfiguration returnValue  = taxService.saveTaxConfiguration(taxConfiguration, merchantStore);
            if(returnValue == null) {
                return "FAILED";
            }
        }
        return "SUCCESS";
    }

    private Object convertJsonToObject(String jsonInput, Class<?> classType) {
        Object output = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            output = objectMapper.readValue(jsonInput, classType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return output;
    }

}
