package com.ucatolica.springrestapi.service.Impl;


import com.ucatolica.springrestapi.model.DeliveryAddress;
import com.ucatolica.springrestapi.repository.AddDeliveryAddressRepository;
import com.ucatolica.springrestapi.service.AddDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddDeliveryAddressImpl implements AddDeliveryAddressService {
    @Autowired
    private AddDeliveryAddressRepository eRepository;

    @Override
    public List<DeliveryAddress> getDeliveryAdd(){
        return eRepository.findAll();
    }

    @Override
    public DeliveryAddress saveDeliveryAdd(DeliveryAddress deliveryAdd) {
        return eRepository.save(deliveryAdd);
    }

    @Override
    public DeliveryAddress getSingleDeliveryAdd(Long id) {

        Optional<DeliveryAddress> deliveryAdd = eRepository.findById(id);
        if(deliveryAdd.isPresent()) {
            return deliveryAdd.get();
        }
        throw new RuntimeException("Shipping address with ID not found" +id);

    }

    @Override
    public void deleteDeliveryAdd(Long id) {
        eRepository.deleteById(id);

    }

    @Override
    public DeliveryAddress updateDeliveryAdd(DeliveryAddress deliveryAdd) {
        return eRepository.save(deliveryAdd);
    }
}
