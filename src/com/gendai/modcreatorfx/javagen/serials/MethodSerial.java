/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://github.com/gendai/ModCreatorFX>
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

import java.io.Serializable;

/**
 * Utility to Serial methods.
 * @author gendai
 * @version 0.0.1
 */
public class MethodSerial implements Serializable{
	private static final long serialVersionUID = 108316591022791228L;
	private int startPos;
	private int length;
	private String name;
	private int numParam;
	
	/**
	 * Create a new MethodSerial.
	 * @param sPos the starting position in term of lines of the method in the
	 * file
	 * @param len the length in term of lines of the method.
	 * @param name the name of the method.
	 * @param numberParams the number of parameter of the method.
	 */
	public MethodSerial(int sPos, int len, String name, int numberParams){
		this.startPos = sPos;
		this.name = name;
		this.length = len;
		this.numParam = numberParams;
	}
	
	public void setStartPos(int pos){
		this.startPos = pos;
	}
	
	public void setLength(int len){
		this.length = len;
	}
	
	public int getStartPos(){
		return startPos;
	}
	
	public int getLength(){
		return length;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumParam(){
		return numParam;
	}
}
