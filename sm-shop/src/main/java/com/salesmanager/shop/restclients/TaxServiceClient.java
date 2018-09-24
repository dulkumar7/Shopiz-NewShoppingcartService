package com.salesmanager.shop.restclients;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.TaxConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaxServiceClient {

    private String taxServiceUrl = "http://localhost:8301";

    @Autowired
    private RestTemplate restTemplate;

    public TaxConfiguration getTaxConfiguration(MerchantStore store) {
        final String url = taxServiceUrl + "/admin/tax/taxconfiguration/edit";
        ResponseEntity responseEntity = restTemplate.postForEntity(url, store.getId(), ResponseEntity.class);
        return (TaxConfiguration) responseEntity.getBody();
    }

    public void saveTaxConfiguration(TaxConfiguration taxConfiguration, MerchantStore store) {
    }
}
