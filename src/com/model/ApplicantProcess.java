package com.model;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicantProcess{
    private Scanner input;
	private String fileName;
	private static ServletContext context;

	/* Called by Listener */
	public static void setServletContext(ServletContext context){
		ApplicantProcess.context = context;
	}
	/* Use this method to access context from any location */
	public static ServletContext getServletContext(){
		return ApplicantProcess.context;
	}

	/*
	public ApplicantProcess(String fileName) {
		this.fileName = fileName;
		System.setProperty("file.encoding", "UTF-8");
		open(); 
		ReadApplicant();
	}
    */
    /*
	public void open() {
		
		try {
			input = new Scanner(new File(fileName), "utf-8");
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
	}
	*/
	/*
	public ArrayList<Applicant> ReadApplier()
	{
		ArrayList<Applicant> list = new ArrayList<Applicant>();
		StringTokenizer tokens = new StringTokenizer(input.nextLine());
		try
		// read records from file using Scanner object
		{
			while (input.hasNextLine())
			{
				Applicant a = new Applicant();
				String token = tokens.nextToken();
				
				if (tokens.hasMoreTokens())
				{
					a.setName(token);
				}
				if (tokens.hasMoreTokens())
				{
					a.setNumber(token);
				}
				if (tokens.hasMoreTokens())
				{
					a.setGrade(token);
				}
				if (tokens.hasMoreTokens())
				{
					a.setSex(token);
				}
				
				list.add(a);	
			// System.out.println( quiz );
			} // end while
		} // end try
		catch (IllegalStateException stateException)
		{
			System.err.println("Error reading from file.");
			System.exit(1);
		} // end catch
		
		
		return list;
	} // end method readRecords
	*/

    /*
	public Applicant jsonStr2Account(String jsonStr) {
		
		Gson gson = new Gson();
		Applicant apply = gson.fromJson(jsonStr, Applicant.class);
		
		return apply;
	}
    */
	/*
	public void WriteApplicant(Applicant applicant)throws IOException{
		FileWriter fw = new FileWriter(new File(fileName), true);
		fw.write(applier2JsonStr(applicant));
		fw.close();
	}
    */
	/*
	public String account2JsonStr(Applicant applicant) {
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(applicant);
		
		return jsonStr;
	}
    */

	public ArrayList<Event> getYourEvents(EventProcess ep, String id){
		ArrayList<Event> activity = new ArrayList<Event>();
		ArrayList<Event> allEvents = (ArrayList<Event>) getServletContext().getAttribute("EventList");

		for(Event event : allEvents){
			for(Applicant a: event.getApplicantList()){
				if((id.equals(a.getNumber()))==true){
					activity.add(event);
				}
			}
		}
		return activity;
	}
}
