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
import javafx.scene.control.ScrollPane;
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
    private VBox newTextBox;
    private ScrollPane scrollPane;
    private Gpt4ApiCaller caller;

    public MainView(double spacing) throws FileNotFoundException
    {
        super(spacing);

        //ObservableList components = this.getChildren();
        try {
            caller = new Gpt4ApiCaller();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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

        newTextBox = new VBox();
        newTextBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        newTextBox.setPrefWidth(250);
        newTextBox.setMaxWidth(250);
        newTextBox.setSpacing(10);
        //newTextBox.getChildren().add(new Label("Hello, I'm Jean-Philippe, a mentor at Lyon Ynov Campus. I'm an IT expert. I have a very high ego. I ate my food without sauce. I must go to the toilet every 5 minutes. I'm funny. I'm from portugal. I'm fan of Cristiano Ronaldo. I should act like if I'm in a date but I'm not an easy guy"));
        //newTextBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(100);
        scrollPane.setPrefWidth(250);
        scrollPane.setMaxHeight(100);
        //scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // scrollPane.setFitToHeight(true);
        // scrollPane.setFitToWidth(true);
        scrollPane.setContent(newTextBox);

        
        VBox scrollPaneContainer = new VBox();
        scrollPaneContainer.setAlignment(javafx.geometry.Pos.CENTER);
        scrollPaneContainer.setPrefHeight(100);
        scrollPaneContainer.setPrefWidth(300);
        scrollPaneContainer.setMaxHeight(100);
        scrollPaneContainer.setMaxWidth(javafx.scene.layout.Region.USE_PREF_SIZE);
        scrollPaneContainer.getChildren().add(scrollPane);


        VBox centerVbox = new VBox();
        centerVbox.setSpacing(10);



        centerVbox.setAlignment(javafx.geometry.Pos.CENTER);
        centerVbox.getChildren().add(imageView);
        centerVbox.getChildren().add(scrollPaneContainer);
        centerVbox.getChildren().add(textArea);

        // BorderPane borderPane = new BorderPane(); // BorderPane is a layout manager that manages nodes in top, bottom, left, right, and center positions
        // borderPane.setCenter(imageView);

        //Label label = new Label("Hello World");


        VBox vbox = new VBox(centerVbox);
        scene = new Scene(vbox, 500, 900);


        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        caller.callApi(textArea.getText());
                        displayPrompt();
                        displayResponse();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
            }
        });

        //components.addAll(imageView);
    }

    private void displayPrompt()
    {
        Label prompt = new Label("You : " + textArea.getText());
        prompt.setWrapText(true);
        newTextBox.getChildren().add(prompt);
        textArea.clear();
    }

    private void displayResponse()
    {
        Label response = new Label("JP : " + caller.getResponse());
        response.setWrapText(true);
        newTextBox.getChildren().add(response);
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
