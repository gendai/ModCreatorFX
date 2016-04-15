package JavaGen.serials;

import java.io.Serializable;

public class MethodSerial implements Serializable{

	private static final long serialVersionUID = 108316591022791228L;
	private int startPos;
	private int length;
	private String name;
	private int numParam;
	
	public MethodSerial(int sPos, int len, String name, int numberParams){
		this.startPos = sPos;
		this.name = name;
		this.length = len;
		this.numParam = numberParams;
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
	
	public int getNumParam(){
		return numParam;
	}
}
