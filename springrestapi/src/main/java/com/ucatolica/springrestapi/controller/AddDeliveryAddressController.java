package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.DeliveryAddress;
import com.ucatolica.springrestapi.service.AddDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddDeliveryAddressController {

    @Autowired
    private AddDeliveryAddressService eService;

    @GetMapping("/delivery")
    public List<DeliveryAddress> getDeliveryAdds() {
        return eService.getDeliveryAdd();
    }

    @GetMapping("/delivery/{id}")
    public DeliveryAddress getDeliveryAdd(@PathVariable("id") Long id) {
        return eService.getSingleDeliveryAdd(id);
    }

    @PostMapping("/delivery")
    public DeliveryAddress saveDelivery_add (@RequestBody DeliveryAddress deliveryAdd) {
        return eService.saveDeliveryAdd(deliveryAdd);
    }

    @PutMapping("/delivery/{id}")
    public DeliveryAddress updateDeliveryAdd(@PathVariable Long id, @RequestBody DeliveryAddress deliveryAdd) {

        deliveryAdd.setAdd_id(id);
        return eService.saveDeliveryAdd(deliveryAdd);
    }

    @DeleteMapping("/delivery")
    public void deleteDeliveryAdd(@RequestParam("id") Long id) {
        eService.deleteDeliveryAdd(id);
    }


}
