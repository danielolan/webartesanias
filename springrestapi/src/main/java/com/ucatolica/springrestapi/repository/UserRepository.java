package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,  Long> {
}
