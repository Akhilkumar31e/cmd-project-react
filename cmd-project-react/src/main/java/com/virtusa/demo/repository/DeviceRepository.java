package com.virtusa.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.demo.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
	Optional<Device> findByDeviceStatus(String deviceStatus);
	List<Device> findByDeviceName(String deviceName);
}
