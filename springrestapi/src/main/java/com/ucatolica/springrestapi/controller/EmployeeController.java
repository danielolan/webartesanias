package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Employee;
import com.ucatolica.springrestapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //@Controller + @ResponseBody
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService eService;



    //localhost:8081/api/employees - Muestra lista
    @GetMapping("/employees")
    public ResponseEntity <List<Employee>> getEmployees(){
        return new ResponseEntity<List<Employee>>(eService.getEmployees(), HttpStatus.OK) ;
    }

    //localhost:8081/api/employees/12 -Obtiene informacion por id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
    }

    //localhost:8081/api/employees - Guarda la informacion
    @PostMapping ("/employees")
    public ResponseEntity <Employee> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<Employee> (eService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //localhost:8081/api/employees/1 --  Actualizacion de la informacion
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    //localhost:8081/api/employees?id=12 - borra informacion
    @DeleteMapping ("/employees")
    public ResponseEntity <HttpStatus> deteleEmployee(@RequestParam Long id){
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    //localhost:8081/api/employees/filterByName?name=xxx - Filtrar nombre
    @GetMapping("/employees/filterByName")
    public ResponseEntity <List<Employee>> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name), HttpStatus.OK);
    }


}
