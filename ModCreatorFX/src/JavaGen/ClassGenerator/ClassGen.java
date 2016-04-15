package JavaGen.ClassGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import JavaGen.BaseGenerator;
import JavaGen.ClassIO.ClassWriter;
import JavaGen.CodeGenerator.CodeBlock;
import JavaGen.CodeGenerator.JavaTypes;
import JavaGen.CodeGenerator.JavaTypes.Visibility;
import JavaGen.MethodGenerator.MethodDeclarator;
import JavaGen.serials.ClassSerial;
import JavaGen.serials.FileSerial;
import JavaGen.serials.MethodSerial;
import JavaGen.util.Utility;

public class ClassGen {

	String name;
	File output;
	BaseGenerator bg;
	Utility util;
	Visibility vi;
	JavaTypes jt;
	int strtpos = 0;
	FileSerial fs;
	ArrayList<String> lines;
	ArrayList<MethodSerial> ams;
	ArrayList<ClassSerial> acs;

	public ClassGen(String name, Visibility v, String pak,  String extend, String implem, String annotation,BaseGenerator bg){
		this.name = name;
		this.bg = bg;
		this.vi = v;
		fs = bg.getFileSerial();
		lines = new ArrayList<>();
		ams = new ArrayList<>();
		acs = new ArrayList<>();
		jt = new JavaTypes();
		util = new Utility();
		this.output = new File(bg.OutputDir);
		try {
			SetPackage(pak);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			CreateBaseClass(extend, implem, annotation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void CreateBaseClass(String extend, String implem, String annotation) throws IOException{
		lines.add("\n");
		if(annotation != ""){
			lines.add(annotation+"\n");
			strtpos = 1;
		}
		String newc = "";
		if(extend.equals("")){
			if(!implem.equals("")){
				newc = " implements "+implem;
			}
		}else{
			if(implem.equals("")){
				newc = " extends "+extend;
			}else{
				newc = "extends "+extend+" implements "+implem;
			}
		}
		lines.add(jt.Visibility(this.vi)+" class "+this.name+newc+" {");
		ClassSerial cs = new ClassSerial(lines.size(), 0, this.name);
		strtpos += lines.size();
		lines.add("\n");
		acs.add(cs);
	}

	public void addDeclaration(CodeBlock cd){
		lines.add(strtpos, cd.getString());
	}
	
	private void SetPackage(String pck) throws IOException{
		lines.add(0,"package "+pck+";\n");
		strtpos++;
	}

	public void SetConstructor(MethodDeclarator md, CodeBlock cd) throws IOException{
		lines.add("\n");
		MethodSerial ms = new MethodSerial(lines.size(), 0, md.name, md.p.n);
		lines.add(md.toString());
		lines.add("\n");
		lines.add(cd.getString());
		lines.add("}\n");
		ms.setLength(lines.size()-ms.getStartPos()-2);
		ams.add(ms);
	}

	public void addMethod(MethodDeclarator md, String annotation,CodeBlock cd) throws IOException{
		lines.add("\n");
		if(annotation != ""){
			lines.add(annotation+"\n");
		}
		MethodSerial ms = new MethodSerial(lines.size(), 0, md.name, md.p.n);
		lines.add(md.toString());
		lines.add("\n");
		lines.add(cd.getString());
		lines.add("}\n");
		ms.setLength(lines.size()-ms.getStartPos()-2);
		ams.add(ms);
	}

	public void addImport(String impo){
		lines.add(1,"import "+impo+";\n");
		strtpos++;
	}
	
	public File getFile(){
		return this.output;
	}

	public void Build() throws IOException{
		lines.add("\n}");
		acs.get(0).setLength(lines.size()-acs.get(0).getStartPos()-2);
		ClassWriter cw = new ClassWriter(output);
		cw.Write(lines);
		Serial();
	}

	public void Serial(){
		fs.setClassSerial(acs);
		fs.setMethodSerial(ams);
		fs.Serial(bg.modName,bg.filename);
	}
}

