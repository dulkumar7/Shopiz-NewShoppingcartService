package com.shopizer.shop.services.taxservice.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/admin/tax/taxclass")
public class TaxClassRestController {

    @PostMapping("/getclass")
    public ResponseEntity<String> getTaxClassList(@RequestBody String storeId) {
        return null;
    }

}
