package app;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import controler.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader();
        URL url=null;
        AnchorPane root = null; 
        
		try {
			url = Main.class.getResource("/vue/GuiView.fxml").toURI().toURL();
		} catch (MalformedURLException e) {
			throw new Error("Error path to fxml file");
		}catch (URISyntaxException e) {
			throw new Error("Error path to fxml file");
		}

        loader.setLocation(url);
        
        try {
			root=loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error loading javaFX");
		} 

        Controleur controller = loader.getController();
        Scene  scene = new Scene(root,960,640);
        
        controller.startScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

	
