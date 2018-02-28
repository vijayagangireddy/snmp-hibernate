package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="snmp_info")
public class SNMPInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="snmpId")
	private Integer snmpId;
	
	
	@Column(name="IPAddress")
	private String ipAddress;
	
	@Column(name="CPU_utilization")
	private String cpu_utilization;
	
	@Column(name="MEMORY_utilization")
	private String memory_utilization;
	
	@Column(name="DISK_utilization")
	private String disk_utilization;
	
	@Column(name="Updated_time")
	private Date updated_time ;
	
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCpu_utilization() {
		return cpu_utilization;
	}

	public void setCpu_utilization(String cpu_utilization) {
		this.cpu_utilization = cpu_utilization;
	}

	public String getMemory_utilization() {
		return memory_utilization;
	}

	public void setMemory_utilization(String memory_utilization) {
		this.memory_utilization = memory_utilization;
	}

	public String getDisk_utilization() {
		return disk_utilization;
	}

	public void setDisk_utilization(String disk_utilization) {
		this.disk_utilization = disk_utilization;
	}

	public Date getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}

	public void setSnmpId(Integer snmpId) {
		this.snmpId = snmpId;
	}
	
	public Integer getSnmpId() {
		return snmpId;
	}

	@Override
	public String toString() {
		return "SNMPInfo [snmpId=" + snmpId + ", ipAddress=" + ipAddress + ", cpu_utilization=" + cpu_utilization
				+ ", memory_utilization=" + memory_utilization + ", disk_utilization=" + disk_utilization
				+ ", updated_time=" + updated_time + "]";
	}
	
		
}
