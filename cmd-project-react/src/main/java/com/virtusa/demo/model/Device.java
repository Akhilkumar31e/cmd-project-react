package com.virtusa.demo.model;

import java.util.Date;

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
	private Date receivedDate;
	
	@Column(name="batteryLevel")
	private String batteryLevel;
	
	@Column(name="lastUpdated")
	private Date lastUpdated;
	
	@Column(name="lastService")
	private Date lastService;
	
	public Device() {
		
	}

	public Device(String deviceName, String deviceStatus, int servicePeriod, String batteryLevel) {
		super();
		this.deviceName = deviceName;
		this.deviceStatus = deviceStatus;
		this.servicePeriod = servicePeriod;
		this.batteryLevel = batteryLevel;
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

	/*public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}*/

	public String getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
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
	
	@Override
	public String toString() {
		return "Device [deviceID=" + deviceID + ", deviceName=" + deviceName + ", deviceStatus=" + deviceStatus
				+ ", servicePeriod=" + servicePeriod + ", receivedDate=" + receivedDate + ", batteryLevel="
				+ batteryLevel + ", lastUpdated=" + lastUpdated + "]";
	}
	
}
