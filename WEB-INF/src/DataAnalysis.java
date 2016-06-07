package com.model;

import com.web.*;
import com.model.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;

public class DataAnalysis{
	public double getSexRatio(ArrayList<Apply> aList){
		int boy,girl;
		for(Apply apply:aList){
			if(apply.sex.equals("male")){
				boy++;
			}else{
				girl++;
			}
		}
		return boy/girl;
	}
	
	
	
	public ArrayList<Event> getHitRatio(ArrayList<Event> eList){
		ArrayList<int> hitArray = new ArrayList<int>();
		ArrayList<Event> top10 = new ArrayList<Event>();
		for(Event event:eList){
			hitArray.add(event.CTR);
		}
		for(int i=1;i<=10;i++){
			top10.add(eList.get(find(hitArray,i)));
		}
		return top10;
	}
	private int find(ArrayList<int> array, int k){
		Arraylist<int> list = new ArrayList<int>();
		for (int i = 0; i < array.size(); ++i){
			int n = array.get(i);
			int j = 0;
			for (int length = Math.Min(list.size(), k); j < length; ++j)
				if (n > list.get(j))
					break;
			if (j < k)
				list.add(j, n);
		}
		return list.get(k-1);
	}
	
	

}