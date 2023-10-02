package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.PurchaseDetails;
import com.ucatolica.springrestapi.service.PurchaseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseDetailsControlller {
    @Autowired
    private PurchaseDetailsService eService;

    @GetMapping("/purchaseDetails")
    public List<PurchaseDetails> getPurchasesDetails() {
        return eService.getPurchaseDetails();
    }

    @GetMapping("/purchaseDetails/{id}")
    public PurchaseDetails getPurchaseDetails(@PathVariable("id") Long id) {
        return eService.getSinglePurchaseDetails(id);
    }

    @PostMapping("/purchaseDetails")
    public PurchaseDetails savePurchaseDetails(@RequestBody PurchaseDetails purchaseDetails) {
        return eService.savePurchaseDetails(purchaseDetails);
    }

    @PutMapping("/purchaseDetails/{id}")
    public PurchaseDetails updatePurchaseDetails(@PathVariable Long id, @RequestBody PurchaseDetails purchaseDetails) {
        purchaseDetails.setDetails_id(id);
        return eService.updatePurchaseDetails(purchaseDetails);
    }

    @DeleteMapping("/purchaseDetails")
    public void deletePurchaseDetails(@RequestParam("id") Long id) {
        eService.deletePurchaseDetails(id);
    }
}
