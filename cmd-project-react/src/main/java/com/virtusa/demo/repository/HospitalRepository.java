package com.virtusa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.demo.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long>{
	List<Hospital> findByHospitalName(String hospitalName);

}
