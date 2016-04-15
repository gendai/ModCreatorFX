/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://bitbucket.org/Gendai/modcreatorfx>
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

package com.gendai.modcreatorfx.modcreator;

import java.io.File;

/**
 * Create the directory for a new mod.
 * @author gendai
 * @version 0.0.1
 */
public class FileCreator {
	private String fname;

	public FileCreator() {
	}

	/**
	 * Create the directory from the mod name.
	 * @param name the mod name.
	 */
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