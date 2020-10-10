package com.virtusa.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="hospital")
public class Hospital {
	
	@Id
	@Column(name="hospitalID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer hospitalID;
	
	@NotNull
	private String hospitalName;
	
	private String City;
	private String Locality;
	private String Pincode;
	
	public Hospital() {
		
	}
	
	public Hospital(int hospitalID) {
		super();
		this.hospitalID=hospitalID;
	}
	
	public Hospital(String hospitalName, String city, String locality, String pincode) {
		super();
		this.hospitalName = hospitalName;
		City = city;
		Locality = locality;
		Pincode = pincode;
	}

	public Integer getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(Integer hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getLocality() {
		return Locality;
	}

	public void setLocality(String locality) {
		Locality = locality;
	}

	public String getPincode() {
		return Pincode;
	}

	public void setPincode(String pincode) {
		Pincode = pincode;
	}

	@Override
	public String toString() {
		return "Hospital [hospitalID=" + hospitalID + ", hospitalName=" + hospitalName + ", City=" + City
				+ ", Locality=" + Locality + ", Pincode=" + Pincode + "]";
	}
	
}