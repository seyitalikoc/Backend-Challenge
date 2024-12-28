package com.example.challenge.repository;

import com.example.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findById(long id);

}
