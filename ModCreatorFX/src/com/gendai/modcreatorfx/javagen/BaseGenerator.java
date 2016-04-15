package com.gendai.modcreatorfx.javagen;


import com.gendai.modcreatorfx.codegen.JavaTypes.Visibility;
import com.gendai.modcreatorfx.javagen.classgen.ClassGen;
import com.gendai.modcreatorfx.javagen.serials.FileSerial;

public class BaseGenerator {
	
	public String OutputDir;
	FileSerial fs;
	public String filename;
	public String modName;
	
	public BaseGenerator(String OutpoutDir, String filen, String modName){
		this.OutputDir = OutpoutDir;
		this.filename = filen;
		this.modName = modName;
	}
	
	public ClassGen createClass(String name, Visibility v, String extend, String implem, String annotation, String pck){
		fs = new FileSerial(0, 2);
		ClassGen cg = new ClassGen(name, v, pck, extend, implem, annotation,this);
		return cg;
	}
	
	public FileSerial getFileSerial(){
		return fs;
	}
}
