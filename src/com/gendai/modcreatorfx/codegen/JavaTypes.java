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

package com.gendai.modcreatorfx.codegen;

/**
 * Class to describe primitive type used by java.Also methods and class
 * declaration utility.
 * @author gendai
 * @version 0.0.1
 */
public class JavaTypes {

	/**
	 * Describe java visibility types.
	 */
	public enum Visibility {
		PUBLIC, PRIVATE, PROTECTED, PUBLIC_STATIC
	}
	
	/**
	 * Describe java return types.
	 */
	public enum ReturnType {
		VOID, BLANK ,INT, STRING, BYTE, INTab, STRINGtab, BYTEtab
	}
	
	/**
	 * Give the String representation of a Visibility.
	 * @param visibility the Visibility to get.
	 * @return the string representation.
	 */
	public String Visibility(Visibility visibility) {
		switch(visibility){
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
	
	/**
	 * Give the String representation of a ReturnType.
	 * @param returntype the ReturnType to get.
	 * @return the string representation.
	 */
	public String ReturnType(ReturnType returntype) {
		switch(returntype){
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

