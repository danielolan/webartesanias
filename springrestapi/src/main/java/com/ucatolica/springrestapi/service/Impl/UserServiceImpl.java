package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.User;
import com.ucatolica.springrestapi.repository.UserRepository;
import com.ucatolica.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de usuarios (UserService).
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository eRepository;

    /**
     * Obtiene todos los usuarios.
     *
     * @return Una lista de todos los usuarios.
     */
    @Override
    public List<User> getUser() {
        return eRepository.findAll();
    }

    /**
     * Guarda un usuario.
     *
     * @param userInfo El usuario a guardar.
     * @return El usuario guardado.
     */
    @Override
    public User saveUser(User userInfo) {
        return eRepository.save(userInfo);
    }

    /**
     * Obtiene un único usuario por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return El usuario encontrado.
     * @throws RuntimeException Si no se encuentra el usuario con el ID especificado.
     */
    @Override
    public User getSingleUser(Long id) {
        Optional<User> userInfo = eRepository.findById(id);
        if (userInfo.isPresent()) {
            return userInfo.get();
        }
        throw new RuntimeException("User not found by ID: " + id);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id El ID del usuario a eliminar.
     */
    @Override
    public void deleteUser(Long id) {
        eRepository.deleteById(id);
    }

    /**
     * Actualiza un usuario.
     *
     * @param userInfo El usuario a actualizar.
     * @return El usuario actualizado.
     */
    @Override
    public User updateUser(User userInfo) {
        return eRepository.save(userInfo);
    }
}
