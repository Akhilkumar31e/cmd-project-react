package com.virtusa.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.demo.model.Hospital;
import com.virtusa.demo.repository.HospitalRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/")
public class HospitalController {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@GetMapping("/hospital")
	public ResponseEntity<List<Hospital>> getAllHospital(@RequestParam(required=false)String name){
		try {
		      List<Hospital> hospital = new ArrayList<Hospital>();

		      if (name == null)
		        hospitalRepository.findAll().forEach(hospital::add);
		      else
		        hospitalRepository.findByHospitalName(name).forEach(hospital::add);

		      if (hospital.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(hospital, HttpStatus.OK);
		    }
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/hospital/{id}")
	  public ResponseEntity<Hospital> getHospitalById(@PathVariable("id") int id) {
	    Optional<Hospital> hospitalData = hospitalRepository.findById(id);

	    if (hospitalData.isPresent()) {
	      return new ResponseEntity<>(hospitalData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/hospital")
	  public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) {
	    try {
	      Hospital _hospital = hospitalRepository
	          .save(new Hospital(hospital.getHospitalName(), hospital.getCity(),hospital.getLocality(), hospital.getPincode()));
	      return new ResponseEntity<>(_hospital, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


}
