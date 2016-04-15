package ModCreator;

import java.io.File;

public class FileCreator {


	private String fname;

	public FileCreator()
	{

	}

	public void Configure(String name){
		this.fname = name;
		File f = new File("./javaoutput/"+name);
		if(!f.exists()){
			f.mkdirs();
		}
	}
	
	public String getFName(){
		return fname;
	}
}
