/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.confirm;

import com.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author MOHAMMED-PC
 */
public class MyCofirme {
    JFXDialog dialog ;
    JFXDialogLayout content = new JFXDialogLayout();
    public MyCofirme(AnchorPane pane,String heade,String text){
        StackPane stackpane = new StackPane();
        content.setHeading(new Text(heade));
        content.setBody(new Text(text));
        JFXButton ok = new JFXButton("OK");
        dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.TOP);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(ok);
        pane.getChildren().add(stackpane);
    }
}
