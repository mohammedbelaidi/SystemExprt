/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users;

import com.ControlledScreen;
import com.Main;
import com.ScreensController;
import com.entitys.Bip;
import com.entitys.Problem;
import com.entitys.ProdSolu;
import com.entitys.Solution;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.models.ModelBip;
import com.models.ModelProblems;
import com.models.ModelSolution;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author MOHAMMED-PC
 */
public class FXMLUsersController implements Initializable, ControlledScreen {

    @FXML
    private JFXRadioButton crn_bleu;

    @FXML
    private JFXRadioButton bip_son;

    @FXML
    private JFXRadioButton autre_p;
    @FXML
    private AnchorPane root;
        @FXML
    private ImageView img_play;

    @FXML
    private Label lable_name;
    @FXML
    private TextField txt_search;
    @FXML
    private AnchorPane pane_play;
    @FXML
    private ScrollPane scroll;
    List<String> dataSearch;
    ScreensController screenPage;
    private AutoCompletionBinding<String> auto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup toggleGroup = new ToggleGroup();
        GridPane container = new GridPane();
        pane_play.setVisible(false);
        crn_bleu.setToggleGroup(toggleGroup);
        bip_son.setToggleGroup(toggleGroup);
        autre_p.setToggleGroup(toggleGroup);
        ModelProblems mp = new ModelProblems();
        List<Problem> problems = mp.getProblems();
        dataSearch = new ArrayList<>();
        for (Problem p : problems) {
            dataSearch.add(p.getSymptome());
        }
        auto = TextFields.bindAutoCompletion(txt_search, dataSearch);
        txt_search.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    if (!txt_search.getText().isEmpty()) {
                        getSoletion(txt_search.getText());
                        //System.err.println("fffff");
                    }
            }

        });
    }

    @FXML
    void back(ActionEvent event) {
        screenPage.setScreen(Main.screen1ID);
        pane_play.setVisible(false);
        txt_search.setText("");
    }
    private void readSond(Bip bip){
        pane_play.setVisible(true);
        lable_name.setText(new File(bip.getAudio()).getName());
        img_play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                play(bip);
            }
        });
    }
    private void play(Bip bip){
         if (bip != null) {
            String uriString = new File(bip.getAudio()).toURI().toString();
            MediaPlayer player = new MediaPlayer(new Media(uriString));
            player.play();
        }
    }
    private void getSoletion(String string) {
        ModelProblems mp = new ModelProblems();
        ModelSolution ms = new ModelSolution();
        ModelBip mb = new ModelBip();
        Problem p = mp.getProblemBySymtom(string);
        if (p != null) {
            List<ProdSolu> pr = mp.getSoluProb(p.getCode());
            Bip bip = mb.getBipByProblem(p.getCodeP());
            if(bip != null)
                readSond(bip);
            else pane_play.setVisible(false);
            List<Solution> solutions = new ArrayList();
            for (int t = 0; t < pr.size(); t++) {
                solutions.add(ms.getSolutionByCode(pr.get(t).getCodeS()));
            }

            VBox root = new VBox();
            for (int i = 0; i < solutions.size(); i++) {
                JFXTextArea text = new JFXTextArea(solutions.get(i).getDescription());
                root.getChildren().add(text);

            }

            scroll.setContent(null);
            scroll.setContent(root);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screenPage = screenPage;
    }

    public static VBox populateDropDownMenu(String text, List<String> options) {
        VBox dropDownMenu = new VBox();
        dropDownMenu.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null))); // colors just for example
        dropDownMenu.setAlignment(Pos.BOTTOM_CENTER); // all these are optional and up to you

        for (String option : options) { // loop through every String in the array
            // if the given text is not empty and doesn't consists of spaces only, as well as it's a part of one (or more) of the options
            if (!text.replace(" ", "").isEmpty() && option.toUpperCase().contains(text.toUpperCase())) {
                Label label = new Label(option); // create a label and set the text 
                // you can add listener to the label here if you want
                // your user to be able to click on the options in the drop-down menu
                dropDownMenu.getChildren().add(label); // add the label to the VBox
            }
        }

        return dropDownMenu; // at the end return the VBox (i.e. drop-down menu)
    }
}
