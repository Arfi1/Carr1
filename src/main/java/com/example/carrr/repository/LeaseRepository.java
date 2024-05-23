package com.example.carrr.repository;

import com.example.carrr.model.Lease;
import com.example.carrr.model.Vehicle;
import com.example.carrr.model.LeaseStatus;
import com.example.carrr.model.LeaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeaseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Fetch all leases and their associated vehicles
    public List<Lease> fetchAll() {
        String sql = "SELECT l.*, v.* FROM lease l LEFT JOIN vehicle v ON l.vehicle_number = v.vehicle_number";
        return jdbcTemplate.query(sql, new RowMapper<Lease>() {
            @Override
            public Lease mapRow(ResultSet rs, int rowNum) throws SQLException {
                Lease lease = new Lease();
                lease.setContractId(rs.getInt("contract_id"));
                lease.setStartDate(rs.getDate("start_date").toLocalDate());
                lease.setEndDate(rs.getDate("end_date").toLocalDate());
                lease.setPrice(rs.getDouble("price"));
                lease.setIsPaid(LeaseStatus.valueOf(rs.getString("is_paid")));
                lease.setLeaseType(LeaseType.valueOf(rs.getString("leasetype")));

                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleNumber(rs.getInt("vehicle_number"));
                vehicle.setFrameNumber(rs.getString("frame_number"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMake(rs.getInt("make"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setFlow(rs.getInt("flow"));
                vehicle.setOdometer(rs.getInt("odometer"));
                vehicle.setFuelType(rs.getString("fuel_type"));
                vehicle.setMotor(rs.getString("motor"));
                vehicle.setGearType(rs.getString("gear_type"));
                vehicle.setIsReadyForLease(LeaseStatus.valueOf(rs.getString("is_ready_for_lease")));

                lease.setVehicle(vehicle);
                return lease;
            }
        });
    }

    // Save or update a lease
    public void saveOrUpdate(Lease lease) {
        if (lease.getContractId() == 0) {
            String sql = "INSERT INTO lease (start_date, end_date, price, vehicle_number, employee_id, customer_id, is_paid, pickup_id, leasetype) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, lease.getStartDate(), lease.getEndDate(), lease.getPrice(), lease.getVehicle().getVehicleNumber(), lease.getEmployee().getUsername(), lease.getCustomer().getCustomerId(), lease.getIsPaid().name(), lease.getPickup().getPickupId(), lease.getLeaseType().name());
        } else {
            String sql = "UPDATE lease SET start_date = ?, end_date = ?, price = ?, vehicle_number = ?, employee_id = ?, customer_id = ?, is_paid = ?, pickup_id = ?, leasetype = ? WHERE contract_id = ?";
            jdbcTemplate.update(sql, lease.getStartDate(), lease.getEndDate(), lease.getPrice(), lease.getVehicle().getVehicleNumber(), lease.getEmployee().getUsername(), lease.getCustomer().getCustomerId(), lease.getIsPaid().name(), lease.getPickup().getPickupId(), lease.getLeaseType().name(), lease.getContractId());
        }
    }

    // Find a lease by its ID
    public Lease findById(int leaseId) {
        String sql = "SELECT l.*, v.* FROM lease l LEFT JOIN vehicle v ON l.vehicle_number = v.vehicle_number WHERE l.contract_id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Lease>() {
            @Override
            public Lease mapRow(ResultSet rs, int rowNum) throws SQLException {
                Lease lease = new Lease();
                lease.setContractId(rs.getInt("contract_id"));
                lease.setStartDate(rs.getDate("start_date").toLocalDate());
                lease.setEndDate(rs.getDate("end_date").toLocalDate());
                lease.setPrice(rs.getDouble("price"));
                lease.setIsPaid(LeaseStatus.valueOf(rs.getString("is_paid")));
                lease.setLeaseType(LeaseType.valueOf(rs.getString("leasetype")));

                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleNumber(rs.getInt("vehicle_number"));
                vehicle.setFrameNumber(rs.getString("frame_number"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMake(rs.getInt("make"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setFlow(rs.getInt("flow"));
                vehicle.setOdometer(rs.getInt("odometer"));
                vehicle.setFuelType(rs.getString("fuel_type"));
                vehicle.setMotor(rs.getString("motor"));
                vehicle.setGearType(rs.getString("gear_type"));
                vehicle.setIsReadyForLease(LeaseStatus.valueOf(rs.getString("is_ready_for_lease")));

                lease.setVehicle(vehicle);
                return lease;
            }
        }, leaseId);
    }

    // Find all leases where is_paid is TRUE
    public List<Lease> findPaidLeases() {
        String sql = "SELECT l.*, v.* FROM lease l LEFT JOIN vehicle v ON l.vehicle_number = v.vehicle_number WHERE l.is_paid = 'TRUE'";
        return jdbcTemplate.query(sql, new RowMapper<Lease>() {
            @Override
            public Lease mapRow(ResultSet rs, int rowNum) throws SQLException {
                Lease lease = new Lease();
                lease.setContractId(rs.getInt("contract_id"));
                lease.setStartDate(rs.getDate("start_date").toLocalDate());
                lease.setEndDate(rs.getDate("end_date").toLocalDate());
                lease.setPrice(rs.getDouble("price"));
                lease.setIsPaid(LeaseStatus.valueOf(rs.getString("is_paid")));
                lease.setLeaseType(LeaseType.valueOf(rs.getString("leasetype")));

                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleNumber(rs.getInt("vehicle_number"));
                vehicle.setFrameNumber(rs.getString("frame_number"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMake(rs.getInt("make"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setFlow(rs.getInt("flow"));
                vehicle.setOdometer(rs.getInt("odometer"));
                vehicle.setFuelType(rs.getString("fuel_type"));
                vehicle.setMotor(rs.getString("motor"));
                vehicle.setGearType(rs.getString("gear_type"));
                vehicle.setIsReadyForLease(LeaseStatus.valueOf(rs.getString("is_ready_for_lease")));

                lease.setVehicle(vehicle);
                return lease;
            }
        });
    }
}
