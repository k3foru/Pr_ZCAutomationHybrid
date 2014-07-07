package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

/** @author Abhishek Singh
 * Costume Methods which can use in any class or method.
 * */

public class FileHandlingUtilities {
	
	// Delete the Existing Directory.
	public static boolean deleteDirectory(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            File delFile = new File(dir, children[i]);
	            if (!delFile.exists()) {
	                System.out.println("Cannot find directory to delete" + delFile.getPath());
	                return false;
	            }
	            boolean success = deleteDirectory(delFile);
	            if (!success) {
	                System.out.println("failure during delete directory" + delFile.getPath());
	                return false;
	            }
	        }
	        // The directory is now empty so now it can be smoked
	    }
	    return dir.delete();
	}
	
	// Delete All Files inside a Directory
	public static void deleteFiles(String dirName){
		File file = new File(dirName);        
        String[] myFiles;      
        if(file.isDirectory()){  
            myFiles = file.list();  
            for (int i=0; i<myFiles.length; i++) {  
                File myFile = new File(file, myFiles[i]);   
                myFile.delete();  
            }  
         } 
	}
	
	// to get Last file name of current URl
	public static String getURLName(String str)
	{
	   Character chr='p';
	   String charString="";
	   String charString1="";
	   
	   int dec=str.length();
	   String stopWhile=""; 
	   while(!stopWhile.equals("stop"))
	   {
		   chr=new Character(str.charAt(dec-6));
		   charString=chr.toString();
		  // System.out.println("-------------"+charString);
	
		   if(charString.equals("/"))
		   {
		      stopWhile="stop";
		   }
		   else
		   {
			   dec--;
			   charString1 =charString+charString1;
		   }
	   }
		   return charString1; 
	}
	
	public static void readTextFile(String fileName) throws IOException{
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuffer sb = new StringBuffer();
		String eachLine = null;
		while ((eachLine = br.readLine()) != null) {
			sb.append(eachLine).append("\n");
		}
		br.close();
		System.out.println("Log contents : \n" + sb.toString());
	}
	
	public static void main(String a[]) throws ParseException{
		//CommonUse.separateDateFormate(CommonUse.getAfterTodaysDate());
		System.out.println(FileHandlingUtilities.deleteDirectory(new File("ScreenShot_TestFailed")));
	}
}
