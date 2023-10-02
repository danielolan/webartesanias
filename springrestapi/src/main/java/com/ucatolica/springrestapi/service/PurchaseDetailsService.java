package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.PurchaseDetails;

import java.util.List;

public interface PurchaseDetailsService {

    List<PurchaseDetails> getPurchaseDetails();

    PurchaseDetails savePurchaseDetails(PurchaseDetails purchaseDetails);

    PurchaseDetails getSinglePurchaseDetails (Long id);

    void deletePurchaseDetails (Long id);

    PurchaseDetails updatePurchaseDetails (PurchaseDetails purchaseDetails);
}
