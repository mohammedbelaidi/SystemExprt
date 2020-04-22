package com;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MOHAMMED-PC
 */
public class Main extends Application {
    double xOffset = 0;
    double yOffset = 0;
    public static Stage mstage;
    public static String screen1ID = "login";
    public static String screen1file = "views/FXMLLogin.fxml";
    public static String screen2ID = "couteuser";
    public static String screen2file = "views/FXMLUser.fxml";
    public static String screen3ID = "FXMLUsers";
    public static String screen3file = "views/FXMLUsers.fxml";
    @Override
    public void start(Stage stage) throws Exception {
        mstage = stage;
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(screen1ID,screen1file);
        mainContainer.loadScreen(screen2ID,screen2file);
        mainContainer.loadScreen(screen3ID,screen3file);
        mainContainer.setScreen(screen1ID);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        //stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
           public void handle(MouseEvent event) {
               xOffset = event.getSceneX();
               yOffset = event.getSceneY();
           }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
            public void handle(MouseEvent event) {
              stage.setX(event.getScreenX() - xOffset);
               stage.setY(event.getScreenY() - yOffset);
            }
        });
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
