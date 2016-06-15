package com.model;

public class ChildJsonPack {

	private String name;
	private int size;
	
	public ChildJsonPack(String name, int size) {
		
		setName(name);
		setSize(size);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}