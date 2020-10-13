package com.virtusa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.demo.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long>{
	List<Hospital> findByHospitalName(String hospitalName);

}
