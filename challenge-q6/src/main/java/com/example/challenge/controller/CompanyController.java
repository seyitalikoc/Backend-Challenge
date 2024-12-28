package com.example.challenge.controller;

import com.example.challenge.entity.Company;
import com.example.challenge.entity.CreateCompany;
import com.example.challenge.entity.Employee;
import com.example.challenge.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public List<Company> listCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/getCompanyInfo/id={id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/getCompanyEmployees/id={id}")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Integer id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @PostMapping("/saveCompany")
    public Company saveCompany(@RequestBody CreateCompany createCompany) {
        return companyService.saveCompany(createCompany);
    }

    @PutMapping("/updateCompany/id={id}")
    public Company updateCompany(@RequestBody CreateCompany company, @PathVariable Integer id) {
        return companyService.updateCompany(company,id);
    }

    @DeleteMapping("/delete/id={id}")
    public void deleteCompany( @PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

}
