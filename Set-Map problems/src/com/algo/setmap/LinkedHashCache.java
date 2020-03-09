package com.algo.setmap;

import java.util.HashMap;
import java.util.Random;

/**
 * This class is used to store & retrieve key & their values. Think of it as a
 * black box. Pass it a key and it returns a value. Pass key,value together to
 * store the key-value pair.
 * 
 * @author Vardhan Kale
 **/

// Link list node contains int and address to previous and next node.
class DListNode {
	Integer key;
	String value;
	DListNode next;
	DListNode previous;

	public DListNode() {
		previous = next = this;
	}

	public DListNode(Integer key, String value) {
		this.key = key;
		this.value = value;
		previous = next = this;
	}
}

class DLinkedList {

	private final DListNode head;
	private DListNode tail;
	private int size;

	// head points to an
	public DLinkedList() {
		head = new DListNode();
	}

	/*
	 * When cache is full, remove the first element from DLL and also from the
	 * hashMap. head is not the first node. heads points to the first node. First
	 * comes after head. head.next is the first node. head.next.next is the second.
	 */

	public DListNode removeFirst() {
		DListNode temp = head.next;
		temp.next.previous = head;
		head.next = temp.next;
		return temp;
	}

	// Most recent item is added last.
	public DListNode addLast(Integer key, String value) {
		// head.previous points to the last node.
		DListNode tmp = new DListNode(key, value);
		head.previous.next = tmp;
		tmp.next = head;
		tmp.previous = head.previous;
		head.previous = tmp;
		return tmp;
	}

	/*
	 * Remove an item from the middle and add it to last. Passing key to HashMap
	 * returns a Node. Remove that node from middle of list and add at the end of
	 * list. The advantage of a doubly linked list is that given a node its so easy
	 * to remove that node from the list and add it at the end. If we had a node of
	 * a singly linked list it would have taken longer to remove it O(n).
	 */

	public void removeAddLast(DListNode p) {
		// Remove the node from the list
		p.previous.next = p.next;
		p.next.previous = p.previous;
		// Add the node to the last of the list
		head.previous.next = p;
		p.previous = head;
		// draw on paper to get a clear picture.
		head.previous.next = p;
		p.next = head;
	}

	public int size() {
		return size;
	}

	public void display() {
		for (DListNode current = head.next; current != head; current = current.next) {
			System.out.print("(" + current.key + "," + current.value + ")->");
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
public class LinkedHashCache {
	private HashMap<Integer, DListNode> index = new HashMap<Integer, DListNode>();
	private DLinkedList list;
	int capacity;

	public LinkedHashCache(int capacity) {
		this.capacity = capacity;
		list = new DLinkedList();
		index = new HashMap<Integer, DListNode>();
	}

	/*
	 * Given a key, get ListNode's value. Remove the node from list and add at the
	 * end.
	 */
	public String get(Integer key) {
		/*
		 * If the key exists in hash table, value exists in cache in that case call
		 * list.removeAddLast(DListNode p) and return node value. If the key does not
		 * exist,
		 */
		DListNode node = index.get(key);
		if (node != null) {
			list.removeAddLast(node);
			return node.value;
		}

		return null;
	}

	public void put(Integer key, String value) {
		DListNode node = index.get(key);
		// If the value exists in map, the content exists in cache. So removeAddLast
		if (node != null) {
			node.value = value;
			list.removeAddLast(node);
			return;
		}

		/*
		 * If cache is full : 1) Remove first item from the front of the list. 2) Remove
		 * first item from the hash-map (index )
		 */

		if (list.size() == capacity) {
			list.removeFirst();
			index.remove(key);
		}

		/*
		 * 1) Add item to the last of list. 2) Add item to the hashMap
		 */

		list.addLast(key, value);
		index.put(key, node);

	}

	public void display() {
		list.display();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedHashCache cache = new LinkedHashCache(5);
		Random r = new Random(100);
		for (int i = 0; i < 10; ++i) {
			cache.put(r.nextInt(5) + 1, "value" + (i + 1));
			cache.display();
		}
		cache.get(1);
		cache.display();

	}

}
