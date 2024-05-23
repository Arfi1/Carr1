package com.example.carrr.controller;

import com.example.carrr.model.Customer;
import com.example.carrr.model.Lease;
import com.example.carrr.model.LeaseType;
import com.example.carrr.model.Vehicle;
import com.example.carrr.service.CustomerService;
import com.example.carrr.service.EmployeeService;
import com.example.carrr.service.LeaseService;
import com.example.carrr.service.VehicleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
public class LeaseController {

    @Autowired
    LeaseService leaseService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/createContract")
    public String createContract(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) {
            return "redirect:/";
        }
        List<Vehicle> availableCars = vehicleService.fetchAvailable();
        model.addAttribute("available", availableCars);

        return "home/createContract";
    }

    @PostMapping("/chooseCar")
    public String chooseCar(Model model, int vehicleNumber, HttpSession session, RedirectAttributes redirectAttributes) {
        Vehicle vehicle = vehicleService.findVehicleById(vehicleNumber);
        if (vehicle == null) {
            redirectAttributes.addFlashAttribute("error", "The car with the specified vehicle number could not be found");
            return "redirect:/createContract";
        } else {
            model.addAttribute("selectedVehicle", vehicle);
            session.setAttribute("vehicleNumber", vehicleNumber); // Save as Integer
            if (vehicle.getFlow() == 1) {
                redirectAttributes.addFlashAttribute("flowError", "The car is already rented out");
                return "redirect:/createContract";
            } else {
                return "home/carSelected";
            }
        }
    }

    @GetMapping("/viewLeases")
    public String viewLeases(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) {
            return "redirect:/";
        }
        List<Lease> leases = leaseService.fetchAll();
        model.addAttribute("leases", leases);
        double totalPrice = leaseService.calculateTotalPriceOfLeasingContracts();
        model.addAttribute("totalPriceRent", totalPrice);
        return "home/viewLeases";
    }

    @GetMapping("/lease")
    public String lease(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) {
            return "redirect:/";
        }
        String username = (String) session.getAttribute("username");
        Integer vehicleNumber = (Integer) session.getAttribute("vehicleNumber");
        List<Customer> customers = customerService.getAllCustomers();
        if (vehicleNumber != null) {
            Vehicle car = vehicleService.findVehicleById(vehicleNumber);
            model.addAttribute("selectedVehicle", car);
            model.addAttribute("username", username);
            model.addAttribute("customers", customers);
            model.addAttribute("leaseTypes", LeaseType.values()); // Add this line
            return "home/lease";
        } else {
            return "home/errorPage";
        }
    }

    @PostMapping("/createLeaseContract")
    public String createLeaseContract(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("customerId") int customerId,
            @RequestParam("username") String username,
            @RequestParam("vehicleNumber") int vehicleNumber,
            @RequestParam("leaseType") LeaseType leaseType,
            Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        Vehicle car = vehicleService.findVehicleById(vehicleNumber);

        model.addAttribute("selectedVehicle", car);
        model.addAttribute("username", username);
        Period period = Period.between(startDate, endDate);
        int totalDays = period.getDays() + period.getMonths() * 30; // Approximate total days

        // Validering baseret på LeaseType
        if (totalDays < leaseType.getMinDays() || totalDays > leaseType.getMaxDays()) {
            String errorMsg = String.format("For %s lease, the duration must be between %d and %d days.",
                    leaseType.name(), leaseType.getMinDays(), leaseType.getMaxDays());
            redirectAttributes.addFlashAttribute("error", errorMsg);
            return "redirect:/lease";
        }

        double monthlyPrice = car.getPrice();
        double totalPrice = (totalDays / 30.0) * monthlyPrice;
        model.addAttribute("totalPrice", totalPrice);
        session.setAttribute("totalPriceRent", totalPrice);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        session.setAttribute("customerId", customerId);
        session.setAttribute("username", username);
        session.setAttribute("leaseType", leaseType);
        Customer customer = customerService.getCustomerById(customerId);
        session.setAttribute("customerName", customer.getFirstName() + " " + customer.getLastName());

        return "redirect:/leaseConfirm";
    }

    @GetMapping("/leaseConfirm")
    public String leaseConfirmation(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) {
            return "redirect:/";
        }
        String username = (String) session.getAttribute("username");
        String customerName = (String) session.getAttribute("customerName");
        LocalDate startDate = (LocalDate) session.getAttribute("startDate");
        LocalDate endDate = (LocalDate) session.getAttribute("endDate");
        double totalPrice = (double) session.getAttribute("totalPriceRent");
        int customerId = (int) session.getAttribute("customerId");

        Integer vehicleNumber = (Integer) session.getAttribute("vehicleNumber"); // Retrieve as Integer
        Vehicle car = vehicleService.findVehicleById(vehicleNumber);
        model.addAttribute("selectedVehicle", car);
        model.addAttribute("username", username);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("customerId", customerId);
        model.addAttribute("totalPriceRent", totalPrice);
        model.addAttribute("customerName", customerName);
        return "home/leaseConfirm";
    }

    @PostMapping("/createLeaseContractConfirmed")
    public String createLeaseContractConfirmed(Model model, HttpSession session, Lease leaseContract) {
        String username = (String) session.getAttribute("username");
        LocalDate startDate = (LocalDate) session.getAttribute("startDate");
        String customerName = (String) session.getAttribute("customerName");
        LocalDate endDate = (LocalDate) session.getAttribute("endDate");
        double totalPrice = (double) session.getAttribute("totalPriceRent");
        int customerId = (int) session.getAttribute("customerId");
        LeaseType leaseType = (LeaseType) session.getAttribute("leaseType");

        Integer vehicleNumber = (Integer) session.getAttribute("vehicleNumber");
        Vehicle vehicle = vehicleService.findVehicleById(vehicleNumber);
        model.addAttribute("selectedVehicle", vehicle);
        model.addAttribute("username", username);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("customerId", customerId);
        model.addAttribute("totalPriceRent", totalPrice);
        model.addAttribute("customerName", customerName);
        model.addAttribute("leaseType", leaseType);
        leaseContract.setLeaseType(leaseType); // Set the lease type in the lease contract
        leaseService.addLeasingContract(leaseContract);
        vehicleService.updateAfterContract(vehicleNumber);
        return "redirect:/menu";
    }
}
