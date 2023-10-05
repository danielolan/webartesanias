package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Purchase;
import com.ucatolica.springrestapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las solicitudes relacionadas con las compras (Purchase).
 */
@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    private PurchaseService eService;

    /**
     * Obtiene todas las compras disponibles.
     *
     * @return Una lista de objetos Purchase que representan las compras.
     */
    @GetMapping("/purchase")
    public List<Purchase> getPurchases() {
        return eService.getPurchases();
    }

    /**
     * Obtiene una compra específica por su identificador.
     *
     * @param id El identificador único de la compra.
     * @return La compra correspondiente al ID proporcionado.
     */
    @GetMapping("/purchase/{id}")
    public Purchase getPurchase(@PathVariable("id") Long id) {
        return eService.getSinglePurchase(id);
    }

    /**
     * Guarda una nueva compra.
     *
     * @param purchase La nueva compra a ser guardada.
     * @return La compra guardada.
     */
    @PostMapping("/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return eService.savePurchase(purchase);
    }

    /**
     * Actualiza una compra existente por su identificador.
     *
     * @param id       El identificador único de la compra a ser actualizada.
     * @param purchase La compra actualizada.
     * @return La compra actualizada.
     */
    @PutMapping("/purchase/{id}")
    public Purchase updatePurchase(@PathVariable Long id, @RequestBody Purchase purchase) {
        purchase.setPurchase_id(id);
        return eService.updatePurchase(purchase);
    }

    /**
     * Elimina una compra por su identificador.
     *
     * @param id El identificador único de la compra a ser eliminada.
     */
    @DeleteMapping("/purchase")
    public void deletePurchase(@RequestParam("id") Long id) {
        eService.deletePurchase(id);
    }
}
