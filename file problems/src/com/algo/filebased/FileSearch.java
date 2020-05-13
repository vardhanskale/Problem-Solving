package com.algo.filebased;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * Problem : Search for a record in a file based one single feature.  
 * Example : Search in emp.csv for empid 
 * 
 * Solution 1 : Linear Search. Read file line by line. 
 * Solution 2 : Use Data Structure  ->  Balanced BST (TreeMap) to create an index.
 * Solution 3 : Use Data Structure  ->  B+ Tree to create a map.
 */
 
public class FileSearch {
	
  /* Use a TreeMap so that you can search faster using the key 
   * Only problem is that index is very deep in a BST if we have 5 crore records.
   * Log ( 5 crore ) is the time that would be required to search
   * If you want a better index with lesser height, use a B+ tree
   * We did not use hash because we need range query which hash does not support.
   * Hashes are good for point qeries only.
   */ 
   private static TreeMap<String, Long> index = new TreeMap<String, Long>(); 
	
   /* 
	*  Creates a RandomAccessFile with 'n records. 
	*  Dynamically constructs -  empid, empname, sal, dept. 
	*  inFile - input file 
    *  n - number of records to create. 	 
	*/ 
	public static void createRandomFile(String infile, long n) throws Exception {
	      BufferedWriter bw = new BufferedWriter(new FileWriter(infile)); 
		  Random r = new Random(100); 
		  for ( long i = 0 ; i < n ; ++i){
                String empid = "e" + (i+1); 
				String empname = "Employee" + (i+1); 
				int sal = r.nextInt(10000) + 10000;
                String dept = "dept" + ( r.nextInt(10) + 1) ; 
                bw.write(empid + "," + empname + "," + sal); 
                bw.newLine(); 				
		  }		
          bw.close();  // This ensures clean updation.   
	      // At the end of this program we would get a large file of employee records.
	}
	
   /*
    * Solution 1: Naive strategy ->  Linear Search.
    * TC : O(N/B)   
    * Read line by line till empid is found.
    * Returns empid.
	*/
	public static String searchFile1(String file, String key ) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(file)); 
	    String line; 
	    String empid;
	    while( (line = br.readLine()) != null ) {
	    	empid = line.split(",")[0]; 
	    	//System.out.println("key is " + key + " empid is " + empid );
	    	if ( empid.equals(key.trim().toLowerCase()))
	    	   return empid; 
	    }
	    br.close();
        // There is no match for a given id. Return null.  	  
	    return null;   
	}
	
   /*
    * Solution 2  : Data Structure Pattern ->  Use Balanced BST to construct index for a file.
    * Index of empid and starting offset of each line.
    * Balanced BST is available out of the box in java as TreeMap/TreeSet
    * We choose treeMap to create index because the records should be sorted based on key/index
	* TC : O(N/B) 
	* It's a one time activity, like the create index command in SQL.
	* Later if an update happens to the file, we can insert records wherever we want and the 
	* value can be updated in index. 
	* 
	* What if one person is reading the record and other person is updating it ? 
	* Concurrancy is important for the update operation. At the moment we ignore that.
	* You may block the entire index, or part of the tree. Many solutions exist. 
    */
	
	public static void init(String file) throws Exception {
		/*
		 *  1. Create a randomAccessFile so that you can get record offset in a file.
		 *  2. Read each line/block, extract get empoyeeId.
		 *  3. Store empId and starting index of that line in a treeMap to create an index.
		 *  This is a one time activity and will be called only once.  
		 */
	   
		RandomAccessFile rf = new RandomAccessFile(new File(file), "r"); 
		String emp; 
		// line_offset stores the starting index for each line. Initially 0.
		long line_offset = 0 ;  
		
		/* Internally rf.readLine() does not just a single line but reads the whole block.
		 * OS always reads the block sorrounding the offset. We dont care about remainig records. 
		 * We just need the offset of the current record which is given by rf.getFilePointer();
		 * Offset starts from 0. When a line is read, the line_offset moves to the next line.
		 */ 
		
		while ( (emp = rf.readLine()) != null ) {
			String empid = emp.split(",")[0]; 
			// We want to search on empid and my initial record is at offset 0. 
			index.put(empid, line_offset); 
			line_offset = rf.getFilePointer(); 
		}	
	}
	
   /* 
    * Solution 3 : DS pattern -> B+ Tree  indexes
    * B+ Treee is a higher degree balanced BST, degree > 2 which supports
    * two types of query in single dimention/paramer
    *   - Point Query 
    *   - Range Query 
    * To balance a BBST, we use a rotation thought, the idea to balance a M degree 
    * search tree was discovered by Bayer  
    * 
    */
	 
	 public static String searchFile2(String file, String key) throws Exception {
	   if ( !index.containsKey(key))
	        return null; 
	   long record_offset = index.get(key); 
	   RandomAccessFile rf = new RandomAccessFile(file,"r") ; 		  
	   rf.seek(record_offset); 
      return rf.readLine(); 
    }     		
	
   /*
    * Solution 1: Naive strategy ->  Linear Search.
    * TC : O(N/B)   
    * Read line by line till the employee number is found. Return line.
    * arg1 ->  emp.csv file.
    * arg2 -> empid record to search.     
    * Read each line. Store all comma seperated elements in an array.
    * Search the employeeId in the array. 
    * Test Data : Download test data for employee records from here https://www.briandunning.com/sample-data/ 
    */
	
	public static String fileSearch1(String filename, String empid) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(filename)); 
		String line;
		String[] tokens;
		while ( (line = br.readLine()) != null) {
			tokens = line.split(",");
			//System.out.println(line); 
			if ( tokens[0] == empid)
				return line;
		}
		br.close(); 
		return null;
	}
	
	public static void testString(String filename, String id) {
     		
	}
	
	public static void main(String[] args) throws Exception {
  		
		   // Create a random File.  Arguments -> D:/Programming/employee.csv  
		    System.out.println("Creating a random file with 1000000 records...");
	    	createRandomFile(args[0], 1000000L);
		  
		   // Solution 1 : Linear search in a file 
	       long start = System.currentTimeMillis(); 
		   System.out.println("Liner search results : " + searchFile1(args[0],args[1] ));
		   long end = System.currentTimeMillis();
		   System.out.println("Time for sequential file access : " + (end - start) + " millisecond" );
		   
		   // Solution 2 : Create an index using TreeMap //  
		 
		   // 1. Create an Index. 
		   System.out.println("Indexing begin...");
		   init(args[0]);
		   System.out.println("Indexing completed");
		 
		   // 2. Search in BBST/TreeMap index
		   start = System.currentTimeMillis();
		   System.out.println("BST(TreeMap) index search results : " + searchFile2(args[0], args[1]));
		   end = System.currentTimeMillis();
		   System.out.println("Time for BST(TreeMap) index search : " + (end - start) + " millisecond" );
			
      	 }
	  
	
		/*
		 * String filename = "D://Programming//emp.csv";
		 * System.out.println(fileSearch1(filename, "205"));
		 */ 
  }

