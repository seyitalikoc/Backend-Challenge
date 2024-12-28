package com.example.challenge.service;

import com.example.challenge.entity.Company;
import com.example.challenge.entity.CreateCompany;
import com.example.challenge.entity.Employee;
import com.example.challenge.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id);
    }

    public List<Employee> getEmployeesByCompanyId(int id) {
        return companyRepository.findById(id).getEmployees();
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveCompany(CreateCompany createCompany) {
        Company company = new Company(createCompany.getCompanyName(), createCompany.getCompanyAddress());
        return companyRepository.save(company);
    }

    public Company updateCompany(CreateCompany createCompany, int id) {
        Company company = companyRepository.findById(id);
        company.setCompanyName(createCompany.getCompanyName());
        company.setCompanyAddress(createCompany.getCompanyAddress());
        return companyRepository.save(company);
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById((long) id);
    }
}
