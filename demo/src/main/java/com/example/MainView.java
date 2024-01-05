package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    
    private Scene scene;
    private TextArea textArea;

    public MainView(double spacing) throws FileNotFoundException
    {
        super(spacing);

        //ObservableList components = this.getChildren();

        URL imageURL = App.class.getResource("images/JP.jpg");
        Image image = new Image(imageURL.toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        textArea = new TextArea();
        textArea.setPrefHeight(100);
        textArea.setPrefWidth(300);
        textArea.setMaxWidth(300);
        textArea.setWrapText(true);

        VBox newTextBox = new VBox();
        newTextBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        newTextBox.setPrefWidth(300);
        newTextBox.setMaxWidth(300);
        newTextBox.setSpacing(10);
        newTextBox.getChildren().add(new Label("JP :"));
        newTextBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        VBox centerVbox = new VBox();
        centerVbox.setSpacing(10);



        centerVbox.setAlignment(javafx.geometry.Pos.CENTER);
        centerVbox.getChildren().add(imageView);
        centerVbox.getChildren().add(newTextBox);
        centerVbox.getChildren().add(textArea);

        // BorderPane borderPane = new BorderPane(); // BorderPane is a layout manager that manages nodes in top, bottom, left, right, and center positions
        // borderPane.setCenter(imageView);

        //Label label = new Label("Hello World");


        VBox vbox = new VBox(centerVbox);
        scene = new Scene(vbox, 500, 400);


        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println(GetText());
                }
            }
        });

        //components.addAll(imageView);
    }

    public Scene GetScene()
    {
        return scene;
    }

    public String GetText()
    {
        return textArea.getText();
    }

    // EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() { 
    //     public void handle(KeyEvent e) 
    //     { 
    //         if (e.getCode().toString().equals("ENTER"))
    //         {
    //             System.out.println("Enter pressed");
    //         }
    //     } 
    // };
}
