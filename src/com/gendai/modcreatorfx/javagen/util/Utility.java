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

package com.gendai.modcreatorfx.javagen.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.gendai.modcreatorfx.codegen.CodeBlock;
import com.gendai.modcreatorfx.javagen.classio.ClassReader;
import com.gendai.modcreatorfx.javagen.classio.ClassWriter;
import com.gendai.modcreatorfx.javagen.methodgen.MethodDeclarator;
import com.gendai.modcreatorfx.javagen.serials.MethodSerial;

/**
 * Utility class.
 * @author gendai
 * @version 0.0.1
 */
public class Utility {

	public Utility(){
	}
	
	/**
	 * Insert the string in the file at the index.
	 * @param index the index to insert.
	 * @param s the string to insert.
	 * @param f the file to insert to.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeAt(int index, String s, File f) 
			throws FileNotFoundException, IOException{
		ClassReader cr = new ClassReader();
		ArrayList<String> lin;
		lin = cr.ClassToArray(cr.getRAF(f),f);
		for(int i = 0; i < index; i++){
			lin.set(i, lin.get(i)+"\n");
		}
		lin.add(index, s);
		for(int i = index+1; i < lin.size(); i++){
			lin.set(i, lin.get(i)+"\n");
		}
		ClassWriter cw = new ClassWriter(f);
		cw.Write(lin);
	}
	
	/**
	 * Insert all the lines from the ArrayList starting at the index into the
	 * file
	 * @param index the index where to start inserting.
	 * @param lines the ArrayList of string to insert.
	 * @param f the file to insert to.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeAt(int index, ArrayList<String> lines, File f) 
			throws FileNotFoundException, IOException{
		ClassReader cr = new ClassReader();
		ArrayList<String> lin;
		lin = cr.ClassToArray(cr.getRAF(f),f);
		for(int i = 0; i < index; i++){
			lin.set(i, lin.get(i)+"\n");
		}
		for(String s : lines){
			lin.add(index,s);
			index++;
		}
		for(int i = index; i < lin.size(); i++){
			lin.set(i, lin.get(i)+"\n");
		}
		ClassWriter cw = new ClassWriter(f);
		cw.Write(lin);
	}
	
	public String getClassName(String s){
		int index = s.lastIndexOf("class");
		int lasti = s.lastIndexOf("{");
		String name = "";
		name = s.substring(index+5, lasti);
		return name;
	}

	/**
	 * Add a method to a MethodSerial without the ClassGenerator of it.
	 * @param md the MethodDeclarator relative to the method.
	 * @param cd the CodeBock relative to the method.
	 * @return A tuple containing the ArrayList of string representing the
	 * method and the MethodSerial of this method.
	 * @throws IOException
	 */
	public Tuple<ArrayList<String>, MethodSerial> addMethod(MethodDeclarator md,
			CodeBlock cd) throws IOException{
		ArrayList<String> res = new ArrayList<>();
		res.add("\n");
		MethodSerial ms = new MethodSerial(res.size(), 0, md.name,
				md.params.numParams);
		res.add(md.toString());
		res.add("\n");
		res.add(cd.getString());
		res.add("}\n");
		ms.setLength(res.size()-ms.getStartPos()-1);
		return new Tuple<ArrayList<String>, MethodSerial>(res, ms);
	}
}
