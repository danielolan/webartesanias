package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.DeliveryAddress;

import java.util.List;

public interface AddDeliveryAddressService {
    List<DeliveryAddress> getDeliveryAdd();

    DeliveryAddress saveDeliveryAdd(DeliveryAddress deliveryAdd);

    DeliveryAddress getSingleDeliveryAdd(Long id);

    void deleteDeliveryAdd(Long id);

    DeliveryAddress updateDeliveryAdd (DeliveryAddress deliveryAdd);
}
