package com.algo.setmap;

import java.util.HashMap;
import java.util.Random;


/*
 * An LRU cache is a fixed size memory to store most frequently used data. 
 * When the cache is full, the least recently used data is pushed out to free up space for storing new data. 
 * This is what happens when the user adds and fetches values :   	   
	1. Adding elements in cache 
		1.1 When cache is not full - Add items in the cache by calling an API. 
		1.2 When cache is full  - Remove last used element and add new element at the end. 
		1.3 When cache already has the item - This means user wants to update an existing value in the cache. 
			Remove the object from its current location, update its value and add it at the end of cache.  
                   				   	                                      
    2. Fetching elements from cache 
 		2.2 When cache has  data - Find the data, remove it from current location, put it at the most recently used location. 
	    2.3 When cache is does not have data - a null value is returned. 
 
 *  While storing the data in the cache we can arrange them in the order they come. 
 *  This way the first item becomes the least recently used and the last one is most recently used.
 *	While fetching the data from cache, we rearrange it so that it is removed from its original position and placed at the top/end. 
 *	This way the recently deleted data will not get deleted when the cache gets full. 
 *	We need a data structure which can add and remove items at the ends. 
 *	One end will be the least used data and the second end will be most recently used data. 
 *	It should also allow us to remove items from the middle easily.
 *
 *  Can we use the Link List to store the data ? 
 *	In Java its implemented as a doubly link list and it has the methods to remove and add methods at the end and from the middle. 
 *  The problem with link list is that it does not allow quick access to data. How to overcome this limitation ? 
 *  We know that a hashtable ( or dictionary in python ) provides fastest data access. TC: O(1) am. 
 *	How about combining a hasMap/Dictionary together  to get the best of both the data structures ? 
 *  Lets  store the key-data in the link list node and add the node to the link list. 
 *  Lets also store a unique key and ListNode reference in HashMap.  
 *  This way the hashMap will serve as an index of a book and the Link list can serve as the chapters of the book. 
 *  Users will supply a primary key, using which we can quickly access the reference of the link list.
 */ 

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
	
	/*
	 * Remove an item from the middle and add it to last. Passing key to HashMap
	 * returns a Node. Remove that node from middle of list and add at the end of
	 * list. The advantage of a doubly linked list is that given a node its so easy
	 * to remove that node from the list and add it at the end. If we had a node of
	 * a singly linked list it would have taken longer to remove it O(n).
	 */
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
	
	/*
	 * When cache is full, remove the first element from DLL and also from the
	 * hashMap. head is not the first node. heads points to the first node. First
	 * comes after head. head.next is the first node. head.next.next is the second.
	 */
	public DListNode removeFirst() {
		DListNode tmp = head.next; 
		head.next = tmp.next;
		tmp.next.prev = head; 
		return head;
	}
	
	// Most recent item is added last.
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

/*
 * Cache is constructed using a hasMap and doubly linked list. HashMap allows
 * quick access to the linked list nodes and contains key & Node Address. Doubly
 * Linked List stores the content of cache.
 * 
 */
public class LRUCache {
	private int capacity; 
	private HashMap<Integer, DListNode> index; 
	private DLinkedList list; 
	
    public LRUCache(int capacity) {
       this.capacity = capacity; 
       list = new DLinkedList();
       index = new HashMap<Integer, DListNode>();
	}

   /*
	* Given a key, get ListNode's value. Remove the node from list and add at the
	* end.
	*/
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
