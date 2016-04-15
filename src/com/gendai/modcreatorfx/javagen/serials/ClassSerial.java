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

import java.io.Serializable;

/**
 * Utility to serial the class into a .ser file.
 * @author gendai
 * @version 0.0.1
 */
public class ClassSerial implements Serializable{
	private static final long serialVersionUID = 6695768997859956472L;
	private int startPos;
	private int length;
	private String name;
	
	/**
	 * Create a new ClassSerial.
	 * @param sPos the starting position of class in the file in terms of line.
	 * @param len the length in term if lines of the class in the file.
	 * @param name the name of the class.
	 */
	public ClassSerial(int sPos, int len, String name){
		this.startPos = sPos;
		this.length = len;
		this.name = name;
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
}
