package com.example.challenge.service;

import com.example.challenge.entity.Company;
import com.example.challenge.entity.CreateEmployee;
import com.example.challenge.entity.Employee;
import com.example.challenge.repository.CompanyRepository;
import com.example.challenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Company getCompanyByEmployeeId(int id) {
        return employeeRepository.findById(id).getCompany();
    }

    public Employee saveEmployee(CreateEmployee createEmployee) {
        Employee employee = new Employee();
        employee.setEmployeeName(createEmployee.getEmployeeName());
        Company company = companyService.getCompanyById(createEmployee.getCompanyId());
        employee.setCompany(company);

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(CreateEmployee createEmployee, long id) {
        Employee employee = new Employee();
        employee.setEmployeeName(createEmployee.getEmployeeName());
        Company company = companyService.getCompanyById(createEmployee.getCompanyId());
        employee.setCompany(company);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById((long) id);
    }
}
