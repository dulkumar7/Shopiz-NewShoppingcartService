package com.salesmanager.shop.restclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.tax.TaxConfiguration;
import com.salesmanager.shop.dto.MerchantStoreDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaxServiceClient {

    private String taxServiceUrl = "http://localhost:8301";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

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

    private MerchantStoreDto convertToDto(MerchantStore store) {
        MerchantStoreDto storeDto = modelMapper.map(store, MerchantStoreDto.class);
        return storeDto;
    }


}
