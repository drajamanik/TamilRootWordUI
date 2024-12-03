package org.wotsoc.tamilroot.wrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Rajamani David
 * @since	May 31, 2019
 *
 */

import java.util.ArrayList;
/**
 * @author rdavid
 *
 */
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitWithDelimiters {
	
	public SplitWithDelimiters(){
		
	}
	
  public static void mainOld(String[] args) throws Exception {
	  String strFile=readFileAsString("c:\\tamil\\sample_ponniyen_selvan.txt");
	  SplitWithDelimiters swd= new SplitWithDelimiters();
	  List<String> strList= swd.splitWords(strFile);
	  for(String str:strList)
		  System.out.println(str);
  }
  
	public static String  readFileAsString(String fileName) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;
		try 
		{
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) 
			{
				sb.append(sCurrentLine);
			}
		} 
		catch (IOException e) 
		{

			e.printStackTrace();

		} finally {
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			}
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}
  
  public List<String> splitWords(String str){
		String[] arrOfStr = str.toString().split("(?=[, . ! , ; : - \\s \" ?.@]+)");
		List<String> wordList =new ArrayList<String>();
		for(String arr:arrOfStr){
			String tempArr[]= SplitWithDelimiters.splitWithDelimiters(arr.trim(),"(?=[, . ! , ; : - \\s+ \" ?.@]+)");
			for(String tempStr:tempArr)
				wordList.add(tempStr.trim());
		}
		return wordList;
  }


  public static String[] splitWithDelimiters(String str, String regex) {
    List<String> parts = new ArrayList<String>();

    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(str);

    int lastEnd = 0;
    while(m.find()) {
      int start = m.start();
      if(lastEnd != start) {
        String nonDelim = str.substring(lastEnd, start);
        parts.add(nonDelim);
      }
      String delim = m.group();
      parts.add(delim);

      int end = m.end();
      lastEnd = end;
    }

    if(lastEnd != str.length()) {
      String nonDelim = str.substring(lastEnd);
      parts.add(nonDelim);
    }

    String[] res =  parts.toArray(new String[]{});
    //System.out.println("result: " + Arrays.toString(res));

    return res;
  }
  
  public String toString(String[] strArr){
	  StringBuffer sb = new StringBuffer();
	  for (String str:strArr)
		   sb.append(str);
	  return sb.toString();
  }
  
  public static char[] toCharArray(String value) {
      return value.toCharArray();
  }

  public static void toCharArrayUnicode(String str) {
	  for (int i = 0; i < str.length();) {
		    int ch = str.codePointAt(i);
		    i += Character.charCount(ch);
		    System.out.println(i+":"+ch+":"+Character.charCount(ch));
	 }
  }
  
	public static void main(String args[]) {
//		  SplitWithDelimiters swd= new SplitWithDelimiters();
//		  List<String> strList= swd.splitWords("இன்றைக்கு இன்றைக்கு.");
//		  for(char ch:toCharArray("பொது"))
//			  System.out.println(ch);
		toCharArrayUnicode("பொது");
  }
}