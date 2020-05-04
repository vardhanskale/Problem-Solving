package com.algo.filebased;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*
This programs clocks the timing of reading from disk vs ram . 
We then clock the time difference between fileWriter vs BufferedWriter.

To transfer data in memory : 
----------------------------
1) Create two integer arrays of n bytes.
2) Transfer the data between two arrays using System.arraycopy() method.
3) Do the transfer 10 lack times and measure the time it takes to copy the data.
4) System.arraycopy(source,sourceStartIndex,destination,destStartIndex, nElements )

To transfer in file without buffering: 
-------------------------------------
1) Write 2n characters in the file using fileWriter.write(charBuffer).
2) Write into file 10 lack times. 
3) clock the time

Transfer data in file by buffering: 
----------------------------------
1) Write 2n characters in the file using BufferedFileWriter.write(charBuffer).
2) Write into file 10 lack times.
3) clock the time 

Testing the program gives the following output : 
------------------------------------------------
time for memory access : 23
time for disk access : 4284
time for buffered disk writing : 180


*/ 

public class MemoryVsDisk {

	private static void runMemoryTest(int n ) 
	{ 
	  int[] buffer1 = new int[n];
	  int[] buffer2 = new int[n];
	  long start = System.currentTimeMillis(); 
	  for( int k=0 ; k < 1000000 ; k++ ) {
	     System.arraycopy(buffer1,0,buffer2,0,n);
	  }
	  long end = System.currentTimeMillis();
	  System.out.printf("time for memory access : " + (end - start));
	  System.out.println();
	}

	private static void runDiskTest(int n ) {
	  long start, end; 
	  try { 
	        //Take n bytes of buffer
	        char[] charBufer = new char[n*2];
		    start = System.currentTimeMillis(); 
		    //Create a file in E:\ with name data1
	        FileWriter fout = new FileWriter("E:\\data1.txt");
	        for ( int k = 0 ; k < 1000000; k++ ) {
	            fout.write(charBufer); 
				// Before writing to the file the content goes into OS buffer. 
				// Flushing ensures that the OS buffer is emptied and content goes in the disk. 
	            fout.flush();		 
		    } 	 
			// If you don't do flush, close is guaranteed to close the file.
	        fout.close(); 
		    end = System.currentTimeMillis();
		    System.out.printf("time for disk access : " + (end - start) );  
	    } catch ( FileNotFoundException e ) {
		   System.err.println(e); 
		   System.exit(1);
		} catch (IOException e ) {
		   System.err.println(e);  
		}
	  System.out.println();
	}
	  
	/* 
	 * Instead of using fileWriter to write to the disk use BufferedWriter.
	 * This is ten forty times faster as it reduces disk access. 
	 */
	private static void runBufferedDiskTest(int n ){
		long start, end; 
		try { 
	        //Take n bytes of buffer
	        char[] charBuffer = new char[n*2];
		    start = System.currentTimeMillis(); 
		    //Create a file in E:\ with name data1
		    FileWriter fout = new FileWriter("E:\\data1.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(fout);
	        for ( int k = 0 ; k < 1000000; k++ ) {
	        	bufferedWriter.write(charBuffer); 
				// Before writing to the file the content goes into OS buffer. 
				// Flushing ensures that the OS buffer is emptied and content goes in the disk. 
	            fout.flush();		 
		    } 	 
			// If you don't do flush, close is guaranteed to close the file.
	        fout.close(); 
		    end = System.currentTimeMillis();
		    System.out.printf("time for buffered disk writing : " + ( end - start ));  
	    } catch ( FileNotFoundException e ) {
		   System.err.println(e); 
		   System.exit(1);
		} catch (IOException e ) {
		   System.err.println(e);  
		}
	} 
	   	  
	public static void main(String[] args){
	    int n = 10; 
		runMemoryTest(n);
		runDiskTest(n);
		runBufferedDiskTest(n); 
	}

}
