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

package com.gendai.modcreatorfx.codegen;

import com.gendai.modcreatorfx.codegen.JavaTypes.ReturnType;

/**
 * Describe the a java expression, used by the CodeBlock class.
 * @author gendai
 * @version 0.0.1
 */
public class JExpr {
	String expression;
	JavaTypes javatype;

	/**
	 * Constructor initialize the JavaType variable.
	 */
	public JExpr() {
		javatype = new JavaTypes();
	}
	
	/**
	 * Create a JExpr by a String.
	 * @param n the expression in String format.
	 */
	private JExpr(String expression) {
		this.expression = expression;
	}
	
	/**
	 * Create a JExpr describing a variable.
	 * @param name the variable name.
	 * @return the corresponding JExpr.
	 */
	public JExpr variable(String name) {
		return new JExpr(name); 
	}
	
	/**
	 * Create a JExpr describing an assignment to another JExpr. 
	 * @param expression the JExpr that will be assigned.
	 * @return the created JExpr.
	 */
	public JExpr assignment(JExpr expression) {
		return new JExpr("= "+expression.expression);
	}
	
	/**
	 * Create a JExpr describing a declaration of a variable.
	 * @param type the variable type from ReturnType.
	 * @param name the variable name.
	 * @return the corresponding JExpr.
	 */
	public JExpr declare(ReturnType type, String name) {
		return new JExpr(javatype.ReturnType(type)+" "+name);
	}
	
	/**
	 * Create a JExpr describing a declaration of a variable.
	 * @param type the variable type from String representation.
	 * @param name the variable name.
	 * @return the corresponding JExpr.
	 */
	public JExpr declare(String type, String name) {
		return new JExpr(type+" "+name);
	}
	
	/**
	 * Cast a String to a JExpr.
	 * @param expression the String to be casted.
	 * @return the corresponding JExpr.
	 */
	public JExpr toJExpr(String expression) {
		return new JExpr(expression);
	}
	
	/**
	 * Cast an integer to a JExpr.
	 * @param i the integer to be casted.
	 * @return the corresponding JExpr.
	 */
	public JExpr toJExpr(int i) {
		return new JExpr(Integer.toString(i));
	}
	
	/**
	 * Create a JExpr describing an addition between two JExpr.
	 * @param leftExpression the left JExpr of the addition.
	 * @param rightExpression the right JExpr of the addition.
	 * @return the created JExpr.
	 */
	public JExpr addition(JExpr leftExpression, JExpr rightExpression) {
		return new JExpr(leftExpression.expression+" + "
							+rightExpression.expression);
	}
	
	/**
	 * Create a JExpr describing a subtraction between two JExpr.
	 * @param leftExpression the left JExpr of the subtraction.
	 * @param rightExpression the right JExpr of the subtraction.
	 * @return the created JExpr.
	 */
	public JExpr subtraction(JExpr leftExpression, JExpr rightExpression) {
		return new JExpr(leftExpression.expression+" - "
							+rightExpression.expression);
	}
	
	/**
	 * Create a JExpr describing a multiplication between two JExpr.
	 * @param leftExpression the left JExpr of the multiplication.
	 * @param rightExpression the right JExpr of the multiplication.
	 * @return the created JExpr.
	 */
	public JExpr multiplication(JExpr leftExpression, JExpr rightExpression) {
		return new JExpr(leftExpression.expression+" * "
							+rightExpression.expression);
	}
	
	/**
	 * Create a JExpr describing a division between two JExpr.
	 * @param leftExpression the left JExpr of the division.
	 * @param rightExpression the right JExpr of the division.
	 * @return the created JExpr.
	 */
	public JExpr division(JExpr leftExpression, JExpr rightExpression) {
		return new JExpr(leftExpression.expression+" / "
							+rightExpression.expression);
	}
	
	/**
	 * Create a JExpr describing a modulo between two JExpr.
	 * @param leftExpression the left JExpr of the modulo.
	 * @param rightExpression the right JExpr of the modulo.
	 * @return the create JExpr.
	 */
	public JExpr mod(JExpr leftExpression, JExpr rightExpression) {
		return new JExpr(leftExpression.expression+" % "
							+rightExpression.expression);
	}
	
	/**
	 * Create a JExpr describing a return of a JExpr.
	 * @param returnExpression the JExpr to be returned.
	 * @return the created JExpr.
	 */
	public JExpr Jreturn(JExpr returnExpression) {
		return new JExpr("return "+returnExpression.expression);
	}
	
	
	/**
	 * Create a JExpr describing a new of a JExpr.
	 * @param expression the JExpr attached with the new.
	 * @return the created JExpr.
	 */
	public JExpr Jnew(JExpr expression) {
		return new JExpr("new "+expression.expression);
	}
	
	/**
	 * Create a JExpr describing a table of java type and of given size. 
	 * @param type the table type.
	 * @param size the table size.
	 * @return the created JExpr.
	 */
	public JExpr Jtab(ReturnType type,int size) {
		return new JExpr(type.toString().substring(0, type.toString().length())
							+Integer.toString(size)+"]");
	}
	
	/**
	 * Create a JExpr describing an ArrayList of java type.
	 * @param type the array type.
	 * @param declare if true return an ArrayList declaration, otherwise
	 * return the generic type.
	 * @return the created JExpr.
	 */
	public JExpr JarrayList(ReturnType type, boolean declare) {
		if(!declare){
			return new JExpr("ArrayList<"+type.toString()+">");
		}else{
			return new JExpr("new ArrayList<"+type.toString()+">()");
		}
	}
	
	/**
	 * Create a JExpr describing annotation.
	 * @param annotation the String representation of the annotation.
	 * @return the Created JExpr.
	 */
	public JExpr annotation(String annotation) {
		return new JExpr(annotation);
	}
	
	/**
	 * Create a JExpr describing a call.
	 * @param call the String representation of the call.
	 * @return the Created JExpr.
	 */
	public JExpr call(String call) {
		return new JExpr(call);
	}
}
