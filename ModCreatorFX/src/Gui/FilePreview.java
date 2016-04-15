package Gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import JavaGen.ClassIO.ClassReader;
import javafx.scene.text.Text;

public class FilePreview {

	private File file;
	
	public FilePreview(String path){
		this.file = new File(path);
	}
	
	public Text getText() throws FileNotFoundException, IOException{
		String res = "";
		ClassReader cr = new ClassReader();
		ArrayList<String> lines = cr.ClassToArray(cr.getRAF(file), file);
		for(String l : lines){
			res = res + "\n" + l;
		}
		return new Text(res);
	}
}
