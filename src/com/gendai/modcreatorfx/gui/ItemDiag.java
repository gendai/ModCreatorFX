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

package com.gendai.modcreatorfx.gui;

import java.io.File;

import com.gendai.modcreatorfx.modcreator.MCTypes.ItemType;
import com.gendai.modcreatorfx.resources.Resource;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The item dialogue box class.
 * @author gendai
 * @version 0.0.1
 */
public class ItemDiag {
	Dialog<ItemInfo> itemInfo;
	File textureFile;
	
	public ItemDiag(){
	}
	
	/**
	 * Create a Dialog<ItemInfo> with all the parameters.
	 * @return the created Dialog.
	 */
	public Dialog<ItemInfo> Show(){
		itemInfo = new Dialog<>();
		itemInfo.setTitle("Mod Creator");
		itemInfo.setHeaderText("Entrer les parametres");
		itemInfo.setResizable(false);
		Stage st = (Stage)itemInfo.getDialogPane().getScene().getWindow();
		st.getIcons().add(new Image(
				Resource.class.getResourceAsStream("icon36.png")));
		Label nm = new Label("Nom: ");
		Label fl = new Label("File: ");
		Label type = new Label("Type: ");
		TextField tnm = new TextField();
		TextField tfl = new TextField();
		tfl.setEditable(false);
		
		Button chb = new Button("...");
		chb.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser chooser = new FileChooser();
				chooser.setTitle("Choose Texture File");
				textureFile = chooser.showOpenDialog(
						chb.getScene().getWindow());
				if(textureFile != null){
					tfl.setText(textureFile.getName());
				}
			}
		});
		
		ObservableList<ItemType.Items> it = FXCollections.observableArrayList();
		it.addAll(ItemType.Items.Axe, ItemType.Items.Shovel,
				  ItemType.Items.Stick);
		ComboBox<ItemType.Items> combo = new ComboBox<>(it);
		
		GridPane grid = new GridPane();
		grid.add(nm, 1, 1);
		grid.add(tnm, 2, 1);
		grid.add(fl, 1, 2);
		grid.add(tfl, 2, 2);
		grid.add(chb, 3, 2);
		grid.add(type, 1, 3);
		grid.add(combo, 2, 3);
		itemInfo.getDialogPane().setContent(grid);
		
		ButtonType buttonOK = new ButtonType("Ok", ButtonData.OK_DONE);
		itemInfo.getDialogPane().getButtonTypes().add(buttonOK);
		
		itemInfo.setResultConverter(new Callback<ButtonType, ItemInfo>() {
			@Override
			public ItemInfo call(ButtonType param) {
				if(param == buttonOK){
					return new ItemInfo(tnm.getText(), textureFile,
							combo.getSelectionModel().getSelectedItem());
				}
				return null;
			}
		});
		return itemInfo;
	}
}