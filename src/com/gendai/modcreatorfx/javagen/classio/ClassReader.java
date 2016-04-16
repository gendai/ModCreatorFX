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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Read through a class.
 * @author gendai
 * @version 0.0.1
 */
public class ClassReader {

	public ClassReader(){
	}
	
	/**
	 * Create a {@link RandomAccessFile} from the file in parameter.
	 * @param f the file to get the {@link RandomAccessFile} from.
	 * @return the created {@link RandomAccessFile}.
	 * @throws FileNotFoundException
	 */
	public RandomAccessFile getRAF(File f) throws FileNotFoundException{
		RandomAccessFile raf;
		raf = new RandomAccessFile(f, "rw");
		return raf;
	}
	
	/**
	 * Get all lines from the {@link RandomAccessFile} of a file and store it in an
	 * ArrayList.
	 * @param raf the {@link RandomAccessFile}.
	 * @param f the {@link File}.
	 * @return the created ArrayList of String lines.
	 * @throws IOException
	 */
	public ArrayList<String> ClassToArray(RandomAccessFile raf, File f) 
			throws IOException{
		ArrayList<String> lines = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	lines.add(line);
		    }
		}
		return lines;
	}
}