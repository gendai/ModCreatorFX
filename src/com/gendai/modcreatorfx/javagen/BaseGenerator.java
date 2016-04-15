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

package com.gendai.modcreatorfx.javagen;

import com.gendai.modcreatorfx.codegen.JavaTypes.Visibility;
import com.gendai.modcreatorfx.javagen.classgen.ClassGen;
import com.gendai.modcreatorfx.javagen.serials.FileSerial;

/**
 * The Generator top level.
 * @author gendai
 * @version 0.0.1
 */
public class BaseGenerator {
	public String outputDir;
	FileSerial fs;
	public String fileName;
	public String modName;
	
	/**
	 * Create a new BaseGenerator.
	 * @param OutpoutDir the directory where to create the file.
	 * @param filen the file name.
	 * @param modName the mod name associated with this file.
	 */
	public BaseGenerator(String OutpoutDir, String filen, String modName){
		this.outputDir = OutpoutDir;
		this.fileName = filen;
		this.modName = modName;
	}
	
	/**
	 * Create a ClassGenerator.
	 * @param name the name of the class.
	 * @param v the Visibility of the class.
	 * @param extend if the class extend something pass it in String format,
	 * otherwise pass "".
	 * @param implem if the class implement someting pass it in String format,
	 * otherwise pass ""
	 * @param annotation if the class need annotation pass it in String 
	 * argument otherwise pass "".
	 * @param pck the package name.
	 * @return
	 */
	public ClassGen createClass(String name, Visibility v, String extend,
			String implem, String annotation, String pck){
		fs = new FileSerial(0, 2);
		ClassGen cg = new ClassGen(name, v, pck, extend, implem,
				annotation,this);
		return cg;
	}
	
	public FileSerial getFileSerial(){
		return fs;
	}
}
