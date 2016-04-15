package com.gendai.modcreatorfx.gui;

import com.gendai.modcreatorfx.resources.Resource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiMain extends Application{


	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(Resource.class.getResource("modcreator.fxml"));
		Scene scene = new Scene(root, 630, 420);
		Image applicationIcon = new Image(Resource.class.getResourceAsStream("icon36.png"));
		primaryStage.getIcons().add(applicationIcon);
		primaryStage.setTitle("ModCreator");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void OpenBrowser(String text){
		getHostServices().showDocument(text);
	}


}
