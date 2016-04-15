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
package com.gendai.modcreatorfx.handler;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gendai.modcreatorfx.gui.FilePreview;
import com.gendai.modcreatorfx.gui.ItemDiag;
import com.gendai.modcreatorfx.gui.ItemInfo;
import com.gendai.modcreatorfx.gui.ModDiag;
import com.gendai.modcreatorfx.gui.ModInfo;
import com.gendai.modcreatorfx.gui.Reference;
import com.gendai.modcreatorfx.modcreator.FileCreator;
import com.gendai.modcreatorfx.template.ItemTemplate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * The Controller of the JavaFX application.
 * @author gendai
 * @version 0.0.1
 */
public class FXMLController implements Initializable{
	@FXML private ListView<String> lstv;
	@FXML private TreeView<String> treeV;
	@FXML private Label modlbl;
	@FXML private TextFlow rich;
	@FXML private Button addItem;
	private final ObservableList<String> items = FXCollections.observableArrayList();
	private TreeItem<String> root;
	private FileCreator fc;
	private ArrayList<ModInfo> mods;

	/**
	 * Update the TreeView items when the Mod tab is selected.
	 * @param e
	 */
	@FXML protected void OnTabSel(Event e){
		if(!lstv.getSelectionModel().isEmpty()){
			root = new TreeItem<String>(lstv.getSelectionModel()
					.getSelectedItem());
			root.setExpanded(true);
			File dir = new File(Reference.OUTPUT_LOCATION
					+lstv.getSelectionModel().getSelectedItem());
			root = getTreeView(dir);
			treeV.setRoot(root);
		}
	}

