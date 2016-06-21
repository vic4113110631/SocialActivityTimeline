package com.model;

public class RelationLink {

	private String source;
	private String target;
	
	public RelationLink(String source, String target) {
		
		this.setSource(source);
		this.setTarget(target);
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getTarget() {
		return this.target;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
}