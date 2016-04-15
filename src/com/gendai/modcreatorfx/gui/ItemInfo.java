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

import com.gendai.modcreatorfx.modcreator.MCTypes.ItemType;

/**
 * Type containing the information for the ItemDiag class.
 * @author gendai
 * @version 0.0.1
 */
public class ItemInfo {
	private String name;
	private File textureFile;
	private ItemType.Items type;
	
	/**
	 * Create a new ItemInfo.
	 * @param name the item name.
	 * @param textureFile the path to the item texture file.
	 * @param type the type of the item.
	 */
	public ItemInfo(String name, File textureFile, ItemType.Items type) {
		this.name = name;
		this.textureFile = textureFile;
		this.type = type;
	}

	/**
	 * Get the name of the item.
	 * @return name of item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of item.
	 * @param name new name of the item.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the texture file of the item.
	 * @return the texture file.
	 */
	public File getTexturefile() {
		return textureFile;
	}

	/**
	 * Set the texture file.
	 * @param texturefile new texture file.
	 */
	public void setTextureFile(File textureFile) {
		this.textureFile = textureFile;
	}

	/**
	 * Get the item type.
	 * @return the item type.
	 */
	public ItemType.Items getType() {
		return type;
	}

	/**
	 * Set the item type.
	 * @param type the new type.
	 */
	public void setType(ItemType.Items type) {
		this.type = type;
	}
}
