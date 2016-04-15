package JavaGen.CodeGenerator;

import java.util.ArrayList;

public class CodeBlock {

	ArrayList<String> lines;
	
	public CodeBlock(){
		lines = new ArrayList<String>();
	}
	
	public void addExpr(JExpr lefthand, JExpr righthand){
		lines.add(lefthand.s + " "+righthand.s+";\n");
	}
	
	public void addExpr(JExpr solohand, boolean annotation){
		if(annotation){
			lines.add(solohand.s+"\n");
		}else{
			lines.add(solohand.s+";\n");
		}
		
	}
	
	public String getString(){
		String res = "";
		for(String li : lines){
			res = res.concat(li);
		}
		return res;
	}
}
