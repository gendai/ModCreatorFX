package Gui;

import java.io.File;

import ModCreator.MCTypes.ItemType;
import Resources.Resource;
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

public class ItemDiag {

	Dialog<ItemInfo> item;
	File texturefile;
	
	public ItemDiag(){
		
	}
	
	public Dialog<ItemInfo> Show(){
		item = new Dialog<>();
		item.setTitle("Mod Creator");
		item.setHeaderText("Entrer les parametres");
		item.setResizable(false);
		Stage st = (Stage)item.getDialogPane().getScene().getWindow();
		st.getIcons().add(new Image(Resource.class.getResourceAsStream("icon36.png")));
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
				texturefile = chooser.showOpenDialog(chb.getScene().getWindow());
				if(texturefile != null){
					tfl.setText(texturefile.getName());
				}
			}
		});
		ObservableList<ItemType.Items> it = FXCollections.observableArrayList();;
		it.addAll(ItemType.Items.Axe, ItemType.Items.Shovel, ItemType.Items.Stick);
		ComboBox<ItemType.Items> combo = new ComboBox<>(it);
		GridPane grid = new GridPane();
		grid.add(nm, 1, 1);
		grid.add(tnm, 2, 1);
		grid.add(fl, 1, 2);
		grid.add(tfl, 2, 2);
		grid.add(chb, 3, 2);
		grid.add(type, 1, 3);
		grid.add(combo, 2, 3);
		item.getDialogPane().setContent(grid);
		
		ButtonType buttonOK = new ButtonType("Ok", ButtonData.OK_DONE);
		item.getDialogPane().getButtonTypes().add(buttonOK);
		
		item.setResultConverter(new Callback<ButtonType, ItemInfo>() {
			@Override
			public ItemInfo call(ButtonType param) {
				if(param == buttonOK){
					return new ItemInfo(tnm.getText(), texturefile, combo.getSelectionModel().getSelectedItem());
				}
				return null;
			}
		});
		return item;
	}
}
