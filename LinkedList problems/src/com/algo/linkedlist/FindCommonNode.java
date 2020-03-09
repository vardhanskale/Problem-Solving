package com.algo.linkedlist;

public class FindCommonNode {

	
	/* 
	 * Approach1 : Brute force : Compare each element in list1 with other element in list2 
	 * If list1 has n elements and list2 has m elements, then the complexity is MxN. 
	 * Hopeless solution. Never give or accept it. 
	 * Returns common node. 
	 */
	public ListNode findCommonNode1(ListNode head1, ListNode head2) {
		for (ListNode current1 = head1.next; current1 != null ; current1 = current1.next  ) {
			for ( ListNode current2 = head2.next; current2 != null ; current2 = current2.next  ) {
				if ( current1 == current2 ) {
					return current1; 
				}
			}
		}	
		return null; 	
	}
		
	/* 
	 * Approach2 : Data structure : Use HashMap to find the duplicate node. 
	 * Add all nodes of list1 in hashMap. 
	 * For the second list do...
	 * if hashMap.contains(node) return true. 
	 * else hashMap.add(node) 
	 */
	public ListNode findCommonNode2(ListNode head1, ListNode head2) {
		for (ListNode current1 = head1.next; current1 != null ; current1 = current1.next  ) {
			for ( ListNode current2 = head2.next; current2 != null ; current2 = current2.next  ) {
				if ( current1 == current2 ) {
					return current1; 
				}
			}
		}	
		return null; 	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
