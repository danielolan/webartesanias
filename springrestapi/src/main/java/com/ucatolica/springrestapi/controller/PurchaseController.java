package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Purchase;
import com.ucatolica.springrestapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    private PurchaseService eService;

    @GetMapping("/purchase")
    public List<Purchase> getPurchases() {
        return eService.getPurchases();
    }


    @GetMapping("/purchase/{id}")
    public Purchase getPurchase(@PathVariable("id") Long id) {
        return eService.getSinglePurchase(id);
    }

    @PostMapping("/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return eService.savePurchase(purchase);
    }

    @PutMapping("/purchase/{id}")
    public Purchase updatePurchase(@PathVariable Long id, @RequestBody Purchase purchase) {

        purchase.setPurchase_id(id);
        return eService.updatePurchase(purchase);

    }


    @DeleteMapping("/purchase")
    public void deletePurchase(@RequestParam("id")Long id) {
        eService.deletePurchase(id);
    }
}
