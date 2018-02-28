package com.example.entities;

public class Average extends SNMPInfo{
	
	private int cpu_avg;
	private int memory_avg;
	private int disk_avg;

	
	public int getCpu_avg() {
		return cpu_avg;
	}
	public void setCpu_avg(int cpu_avg) {
		this.cpu_avg = cpu_avg;
	}
	public int getMemory_avg() {
		return memory_avg;
	}
	public void setMemory_avg(int memory_avg) {
		this.memory_avg = memory_avg;
	}
	public int getDisk_avg() {
		return disk_avg;
	}
	public void setDisk_avg(int disk_avg) {
		this.disk_avg = disk_avg;
	}
	
}
