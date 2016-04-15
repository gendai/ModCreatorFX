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
import com.gendai.modcreatorfx.codegen.JavaTypes.ReturnType;
import com.gendai.modcreatorfx.codegen.JavaTypes.Visibility;

/**
 * Create the method description needed for generating.
 * @author gendai
 * @version 0.0.1
 */
public class MethodDeclarator {
	public String name;
	ReturnType returnType;
	public MethodParams params;
	Visibility visibility;
	JavaTypes javaType;
	
	/**
	 * Set up the method parameters.
	 * @param name the name of the method.
	 * @param t the ReturnType of the method.
	 * @param p the MethodParams relative to the method.
	 * @param v the Visibility of this method.
	 */
	public MethodDeclarator(String name, ReturnType t, MethodParams p, Visibility v){
		this.name = name;
		this.returnType = t;
		this.params = p;
		this.visibility = v;
		javaType = new JavaTypes();
	}
		
	/**
	 * Get the string representation of the MethodDeclarator.
	 */
	@Override
	public String toString(){
		String res = "";
		if(params.numParams > 0){
			res = javaType.Visibility(this.visibility)+" "
					+javaType.ReturnType(this.returnType)
					+" "+this.name+"("+this.params.toString()+"){";
		}else{
			res = javaType.Visibility(this.visibility)
					+" "+javaType.ReturnType(this.returnType)+" "
					+this.name+"( ){";
		}
		return res;
	}
}