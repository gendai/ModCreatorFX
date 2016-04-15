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
package com.gendai.modcreatorfx.javagen.methodgen;

import com.gendai.modcreatorfx.codegen.JavaTypes;

/**
 * Describe parameters needed for the method.
 * @author gendai
 * @version 0.0.1
 */
public class MethodParams{
	String paramsName[];
	String paramsType[];
	public int numParams;
	JavaTypes javaType;
	
	/**
	 * Set the number of parameters.
	 * @param num the number of parameters.
	 */
	public MethodParams(int num){
		this.numParams = num;
		javaType = new JavaTypes();
	}
	
	/**
	 * Set the parameter name.
	 * @param p a String array containing the names of each parameter.
	 */
	public void setParamName(String[] p){
		if(p.length != numParams){
			throw new Error("bad length");
		}
		this.paramsName = p;
	}
	
	/**
	 * Set the parameter type.
	 * @param t a String array containing the string representation of
	 * the type of each parameter.
	 */
	public void setParamType(String[] t){
		if(t.length != numParams){
			throw new Error("bad length");
		}
		this.paramsType = t;
	}
	
	/**
	 * Give the string representation.
	 */
	@Override
	public String toString(){
		String res = "";
		for(int i = 0; i < numParams-1; i++){
			res = res.concat(paramsType[i]+" "+paramsName[i]+", ");
		}
		res = res.concat(paramsType[numParams-1]+" "+paramsName[numParams-1]);
		return res;
	}
}