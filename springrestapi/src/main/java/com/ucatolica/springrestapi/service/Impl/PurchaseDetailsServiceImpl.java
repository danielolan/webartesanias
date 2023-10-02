package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.PurchaseDetails;
import com.ucatolica.springrestapi.repository.PurchaseDetailsRepository;
import com.ucatolica.springrestapi.service.PurchaseDetailsService;
import com.ucatolica.springrestapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseDetailsServiceImpl implements PurchaseDetailsService {


    @Autowired
    private PurchaseDetailsRepository eRepository;

    @Override
    public List<PurchaseDetails> getPurchaseDetails(){
        return eRepository.findAll();
    }

    @Override
    public PurchaseDetails savePurchaseDetails(PurchaseDetails purchaseDetails) {
        return eRepository.save(purchaseDetails);
    }

    @Override
    public PurchaseDetails getSinglePurchaseDetails(Long id) {
        Optional<PurchaseDetails> purchaseDetails = eRepository.findById(id);
        if(purchaseDetails.isPresent()) {
            return purchaseDetails.get();
        }
        throw new RuntimeException("No order details found with ID" +id);

    }

    @Override
    public void deletePurchaseDetails(Long id) {
        eRepository.deleteById(id);

    }

    @Override
    public PurchaseDetails updatePurchaseDetails(PurchaseDetails purchaseDetails) {
        return eRepository.save(purchaseDetails);
    }
}
