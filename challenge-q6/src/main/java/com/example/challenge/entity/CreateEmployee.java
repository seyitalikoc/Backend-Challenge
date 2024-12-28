package com.example.challenge.entity;

public class CreateEmployee {
    private String employeeName;
    private int companyId;

    public CreateEmployee() {
    }

    public CreateEmployee(String employeeName, String employeeAddress, int companyId) {
        this.employeeName = employeeName;
        this.companyId = companyId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
