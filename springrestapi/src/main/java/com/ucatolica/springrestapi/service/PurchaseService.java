package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {

    List<Purchase> getPurchases();

    Purchase savePurchase(Purchase purchase);

    Purchase getSinglePurchase (Long id);

    void deletePurchase(Long id);

    Purchase updatePurchase (Purchase purchase);
}
