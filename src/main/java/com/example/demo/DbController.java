package com.example.demo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.snmp4j.smi.OID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.Average;
import com.example.entities.SNMPInfo;
import com.example.util.HibernateUtil;
import com.google.gson.Gson;


@RestController
public class DbController {
 	
    @RequestMapping(value="/{ipaddress:.*}",method=RequestMethod.GET)
	public void getSNMP(@PathVariable("ipaddress") String paramName) throws IOException{
    	
    	Timerclass tc = new Timerclass(paramName);
    	tc.getTime(paramName);
    	
    	System.out.println("inside getsnmp = "+paramName);
    	    	
	}
          	    
    public void sayHello(String paramName) throws IOException{
		
		long count = 0;
		
        Controllerdb client = new Controllerdb("udp:"+paramName+"/161");
		System.out.println("udp:"+paramName+"/161");	
		client.start();
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			Criteria crit = session.createCriteria(SNMPInfo.class);
			crit.setProjection(Projections.rowCount());
		     count = (long)crit.uniqueResult();
		     
		     System.out.println("count is = " +count);
						
		}catch(HibernateException e){
			
			e.printStackTrace();
		}
		
		if (count > 19){
	
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
						
			session.beginTransaction();			
			SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM snmp_info WHERE snmpID LIMIT 1");
			sqlQuery.executeUpdate();
					
		}catch(HibernateException e){
			e.printStackTrace();
		}
		}
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
						
			session.beginTransaction();
			
			SNMPInfo snmpinfo = new SNMPInfo();
			snmpinfo.setSnmpId(101);
			snmpinfo.setIpAddress(paramName);
			snmpinfo.setCpu_utilization(client.getAsString(new OID(".1.3.6.1.4.1.2021.11.9.0")));
			snmpinfo.setMemory_utilization(client.getAsString(new OID(".1.3.6.1.4.1.2021.4.6.0")));
			snmpinfo.setDisk_utilization(client.getAsString(new OID(".1.3.6.1.4.1.2021.9.1.8.1")));
			snmpinfo.setUpdated_time(new Date());
			
			session.save(snmpinfo);
			session.getTransaction().commit();
			
			System.out.println("Successfully inserted.");
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
	
			
	}

	    @RequestMapping(value="/{starttime}/{endtime:.*}",method=RequestMethod.GET)
		public String fetchingData(@PathVariable("starttime") String startTime,@PathVariable("endtime") String endTime) throws IOException, ParseException{
	
	    List<SNMPInfo> list = new ArrayList<SNMPInfo>();
	    
	  
	   	
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			   		 
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
		        /*localhost:8087/fromdate/todate*/
		        
			List<Average> snmpList = new ArrayList<Average>();
			Average snmp= new Average();
			 
		        Date startDate = formatter.parse(startTime);
		        Date endDate = formatter.parse(endTime);
		        System.out.println("startdate =" +startDate);
		        System.out.println("enddate" +endDate);
		        
		        list = session.createCriteria(SNMPInfo.class)
		                .add(Restrictions.between("updated_time", startDate, endDate))
		                .list();
		        int cpu_sum = 0;
		        int memory_sum = 0;
		        int disk_sum = 0;
		        
		        
		        		        
		        for (SNMPInfo order : list) {
		        	
		        	cpu_sum = cpu_sum + Integer.parseInt(order.getCpu_utilization());
		        	memory_sum = memory_sum + Integer.parseInt(order.getMemory_utilization());
		        	disk_sum = disk_sum + Integer.parseInt(order.getDisk_utilization());
		        			        	
		        }
		       
		        int cpu_avg = cpu_sum / list.size();
		        int memory_avg = memory_sum / list.size();
		        int disk_avg = disk_sum / list.size();
		        System.out.println("cpu_utilization = " +cpu_avg + " , memory_utilization = "+memory_avg + " , disk_utilization = "+disk_avg);
		        
		        snmp.setCpu_avg(cpu_avg);
		        snmp.setMemory_avg(memory_avg);
		        snmp.setDisk_avg(disk_avg);
		        snmpList.add(snmp);
		        
		       list.addAll(snmpList);		        
		        
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		String json = new Gson().toJson(list);
		return json;
		
}
}