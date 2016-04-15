package JavaGen.ClassIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ClassReader {

	
	public ClassReader(){
		
	}
	
	public RandomAccessFile getRAF(File f) throws FileNotFoundException{
		RandomAccessFile raf;
		raf = new RandomAccessFile(f, "rw");
		return raf;
	}
	
	public ArrayList<String> ClassToArray(RandomAccessFile raf, File f) throws IOException{
		ArrayList<String> lines = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	lines.add(line);
		    }
		}
		return lines;
	}
	
}
