package com.example.Toko.controller;

import com.example.Toko.model.Merchant;
import com.example.Toko.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



@CrossOrigin("*")
@RequestMapping(value = "/api/merchant")
@RestController
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);

    @GetMapping(produces = "application/json")
    @Operation(summary = "Api to get all merchant")
    public List<Merchant> getMerchant() {
        return merchantService.getAllMerchant();
    }

    @PostMapping(value = "add-merchant")
    public ResponseEntity<Merchant> addMerchant(@RequestBody Merchant merchant) {
        log.info("Received request to add merchant with code: {}", merchant);


        Merchant addedMerchant = merchantService.addMerchant(Merchant.builder()
                .MerchantCode(merchant.getMerchantCode())
                .name(merchant.getName())
                .location(merchant.getLocation())
                .open(merchant.isOpen())
                .build());

        if (addedMerchant != null) {
            return ResponseEntity.ok(addedMerchant);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping(value = "update-merchant")
    public ResponseEntity updateMerchant(@RequestBody Merchant merchant) {
        Merchant updatedMerchant = merchantService.updateMerchant(merchant);
        if (updatedMerchant != null) {
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update merchant", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{MerchantCode}")
    public String deleteMerchant(@PathVariable("MerchantCode") String MerchantCode) {
        merchantService.deleteMerchant(MerchantCode);
        return "Delete merchant " + MerchantCode + " success!";
    }

    @GetMapping("/detail/{merchantCode}")
    public ResponseEntity getMerchantDetail(@PathVariable String merchantCode) {
        Merchant merchant = merchantService.getMerchantDetail(merchantCode);
        if (merchant != null) {
            return new ResponseEntity<>(merchant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Merchant not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/detail")
    @Operation(summary = "Getting detail of one merchant by merchant code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Merchant found"),
            @ApiResponse(responseCode = "404", description = "Inputted merchant code not found")
    })

    private List<?> testWildCard(){
        return Arrays.asList("String", 40);
    }

}
