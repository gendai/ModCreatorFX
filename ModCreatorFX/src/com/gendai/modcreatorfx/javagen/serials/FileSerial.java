package com.gendai.modcreatorfx.javagen.serials;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileSerial implements Serializable{

	private static final long serialVersionUID = -7571066539769329184L;
	int packagePos;
	int importPos;
	ArrayList<ClassSerial> Cserial;
	ArrayList<MethodSerial> Mserial;
	
	public FileSerial(int pPos, int imPos){
		this.packagePos = pPos;
		this.importPos = imPos;
		Cserial = new ArrayList<>();
		Mserial = new ArrayList<>();
	}
	
	public void addClass(ClassSerial cs){
		Cserial.add(cs);
	}
	
	public void addMethod(MethodSerial ms){
		Mserial.add(ms);
	}
	
	public MethodSerial getMethod(int index){
		return Mserial.get(index);
	}
	
	public ClassSerial getClass(int index){
		return Cserial.get(index);
	}
	
	public MethodSerial getMethod(String name, int numParam){
		for(MethodSerial ms : Mserial){
			if(ms.getName().equals(name) && ms.getNumParam() == numParam){
				return ms;
			}
		}
		return null;
	}
	
	public ClassSerial getClass(String name){
		for(ClassSerial cs : Cserial){
			if(cs.getName() == name){
				return cs;
			}
		}
		return null;
	}
	
	public void setClassSerial(ArrayList<ClassSerial> acs){
		this.Cserial = acs;
	}
	
	public void setMethodSerial(ArrayList<MethodSerial> ams){
		this.Mserial = ams;
	}

	public ArrayList<ClassSerial> getCserial() {
		return Cserial;
	}

	public ArrayList<MethodSerial> getMserial() {
		return Mserial;
	}
	
	public void Serial(String modname,String fileName){
		ObjectOutputStream oos;
		File file = new File("./javaoutput/"+modname+"/"+fileName+".ser");
		System.out.println("Filepath: "+file.getAbsolutePath());
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(
									file)));
			oos.writeObject(this);
			oos.close();	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
