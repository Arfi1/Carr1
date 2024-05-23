package com.example.carrr.service;

import com.example.carrr.model.Lease;
import com.example.carrr.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;

    @Autowired
    public LeaseService(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    // Fetch all leases
    public List<Lease> fetchAll() {
        return leaseRepository.fetchAll();
    }

    // Add or update a lease
    public void addLeasingContract(Lease lease) {
        leaseRepository.saveOrUpdate(lease);
    }

    // Calculate the total price of all leasing contracts
    public double calculateTotalPriceOfLeasingContracts() {
        List<Lease> leases = leaseRepository.fetchAll();
        return leases.stream().mapToDouble(Lease::getPrice).sum();
    }

    // Find a lease by ID
    public Lease findLeaseById(int id) {
        return leaseRepository.findById(id);
    }

    // Fetch all paid leases
    public List<Lease> fetchFlow1() {
        return leaseRepository.findPaidLeases();
    }

    // Save or update a lease
    public void saveLease(Lease lease) {
        leaseRepository.saveOrUpdate(lease);
    }
}
