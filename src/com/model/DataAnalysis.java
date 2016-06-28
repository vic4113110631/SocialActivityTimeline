package com.model;

import java.util.*;

public class DataAnalysis{

	public int[] getSexRatio(ArrayList<Applicant> applicantList){
		int sex[] = new int[2];
		for(Applicant applicant : applicantList){
			if(applicant.getSex() == Sex.MALE){
				sex[0]++;
			}else{
				sex[1]++;
			}
		}
		return sex;
	}

	public ArrayList<Event> getHitRatio(ArrayList<Event> eList){
		ArrayList<Integer> hitArray = new ArrayList<Integer>();
		ArrayList<Event> top10 = new ArrayList<Event>();
		for(Event event: eList){
			hitArray.add(event.getCTR());
		}
		int j=0;
		int k=1;
		for(int i=1;i<=10;i++){
			j =find(hitArray,i);
			for(Event e:eList){
				if(e.getCTR()==j){
					k=e.getId();
					System.out.println(k);
					System.out.println(e.toString());
					break;
				}
			}
			System.out.println(i+".>>["+j+"]"+eList.get(k).getName());
			top10.add(eList.get(k));
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

	public Hashtable<Applicant, ArrayList<Event>> calWhoAndWhoseEvent(ApplicantProcess ap, EventProcess ep, ArrayList<Event> master, ArrayList<Event> eventFilter, String kwd){

		ArrayList<Type> myEventType = new ArrayList<Type>();
		for(Event e : master){
			Type thetype = e.getType();
			if(!myEventType.contains(thetype)) myEventType.add(thetype);
		}

		Hashtable<Applicant, ArrayList<Event>> table = new Hashtable<Applicant, ArrayList<Event>>();
		for(Event e : master){
			if(eventFilter.contains(e)) continue;
			ArrayList<Applicant> applicants = e.getApplicantList();
			for(Applicant a : applicants){
				if(table.containsKey(a) || a.getNumber().equals(kwd)) continue;
				ArrayList<Event> value = new ArrayList<Event>();
				ArrayList<Event> yourevents = ap.getYourEvents(ep, a.getNumber());
				for(Event y : yourevents) if(myEventType.contains(y.getType())) value.add(y);
				table.put(a, value);
			}
		}

		return table;
	}

	public Hashtable<Applicant, Integer> tableConvert(Hashtable<Applicant, ArrayList<Event>> table){

		Hashtable<Applicant, Integer> result = new Hashtable<Applicant, Integer>();

		for(Applicant a : table.keySet()) result.put(a, table.get(a).size());

		return result;
	}

	public ArrayList<Map.Entry<Applicant, Integer>> sortRlationTable(Hashtable<Applicant, Integer> t){

		ArrayList<Map.Entry<Applicant, Integer>> l = new ArrayList<Map.Entry<Applicant, Integer>>(t.entrySet());
		Collections.sort(l, new Comparator<Map.Entry<Applicant, Integer>>(){
			public int compare(Map.Entry<Applicant, Integer> o1, Map.Entry<Applicant, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return l;
	}

	public String RelationAnalysis(ApplicantProcess ap, EventProcess ep, String kwd, ArrayList<Event> mainevents, Hashtable<Applicant, ArrayList<Event>> table){

		ArrayList<RelationLink> rls = new ArrayList<RelationLink>();
		ArrayList<String> elements = new ArrayList<String>();
		elements.add(kwd);

		Hashtable<Applicant, ArrayList<Event>> maintable = this.calWhoAndWhoseEvent(ap, ep, mainevents, new ArrayList<Event>(), kwd);

		ArrayList<Map.Entry<Applicant, Integer>> mainArray = sortRlationTable(tableConvert(maintable));
		for(int i = 0; i < 5; ++i){
			Map.Entry<Applicant, Integer> pair = mainArray.get(i);
			Applicant akey = pair.getKey();
			System.out.println(akey);
			table.put(akey, maintable.get(akey));
		}


		ArrayList<Applicant> keySet = new ArrayList<Applicant>();
		for(Applicant x : table.keySet()) keySet.add(x);

		for(Applicant a : keySet){
			String relA = a.getNumber();
			RelationLink r = new RelationLink(kwd, relA);
			rls.add(r); if(!elements.contains(relA)) elements.add(relA);

			Hashtable<Applicant, ArrayList<Event>> secondTable = this.calWhoAndWhoseEvent(ap, ep, table.get(a), mainevents, kwd);


			ArrayList<Map.Entry<Applicant, Integer>> secondArray = sortRlationTable(tableConvert(secondTable));
			for(int i = 0; i < secondArray.size(); ++i){
				Map.Entry<Applicant, Integer> pair = secondArray.get(i);
				Applicant akey = pair.getKey();
				String relB = akey.getNumber();
				if(!table.containsKey(akey)){
					table.put(akey, secondTable.get(akey));
					RelationLink rr = new RelationLink(relA, relB);
					rls.add(rr); if(!elements.contains(relB)) elements.add(relB);
					break;
				}
			}
		}

		return prepareRelationJson_BadMethod(rls, elements);
	}

	public ArrayList<RelationTableEntry> tableSplit(ArrayList<Event> master, Hashtable<Applicant, ArrayList<Event>> table){

		ArrayList<RelationTableEntry> result = new ArrayList<RelationTableEntry>();

		for(Applicant a : table.keySet()){
			RelationTableEntry rte = new RelationTableEntry(a.getNumber());
			ArrayList<Event> second = table.get(a);
			for(Event e : second){
				if(master.contains(e)) rte.addIntersection(e);
				else rte.addNointersection(e);
			}
			result.add(rte);
		}

		Collections.sort(result, new Comparator<RelationTableEntry>(){
			public int compare(RelationTableEntry o1, RelationTableEntry o2) {
				return o2.getTotalScore().compareTo(o1.getTotalScore());
			}
		});

		return result;
	}

	public String prepareRelationJson_BadMethod(ArrayList<RelationLink> allRelations, ArrayList<String> allElements){

		String result = "{container: document.getElementById('relationChart'),boxSelectionEnabled: false,autounselectify: true,layout: {name: 'dagre'},style: [{selector: 'node',style: {'content': 'data(id)','text-opacity': 0.5,'text-valign': 'center','text-halign': 'right','background-color': '#11479e'}},{selector: 'edge',style: {'width': 4,'target-arrow-shape': 'triangle','line-color': '#9dbaea','target-arrow-color': '#9dbaea','curve-style': 'bezier'}}],elements: {nodes: [";

		for(String id : allElements) result += "{ data: { id: '" + id + "' } },";

		result += "],edges: [";

		for(RelationLink rl : allRelations) result += "{ data: { source: '" + rl.getSource() + "', target: '" + rl.getTarget() + "' } },";

		result += "]}}";
		return result;
	}

}