package com.algo.linkedlist;

public class ReverseList {

	public static void reverse1(ListNode head) {

		ListNode current = head.next;
		ListNode previous = null;
		ListNode next = null;

		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		// previous will be the last node when current is null. Make node point to current.
		head.next = previous;

	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		ListNode head = LinkedListUtils.createList(n);
		System.out.println("List before reversal");
		LinkedListUtils.displayList(head);
		reverse1(head);
		System.out.println("List after reversal");
		LinkedListUtils.displayList(head);
	}

}
