package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que gestiona las operaciones de base de datos para los empleados (Employee).
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Encuentra empleados por su nombre.
     *
     * @param name El nombre del empleado a buscar.
     * @return Una lista de empleados con el nombre especificado.
     */
    List<Employee> findByName(String name);
}

//32



