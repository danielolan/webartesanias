package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.User;

import java.util.List;

/**
 * Interfaz que define los métodos para el servicio de usuarios (User).
 */
public interface UserService {

    /**
     * Obtiene todos los usuarios.
     *
     * @return Una lista de todos los usuarios.
     */
    List<User> getUser();

    /**
     * Guarda un usuario.
     *
     * @param user El usuario a guardar.
     * @return El usuario guardado.
     */
    User saveUser(User user);

    /**
     * Obtiene un único usuario por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return El usuario encontrado o null si no se encuentra.
     */
    User getSingleUser(Long id);

    /**
     * Elimina un usuario por su ID.
     *
     * @param id El ID del usuario a eliminar.
     */
    void deleteUser(Long id);

    /**
     * Actualiza un usuario.
     *
     * @param user El usuario a actualizar.
     * @return El usuario actualizado.
     */
    User updateUser(User user);
}
