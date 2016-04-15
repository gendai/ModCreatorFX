package com.gendai.modcreatorfx.javagen.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import com.gendai.modcreatorfx.codegen.CodeBlock;
import com.gendai.modcreatorfx.javagen.classio.ClassReader;
import com.gendai.modcreatorfx.javagen.classio.ClassWriter;
import com.gendai.modcreatorfx.javagen.methodgen.MethodDeclarator;
import com.gendai.modcreatorfx.javagen.serials.MethodSerial;

public class Utility {

	public Utility(){
		
	}
	
	public int GetNextLine(RandomAccessFile f){
		try {
			while(f.read() != '\n'){
			}
			return (int)f.getFilePointer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void WriteAt(int index, String s, File f) throws FileNotFoundException, IOException{
		ClassReader cr = new ClassReader();
		ArrayList<String> lin;
		lin = cr.ClassToArray(cr.getRAF(f),f);
		for(int i = 0; i < index; i++){
			lin.set(i, lin.get(i)+"\n");
		}
		lin.add(index, s);
		for(int i = index+1; i < lin.size(); i++){
			lin.set(i, lin.get(i)+"\n");
		}
		ClassWriter cw = new ClassWriter(f);
		cw.Write(lin);
	}
	
	public void WriteAt(int index, ArrayList<String> lines, File f) throws FileNotFoundException, IOException{
		ClassReader cr = new ClassReader();
		ArrayList<String> lin;
		lin = cr.ClassToArray(cr.getRAF(f),f);
		for(int i = 0; i < index; i++){
			lin.set(i, lin.get(i)+"\n");
		}
		for(String s : lines){
			lin.add(index,s);
			index++;
		}
		for(int i = index; i < lin.size(); i++){
			lin.set(i, lin.get(i)+"\n");
		}
		ClassWriter cw = new ClassWriter(f);
		cw.Write(lin);
	}
	
	public String getClassName(String s){
		int index = s.lastIndexOf("class");
		int lasti = s.lastIndexOf("{");
		String name = "";
		name = s.substring(index+5, lasti);
		return name;
	}

	public Tuple<ArrayList<String>, MethodSerial> addMethod(MethodDeclarator md, CodeBlock cd) throws IOException{
		ArrayList<String> res = new ArrayList<>();
		res.add("\n");
		MethodSerial ms = new MethodSerial(res.size(), 0, md.name, md.p.n);
		res.add(md.toString());
		res.add("\n");
		res.add(cd.getString());
		res.add("}\n");
		ms.setLength(res.size()-ms.getStartPos()-1);
		return new Tuple<ArrayList<String>, MethodSerial>(res, ms);
	}
}
