package com.algo.setmap;

import java.util.HashMap;
import java.util.Random;

class DListNode {
	
	 Integer key; 
	 String value; 
	 DListNode prev; 
	 DListNode next; 
	 
	 public DListNode(){
		 prev = next = this; 
	 }
	 
	 public DListNode(Integer key, String value) {
		 this.key = key; 
		 this.value = value; 
		 prev = next = this; 
	 }
	 
}

class DLinkedList{
	
	private DListNode head; 
	private int size;
		
	public DLinkedList() {
		head = new DListNode(); 
	}
	
	public void removeAddLast(DListNode p) {
		// remove
		p.prev.next = p.next; 
		p.next.prev =  p.prev; 
		// addLast 
		p.prev = head.prev; 
		p.next = head; 
		head.prev.next = p;
		head.prev = p; 
		
	}
	
	public DListNode removeFirst() {
		DListNode tmp = head.next; 
		head.next = tmp.next;
		tmp.next.prev = head; 
		return head;
	}
	
	public DListNode addLast(Integer key, String value) {
		DListNode tmp = new DListNode(key, value);
		tmp.prev = head.prev;
		tmp.next = head;
		head.prev.next = tmp;
		head.prev = tmp;
		return tmp;
		
	}
	public int getSize() {
		return size;
	}
	
	
	public void display() {	
		for ( DListNode current = head.next ; current != head ; current = current.next ) {
			System.out.print("(" + current.key + "," + current.value + ")->" ); 
		}
		System.out.println();
	}
}

public class LRUCache {
	private int capacity; 
	private HashMap<Integer, DListNode> index; 
	private DLinkedList list; 
	
    public LRUCache(int capacity) {
       this.capacity = capacity; 
       list = new DLinkedList();
       index = new HashMap<Integer, DListNode>();
	}

    public String get(Integer key) {
		DListNode node = index.get(key);
		list.removeAddLast(node);
		return node.value;
	}
    
	public void put(int key, String value) {
        
    	// If hashMap contains key-value, remove the node and add it to the last of list
    	DListNode node = index.get(key); 
    	if ( node != null ) {
    		node.value = value; 
    		list.removeAddLast(node);
    		return; 
    	}
    	
    	// If cache is full, remove entries from hashMap and link list. 
    	if ( list.getSize() == capacity ) {
    		list.removeAddLast(node);
    		index.remove(key); 
    	}
    	
    	// Cache has capacity and item does not exists in cache. index.get(key) returns null.
    	// add the new node at the end of the link list and add an entry in hashMap.
    	node = list.addLast(key,value);
    	index.put(key, node);
    }

    public void display() {
    	list.display();
    }
    
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(5); 
		//Pass 10 random key value pairs in the LRU cache class. 
		Random r = new Random(100); 
		for ( int i = 0; i < 10 ; ++i ) {
			cache.put(r.nextInt(5) + 1, "value" + (i+1));
		}
		cache.display();
		cache.get(1);
		System.out.println("Values after getting value '1' ");
		cache.display();
	}

}
