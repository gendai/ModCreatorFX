/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://bitbucket.org/Gendai/modcreatorfx>
 * 
 */
package com.gendai.modcreatorfx.codegen;

public class JavaTypes {

	public enum Visibility{
		PUBLIC, PRIVATE, PROTECTED, PUBLIC_STATIC
	}
	
	public enum ReturnType{
		VOID, BLANK ,INT, STRING, BYTE, INTab, STRINGtab, BYTEtab
	}
	
	public String Visibility(Visibility v){
		switch(v){
		case PUBLIC:
			return "public";
		case PRIVATE:
			return "private";
		case PROTECTED:
			return "protected";
		case PUBLIC_STATIC:
			return "public static";
		}
		return "";
	}
	
	public String ReturnType(ReturnType rt){
		switch(rt){
		case VOID:
			return "void";
		case INT:
			return "int";
		case STRING:
			return "String";
		case BYTE:
			return "byte";
		case INTab:
			return "int[]";
		case STRINGtab:
			return "String[]";
		case BYTEtab:
			return "byte[]";
		case BLANK:
			return "";
		}
		return "";
	}

}

