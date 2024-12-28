package com.example.challenge.controller;

import com.example.challenge.entity.Company;
import com.example.challenge.entity.CreateEmployee;
import com.example.challenge.entity.Employee;
import com.example.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public List<Employee> listEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeInfo/id={id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getEmployeeCompany/id={id}")
    public Company getEmployeesByCompanyId(@PathVariable Integer id) {
        return employeeService.getCompanyByEmployeeId(id);
    }

    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody CreateEmployee createEmployee) {
        return employeeService.saveEmployee(createEmployee);
    }

    @PutMapping("/updateEmployee/id={id}")
    public Employee updateEmployee(@RequestBody CreateEmployee employee, @PathVariable Integer id) {
        return employeeService.updateEmployee(employee,id);
    }

    @DeleteMapping("/deleteEmployee/id={id}")
    public void deleteEmployee( @PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
