/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://bitbucket.org/Gendai/modcreatorfx>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.gendai.modcreatorfx.javagen.serials;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Utility to serial a file class and methods.
 * @author gendai
 * @version 0.0.1
 */
public class FileSerial implements Serializable{
	private static final long serialVersionUID = -7571066539769329184L;
	int packagePos;
	int importPos;
	ArrayList<ClassSerial> classSerial;
	ArrayList<MethodSerial> methodSerial;
	
	/**
	 * Create a FileSerial.
	 * @param pPos the package position in term of line in the file.
	 * @param imPos the first import position in term of line in the file.
	 */
	public FileSerial(int pPos, int imPos){
		this.packagePos = pPos;
		this.importPos = imPos;
		classSerial = new ArrayList<>();
		methodSerial = new ArrayList<>();
	}
	
	public void addClass(ClassSerial cs){
		classSerial.add(cs);
	}
	
	public void addMethod(MethodSerial ms){
		methodSerial.add(ms);
	}
	
	public MethodSerial getMethod(int index){
		return methodSerial.get(index);
	}
	
	public ClassSerial getClass(int index){
		return classSerial.get(index);
	}
	
	/**
	 * Get a MethodSerial from the file.
	 * @param name the name of the method.
	 * @param numParam the number of parameter of the method.
	 * @return the MethodSerial.
	 */
	public MethodSerial getMethod(String name, int numParam){
		for(MethodSerial ms : methodSerial){
			if(ms.getName().equals(name) && ms.getNumParam() == numParam){
				return ms;
			}
		}
		return null;
	}
	
	/**
	 * Get a ClassSerial from a file.
	 * @param name the name of the class.
	 * @return the ClassSerial.
	 */
	public ClassSerial getClass(String name){
		for(ClassSerial cs : classSerial){
			if(cs.getName() == name){
				return cs;
			}
		}
		return null;
	}
	
	public void setClassSerial(ArrayList<ClassSerial> acs){
		this.classSerial = acs;
	}
	
	public void setMethodSerial(ArrayList<MethodSerial> ams){
		this.methodSerial = ams;
	}

	public ArrayList<ClassSerial> getCserial() {
		return classSerial;
	}

	public ArrayList<MethodSerial> getMserial() {
		return methodSerial;
	}
	
	/**
	 * Serial the file.
	 * @param modname the mod name which own the file.
	 * @param fileName the name file to store.
	 */
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
