package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.DeliveryAddress;
import com.ucatolica.springrestapi.service.AddDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las solicitudes relacionadas con las direcciones de entrega (DeliveryAddress).
 */
@RestController
@RequestMapping("/api")
public class AddDeliveryAddressController {

    @Autowired
    private AddDeliveryAddressService eService;

    /**
     * Obtiene todas las direcciones de entrega disponibles.
     *
     * @return Una lista de objetos DeliveryAddress que representan las direcciones de entrega.
     */
    @GetMapping("/delivery")
    public List<DeliveryAddress> getDeliveryAdds() {
        return eService.getDeliveryAdd();
    }

    /**
     * Obtiene una dirección de entrega específica por su identificador.
     *
     * @param id El identificador único de la dirección de entrega.
     * @return La dirección de entrega correspondiente al ID proporcionado.
     */
    @GetMapping("/delivery/{id}")
    public DeliveryAddress getDeliveryAdd(@PathVariable("id") Long id) {
        return eService.getSingleDeliveryAdd(id);
    }

    /**
     * Guarda una nueva dirección de entrega.
     *
     * @param deliveryAdd La nueva dirección de entrega a ser guardada.
     * @return La dirección de entrega guardada.
     */
    @PostMapping("/delivery")
    public DeliveryAddress saveDelivery_add(@RequestBody DeliveryAddress deliveryAdd) {
        return eService.saveDeliveryAdd(deliveryAdd);
    }

    /**
     * Actualiza una dirección de entrega existente por su identificador.
     *
     * @param id          El identificador único de la dirección de entrega a ser actualizada.
     * @param deliveryAdd La dirección de entrega actualizada.
     * @return La dirección de entrega actualizada.
     */
    @PutMapping("/delivery/{id}")
    public DeliveryAddress updateDeliveryAdd(@PathVariable Long id, @RequestBody DeliveryAddress deliveryAdd) {
        deliveryAdd.setAdd_id(id);
        return eService.saveDeliveryAdd(deliveryAdd);
    }

    /**
     * Elimina una dirección de entrega por su identificador.
     *
     * @param id El identificador único de la dirección de entrega a ser eliminada.
     */
    @DeleteMapping("/delivery")
    public void deleteDeliveryAdd(@RequestParam("id") Long id) {
        eService.deleteDeliveryAdd(id);
    }
}
