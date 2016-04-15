package Gui;

import java.io.File;

import ModCreator.MCTypes.ItemType;

public class ItemInfo {

	private String name;
	private File texturefile;
	private ItemType.Items type;
	
	public ItemInfo(String nm, File tfile, ItemType.Items t){
		this.name = nm;
		this.texturefile = tfile;
		this.type = t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getTexturefile() {
		return texturefile;
	}

	public void setTexturefile(File texturefile) {
		this.texturefile = texturefile;
	}

	public ItemType.Items getType() {
		return type;
	}

	public void setType(ItemType.Items type) {
		this.type = type;
	}
}
