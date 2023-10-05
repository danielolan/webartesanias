package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.User;
import com.ucatolica.springrestapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las solicitudes relacionadas con los usuarios (User).
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService eService;

    /**
     * Obtiene todos los usuarios disponibles.
     *
     * @return Una lista de objetos User que representan a los usuarios.
     */
    @GetMapping("/user")
    public List<User> getUser() {
        return eService.getUser();
    }

    /**
     * Obtiene un usuario específico por su identificador.
     *
     * @param id El identificador único del usuario.
     * @return El usuario correspondiente al ID proporcionado.
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return eService.getSingleUser(id);
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param user El nuevo usuario a ser guardado y validado con anotaciones de Jakarta Validation.
     * @return El usuario guardado.
     */
    @PostMapping("/user")
    public User saveUser(@Valid @RequestBody User user) {
        return eService.saveUser(user);
    }

    /**
     * Actualiza un usuario existente por su identificador.
     *
     * @param id   El identificador único del usuario a ser actualizado.
     * @param user El usuario actualizado.
     * @return El usuario actualizado.
     */
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUser_id(id);
        return eService.updateUser(user);
    }

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id El identificador único del usuario a ser eliminado.
     */
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam("id") Long id) {
        eService.deleteUser(id);
    }
}
