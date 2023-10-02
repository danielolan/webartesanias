package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    //Consultar
    List<Employee> getEmployees();

    //Guardar
    Employee saveEmployee(Employee employee);

    //Conssultar por id
    Employee getSingleEmployee (Long id);

    //Eliminar
    void deleteEmployee (Long id);

    //Actualizar
    Employee updateEmployee (Employee employee);

    //Filtar por nombre
    List<Employee> getEmployeesByName(String name);


}
