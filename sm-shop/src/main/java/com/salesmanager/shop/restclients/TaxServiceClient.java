package com.salesmanager.shop.restclients;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.TaxConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TaxServiceClient {

    private String taxServiceUrl = "http://localhost:8301";

    private RestTemplate restTemplate;

    public TaxServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TaxConfiguration getTaxConfiguration(MerchantStore store) {
        final String url = taxServiceUrl + "/admin/tax/taxconfiguration/edit";
        ResponseEntity responseEntity = restTemplate.postForEntity(url, store, ResponseEntity.class);
        return (TaxConfiguration) responseEntity.getBody();
    }
}
