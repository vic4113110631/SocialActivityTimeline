package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccountProcess {

	private Scanner input;
	private String fileName;
	private ArrayList<Account> accountArray;
    private Hashtable<String, String> accountTable;
	
	public AccountProcess(String fileName) {
		
		this.fileName = fileName;
		this.accountTable = new Hashtable<String, String>();
		this.accountArray = new ArrayList<Account>();
		System.setProperty("file.encoding", "UTF-8");
		open(); readAccounts();
	}
	
	private void open() {
		
		try {
			input = new Scanner(new File(fileName), "utf-8");
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
	}
	
	public String pwdCheck(String pwd) {
		
		if(this.accountTable.containsKey(pwd)) return this.accountTable.get(pwd);
		else return "0000";
	}
	
	private void readAccounts() {
		
		try {
			while (input.hasNextLine()) {
				String accountJsonStr = input.nextLine();
				Account account = this.jsonStr2Account(accountJsonStr);
				this.addAccount(account);
			}
		}
		catch (IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		}
	}
	
	private void addAccount(Account account){
		
		if(!this.accountTable.containsKey(account.getPassword())){
			this.accountTable.put(account.getPassword(), account.getId());
			this.accountArray.add(account);
		}
	}
	
	public void addNewAccount(Account account) throws IOException {
		this.addAccount(account);
		this.writeAccounts(account);
	}
	
	private void writeAccounts(Account account) throws IOException {
		
		FileWriter fw = new FileWriter(new File(fileName), true);
		fw.write(account2JsonStr(account));
		fw.close();
	}
	
	public Account jsonStr2Account(String jsonStr) {
		
		Gson gson = new Gson();
		Account account = gson.fromJson(jsonStr, Account.class);
		
		return account;
	}
	
	public String account2JsonStr(Account account) {
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(account);
		
		return jsonStr;
	}
	
	public String account2JsonStrP(Account account) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(account);
		
		return jsonStr;
	}
}