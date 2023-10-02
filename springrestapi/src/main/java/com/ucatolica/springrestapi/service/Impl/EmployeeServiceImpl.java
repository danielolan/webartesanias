package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.Employee;
import com.ucatolica.springrestapi.repository.EmployeeRepository;
import com.ucatolica.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository eRepository;


    //Consultar
    @Override
    public List<Employee> getEmployees() {

        return eRepository.findAll();
    }

    //Guardar
    @Override
    public Employee saveEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    //Consultar por id
    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee = eRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new RuntimeException("Employee is not found for the id" +id);
    }

    //Eliminar
    @Override
    public void deleteEmployee(Long id) {
        eRepository.deleteById(id);
    }

    //Actualizar
    @Override
    public Employee updateEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    //Filtrar por nombre
    @Override
    public List<Employee> getEmployeesByName(String name) {
        return eRepository.findByName(name);
    }





}


    /*
    //Aca se agregan los datos a llenar en la lista
	//Los cuales son mostrados al momento de hacer el request
	//	private static List<Employee> list = new ArrayList<>();

    private static List <Employee> list = new ArrayList<>();
    static {

        Employee e = new Employee();
        e.setName("Juan");
        e.setAge(30L);
        e.setDepartment("TI");
        e.setEmail("jc@gmail.com");
        e.setLocation("Bogota");
        list.add(e);

        e = new Employee();
        e.setName("Maria");
        e.setAge(32L);
        e.setDepartment("TI");
        e.setEmail("mc@gmail.com");
        e.setLocation("Cali");
        list.add(e);
    }*/

