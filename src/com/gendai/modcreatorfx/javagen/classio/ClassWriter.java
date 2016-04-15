package com.gendai.modcreatorfx.javagen.classio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ClassWriter {

	File file;
	RandomAccessFile raf;
	
	public ClassWriter(File f){
		this.file = f;
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void Write(ArrayList<String> lines) throws IOException{
		for(String l : lines){
			raf.writeBytes(l);
		}
	}
}
