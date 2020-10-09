package com.virtusa.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.demo.model.Device;
import com.virtusa.demo.repository.DeviceRepository;

@CrossOrigin(origins="http://localhost:9090")
@RestController
@RequestMapping("/")
public class DeviceController {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@GetMapping("/device")
	public ResponseEntity<List<Device>> getAllDevices(@RequestParam(required=false)String name){
		try {
		      List<Device> device = new ArrayList<Device>();

		      if (name == null)
		        deviceRepository.findAll().forEach(device::add);
		      else
		        deviceRepository.findByDeviceName(name).forEach(device::add);

		      if (device.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(device, HttpStatus.OK);
		    }
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/device/{id}")
	  public ResponseEntity<Device> getTutorialById(@PathVariable("id") long id) {
	    Optional<Device> deviceData = deviceRepository.findById(id);

	    if (deviceData.isPresent()) {
	      return new ResponseEntity<>(deviceData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/device")
	  public ResponseEntity<Device> createDevice(@RequestBody Device device) {
	    try {
	      Device _device = deviceRepository
	          .save(new Device(device.getDeviceName(), device.getDeviceStatus(),device.getServicePeriod(),device.getReceivedDate(),device.getBatteryLevel()));
	      return new ResponseEntity<>(_device, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/device/{id}")
	  public ResponseEntity<Device> updateDevice(@PathVariable("id") long id, @RequestBody Device device) {
	    Optional<Device> deviceData = deviceRepository.findById(id);

	    if (deviceData.isPresent()) {
	      Device _device = deviceData.get();
	      _device.setDeviceName(device.getDeviceName());
	      _device.setDeviceStatus(device.getDeviceStatus());
	      _device.setServicePeriod(device.getServicePeriod());
	      _device.setReceivedDate(device.getReceivedDate());
	      _device.setBatteryLevel(device.getBatteryLevel());
	      return new ResponseEntity<>(deviceRepository.save(_device), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/device/{id}")
	  public ResponseEntity<HttpStatus> deleteDevice(@PathVariable("id") long id) {
	    try {
	      deviceRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("/device")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	      deviceRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }
}
