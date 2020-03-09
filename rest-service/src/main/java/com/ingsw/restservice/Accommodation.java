package com.ingsw.restservice;

public class Accommodation {
	
	private long id;
	private String content;
	private String name ;

	public Accommodation(long id, String content, String name) {
		this.id = id;
		this.content = content;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	public String getName() {
		return name;
	}

}
