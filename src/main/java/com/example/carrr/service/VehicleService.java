package com.example.carrr.service;

import com.example.carrr.model.Vehicle;
import com.example.carrr.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> fetchAll() {
        return vehicleRepository.fetchAll();
    }

    public List<Vehicle> fetchAvailable() {
        return vehicleRepository.fetchAvailable();
    }

    public List<Vehicle> fetchNotReady() {
        return vehicleRepository.fetchNotReady();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.addVehicle(vehicle);
    }

    public boolean deleteVehicle(int vehicleNumber) {
        return vehicleRepository.deleteVehicle(vehicleNumber);
    }

    public Vehicle findVehicleById(int vehicleNumber) {
        return vehicleRepository.findVehicleById(vehicleNumber);
    }

    public void updateVehicle(Vehicle vehicle, int vehicleNumber) {
        vehicleRepository.updateVehicle(vehicle, vehicleNumber);
    }

    public void updateAfterContract(int vehicleNumber) {
        vehicleRepository.updateAfterContract(vehicleNumber);
    }

    public void updateAfterDamageReport(int vehicleNumber) {
        vehicleRepository.updateAfterDamageReport(vehicleNumber);
    }

    public List<Map<String, Object>> getTotalPricesData() {
        return vehicleRepository.getTotalPricesData();
    }
}
