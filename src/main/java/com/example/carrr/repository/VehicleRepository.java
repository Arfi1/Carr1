package com.example.carrr.repository;

import com.example.carrr.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VehicleRepository {
    @Autowired
    JdbcTemplate template;

    // Return a list of all vehicles
    public List<Vehicle> fetchAll() {
        String sql = "SELECT * FROM vehicle";
        RowMapper<Vehicle> rowMapper = new BeanPropertyRowMapper<>(Vehicle.class);
        return template.query(sql, rowMapper);
    }

    // Return a list of available vehicles (ready for lease)
    public List<Vehicle> fetchAvailable() {
        String sql = "SELECT * FROM vehicle WHERE is_ready_for_lease = 'TRUE'";
        RowMapper<Vehicle> rowMapper = new BeanPropertyRowMapper<>(Vehicle.class);
        return template.query(sql, rowMapper);
    }

    // Return a list of vehicles not ready for lease
    public List<Vehicle> fetchNotReady() {
        String sql = "SELECT * FROM vehicle WHERE is_ready_for_lease = 'FALSE'";
        RowMapper<Vehicle> rowMapper = new BeanPropertyRowMapper<>(Vehicle.class);
        return template.query(sql, rowMapper);
    }

    // Add a vehicle
    public void addVehicle(Vehicle v) {
        String sql = "INSERT INTO vehicle (vehicle_number, frame_number, brand, model, make, color, price, flow, odometer, fuel_type, motor, gear_type, is_ready_for_lease) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, v.getVehicleNumber(), v.getFrameNumber(), v.getBrand(), v.getModel(), v.getMake(), v.getColor(), v.getPrice(), v.getFlow(), v.getOdometer(), v.getFuelType(), v.getMotor(), v.getGearType(), v.getIsReadyForLease().name());
    }

    // Delete a vehicle
    public Boolean deleteVehicle(int vehicleNumber) {
        String sql = "DELETE FROM vehicle WHERE vehicle_number = ?";
        return template.update(sql, vehicleNumber) > 0;
    }

    // Find a vehicle by vehicle_number
    public Vehicle findVehicleById(int vehicleNumber) {
        String sql = "SELECT * FROM vehicle WHERE vehicle_number = ?";
        RowMapper<Vehicle> rowMapper = new BeanPropertyRowMapper<>(Vehicle.class);
        List<Vehicle> vehicles = template.query(sql, rowMapper, vehicleNumber);
        if (vehicles.size() == 1) {
            return vehicles.get(0);
        } else {
            return null;
        }
    }

    // Update a vehicle
    public void updateVehicle(Vehicle v, int vehicleNumber) {
        String sql = "UPDATE vehicle SET frame_number = ?, brand = ?, model = ?, make = ?, color = ?, price = ?, flow = ?, odometer = ?, fuel_type = ?, motor = ?, gear_type = ?, is_ready_for_lease = ? WHERE vehicle_number = ?";
        template.update(sql, v.getFrameNumber(), v.getBrand(), v.getModel(), v.getMake(), v.getColor(), v.getPrice(), v.getFlow(), v.getOdometer(), v.getFuelType(), v.getMotor(), v.getGearType(), v.getIsReadyForLease().name(), vehicleNumber);
    }

    // Mark a vehicle as rented (flow = 1)
    public void updateAfterContract(int vehicleNumber) {
        String sql = "UPDATE vehicle SET flow = 1 WHERE vehicle_number = ?";
        template.update(sql, vehicleNumber);
    }

    // Mark a vehicle after a damage report (flow = 2)
    public void updateAfterDamageReport(int vehicleNumber) {
        String sql = "UPDATE vehicle SET flow = 2 WHERE vehicle_number = ?";
        template.update(sql, vehicleNumber);
    }

    // Join table to get total prices data
    public List<Map<String, Object>> getTotalPricesData() {
        String sql = "SELECT vehicle.vehicle_number, vehicle.brand, vehicle.model, vehicle.flow, lease.contract_id, lease.username, lease.customer_id, lease.start_date, lease.end_date, vehicle.price AS vehicle_price, lease.price AS contract_price FROM vehicle JOIN lease ON vehicle.vehicle_number = lease.vehicle_number WHERE vehicle.flow = 1";
        return template.queryForList(sql);
    }
}
