package com.gendai.modcreatorfx.gui;

import java.io.Serializable;

public class ModInfo implements Serializable{

	private static final long serialVersionUID = 5846368203020234388L;
	private String name;
	private String version;
	private String Description;
	
	public ModInfo(String name, String ver, String des){
		this.name = name;
		this.version = ver;
		this.Description = des;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getVersion(){
		return this.version;
	}
	
	public void setName(String newName){
		this.name = newName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
