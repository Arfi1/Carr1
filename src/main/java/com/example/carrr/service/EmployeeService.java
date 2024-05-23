package com.example.carrr.service;

import com.example.carrr.model.Employee;
import com.example.carrr.repository.EmployeeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployee(int id) {
        return employeeRepository.getEmployee(id);
    }

    public void delete(int id) {
        employeeRepository.delete(id);
    }

    public Employee authenticate(String username, String userPassword) {
        Employee employee = employeeRepository.findByUserName(username);
        if (employee != null && employee.getUserPassword().equals(userPassword)) {
            return employee; // succes autentificering
        }
        return null; // employee kunne ikke autentificeres
    }

    public Employee findEmployeeByUsername(String username) {
        return employeeRepository.findByUserName(username);
    }

    public void saveEmployee(Employee newEmployee) {
        employeeRepository.saveOrUpdate(newEmployee);
    }

    public boolean checkSession(HttpSession session) {
        // Check if the session is valid by verifying if an employee is logged in
        return session.getAttribute("employee") != null;
    }
}
