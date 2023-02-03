package com.example.demorest;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id){
        super("Couldn not the employee "+id);
    }
}
