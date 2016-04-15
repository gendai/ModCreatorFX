package Handler;

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

import Gui.FilePreview;
import Gui.ItemDiag;
import Gui.ItemInfo;
import Gui.ModDiag;
import Gui.ModInfo;
import Gui.Reference;
import ModCreator.FileCreator;
import Template.ItemTemplate;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FXMLController implements Initializable{
	@FXML private ListView<String> lstv;
	@FXML private TreeView<String> TreeV;
	@FXML private Label Modlbl;
	@FXML private TextFlow Rich;
	@FXML private Button AddItem;
	@FXML private Pane PanTest;
	private final ObservableList<String> items = FXCollections.observableArrayList();
	private TreeItem<String> root;
	private FileCreator fc;
	private ArrayList<ModInfo> mods;

	@FXML protected void OnTabSel(Event e){
		if(!lstv.getSelectionModel().isEmpty()){
			root = new TreeItem<String>(lstv.getSelectionModel().getSelectedItem());
			root.setExpanded(true);
			File dir = new File(Reference.outputLocation+lstv.getSelectionModel().getSelectedItem());
			root = getTreeView(dir);
			TreeV.setRoot(root);
		}
	}

	@FXML protected void OnLinkClick(ActionEvent e){
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/fangyidong/json-simple"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	@FXML protected void OnSelect(MouseEvent e){
		if(lstv.getSelectionModel().getSelectedIndex() != -1){
			Modlbl.setText(lstv.getSelectionModel().getSelectedItem());
			AddItem.setDisable(false);
		}else{
			Modlbl.setText("Choisissez un mod");
			AddItem.setDisable(true);
		}
	}

	@FXML protected void OnItemClick(ActionEvent e){
		/*File f = new File(Reference.outputLocation+lstv.getSelectionModel().getSelectedItem()+"/test.java");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		Dialog<ItemInfo> diag = new ItemDiag().Show();
		Optional<ItemInfo> res = diag.showAndWait();
		if(res.isPresent()){
			String tmp = res.get().getName().replaceAll(" ", "");
			if(res.get().getName().isEmpty() || tmp.equals("") || res.get().getType() == null || res.get().getTexturefile() == null){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Erreur sur les parametres");
				alert.setContentText("Tous les parametres ne sont pas conforme");
				alert.showAndWait();
			}else{
				System.out.println("All went right!!");
				ItemTemplate template = new ItemTemplate(mods.get(lstv.getSelectionModel().getSelectedIndex()),res.get());
				try {
					template.create();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@FXML protected void OnAddClick(ActionEvent e){
		Dialog<ModInfo> diag = new ModDiag().ShowAdd();
		Optional<ModInfo> res = diag.showAndWait();
		if(res.isPresent()){
			String tmp = res.get().getName().replaceAll(" ", "");
			if(items.contains(res.get().getName()) || res.get().getName().isEmpty() || tmp.equals("")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Erreur sur le nom du mod");
				alert.setContentText("Ce nom de mod existe déjà ou vous n'avez pas entré de nom");
				alert.showAndWait();
			}else{
				mods.add(res.get());
				fc.Configure(res.get().getName());
				items.add(res.get().getName());
				Serial();
			}
		}

	}

	@FXML protected void OnDelClick(ActionEvent e){
		int index = lstv.getSelectionModel().getSelectedIndex();
		if(items.size() != 0 && index != -1){
			File f = new File(Reference.outputLocation+lstv.getSelectionModel().getSelectedItem());
			File[] filetemp = f.listFiles();
			for(int i = 0; i < filetemp.length; i++){
				filetemp[i].delete();
			}
			f.delete();
			File configf = new File(Reference.configLocation+lstv.getSelectionModel().getSelectedItem()+".ser");
			configf.delete();
			mods.remove(index);
			items.remove(index);
			Serial();
		}
	}

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
					File f = new File(Reference.outputLocation+lstv.getSelectionModel().getSelectedItem());
					File newf = new File(Reference.outputLocation+res.get().getName());
					File configf = new File(Reference.configLocation+lstv.getSelectionModel().getSelectedItem()+".ser");
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.fc = new FileCreator();
		mods = new ArrayList<>();
		ReadSerial();
		File dir2 = new File(Reference.outputLocation);
		File[] filetemp2 = dir2.listFiles();
		for(int i = 0; i < filetemp2.length; i++){
			if(!filetemp2[i].getName().equals("config"))
			{
				items.add(filetemp2[i].getName());
			}
		}
		lstv.setItems(items);
		lstv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val){
				int in = getIndex(new_val);
				if(Rich.getChildren().size() != 0){
					Rich.getChildren().remove(0, Rich.getChildren().size());
				}
				root = new TreeItem<String>(new_val);
				root.setExpanded(true);
				File dir = new File(Reference.outputLocation+lstv.getSelectionModel().getSelectedItem());
				root = getTreeView(dir);
				TreeV.setRoot(root);
				if(lstv.getItems().size() > 0){
					if(TreeV.getSelectionModel().getSelectedItem() != null && TreeV.getSelectionModel().getSelectedItem().equals(root)){
						Text tm = new Text("Mod: ");
						tm.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
						Text text2 = new Text("Version: ");
						text2.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
						Text text3 = new Text("Description: ");
						text3.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
						Text t1 = new Text(mods.get(in).getName()+"\n");
						t1.setFont(Font.font("Helvetica",12));
						Text t2 = new Text(mods.get(in).getVersion()+"\n");
						t2.setFont(Font.font("Helvetica", 12));
						Text t3 = new Text(mods.get(in).getDescription()+"\n");
						t3.setFont(Font.font("Helvetica", 12));
						Rich.getChildren().addAll(tm, t1, text2, t2, text3, t3);
					}
				}else{
					Text def = new Text("Aucun mod");
					Rich.getChildren().clear();
					Rich.getChildren().add(def);
					root.getChildren().clear();
					root.setValue("");
					TreeV.setRoot(root);
				}

			};

		});
		TreeV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
				if(Rich.getChildren().size() != 0){
					Rich.getChildren().remove(0, Rich.getChildren().size());
				}
				if(newValue != null && newValue.equals(TreeV.getRoot())){
					Text tm = new Text("Mod: ");
					tm.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
					Text text2 = new Text("Version: ");
					text2.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
					Text text3 = new Text("Description: ");
					text3.setFont(Font.font("Helvetica", FontWeight.BOLD ,16));
					Text t1 = new Text(mods.get(lstv.getSelectionModel().getSelectedIndex()).getName()+"\n");
					t1.setFont(Font.font("Helvetica",12));
					Text t2 = new Text(mods.get(lstv.getSelectionModel().getSelectedIndex()).getVersion()+"\n");
					t2.setFont(Font.font("Helvetica", 12));
					Text t3 = new Text(mods.get(lstv.getSelectionModel().getSelectedIndex()).getDescription()+"\n");
					t3.setFont(Font.font("Helvetica", 12));
					Rich.getChildren().addAll(tm, t1, text2, t2, text3, t3);
				}else if(!TreeV.getSelectionModel().isEmpty() && TreeV.getSelectionModel().getSelectedItem().getValue().endsWith(".java")){
					FilePreview fp = new FilePreview(Reference.outputLocation+"/"+getTreeViewPath(TreeV.getSelectionModel().getSelectedItem()));
					try {
						Rich.getChildren().add(fp.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	private String getTreeViewPath(TreeItem<String> t){
		String res = t.getValue();
		while(t.getParent() != null){
			res = t.getParent().getValue() + "/" + res;
			t = t.getParent();
		}
		return res;
	}

	private TreeItem<String> getTreeView(File dir){
		TreeItem<String> rtemp = new TreeItem<>(dir.getName());
		File[] filetemp = dir.listFiles();
		for(int i = 0; i < filetemp.length; i++){
			if(!filetemp[i].getName().endsWith(".ser")){
				if(filetemp[i].isFile()){
					rtemp.getChildren().add(new TreeItem<String>(filetemp[i].getName()));
				}else{
					TreeItem<String> newroot = getTreeView(new File(filetemp[i].getPath()));
					rtemp.getChildren().add(newroot);
				}
			}
		}
		return rtemp;
	}

	private int getIndex(String name){
		for(ModInfo mod : mods){
			if(mod.getName().equals(name)){
				return mods.indexOf(mod);
			}
		}
		return -1;
	}

	private void Serial(){
		ObjectOutputStream oos;
		for(ModInfo mi : mods){
			File file = new File(Reference.configLocation+mi.getName()+".ser");
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

	private void ReadSerial(){
		File f = new File(Reference.configLocation);
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
