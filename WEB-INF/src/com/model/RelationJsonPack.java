package com.model;

import java.util.ArrayList;

public class RelationJsonPack {

	private String name;
	private ArrayList<com.model.LittleJsonPack> children;

	public RelationJsonPack(String name, ArrayList<LittleJsonPack> one) {

		setName(name);
		setChild(one);
	}



	public String getName() {
		return this.name;
	}

	public ArrayList<LittleJsonPack> getChildren() {
		return this.children;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChild(ArrayList<LittleJsonPack> child) {
		this.children = child;
	}
}