package com.example.demorest.Service.impl;

import com.example.demorest.Repository.EmployeeRepository;
import com.example.demorest.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DemoService {


    private final EmployeeRepository employeeRepository;


    @Autowired
    public DemoService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }


    public List<Employee> getAllData() {
        return employeeRepository.findAll();
    }

    public List<Employee> postAllData(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

//    public Employee updateData(Long id, Employee employee) {
//        Employee employee1=employeeRepository.findById(id).orElseThrow(() -> new NullPointerException());
//        employee1.setName(employee.getName());
//        employee1.setId(employee.getId());
//        employee1.setRole(employee.getRole());
//        return employeeRepository.save(employee1);
//    }


    public void deleteData(Long id){
 boolean exists=employeeRepository.existsById(id);
  if(!exists){
      throw  new IllegalStateException("student with Id"+id+"does not exists");
  }
  employeeRepository.deleteById(id);
   }

   @Transactional
    public ResponseEntity<Employee> updateEmployee(Long id, @RequestBody Employee employee) {
Employee updateEmployee=employeeRepository.findById(id).orElseThrow(()-> new NullPointerException());

   updateEmployee.setName(employee.getName());
   updateEmployee.setRole(employee.getRole());
    employeeRepository.save(employee);
    return ResponseEntity.ok(updateEmployee);


   }

//    @Transactional
//    save(){
//
//    }
//
//    void findById(Long id){
//        if (id!=null){
//            employeeRepository.deleteById(id);
//
//        }
//        new NullPointerException();
//
//    }
}