	/**
	 * Open the browser with the JSON-simple link.
	 * @param e
	 */
	@FXML protected void OnLinkClick(ActionEvent e){
		try {
			Desktop.getDesktop().browse(
					new URI("https://github.com/fangyidong/json-simple"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Disable the add item button if no mod is selected.
	 * @param e
	 */
	@FXML protected void OnSelect(MouseEvent e){
		if(lstv.getSelectionModel().getSelectedIndex() != -1){
			modlbl.setText(lstv.getSelectionModel().getSelectedItem());
			addItem.setDisable(false);
		}else{
			modlbl.setText("Choisissez un mod");
			addItem.setDisable(true);
		}
	}

	/**
	 * Open the Item dialog box and if parameters are right, create the item.
	 * @param e
	 */
	@FXML protected void OnItemClick(ActionEvent e){
		Dialog<ItemInfo> diag = new ItemDiag().Show();
		Optional<ItemInfo> res = diag.showAndWait();
		if(res.isPresent()){
			String tmp = res.get().getName().replaceAll(" ", "");
			if(res.get().getName().isEmpty() || tmp.equals("") 
					|| res.get().getType() == null 
					|| res.get().getTexturefile() == null){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Erreur sur les parametres");
				alert.setContentText("Tous les parametres ne "
						+ "sont pas conforme");
				alert.showAndWait();
			}else{
				System.out.println("All went right!!");
				ItemTemplate template = new ItemTemplate(
						mods.get(lstv.getSelectionModel().getSelectedIndex()),
						res.get());
				try {
					template.create();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Open the mod dialog box and add the mod if all parameters are right.
	 * @param e
	 */
	@FXML protected void OnAddClick(ActionEvent e){
		Dialog<ModInfo> diag = new ModDiag().ShowAdd();
		Optional<ModInfo> res = diag.showAndWait();
		if(res.isPresent()){
			String tmp = res.get().getName().replaceAll(" ", "");
			if(items.contains(res.get().getName()) 
					|| res.get().getName().isEmpty() || tmp.equals("")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Erreur sur le nom du mod");
				alert.setContentText("Ce nom de mod existe déjà ou "
						+ "vous n'avez pas entré de nom");
				alert.showAndWait();
			}else{
				mods.add(res.get());
				fc.Configure(res.get().getName());
				items.add(res.get().getName());
				Serial();
			}
		}
	}
	
	/**
	 * Delete the mod selected and the associated folders.
	 * @param e
	 */
	@FXML protected void OnDelClick(ActionEvent e){
		int index = lstv.getSelectionModel().getSelectedIndex();
		if(items.size() != 0 && index != -1){
			File f = new File(Reference.OUTPUT_LOCATION
					+lstv.getSelectionModel().getSelectedItem());
			File[] fileTemp = f.listFiles();
			for(int i = 0; i < fileTemp.length; i++){
				fileTemp[i].delete();
			}
			f.delete();
			File configf = new File(Reference.CONFIG_LOCATION
					+lstv.getSelectionModel().getSelectedItem()+".ser");
			configf.delete();
			mods.remove(index);
			items.remove(index);
			Serial();
		}
	}

	/**
	 * Open the Renew dialog box and update the mod info.
	 * @param e
	 */
	@FXML protected void OnRenameClick(ActionEvent e){
		int index = lstv.getSelectionModel().getSelectedIndex();
		
		if(items.size() != 0 && index != -1){
			Dialog<ModInfo> diag = new ModDiag().ShowRenew(mods.get(index));
			Optional<ModInfo> res = diag.showAndWait();
			if(res.isPresent() && !res.get().equals("")){
				if(items.contains(res.get())){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("Erreur sur le nom du mod");
					alert.setContentText("Ce nom de mod existe déjà");
					alert.showAndWait();
				}else{
					File f = new File(Reference.OUTPUT_LOCATION
							+lstv.getSelectionModel().getSelectedItem());
					File newf = new File(Reference.OUTPUT_LOCATION
							+res.get().getName());
					File configf = new File(Reference.CONFIG_LOCATION
							+lstv.getSelectionModel().getSelectedItem()+".ser");
					configf.delete();
					f.renameTo(newf);
					mods.get(index).setName(res.get().getName());
					mods.get(index).setVersion(res.get().getVersion());
					mods.get(index).setDescription(res.get().getDescription());
					items.set(index,res.get().getName());
					Serial();
				}
			}
		}
	}
	
	/**
	 * Initialize method to set the listview and treeview listenner.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.fc = new FileCreator();
		mods = new ArrayList<>();
		ReadSerial();
		File dir2 = new File(Reference.OUTPUT_LOCATION);
		File[] filetemp2 = dir2.listFiles();
		
		for(int i = 0; i < filetemp2.length; i++){
			if(!filetemp2[i].getName().equals("config"))
			{
				items.add(filetemp2[i].getName());
			}
		}
		lstv.setItems(items);
		lstv.getSelectionModel().selectedItemProperty().
		addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov,
					String old_val, String new_val){
				int in = getIndex(new_val);
				if(rich.getChildren().size() != 0){
					rich.getChildren().remove(0, rich.getChildren().size());
				}
				root = new TreeItem<String>(new_val);
				root.setExpanded(true);
				File dir = new File(Reference.OUTPUT_LOCATION
						+lstv.getSelectionModel().getSelectedItem());
				root = getTreeView(dir);
				treeV.setRoot(root);
				if(lstv.getItems().size() > 0){
					if(treeV.getSelectionModel().getSelectedItem() != null 
							&& treeV.getSelectionModel().getSelectedItem()
							.equals(root)){
						Text tm = new Text("Mod: ");
						tm.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
						Text text2 = new Text("Version: ");
						text2.setFont(Font.font("Helvetica",
								FontWeight.BOLD ,16));
						Text text3 = new Text("Description: ");
						text3.setFont(Font.font("Helvetica",
								FontWeight.BOLD ,16));
						Text t1 = new Text(mods.get(in).getName()+"\n");
						t1.setFont(Font.font("Helvetica",12));
						Text t2 = new Text(mods.get(in).getVersion()+"\n");
						t2.setFont(Font.font("Helvetica", 12));
						Text t3 = new Text(mods.get(in).getDescription()+"\n");
						t3.setFont(Font.font("Helvetica", 12));
						rich.getChildren().addAll(tm, t1, text2, t2, text3, t3);
					}
				}else{
					Text def = new Text("Aucun mod");
					rich.getChildren().clear();
					rich.getChildren().add(def);
					root.getChildren().clear();
					root.setValue("");
					treeV.setRoot(root);
				}
			};
		});
		treeV.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<TreeItem<String>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> 
			observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
				if(rich.getChildren().size() != 0){
					rich.getChildren().remove(0, rich.getChildren().size());
				}
				if(newValue != null && newValue.equals(treeV.getRoot())){
					Text tm = new Text("Mod: ");
					tm.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
					Text text2 = new Text("Version: ");
					text2.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
					Text text3 = new Text("Description: ");
					text3.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
					Text t1 = new Text(mods.get(lstv.getSelectionModel()
							.getSelectedIndex()).getName()+"\n");
					t1.setFont(Font.font("Helvetica",12));
					Text t2 = new Text(mods.get(lstv.getSelectionModel()
							.getSelectedIndex()).getVersion()+"\n");
					t2.setFont(Font.font("Helvetica", 12));
					Text t3 = new Text(mods.get(lstv.getSelectionModel()
							.getSelectedIndex()).getDescription()+"\n");
					t3.setFont(Font.font("Helvetica", 12));
					rich.getChildren().addAll(tm, t1, text2, t2, text3, t3);
				}else if(!treeV.getSelectionModel().isEmpty() 
						&& treeV.getSelectionModel().getSelectedItem()
						.getValue().endsWith(".java")){
					FilePreview fp = new FilePreview(Reference.OUTPUT_LOCATION
							+"/"+getTreeViewPath(treeV.getSelectionModel()
									.getSelectedItem()));
					try {
						rich.getChildren().add(fp.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * Recursive function to get the path from a TreeItem.
	 * @param treeItem the tree item.
	 * @return the String representation of the path.
	 */
	private String getTreeViewPath(TreeItem<String> treeItem){
		String res = treeItem.getValue();
		while(treeItem.getParent() != null){
			res = treeItem.getParent().getValue() + "/" + res;
			treeItem = treeItem.getParent();
		}
		return res;
	}

	/**
	 * Recursive function to get the TreeView from a file dir.
	 * @param dir the dir to get the TreeView.
	 * @return the corresponding TreeView.
	 */
	private TreeItem<String> getTreeView(File dir){
		TreeItem<String> rtemp = new TreeItem<>(dir.getName());
		File[] filetemp = dir.listFiles();
		for(int i = 0; i < filetemp.length; i++){
			if(!filetemp[i].getName().endsWith(".ser")){
				if(filetemp[i].isFile()){
					rtemp.getChildren().add(new TreeItem<String>(
							filetemp[i].getName()));
				}else{
					TreeItem<String> newroot = getTreeView(new File(
							filetemp[i].getPath()));
					rtemp.getChildren().add(newroot);
				}
			}
		}
		return rtemp;
	}

	/**
	 * Get the index of the mod from it's name.
	 * @param name the mod name.
	 * @return the index in the mod ArrayList.
	 */
	private int getIndex(String name) {
		for(ModInfo mod : mods){
			if(mod.getName().equals(name)){
				return mods.indexOf(mod);
			}
		}
		return -1;
	}

	/**
	 * Serial the mod info object to the config folder location.
	 */
	private void Serial() {
		ObjectOutputStream oos;
		for(ModInfo mi : mods){
			File file = new File(Reference.CONFIG_LOCATION+mi.getName()+".ser");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				oos = new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(
										file)));
				oos.writeObject(mi);
				oos.close();	
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read all .ser file in the config location and add each mod to the 
	 * ArrayList mod.
	 */
	private void ReadSerial(){
		File f = new File(Reference.CONFIG_LOCATION);
		if(!f.exists()){
			f.mkdirs();
		}
		File[] filetemp = f.listFiles();
		for(int i = 0; i < filetemp.length; i++){
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(filetemp[i]);
				@SuppressWarnings("resource")
				ObjectInputStream in = new ObjectInputStream(fileIn);
				mods.add((ModInfo)in.readObject());
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
}