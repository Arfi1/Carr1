package com.example.carrr.controller;

import com.example.carrr.model.Employee;
import com.example.carrr.model.Usertype;
import com.example.carrr.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private final Map<String, Integer> loginAttempts = new HashMap<>();
    private final EmployeeService employeeService;

    @Autowired
    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showLoginForm() {
        return "home/index";  // Make sure this is your main page or redirect to the appropriate page
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/employeeLogin")
    public String employeeLogin(HttpSession session, Model model) {
        return "home/employeeLogin";
    }

    @PostMapping("/employeeLogin")
    public String login(@RequestParam String username,
                        @RequestParam String userPassword,
                        Model model, HttpSession session) {
        loginAttempts.put(username, loginAttempts.getOrDefault(username, 0) + 1);

        Employee loggedInEmployee = employeeService.authenticate(username, userPassword);

        if (loggedInEmployee != null) {
            session.setAttribute("employee", loggedInEmployee);
            session.setAttribute("userType", loggedInEmployee.getUsertype());
            loginAttempts.put(username, 0); // Reset login attempts on successful login
            return redirectToUserSpecificPage(loggedInEmployee.getUsertype());
        } else {
            model.addAttribute("loginError", "Error logging in. Please check your username and password.");
            if (loginAttempts.get(username) >= 3) {
                model.addAttribute("loginError", "Too many failed attempts. Please try again later.");
            }
            return "home/employeeLogin";
        }
    }

    private String redirectToUserSpecificPage(Usertype userType) {
        switch (userType) {
            case DATAREGISTRATOR:
                return "redirect:/dataRegistration";
            case DAMAGEREPORTER:
                return "redirect:/damageReport";
            case BUSINESSDEVELOPER:
                return "redirect:/businessDevelopment";
            case ADMIN:
                return "redirect:/menu";
            // default:
            //  return "redirect:/menu";
        }
        return null;
    }
}
