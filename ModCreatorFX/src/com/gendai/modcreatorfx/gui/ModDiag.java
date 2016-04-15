package com.gendai.modcreatorfx.gui;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;

import com.gendai.modcreatorfx.resources.Resource;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ModDiag {

	Dialog<ModInfo> diagAdd;
	Dialog<ModInfo> diagRen;
	
	public ModDiag(){
		
	}
	
	public Dialog<ModInfo> ShowRenew(ModInfo mi){
		diagRen = new Dialog<>();
		diagRen.setTitle("Mod Creator");
		diagRen.setHeaderText("Modifier paramètres du mod");
		diagRen.setResizable(false);
		Stage st = (Stage)diagRen.getDialogPane().getScene().getWindow();
		st.getIcons().add(new Image(Resource.class.getResourceAsStream("icon36.png")));
		Label nm = new Label("Nom: ");
		Label ver = new Label("Version: ");
		Label des = new Label("Description: ");
		TextField tnm = new TextField(mi.getName());
		TextField tver = new TextField(mi.getVersion());
		TextArea tdes = new TextArea(mi.getDescription());
	
		GridPane grid = new GridPane();
		grid.add(nm, 1, 1);
		grid.add(tnm, 2, 1);
		grid.add(ver, 1, 2);
		grid.add(tver, 2, 2);
		grid.add(des, 1, 3);
		grid.add(tdes, 2, 3);
		diagRen.getDialogPane().setContent(grid);
		
		ButtonType buttonOK = new ButtonType("Ok", ButtonData.OK_DONE);
		diagRen.getDialogPane().getButtonTypes().add(buttonOK);
		
		diagRen.setResultConverter(new Callback<ButtonType, ModInfo>() {
			@Override
			public ModInfo call(ButtonType param) {
				if(param == buttonOK){
					return new ModInfo(tnm.getText(), tver.getText(), tdes.getText());
				}
				return null;
			}
		});
		return diagRen;
	}
	
	public Dialog<ModInfo> ShowAdd(){
		diagAdd = new Dialog<>();
		diagAdd.setTitle("Mod Creator");
		diagAdd.setHeaderText("Entrer le nom, la version et une descritpion du mod");
		diagAdd.setResizable(false);
		Stage st = (Stage)diagAdd.getDialogPane().getScene().getWindow();
		st.getIcons().add(new Image(Resource.class.getResourceAsStream("icon36.png")));
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
					return new ModInfo(tnm.getText(), tver.getText(), tdes.getText());
				}
				return null;
			}
		});
		return diagAdd;
	}
}
