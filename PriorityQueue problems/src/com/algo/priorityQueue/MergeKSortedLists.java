package com.algo.priorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/* 
 * Problem : Sort K Sorted Lists.
 * Input   : Array of head nodes of K linked list. ( First nodes of K different link lists ) 
 * Output  : Sorted Linked List containing all the elements.  
 * 
 * General Approach is similar to merging K sorted arrays : 
 * 1. Take the head nodes of all the link lists and add them in priority Queue, 
 *    so that nodes are sorted as per comparator logic. 
 * 2. Take out the smallest element from Q & add it at the end of the merged list. 
 * 3. If the smallest element taken out has more nodes behind it, add its 
 *    next node back into priority queue. 
 * 4. Keep doing steps 1 to 3 till the queue is not empty. 
 * 5. Return the merged list. 
 *    TC : O(Nlog(K)) where N is the total number of nodes in all the lists and K is total no of lists.
 *    
 * Details
 * =======
 *   1. Create a priorityQueue. 
 *   2. Add all nodes in the array into the priority Queue. 
 *	 3. Pop an element from PQueue( let's call it 'newNode'). 
 *   4. If 'newNode' has more elements after it, add its next element back in priorityQueue. 
 *	 5. Add the nextNode at the end of the new merged list : 
 *  	5.1 Create a new 'headNode' for the target merged list. It always points to the first node in the list. 
 *  	5.2 We need a way to remember the last node in this list so that we can remember where to add the 'nextNode'. 
 *  	5.3 Let 'lastNode' remember the last node of this list. Initially lastNode = headNode. 
 * 		5.4 As you keep adding Nodes at the end of the list, keep moving lastNode to the end.  
 *     
 */ 

class ListNode {
	int value; 
	ListNode next; 
	
	public ListNode(int value) {
		this.value = value;
	}
	public String toString() {
		return  value + " ->" ;
	}
}
public class MergeKSortedLists {

	public static Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
	    @Override
	    public int compare(ListNode e1, ListNode e2) {
	           return e1.value - e2.value;
	    }
    };	
	 
    public static ListNode mergeKSortedLists(ListNode[] nodes) {
 
        if ( nodes == null ) return null;  
  
        // 1. Create a priorityQueue. 
        PriorityQueue<ListNode> pQueue = new PriorityQueue<ListNode>(Math.max(1,nodes.length),ListNodeComparator); 
        
        //2. Add all list nodes in priority Queue 
    	for (int i = 0 ; i < nodes.length ; i++ ) {
    		if ( nodes[i] != null ) {
    		    pQueue.add(nodes[i]);    
    		}
    	}
    	
    	// Print the contents of priorityQueue.
    	System.out.println("Contents of priority queue prior to merging are : "); 
    	java.util.Iterator<ListNode> pQueueIterator = pQueue.iterator();
    	while (pQueueIterator.hasNext() ) {
    		System.out.print(pQueueIterator.next().toString());
    	}
    	
        ListNode newNode = null; // Node retrieved from priorityQueue
        ListNode headNode = new ListNode(-1); // head node of the mergedList to be returned back to caller.
        ListNode lastNode = headNode; // Last node in new merged List.
    	while ( !pQueue.isEmpty()) {
        	//3. Pop the smallest element from priorityQueue 
        	newNode = pQueue.poll(); 
        	
        	//4. If newNode has more elements after it, put them in pQueue again. 
    		if (newNode.next != null){
    			pQueue.add(newNode.next); 
    		}
    		
    		//5. Add the nextNode at the end of the new merged List. 
    		lastNode.next = newNode;
    		lastNode = newNode; 
    		newNode.next = null; 
        }	
        return headNode.next; 
    }
    
    public static void printList(ListNode node) {
    	while(node != null) {
    		System.out.print(node.value + " -> "); 
    		node = node.next; 
    	}
    	System.out.print("null"); 
    }
    
	public static void main(String[] args) {
		int k = 3 ; // K sorted Linked List; 
		
		// An array to store the head nodes of the link list 
		ListNode[] nodes = new ListNode[k]; 
		nodes[0] = new ListNode(1);
		nodes[0].next = new ListNode(5); 
		nodes[0].next.next = new ListNode(7); 
		
		nodes[1] = new ListNode(2); 
		nodes[1].next = new ListNode(3); 
		nodes[1].next.next = new ListNode(6);
		nodes[1].next.next.next = new ListNode(9);
		
		nodes[2] = new ListNode(4); 
		nodes[2].next = new ListNode(8); 
		nodes[2].next.next = new ListNode(10);
	
		System.out.println("Contents of all the lists ...");
		for (int i = 0 ; i < k ; i++ ) {
			printList(nodes[i]);
			System.out.println("");
		}
		// Merge all lists into one. 
		ListNode head = mergeKSortedLists(nodes);
		System.out.println("\nContents of merged list ...");
		printList(head); 
	}

}