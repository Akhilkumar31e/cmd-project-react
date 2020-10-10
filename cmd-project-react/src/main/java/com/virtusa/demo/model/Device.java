package com.virtusa.demo.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="device")
public class Device {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long deviceID;
	
	@Column(name="deviceName")
	private String deviceName;
	
	@Column(name="deviceStatus")
	private String deviceStatus;
	
	@Column(name="servicePeriod")
	private int servicePeriod;
	
	@Column(name="receivedDate")
	private Date receivedDate;
	
	@Column(name="batteryLevel")
	private String batteryLevel;
	
	@Column(name="lastUpdated")
	private Date lastUpdated;
	
	@Column(name="lastService")
	private Date lastService;
	
	@Column(name="assetNumber")
	private String assetNumber;
	
	@Column(name="modelNumber")
	private String modelNumber;
	
	@Column(name="manufactureDate")
	private String manufactureDate;
	
	@Column(name="operatingTime")
	private Integer operatingTime;
	
	@Column(name="serialNumber")
	private String serialNumber;
	
	@NotNull
	@ManyToOne
	@JoinColumn
	private Hospital hospital;
	
	public Device() {
		
	}

	public Device(String deviceName, String deviceStatus, int servicePeriod,String batteryLevel, String assetNumber, String modelNumber,
			String manufactureDate, Integer operatingTime, String serialNumber, @NotNull Hospital hospital) {
		super();
		this.deviceName = deviceName;
		this.deviceStatus = deviceStatus;
		this.servicePeriod = servicePeriod;
		this.batteryLevel = batteryLevel;
		this.assetNumber = assetNumber;
		this.modelNumber = modelNumber;
		this.manufactureDate = manufactureDate;
		this.operatingTime = operatingTime;
		this.serialNumber = serialNumber;
		this.hospital = hospital;
		this.receivedDate=new Date();
	}

	public long getDeviceID() {
		return deviceID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public int getServicePeriod() {
		return servicePeriod;
	}

	public void setServicePeriod(int servicePeriod) {
		this.servicePeriod = servicePeriod;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdate(Date lastUpdated) {
		this.lastUpdated=lastUpdated;
	}
	
	@PrePersist
	public void onCreate() {
		this.receivedDate = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		this.lastUpdated = new Date();
	}

	public void setLastService() {
		this.lastService=new Date();
	}
	
	public Date getLastService() {
		return lastService;
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
	public String getManufactureDate() {
		return manufactureDate;
	}
	
	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Integer getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(Integer operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Device [deviceID=" + deviceID + ", deviceName=" + deviceName + ", deviceStatus=" + deviceStatus
				+ ", servicePeriod=" + servicePeriod + ", receivedDate=" + receivedDate + ", batteryLevel="
				+ batteryLevel + ", lastUpdated=" + lastUpdated + ", lastService=" + lastService + ", assetNumber="
				+ assetNumber + ", modelNumber=" + modelNumber + ", manufactureDate=" + manufactureDate
				+ ", operatingTime=" + operatingTime + ", serialNumber=" + serialNumber + ", hospital=" + hospital.getHospitalName()
				+ "]";
	}
}
