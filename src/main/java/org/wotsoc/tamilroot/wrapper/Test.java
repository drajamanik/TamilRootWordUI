package org.wotsoc.tamilroot.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Test {
	public static void main(String args[]) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

        // Creating another linked list and copying 
        ArrayList<String> sec_list = new ArrayList<String>(); 
        sec_list = (ArrayList<String>)list.clone(); 
        sec_list.add("6");
        sec_list.add("7");
        sec_list.add("8");
        sec_list.add("9");
        sec_list.add("10");
        sec_list.add(5, "50");
        // Displaying the list 
        System.out.println("First ArrayList: " + list); 
        // Displaying the other linked list 
        System.out.println("Second ArrayList is: " + sec_list);
	        
//		list.stream().parallel().forEach(i -> {
//		    System.out.println(i);
//		  });
	}
}
