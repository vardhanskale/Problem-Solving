package com.algo.bst;

public class MyInteger {
	private int data; 
	
	MyInteger(){
	    this.data = 0; 		
	}
	
	MyInteger(int data){
       this.data = data; 		
	}
	
	public void incr() {
		++data; 
	}
	
	public int get() {
		return data; 
	}
	
}
