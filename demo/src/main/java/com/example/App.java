package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene mainViewScene;

    private final String TITLE = "Mentor Dating Simulator";
    private final double WIDTH = 500;
    private final double HEIGHT = 700;

    @Override
    public void start(Stage stage) throws IOException { // Must have function in JavaFX
        MainView mainView = new MainView(20.0);
        //mainViewScene = new Scene(mainView);
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.setScene(mainView.GetScene());
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        mainViewScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // private static Parent loadImage(String image) throws IOException {
    //     FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(image + ".jpg"));
    //     return fxmlLoader.load();
    // }

    public static void main(String[] args) {
        launch();
    }

}