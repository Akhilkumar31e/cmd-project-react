package com.virtusa.demo.model;

import javax.persistence.*;

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
	private String receivedDate;
	
	@Column(name="batteryLevel")
	private String batteryLevel;
	
	public Device() {
		
	}

	public Device(String deviceName, String deviceStatus, int servicePeriod, String receivedDate,
			String batteryLevel) {
		super();
		this.deviceName = deviceName;
		this.deviceStatus = deviceStatus;
		this.servicePeriod = servicePeriod;
		this.receivedDate = receivedDate;
		this.batteryLevel = batteryLevel;
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

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	@Override
	public String toString() {
		return "Device [deviceID=" + deviceID + ", deviceName=" + deviceName + ", deviceStatus=" + deviceStatus
				+ ", servicePeriod=" + servicePeriod + ", receivedDate=" + receivedDate + ", batteryLevel="
				+ batteryLevel + "]";
	}
	
}
