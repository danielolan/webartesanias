package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.Purchase;
import com.ucatolica.springrestapi.repository.PurchaseRepository;
import com.ucatolica.springrestapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository eRepository;


    @Override
    public List<Purchase> getPurchases(){
        return eRepository.findAll();
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return eRepository.save(purchase);
    }

    @Override
    public Purchase getSinglePurchase(Long id) {
        Optional<Purchase> purchase = eRepository.findById(id);
        if(purchase.isPresent()) {
            return purchase.get();
        }
        throw new RuntimeException("No se encontro compra con el ID "+id);

    }

    @Override
    public void deletePurchase(Long id) {
        eRepository.deleteById(id);

    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {

        return eRepository.save(purchase);



    }
}
