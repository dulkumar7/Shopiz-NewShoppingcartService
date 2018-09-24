package com.shopizer.shop.services.taxservice.restcontroller;

import com.shopizer.shop.services.taxservice.model.TaxConfiguration;
import com.shopizer.shop.services.taxservice.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tax/taxconfiguration")
public class TaxConfigurationRestController {

    private TaxService taxService;

    public TaxConfigurationRestController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping("/edit")
    public ResponseEntity<TaxConfiguration> getTaxConfiguration(@RequestBody String storeId) {
        TaxConfiguration taxConfiguration = taxService.getTaxConfiguration(storeId);
        if(taxConfiguration == null) {
            return new ResponseEntity<>(new TaxConfiguration(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(taxConfiguration, HttpStatus.OK);
    }
}
