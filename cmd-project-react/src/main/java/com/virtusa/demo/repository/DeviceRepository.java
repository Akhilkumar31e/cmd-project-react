package com.virtusa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.demo.model.Device;
import com.virtusa.demo.model.Hospital;

public interface DeviceRepository extends JpaRepository<Device, Long> {
	List<Device> findByDeviceStatus(String deviceStatus);
	List<Device> findByDeviceName(String deviceName);
	
}
