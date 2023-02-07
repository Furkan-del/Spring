package com.example.demorest.Controller;

import com.example.demorest.Service.impl.DemoService;

import com.example.demorest.model.Employee;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// this class is our controller class like an Apı Layer
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final DemoService demoService;
    public EmployeeController(DemoService demoService) {
        this.demoService=demoService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> all() {
     return   ResponseEntity.ok(demoService.getAllData());
    }

    @PostMapping("/employeepost")
    List<Employee>  postEmployee() {
        Employee employee = new Employee();
        employee.setName("DONALD");
        employee.setRole("serverant");
        Employee employee2 = new Employee();
        employee2.setName("mojo");
        employee2.setRole("tester");
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
        return demoService.postAllData(list);

    }



   /* @PutMapping("/employeupdate/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee){
        return demoService.updateData(id,employee);
    }*/

    //for single item!!
   /* @GetMapping("/employeeget/{id}")
    Employee one(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

    }*/

/*  @PutMapping("/employeeput/{id}")
   Employee replaceEmployee( Employee newEmployee, @PathVariable Long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(
                        employee -> {
                            employee.setName("mert");
                            employee.setRole("asd");
                            employeeRepository.save(employee);
                        },
                        ()->{
                            throw new RuntimeException();
                        }
                );
        return null;
    }*/




    @PutMapping("employeeupdate/{id}")
    public ResponseEntity<Employee> Employee(@PathVariable("id") Long id,
                      @RequestBody Employee employee){
         return demoService.updateEmployee(id,employee);

    }


    @DeleteMapping("employeedelete/{studentId}")
     void deleteEmployee(@PathVariable("studentId") Long studentId){
        demoService.deleteData(studentId);
    }


    //dependencyInjection


}
