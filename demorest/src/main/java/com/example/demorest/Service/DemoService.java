package com.example.demorest.Service;

import com.example.demorest.Repository.EmployeeRepository;
import com.example.demorest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public Employee postData(Employee employee) {
        return employeeRepository.save(employee);
    }

//    public Employee updateData(Long id, Employee employee) {
//        Employee employee1=employeeRepository.findById(id).orElseThrow(() -> new NullPointerException());
//        employee1.setName(employee.getName());
//        employee1.setId(employee.getId());
//        employee1.setRole(employee.getRole());
//        return employeeRepository.save(employee1);
//    }


    public void deleteData(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with Id" + id + "does not exists");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public ResponseEntity<Employee> updateEmployee(Long id, @RequestBody Employee employee) {
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(() -> new NullPointerException());

        updateEmployee.setName(employee.getName());
        updateEmployee.setRole(employee.getRole());
        employeeRepository.save(employee);
        return ResponseEntity.ok(updateEmployee);


    }

    public  ResponseEntity<Employee> patchEmployee(@PathVariable Long id,@RequestBody  Employee employee) {
       try{
           Employee employee1=employeeRepository.findById(id).get();
           employee1.setRole(employee.getRole());
           return new ResponseEntity<Employee>(employeeRepository.save(employee), HttpStatus.OK);

       }
          catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
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
