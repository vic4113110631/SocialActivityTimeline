package com.model;

import java.util.ArrayList;

public class LittleJsonPack {

	private String name;
	private ArrayList<ChildJsonPack> children;
	
	public LittleJsonPack(String name, ChildJsonPack one) {
		
		setName(name);
		children = new ArrayList<ChildJsonPack>();
		children.add(one);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<ChildJsonPack> getChildren() {
		return this.children;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}