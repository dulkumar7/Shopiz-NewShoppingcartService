package com.shopizer.shop.services.taxservice.restcontroller;

import com.shopizer.shop.services.taxservice.model.MerchantConfiguration;
import com.shopizer.shop.services.taxservice.model.MerchantStore;
import com.shopizer.shop.services.taxservice.model.TaxConfiguration;
import com.shopizer.shop.services.taxservice.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity saveTaxConfiguration(@RequestBody Map reqBody) {
        TaxConfiguration taxConfiguration = (TaxConfiguration) reqBody.get("taxConfiguration");
        MerchantStore merchantStore = (MerchantStore) reqBody.get("merchantStore");
        if(taxConfiguration != null && merchantStore != null) {
            MerchantConfiguration returnValue  = taxService.saveTaxConfiguration(taxConfiguration, merchantStore);
            if(returnValue != null) {
                return new ResponseEntity<>(returnValue, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new MerchantConfiguration(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
