package com.model;

import java.util.ArrayList;
import java.util.Hashtable;

public class DataAnalysis{

	public double getSexRatio(ArrayList<Applicant> applicantList){
		int boy = 0, girl = 0;
		for(Applicant applicant : applicantList){
			if(applicant.getSex().equals(Sex.MALE)){
				boy++;
			}else{
				girl++;
			}
		}
		return boy/girl;
	}
	
	
	
	public ArrayList<Event> getHitRatio(ArrayList<Event> eList){
		ArrayList<Integer> hitArray = new ArrayList<Integer>();
		ArrayList<Event> top10 = new ArrayList<Event>();
		for(Event event:eList){
			hitArray.add(event.getCTR());
		}
		for(int i=1;i<=10;i++){
			top10.add(eList.get(find(hitArray,i)));
		}
		return top10;
	}
	private int find(ArrayList<Integer> array, int k){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.size(); ++i){
			int n = array.get(i);
			int j = 0;
			for (int length = Math.min(list.size(), k); j < length; ++j)
				if (n > list.get(j))
					break;
			if (j < k)
				list.add(j, n);
		}
		return list.get(k-1);
	}


	public ArrayList<Event> whatIParticipateIn(String kwd) {
		return null;
	}

	public Hashtable<String,ArrayList<Event>> relationDistanceTable(ArrayList<Event> myEvents) {
        return null;
    }

	public void RelationJsonPacker(String kwd, Hashtable<String, ArrayList<Event>> table) {
		return;
	}


}