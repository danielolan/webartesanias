package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Employee;

import java.util.List;

/**
 * Interfaz que define los métodos para el servicio de empleados (Employee).
 */
public interface EmployeeService {

    /**
     * Obtiene todos los empleados.
     *
     * @return Una lista de todos los empleados.
     */
    List<Employee> getEmployees();

    /**
     * Guarda un empleado.
     *
     * @param employee El empleado a guardar.
     * @return El empleado guardado.
     */
    Employee saveEmployee(Employee employee);

    /**
     * Obtiene un único empleado por su ID.
     *
     * @param id El ID del empleado a obtener.
     * @return El empleado encontrado o null si no se encuentra.
     */
    Employee getSingleEmployee(Long id);

    /**
     * Elimina un empleado por su ID.
     *
     * @param id El ID del empleado a eliminar.
     */
    void deleteEmployee(Long id);

    /**
     * Actualiza un empleado.
     *
     * @param employee El empleado a actualizar.
     * @return El empleado actualizado.
     */
    Employee updateEmployee(Employee employee);

    /**
     * Obtiene una lista de empleados por su nombre.
     *
     * @param name El nombre por el cual filtrar los empleados.
     * @return Una lista de empleados con el nombre especificado.
     */
    List<Employee> getEmployeesByName(String name);
}
