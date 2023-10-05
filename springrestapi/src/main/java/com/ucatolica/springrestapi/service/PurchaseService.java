package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interfaz que define los métodos para el servicio de compras (Purchase).
 */
@Service
public interface PurchaseService {

    /**
     * Obtiene todas las compras.
     *
     * @return Una lista de todas las compras.
     */
    List<Purchase> getPurchases();

    /**
     * Guarda una compra.
     *
     * @param purchase La compra a guardar.
     * @return La compra guardada.
     */
    Purchase savePurchase(Purchase purchase);

    /**
     * Obtiene una única compra por su ID.
     *
     * @param id El ID de la compra a obtener.
     * @return La compra encontrada o null si no se encuentra.
     */
    Purchase getSinglePurchase(Long id);

    /**
     * Elimina una compra por su ID.
     *
     * @param id El ID de la compra a eliminar.
     */
    void deletePurchase(Long id);

    /**
     * Actualiza una compra.
     *
     * @param purchase La compra a actualizar.
     * @return La compra actualizada.
     */
    Purchase updatePurchase(Purchase purchase);
}
