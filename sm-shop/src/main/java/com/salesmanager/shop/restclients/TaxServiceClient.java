package com.salesmanager.shop.restclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.TaxConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaxServiceClient {

    //private String taxServiceUrl = "http://localhost:8301";
    private String taxServiceUrl = "https://shopizer-tax-service-hb.cfapps.io";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public TaxConfiguration getTaxConfiguration(MerchantStore store) {
        final String url = taxServiceUrl + "/rest/admin/tax/taxconfiguration/edit";
        ResponseEntity responseEntity = restTemplate.postForEntity(url, store.getId(), ResponseEntity.class);
        return (TaxConfiguration) responseEntity.getBody();
    }

    public void saveTaxConfiguration(TaxConfiguration taxConfiguration, MerchantStore merchantStore) {
        final String url = taxServiceUrl + "/rest/admin/tax/taxconfiguration/save";
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("taxConfiguration", convertObjectToString(taxConfiguration));
        //convert entity MerchantStore to a DTO object
        reqBody.put("merchantStore", convertObjectToString(merchantStore));
        restTemplate.postForObject(url, reqBody, ResponseEntity.class);
    }

    private String convertObjectToString(Object input) {
        String output = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            output = objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return (output == null ? "": output);
    }
}
