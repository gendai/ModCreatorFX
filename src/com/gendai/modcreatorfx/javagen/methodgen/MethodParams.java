package com.gendai.modcreatorfx.javagen.methodgen;

import com.gendai.modcreatorfx.codegen.JavaTypes;

public class MethodParams{
	
	String paramsName[];
	//ReturnType paramsType[];
	String paramsType[];
	public int n;
	JavaTypes jt;
	
	public MethodParams(int num){
		this.n = num;
		jt = new JavaTypes();
	}
	
	public void setParamName(String[] p){
		if(p.length != n){
			throw new Error("bad length");
		}
		this.paramsName = p;
	}
	
	/*public void setParamType(ReturnType[] t){
		if(t.length != n){
			throw new Error("bad length");
		}
		this.paramsType = t;
	}*/
	
	public void setParamType(String[] t){
		if(t.length != n){
			throw new Error("bad length");
		}
		this.paramsType = t;
	}
	
	@Override
	public String toString(){
		String res = "";
		for(int i = 0; i < n-1; i++){
			res = res.concat(paramsType[i]+" "+paramsName[i]+", ");
		}
		res = res.concat(paramsType[n-1]+" "+paramsName[n-1]);
		return res;
	}
}