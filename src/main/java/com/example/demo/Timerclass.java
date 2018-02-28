package com.example.demo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Timerclass extends TimerTask {
	
	public String ipAddress = ""; 
    
	
	DbController db = new DbController();
	public Timerclass(String ipaddress){
		this.ipAddress=ipaddress;
	}
	
	public void run() {
    	
    	System.out.println("inside run ="+ipAddress);
    	try {
			
			db.sayHello(ipAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
 

public void getTime(String ipaddress){
	
		System.out.println("get time"+ipAddress);	
	 // And From your main() method or any other method
	 Timer timer = new Timer(ipaddress);
	timer.schedule(new Timerclass(ipaddress), 0, 1000);{

}
}
 
}