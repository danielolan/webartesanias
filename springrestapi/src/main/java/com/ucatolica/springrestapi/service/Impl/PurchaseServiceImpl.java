package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.Purchase;
import com.ucatolica.springrestapi.repository.PurchaseRepository;
import com.ucatolica.springrestapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de compras (PurchaseService).
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository eRepository;

    /**
     * Obtiene todas las compras.
     *
     * @return Una lista de todas las compras.
     */
    @Override
    public List<Purchase> getPurchases() {
        return eRepository.findAll();
    }

    /**
     * Guarda una compra.
     *
     * @param purchase La compra a guardar.
     * @return La compra guardada.
     */
    @Override
    public Purchase savePurchase(Purchase purchase) {
        return eRepository.save(purchase);
    }

    /**
     * Obtiene una única compra por su ID.
     *
     * @param id El ID de la compra a obtener.
     * @return La compra encontrada.
     * @throws RuntimeException Si no se encuentra la compra con el ID especificado.
     */
    @Override
    public Purchase getSinglePurchase(Long id) {
        Optional<Purchase> purchase = eRepository.findById(id);
        if (purchase.isPresent()) {
            return purchase.get();
        }
        throw new RuntimeException("No purchase found with the ID: " + id);
    }

    /**
     * Elimina una compra por su ID.
     *
     * @param id El ID de la compra a eliminar.
     */
    @Override
    public void deletePurchase(Long id) {
        eRepository.deleteById(id);
    }

    /**
     * Actualiza una compra.
     *
     * @param purchase La compra a actualizar.
     * @return La compra actualizada.
     */
    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return eRepository.save(purchase);
    }
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void deleteAllPurchases() {
        purchaseRepository.deleteAll();
    }
}
