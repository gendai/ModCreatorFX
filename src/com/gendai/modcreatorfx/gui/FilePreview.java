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

package com.gendai.modcreatorfx.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.gendai.modcreatorfx.javagen.classio.ClassReader;

import javafx.scene.text.Text;

/**
 * Class used to give a {@link Text} of a java file.
 * @author gendai
 * @version 0.0.1
 */
public class FilePreview {
	private File file;
	
	/**
	 * Create a new {@link FilePreview} of a file.
	 * @param path the path of the file.
	 */
	public FilePreview(String path) {
		this.file = new File(path);
	}
	
	/**
	 * Create a {@link Text} object with the content of the file.
	 * @return {@link Text} object.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Text getText() throws FileNotFoundException, IOException {
		String res = "";
		ClassReader cr = new ClassReader();
		ArrayList<String> lines = cr.ClassToArray(cr.getRAF(file), file);
		for(String l : lines){
			res = res + "\n" + l;
		}
		return new Text(res);
	}
}
