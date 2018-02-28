package com.example.service;

import org.springframework.stereotype.Service;

@Service("snmpserviceimpl")
public class SNMPServiceImpl implements SNMPService{

	public void print(){
		System.out.println("i am inside service");
		
	}
}
