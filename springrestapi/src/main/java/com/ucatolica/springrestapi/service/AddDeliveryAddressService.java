package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.DeliveryAddress;

import java.util.List;

/**
 * Interfaz que define los métodos para el servicio de direcciones de entrega (DeliveryAddress).
 */
public interface AddDeliveryAddressService {

    /**
     * Obtiene todas las direcciones de entrega.
     *
     * @return Una lista de todas las direcciones de entrega.
     */
    List<DeliveryAddress> getDeliveryAdd();

    /**
     * Guarda una dirección de entrega.
     *
     * @param deliveryAdd La dirección de entrega a guardar.
     * @return La dirección de entrega guardada.
     */
    DeliveryAddress saveDeliveryAdd(DeliveryAddress deliveryAdd);

    /**
     * Obtiene una única dirección de entrega por su ID.
     *
     * @param id El ID de la dirección de entrega a obtener.
     * @return La dirección de entrega encontrada o null si no se encuentra.
     */
    DeliveryAddress getSingleDeliveryAdd(Long id);

    /**
     * Elimina una dirección de entrega por su ID.
     *
     * @param id El ID de la dirección de entrega a eliminar.
     */
    void deleteDeliveryAdd(Long id);

    /**
     * Actualiza una dirección de entrega.
     *
     * @param deliveryAdd La dirección de entrega a actualizar.
     * @return La dirección de entrega actualizada.
     */
    DeliveryAddress updateDeliveryAdd(DeliveryAddress deliveryAdd);
}
