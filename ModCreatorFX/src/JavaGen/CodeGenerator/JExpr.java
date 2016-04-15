package JavaGen.CodeGenerator;

import JavaGen.CodeGenerator.JavaTypes.ReturnType;

public class JExpr {

	String s;
	JavaTypes jt;

	public JExpr(){
		jt = new JavaTypes();
	}
	
	private JExpr(String n){
		this.s = n;
	}
	
	public JExpr Variable(String name){
		return new JExpr(name); 
	}
	
	public JExpr Assig(JExpr expr){
		return new JExpr("= "+expr.s);
	}
	
	public JExpr declare(ReturnType type, String name){
		return new JExpr(jt.ReturnType(type)+" "+name);
	}
	
	public JExpr declare(String type, String name){
		return new JExpr(type+" "+name);
	}
	
	public JExpr ToJExpr(String s){
		return new JExpr(s);
	}
	
	public JExpr ToJExpr(int i){
		return new JExpr(Integer.toString(i));
	}
	
	public JExpr addition(JExpr left, JExpr right){
		return new JExpr(left.s+" + "+right.s);
	}
	
	public JExpr soustraction(JExpr left, JExpr right){
		return new JExpr(left.s+" - "+right.s);
	}
	
	public JExpr mult(JExpr left, JExpr right){
		return new JExpr(left.s+" * "+right.s);
	}
	
	public JExpr div(JExpr left, JExpr right){
		return new JExpr(left.s+" / "+right.s);
	}
	
	public JExpr mod(JExpr left, JExpr right){
		return new JExpr(left.s+" % "+right.s);
	}
	
	public JExpr Jreturn(JExpr ret){
		return new JExpr("return "+ret.s);
	}
	
	public JExpr Jnew(JExpr exp){
		return new JExpr("new "+exp.s);
	}
	
	public JExpr JTab(ReturnType t,int size){
		return new JExpr(t.toString().substring(0, t.toString().length())+Integer.toString(size)+"]");
	}
	
	public JExpr JArrayList(ReturnType t, boolean declare){
		if(!declare){
			return new JExpr("ArrayList<"+t.toString()+">");
		}else{
			return new JExpr("new ArrayList<"+t.toString()+">()");
		}
	}
	
	public JExpr Annotation(String s){
		return new JExpr(s);
	}
	
	public JExpr call(String s){
		return new JExpr(s);
	}
}
