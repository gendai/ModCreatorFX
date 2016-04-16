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

package com.gendai.modcreatorfx.javagen.classgen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.gendai.modcreatorfx.codegen.CodeBlock;
import com.gendai.modcreatorfx.codegen.JavaTypes;
import com.gendai.modcreatorfx.codegen.JavaTypes.Visibility;
import com.gendai.modcreatorfx.javagen.BaseGenerator;
import com.gendai.modcreatorfx.javagen.classio.ClassWriter;
import com.gendai.modcreatorfx.javagen.methodgen.MethodDeclarator;
import com.gendai.modcreatorfx.javagen.serials.ClassSerial;
import com.gendai.modcreatorfx.javagen.serials.FileSerial;
import com.gendai.modcreatorfx.javagen.serials.MethodSerial;
import com.gendai.modcreatorfx.javagen.util.Utility;

/**
 * Class generator.
 * @author gendai
 * @version 0.0.1 
*/
public class ClassGen {
	String name;
	File output;
	BaseGenerator bg;
	Utility util;
	Visibility vi;
	JavaTypes jt;
	int strtpos = 0;
	FileSerial fs;
	ArrayList<String> lines;
	ArrayList<MethodSerial> ams;
	ArrayList<ClassSerial> acs;

	/**
	 * Define a new class in the {@link BaseGenerator} passed in argument.
	 * @param name the class name.
	 * @param v the {@link Visibility} of the class.
	 * @param pak the package name.
	 * @param extend if the class extends something pass it in String format,
	 * otherwise pass "".
	 * @param implem if the class implements something pass it in String format,
	 * otherwise pass "".
	 * @param annotation if the class need annotation pass it in String format,*
	 * otherwise pass "".
	 * @param bg the {@link BaseGenerator} where the class will be added.
	 */
	public ClassGen(String name, Visibility v, String pak,  String extend,
			String implem, String annotation, BaseGenerator bg) {
		this.name = name;
		this.bg = bg;
		this.vi = v;
		fs = bg.getFileSerial();
		lines = new ArrayList<>();
		ams = new ArrayList<>();
		acs = new ArrayList<>();
		jt = new JavaTypes();
		util = new Utility();
		this.output = new File(bg.outputDir);
		try {
			setPackage(pak);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			CreateBaseClass(extend, implem, annotation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate the string representation of the class, add its lines to the
	 * ArrayList of lines, then serial the class and add it in the ArrayList.
	 * @param extend if the class extend something pass it in String format,
	 * otherwise pass "".
	 * @param implem if the class implement something pass it in String format,
	 * otherwise pass "".
	 * @param annotation if the class need annotation pass it in String 
	 * argument otherwise pass "".
	 * @throws IOException
	 */
	private void CreateBaseClass(String extend, String implem, String annotation) throws IOException{
		lines.add("\n");
		if(annotation != ""){
			lines.add(annotation+"\n");
			strtpos = 1;
		}
		String newc = "";
		if(extend.equals("")){
			if(!implem.equals("")){
				newc = " implements "+implem;
			}
		}else{
			if(implem.equals("")){
				newc = " extends "+extend;
			}else{
				newc = "extends "+extend+" implements "+implem;
			}
		}
		lines.add(jt.Visibility(this.vi)+" class "+this.name+newc+" {");
		ClassSerial cs = new ClassSerial(lines.size(), 0, this.name);
		strtpos += lines.size();
		lines.add("\n");
		acs.add(cs);
	}

	/**
	 * Add declarations to the class.
	 * @param cd the {@link CodeBlock} containing the declarations
	 */
	public void addDeclaration(CodeBlock cd){
		lines.add(strtpos, cd.getString());
	}
	
	/**
	 * Set the package of the class.
	 * @param pck the package string representation.
	 * @throws IOException
	 */
	private void setPackage(String pck) throws IOException {
		lines.add(0,"package "+pck+";\n");
		strtpos++;
	}

	/**
	 * Set the constructor of the class.
	 * @param md the {@link MethodDeclarator} relative to this class.
	 * @param cd the {@link CodeBlock} relative to this class.
	 * @throws IOException
	 */
	public void SetConstructor(MethodDeclarator md, CodeBlock cd) 
			throws IOException {
		lines.add("\n");
		MethodSerial ms = new MethodSerial(lines.size(), 0, md.name, md.params.numParams);
		lines.add(md.toString());
		lines.add("\n");
		lines.add(cd.getString());
		lines.add("}\n");
		ms.setLength(lines.size()-ms.getStartPos()-2);
		ams.add(ms);
	}

	/**
	 * Add a method to this class.
	 * @param md the {@link MethodDeclarator} if this method.
	 * @param annotation if the method need annotation pass it in string format,
	 * otherwise pass "",
	 * @param cd the {@link CodeBlock} relative to this method.
	 * @throws IOException
	 */
	public void addMethod(MethodDeclarator md, String annotation,CodeBlock cd) 
			throws IOException{
		lines.add("\n");
		if(annotation != ""){
			lines.add(annotation+"\n");
		}
		MethodSerial ms = new MethodSerial(lines.size(), 0, md.name, md.params.numParams);
		lines.add(md.toString());
		lines.add("\n");
		lines.add(cd.getString());
		lines.add("}\n");
		ms.setLength(lines.size()-ms.getStartPos()-2);
		ams.add(ms);
	}

	/**
	 * Add import to class.
	 * @param impo the import string representation.
	 */
	public void addImport(String impo){
		lines.add(1,"import "+impo+";\n");
		strtpos++;
	}
	
	/**
	 * Get the file where the class is written.
	 * @return the file containing the class.
	 */
	public File getFile(){
		return this.output;
	}

	/**
	 * Write all the lines from the ArrayList of lines to the files of the class
	 * and Serial this class.
	 * @throws IOException
	 */
	public void Build() throws IOException{
		lines.add("\n}");
		acs.get(0).setLength(lines.size()-acs.get(0).getStartPos()-2);
		ClassWriter cw = new ClassWriter(output);
		cw.Write(lines);
		Serial();
	}

	/**
	 * Serial the class.
	 */
	public void Serial(){
		fs.setClassSerial(acs);
		fs.setMethodSerial(ams);
		fs.Serial(bg.modName,bg.fileName);
	}
}

