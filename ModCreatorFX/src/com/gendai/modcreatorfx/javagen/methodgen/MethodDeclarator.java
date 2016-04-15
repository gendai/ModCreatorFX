package com.gendai.modcreatorfx.javagen.methodgen;

import com.gendai.modcreatorfx.codegen.JavaTypes;
import com.gendai.modcreatorfx.codegen.JavaTypes.ReturnType;
import com.gendai.modcreatorfx.codegen.JavaTypes.Visibility;

public class MethodDeclarator {

	public String name;
	ReturnType returntype;
	public MethodParams p;
	Visibility visibility;
	JavaTypes jt;
	
	public MethodDeclarator(String name, ReturnType t, MethodParams p, Visibility v){
		this.name = name;
		this.returntype = t;
		this.p = p;
		this.visibility = v;
		jt = new JavaTypes();
	}
		
	@Override
	public String toString(){
		String res = "";
		if(p.n > 0){
			res = jt.Visibility(this.visibility)+" "+jt.ReturnType(this.returntype)+" "+this.name+"("+this.p.toString()+"){";
		}else{
			res = jt.Visibility(this.visibility)+" "+jt.ReturnType(this.returntype)+" "+this.name+"( ){";
		}
		return res;
	}
	
	
}
