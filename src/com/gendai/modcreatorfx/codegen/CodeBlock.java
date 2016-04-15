/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://bitbucket.org/Gendai/modcreatorfx>
 * 
 */
package com.gendai.modcreatorfx.codegen;

import java.util.ArrayList;

/**
 * Class to define a code region.
 * @author gendai
 * @version 0.0.1
 **/
public class CodeBlock {

	/**
	 * The ArrayList containing all the lines of the code region.
	 */
	ArrayList<String> lines;
	
	/**
	 * Constructor used to initialize the ArrayList.
	 */
	public CodeBlock(){
		lines = new ArrayList<String>();
	}
	
	/**
	 * Add a line composed of two JExpr to the code region. 
	 * @param lefthand the left JExpr of the line to be added.
	 * @param righthand the right JExpr of the line to be added.
	 */
	public void addExpr(JExpr lefthand, JExpr righthand){
		lines.add(lefthand.s + " "+righthand.s+";\n");
	}
	
	/**
	 * Add a line composed of only one JExpr to the code region.
	 * @param solohand the JExpr to be added.
	 * @param annotation if true, line will end without ";".
	 */
	public void addExpr(JExpr solohand, boolean annotation){
		if(annotation){
			lines.add(solohand.s+"\n");
		}else{
			lines.add(solohand.s+";\n");
		}
		
	}
	
	/**
	 * Convert the code region to a string.
	 * @return the string representation of the code region.
	 */
	public String getString(){
		String res = "";
		for(String li : lines){
			res = res.concat(li);
		}
		return res;
	}
}
