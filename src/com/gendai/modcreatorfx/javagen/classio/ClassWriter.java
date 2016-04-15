/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://github.com/gendai/ModCreatorFX>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.gendai.modcreatorfx.javagen.classio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Write to the file.
 * @author gendai
 * @version 0.0.1
 */
public class ClassWriter {
	File file;
	RandomAccessFile raf;
	
	/**
	 * Set up the RandomAccessFile from the File parameter.
	 * @param f the file to write to.
	 */
	public ClassWriter(File f){
		this.file = f;
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Iterate through the ArrayList of string and write each lines into the
	 * file.
	 * @param lines the ArrayList of String to write.
	 * @throws IOException
	 */
	public void Write(ArrayList<String> lines) throws IOException{
		for(String l : lines){
			raf.writeBytes(l);
		}
	}
}
