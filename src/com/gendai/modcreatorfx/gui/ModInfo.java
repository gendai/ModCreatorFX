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

import java.io.Serializable;

/**
 * Type containing the information for the ModDiag class.
 * @author gendai
 * @version 0.0.1
 */
public class ModInfo implements Serializable{
	private static final long serialVersionUID = 5846368203020234388L;
	private String name;
	private String version;
	private String description;
	
	/**
	 * Create a new ModInfo.
	 * @param name the mod name.
	 * @param version the mod version.
	 * @param description the mod description.
	 */
	public ModInfo(String name, String version, String description){
		this.name = name;
		this.version = version;
		this.description = description;
	}
	
	/**
	 * Get the name.
	 * @return the mod name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Get the version.
	 * @return the mod version.
	 */
	public String getVersion(){
		return this.version;
	}
	
	/**
	 * Set the new name.
	 * @param newName the new name.
	 */
	public void setName(String newName){
		this.name = newName;
	}

	/**
	 * Get the description.
	 * @return the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the new description.
	 * @param description the new description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the new version.
	 * @param version the new version.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
