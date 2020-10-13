package com.virtusa.demo.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;

import javax.persistence.*;

@Entity
public class TechnicianDevice implements Serializable{
	
	@Id
    @ManyToOne
    @JoinColumn
    private Device device;

    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    private Date reportDate;
    
    private Date serviceDate;
    
    public TechnicianDevice(User user) {
        this.user=user;
        this.reportDate=new Date();
    }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicianDevice)) return false;
        TechnicianDevice that = (TechnicianDevice) o;
        return Objects.equals(device.getDeviceName(), that.device.getDeviceName()) &&
                Objects.equals(user.getUsername(), that.user.getUsername()) &&
                Objects.equals(serviceDate, that.serviceDate) &&
                Objects.equals(reportDate, that.reportDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device.getDeviceName(), user.getUsername(), reportDate, serviceDate);
    }

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
}
