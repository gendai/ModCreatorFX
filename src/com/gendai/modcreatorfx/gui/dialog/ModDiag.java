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

package com.gendai.modcreatorfx.gui.dialog;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;

import com.gendai.modcreatorfx.gui.dialog.info.ModInfo;
import com.gendai.modcreatorfx.resources.Resource;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The mod dialogue box class.
 * @author gendai
 * @version 0.0.1
 */
public class ModDiag {
	Dialog<ModInfo> diagAdd;
	Dialog<ModInfo> diagRename;
	
	public ModDiag(){
	}
	
	/**
	 * Create a Dialog<ModInfo> with all the parameters to modify the mod info.
	 * @param modInfo the current mod info.
	 * @return the created Dialog<ModInfo>.
	 */
	public Dialog<ModInfo> ShowRenew(ModInfo modInfo){
		diagRename = new Dialog<>();
		diagRename.setTitle("Mod Creator");
		diagRename.setHeaderText("Modifier paramètres du mod");
		diagRename.setResizable(false);
		Stage st = (Stage)diagRename.getDialogPane().getScene().getWindow();
		st.getIcons().add(new Image(Resource.class.getResourceAsStream("icon36.png")));
		Label nm = new Label("Nom: ");
		Label ver = new Label("Version: ");
		Label des = new Label("Description: ");
		TextField tnm = new TextField(modInfo.getName());
		TextField tver = new TextField(modInfo.getVersion());
		TextArea tdes = new TextArea(modInfo.getDescription());
	
		GridPane grid = new GridPane();
		grid.add(nm, 1, 1);
		grid.add(tnm, 2, 1);
		grid.add(ver, 1, 2);
		grid.add(tver, 2, 2);
		grid.add(des, 1, 3);
		grid.add(tdes, 2, 3);
		diagRename.getDialogPane().setContent(grid);
		
		ButtonType buttonOK = new ButtonType("Ok", ButtonData.OK_DONE);
		diagRename.getDialogPane().getButtonTypes().add(buttonOK);
		
		diagRename.setResultConverter(new Callback<ButtonType, ModInfo>() {
			@Override
			public ModInfo call(ButtonType param) {
				if(param == buttonOK){
					return new ModInfo(tnm.getText(), tver.getText(),
							tdes.getText());
				}
				return null;
			}
		});
		return diagRename;
	}
	
	/**
	 * Create a Dialog<ModInfo> with all the parameters to create a new mod. 
	 * @return the created Dialog.
	 */
	public Dialog<ModInfo> ShowAdd(){
		diagAdd = new Dialog<>();
		diagAdd.setTitle("Mod Creator");
		diagAdd.setHeaderText("Entrer le nom, la version et une descritpion "
				+ "du mod");
		diagAdd.setResizable(false);
		Stage st = (Stage)diagAdd.getDialogPane().getScene().getWindow();
		st.getIcons().add(new Image(
				Resource.class.getResourceAsStream("icon36.png")));
		Label nm = new Label("Nom: ");
		Label ver = new Label("Version: ");
		Label des = new Label("Description: ");
		TextField tnm = new TextField();
		TextField tver = new TextField();
		TextArea tdes = new TextArea();
	
		GridPane grid = new GridPane();
		grid.add(nm, 1, 1);
		grid.add(tnm, 2, 1);
		grid.add(ver, 1, 2);
		grid.add(tver, 2, 2);
		grid.add(des, 1, 3);
		grid.add(tdes, 2, 3);
		diagAdd.getDialogPane().setContent(grid);
		
		ButtonType buttonOK = new ButtonType("Ok", ButtonData.OK_DONE);
		diagAdd.getDialogPane().getButtonTypes().add(buttonOK);
		
		diagAdd.setResultConverter(new Callback<ButtonType, ModInfo>() {
			@Override
			public ModInfo call(ButtonType param) {
				if(param == buttonOK){
					return new ModInfo(tnm.getText(), tver.getText(),
							tdes.getText());
				}
				return null;
			}
		});
		return diagAdd;
	}
}
