package com.example.demo;

public class Test {
	
	
	public void m(int a){
		System.out.println("int method"+a);
	}
	
	public void m(float a){
		System.out.println("float method"+a);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t= new Test();
		t.m(10l);

	}

}
