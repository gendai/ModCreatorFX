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

import com.gendai.modcreatorfx.resources.Resource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The Main class launching the application.
 * @author gendai
 * @version 0.0.1
 */
public class GuiMain extends Application{

	public static void main(String args[]){
		launch(args);
	}

	/**
	 * Start method from JavaFX.
	 */
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
}
