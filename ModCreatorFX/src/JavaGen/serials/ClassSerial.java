package JavaGen.serials;

import java.io.Serializable;

public class ClassSerial implements Serializable{
	
	private static final long serialVersionUID = 6695768997859956472L;
	private int startPos;
	private int length;
	private String name;
	
	public ClassSerial(int sPos, int len, String name){
		this.startPos = sPos;
		this.length = len;
		this.name = name;
	}
	
	public void setStartPos(int pos){
		this.startPos = pos;
	}
	
	public void setLength(int len){
		this.length = len;
	}
	
	public int getStartPos(){
		return startPos;
	}
	
	public int getLength(){
		return length;
	}
	
	public String getName(){
		return name;
	}
}
