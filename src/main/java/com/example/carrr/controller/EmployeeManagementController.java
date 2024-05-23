package com.example.carrr.controller;

import com.example.carrr.model.Usertype;
import com.example.carrr.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.carrr.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeManagementController {

    private final EmployeeService employeeService;
    private final Map<String, Integer> loginAttempts = new HashMap<>();

    @Autowired
    public EmployeeManagementController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/menu")
    public String menu(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        Usertype userType = (Usertype) session.getAttribute("userType");

        if (employee != null) {
            model.addAttribute("employee", employee);
            model.addAttribute("userType", userType);
            return "home/menu";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee != null && employee.getUsertype() == Usertype.ADMIN) {
            model.addAttribute("newEmployee", new Employee());
            model.addAttribute("allUserTypes", Usertype.values());
            return "home/registration";
        } else {
            return "redirect:/menu";
        }
    }

    @PostMapping("/registration")
    public String createNewEmployee(@ModelAttribute("newEmployee") Employee newEmployee, Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee != null && employee.getUsertype() == Usertype.ADMIN) {
            Employee existingEmployee = employeeService.findEmployeeByUsername(newEmployee.getUsername());
            if (existingEmployee != null) {
                model.addAttribute("registrationError", "An employee with that username already exists.");
                return "home/registration";
            }
            employeeService.saveEmployee(newEmployee);
            model.addAttribute("registrationSuccess", "Employee registered successfully.");
            return "home/registration";
        } else {
            return "redirect:/menu";
        }
    }

    @GetMapping("/deleteEmployee")
    public String showAllEmployees(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee != null && employee.getUsertype() == Usertype.ADMIN) {
            List<Employee> employees = employeeService.getEmployees();
            model.addAttribute("employees", employees);
            return "home/employeeList";
        } else {
            return "redirect:/menu";
        }
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") int id, HttpSession session) {
        Employee requestingEmployee = (Employee) session.getAttribute("employee");
        if (requestingEmployee != null && requestingEmployee.getUsertype() == Usertype.ADMIN) {
            employeeService.delete(id);
            return "redirect:/menu";
        } else {
            return "redirect:/menu";
        }
    }

    @GetMapping("/employeeList")
    public String showAllEmployees(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee != null && employee.getUsertype() == Usertype.ADMIN) {
            List<Employee> employees = employeeService.getEmployees();
            model.addAttribute("employees", employees);
            return "home/employeeList";
        } else {
            return "redirect:/menu";
        }
    }

    @GetMapping("/updateEmployee")
    public String showUpdateForm(@RequestParam("id") int id, Model model, HttpSession session) {
        Employee requestingEmployee = (Employee) session.getAttribute("employee");
        if (requestingEmployee != null && requestingEmployee.getUsertype() == Usertype.ADMIN) {
            Employee employee = employeeService.getEmployee(id);
            if (employee != null) {
                model.addAttribute("employee", employee);
                model.addAttribute("allUserTypes", Usertype.values());
                return "home/updateEmployee";
            }
        }
        return "redirect:/menu";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session) {
        Employee requestingEmployee = (Employee) session.getAttribute("employee");
        if (requestingEmployee != null && requestingEmployee.getUsertype() == Usertype.ADMIN) {
            if (!employee.getUserPassword().equals(confirmPassword)) {
                model.addAttribute("passwordError", "Passwords do not match.");
                model.addAttribute("employee", employee);
                model.addAttribute("allUserTypes", Usertype.values());
                return "home/updateEmployee";
            }
            employeeService.saveEmployee(employee);
            model.addAttribute("updateSuccess", "Employee updated successfully.");
        }
        return "redirect:/menu";
    }

  /*  @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }*/

    @GetMapping("/dataRegistration")
    public String showDataRegistration(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee != null && (employee.getUsertype() == Usertype.DATAREGISTRATOR || employee.getUsertype() == Usertype.ADMIN)) {
            return "home/dataRegistration";
        } else {
            model.addAttribute("accessDenied", "You don't have permission to access this page.");
            return "redirect:/menu";
        }
    }

    @GetMapping("/businessDevelopment")
    public String showBusinessDevelopment(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee != null && (employee.getUsertype() == Usertype.BUSINESSDEVELOPER || employee.getUsertype() == Usertype.ADMIN)) {
            return "home/businessDevelopment";
        } else {
            model.addAttribute("accessDenied", "You don't have permission to access this page.");
            return "redirect:/menu";
        }
    }
}
