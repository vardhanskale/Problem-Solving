package com.algo.filebased;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile; ;

/*
 * Program to illustrate usage of java RandomAccessFiles. 
 * Think of it as a big character array which has a file pointer initially pointing at 0 index. 
 * The randomAccessFile.seek() method allows us to move the pointer at a specified location from
 * which we can access the content of the file using the  randomAccessFile.readLine() method. 
 * 
 * Argument : E:\Programming\simplefile.txt
 * 
 * Contents of file : 
 * abcd (Offset is 4 characters + newline is taken as 2 characters) 
 * def
 * xyz
 * pqr
 * abcd
 * asdfds
 * jfjlkdsf
 * Cursor is the current position in a file.
 * 
 * Output : 
 * 0
 * 6
 * 11
 * 16
 * abcd
 * null
 */

public class RandomAccessFileTest {

   public static void main( String[] args ) throws Exception {
       File file = new File(args[0]);  
       // Create a random access file object. Supply the file which we want to read. 
       RandomAccessFile rf = new RandomAccessFile(file,"r"); 
       // First offset is 0. So getFilePointer() returns 0.
	   System.out.println(rf.getFilePointer()); 
	   // Read one line : 'abcd' (4 chars) + newline (2 chars) = 6 characters. 
	   // So now the pointer is at the 6th character. 
	   rf.readLine(); 
	   System.out.println(rf.getFilePointer()); 
       // Read one line : 'def' (3 chars ) + newline (2 chars) = 5 characters. 
	   // So now our file pointer would be at 6 + 5 = 11th position. 
	   rf.readLine(); 
	   System.out.println(rf.getFilePointer());
	   // Read one line : 'xyz' ( 3 chars ) + newline ( 2 characters ) =  5 characters.  
	   // So now our file pointer would be at 11 + 5 = 16th position.
 	   rf.readLine(); 
	   System.out.println(rf.getFilePointer());
	   // Now lets reset the file pointer to the start of the file, i,e at index 0
	   // If we do a read line now, we will again get the first line 'abcd'. 
	   rf.seek(0);
	   System.out.println(rf.readLine()); 
	   // Lets take the file pointer to the end of the file.
	   rf.seek(file.length()); 
	   System.out.println(rf.readLine()); 
   }
   
}   