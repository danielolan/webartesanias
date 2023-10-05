package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.DeliveryAddress;
import com.ucatolica.springrestapi.repository.AddDeliveryAddressRepository;
import com.ucatolica.springrestapi.service.AddDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de direcciones de entrega (AddDeliveryAddressService).
 */
@Service
public class AddDeliveryAddressImpl implements AddDeliveryAddressService {

    @Autowired
    private AddDeliveryAddressRepository eRepository;

    /**
     * Obtiene todas las direcciones de entrega.
     *
     * @return Una lista de todas las direcciones de entrega.
     */
    @Override
    public List<DeliveryAddress> getDeliveryAdd() {
        return eRepository.findAll();
    }

    /**
     * Guarda una dirección de entrega.
     *
     * @param deliveryAdd La dirección de entrega a guardar.
     * @return La dirección de entrega guardada.
     */
    @Override
    public DeliveryAddress saveDeliveryAdd(DeliveryAddress deliveryAdd) {
        return eRepository.save(deliveryAdd);
    }

    /**
     * Obtiene una única dirección de entrega por su ID.
     *
     * @param id El ID de la dirección de entrega a obtener.
     * @return La dirección de entrega encontrada.
     * @throws RuntimeException Si no se encuentra la dirección de entrega con el ID especificado.
     */
    @Override
    public DeliveryAddress getSingleDeliveryAdd(Long id) {
        Optional<DeliveryAddress> deliveryAdd = eRepository.findById(id);
        if (deliveryAdd.isPresent()) {
            return deliveryAdd.get();
        }
        throw new RuntimeException("Delivery address with ID not found: " + id);
    }

    /**
     * Elimina una dirección de entrega por su ID.
     *
     * @param id El ID de la dirección de entrega a eliminar.
     */
    @Override
    public void deleteDeliveryAdd(Long id) {
        eRepository.deleteById(id);
    }

    /**
     * Actualiza una dirección de entrega.
     *
     * @param deliveryAdd La dirección de entrega a actualizar.
     * @return La dirección de entrega actualizada.
     */
    @Override
    public DeliveryAddress updateDeliveryAdd(DeliveryAddress deliveryAdd) {
        return eRepository.save(deliveryAdd);
    }
}
