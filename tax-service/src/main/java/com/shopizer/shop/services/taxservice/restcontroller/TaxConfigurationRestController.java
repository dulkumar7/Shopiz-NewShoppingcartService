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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/admin/tax/taxconfiguration")
public class TaxConfigurationRestController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/edit")
    public ResponseEntity<TaxConfiguration> getTaxConfiguration(@RequestBody Integer storeId) {
        TaxConfiguration taxConfiguration = taxService.getTaxConfiguration(storeId);
        if(taxConfiguration == null) {
            return new ResponseEntity<>(new TaxConfiguration(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(taxConfiguration, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveTaxConfiguration(@RequestBody Map<String, String> reqBody) {

        TaxConfiguration taxConfiguration = (TaxConfiguration) convertJsonToObject(reqBody.get("taxConfiguration").trim(), TaxConfiguration.class);
        MerchantStore merchantStore = (MerchantStore) convertJsonToObject(reqBody.get("merchantStore").trim(), MerchantStore.class);

        if(taxConfiguration != null && merchantStore != null) {
            MerchantConfiguration returnValue  = taxService.saveTaxConfiguration(taxConfiguration, merchantStore);
            if(returnValue != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Object convertJsonToObject(String jsonInput, Class<?> classType) {
        ObjectMapper mapper = new ObjectMapper();
        Object output = null;
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            output = mapper.readValue(jsonInput, classType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return output;
    }
    
    private String convertObjectToString(Object input) {
        ObjectMapper mapper = new ObjectMapper();
        String output = null;
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            output = mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return (output == null ? "": output);
    }

}
