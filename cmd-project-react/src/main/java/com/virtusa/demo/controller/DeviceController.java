package com.virtusa.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.virtusa.demo.model.TechnicianDevice;
import com.virtusa.demo.model.User;
import com.virtusa.demo.repository.DeviceRepository;
import com.virtusa.demo.repository.UserRepository;
import com.virtusa.demo.security.services.UserDetailsImpl;


@CrossOrigin(origins="*")

@RestController
@RequestMapping("/")
public class DeviceController {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	UserRepository userRepository;
	
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
	  public ResponseEntity<Device> getDeviceById(@PathVariable("id") long id) {
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
	      Device _device = deviceRepository.save(new Device(device.getDeviceName(),device.getDeviceStatus(),device.getServicePeriod(),device.getBatteryLevel(),
	    		  device.getAssetNumber(),device.getModelNumber(),device.getManufactureDate(),device.getOperatingTime(),device.getSerialNumber(),device.getHospital()));
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
	      _device.onUpdate();
	      _device.setBatteryLevel(device.getBatteryLevel());
	      _device.setAssetNumber(device.getAssetNumber());
	      _device.setModelNumber(device.getModelNumber());
	      _device.setManufactureDate(device.getManufactureDate());
	      _device.setOperatingTime(device.getOperatingTime());
	      _device.setSerialNumber(device.getSerialNumber());
	      _device.setHospital(device.getHospital());
	      return new ResponseEntity<>(deviceRepository.save(_device), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @PutMapping("/device/status/{id}")
	  public ResponseEntity<Device> updateStatus(@PathVariable("id") long id, @RequestBody Device device) {
		    Optional<Device> deviceData = deviceRepository.findById(id);

		    if (deviceData.isPresent()) {
		      Device _device = deviceData.get();
		      _device.setDeviceStatus(device.getDeviceStatus());
		      _device.onUpdate();
		      _device.setBatteryLevel(device.getBatteryLevel());
		      _device.setOperatingTime(device.getOperatingTime());
		      _device.setLastService();
		      return new ResponseEntity<>(deviceRepository.save(_device), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	  }
	  
	  /*@PutMapping("/device/assignUser/{id}")
	  public ResponseEntity<TechnicianDevice> assginUser(@PathVariable("id") long id, @RequestBody Device device){
		  Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username;
		  if(principal instanceof UserDetails) {
			  username=((UserDetails)principal).getUsername();
		  }else {
			  username=principal.toString();
		  }
		  User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		  TechnicianDevice techDevice=new TechnicianDevice(user);
		  
		  Optional<Device> deviceData = deviceRepository.findById(id);
		  if(deviceData.isPresent()) {
			  Device _device=deviceData.get();
			  techDevice.setDevice(_device);
			  _device.setTechnicanDevices(techDevice);
			  return new ResponseEntity<>(techDevice,HttpStatus.OK);
		  }
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }*/
	  
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
	  @GetMapping("/device/repair")
	  public ResponseEntity<List<Device>> getAllRepairDevices() {
		      List<Device> devices = new ArrayList<Device>();

		      deviceRepository.findByDeviceStatus("repair").forEach(devices::add);
		      if (devices.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(devices, HttpStatus.OK);
	  }
	  
	  @GetMapping("/device/replace")
	  public ResponseEntity<List<Device>> getAllReplaceDevice(){
		  List<Device> devices = new ArrayList<Device>();

	        deviceRepository.findByDeviceStatus("replace").forEach(devices::add);
	      if (devices.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(devices, HttpStatus.OK);
	  }

	  @GetMapping("/device/remove")
	  public ResponseEntity<List<Device>> getAllRemoveDevice(){
		  List<Device> devices = new ArrayList<Device>();

	       deviceRepository.findByDeviceStatus("remove").forEach(devices::add);
	      if (devices.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(devices, HttpStatus.OK);
	  }
	  
	  @GetMapping("/device/service")
	  public ResponseEntity<List<Device>> getAllServiceDevice(){
		  List<Device> devices = new ArrayList<Device>();

	        deviceRepository.findByDeviceStatus("service").forEach(devices::add);
	      if (devices.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      
	      return new ResponseEntity<>(devices, HttpStatus.OK);
	  }
	  
	  @GetMapping("/device/recentlyUpdated")
	  public ResponseEntity<List<Device>> getRecentlyUpdatedDevice(){
		  List<Device> deviceData=deviceRepository.findAll(Sort.by("lastUpdated").descending());
		  if(deviceData.isEmpty()) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		  else {
			  return new ResponseEntity<>(deviceData,HttpStatus.OK);
		  }
	  }
	  
	  /*@GetMapping("/device/hospital/{id}")
	  public ResponseEntity<List<Device>> getDevicesByHospital(@RequestBody Hospital hospital){
		  List<Device> deviceData=deviceRepository.findByHospital(hospital);
		  if(deviceData.isEmpty()) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		  else {
			  return new ResponseEntity<>(deviceData,HttpStatus.OK);
		  }
	  }*/
}
