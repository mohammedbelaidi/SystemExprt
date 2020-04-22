/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import com.ControlledScreen;
import com.Main;
import static com.Main.mstage;
import com.ScreensController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MOHAMMED-PC
 */
public class FXMLLoginController implements Initializable,ControlledScreen {

    /**
     * Initializes the controller class.
     */
  
 


    @FXML
    private Button btn_login,user_simple;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        exite.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Main.mstage.close();
//            }
//        });
//        rid.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                mstage.setIconified(true);
//            }
//        });
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent event) {
               
               
               screenPage.setScreen(Main.screen2ID);
            }
        });
        user_simple.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent event) {
               
               
               screenPage.setScreen(Main.screen3ID);
            }
        });
   
    }    
    
    private void login(){
        try {
            mstage.close();
            Parent root = FXMLLoader.load(Main.class.getClassLoader().getResource("login/FXMLUser.fxml"));
            Scene scene = new Scene(root);
            mstage.setScene(scene);
            
            mstage.show();
        } catch (Exception ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ScreensController screenPage;
    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screenPage = screenPage;
    }
    
}
