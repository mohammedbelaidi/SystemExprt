/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coteuser;

import com.ControlledScreen;
import com.Main;
import com.ScreensController;
import com.confirm.MyCofirme;
import com.entitys.Bip;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javazoom.jlme.util.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.entitys.Problem;
import com.entitys.ProdSolu;
import com.entitys.Solution;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.models.ModelBip;
import com.models.ModelProblems;
import com.models.ModelSolution;
import com.test.ConvertData;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

/**
 * FXML Controller class
 *
 * @author MOHAMMED-PC
 */
public class FXMLUserController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXTabPane root;
        @FXML
    private AnchorPane blue_pane;
    @FXML
    private JFXTextField txt_cran_code;

    @FXML
    private JFXTextField txt_cran_symptome;
    @FXML
    private JFXTextArea txt_autr_solution;
   @FXML
    private JFXTextArea txt_cran_solution;
    @FXML
    private JFXListView<String> list_cran_solution;

    @FXML
    private JFXButton btn_cran_solution;

    @FXML
    private JFXButton btn_cran_insert;

    @FXML
    private JFXTextField txt_bip_code;

    @FXML
    private JFXTextField txt_bip_file_symptom;

    @FXML
    private ImageView btn_add_file_bip;
    @FXML
    Button btn_bip_playe;
    @FXML
    private JFXButton btn_bip_insert;
    @FXML
    private JFXSpinner spinner_solu_ecran;

    @FXML
    private JFXTextField txt_autre_symptom;

    @FXML
    private JFXListView<String> list_autre_solution, list_autre_solution1, list_cran_solution1;

    @FXML
    private JFXButton btn_autre_solution;

    @FXML
    private JFXButton btn_autre_insert;
    @FXML
    private ImageView img_play;
    @FXML
    private TableView<Problem> tab_autre, table_bip, tab_ecran;
    @FXML
    private TableColumn<Problem, String>  clmn_ecran_symptm;
    @FXML
    private TableColumn<Problem, String> clmn_bip_symptom;
    @FXML
    private TableColumn<Problem, String> clmn_autre_symtom;
    private ObservableList<Problem> data_ecran = null, autre = null, bipso = null;
    @FXML
    private TableColumn<Problem, Integer> clmn_ecran_code,clmn_bip_code;
    
       private void addColumn() {
        
        clmn_ecran_code.setCellValueFactory(new PropertyValueFactory<>("codeP"));
       
        
        clmn_ecran_symptm.setCellValueFactory(new PropertyValueFactory<>("symptome"));
       
        clmn_autre_symtom.setCellValueFactory(new PropertyValueFactory<>("symptome"));
        clmn_bip_code.setCellValueFactory(new PropertyValueFactory<>("codeP"));
        clmn_bip_symptom.setCellValueFactory(new PropertyValueFactory<>("symptome"));

    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        spinner_solu_ecran.setVisible(false);
        addColumn();
        addDataToTable();
        addDataToTableAutre();
        addDataToTableBip();
        list_cran_solution.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                txt_cran_solution.setText(newValue.toString());
                           }
        });
        list_cran_solution1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue != null)
                txt_cran_solution.setText(newValue.toString());
                           }
        });
        tab_ecran.getSelectionModel().selectedItemProperty().addListener((new ChangeListener<Problem>() {
            @Override
            public void changed(ObservableValue<? extends Problem> observable, Problem oldValue, Problem newValue) {
                //To change body of generated methods, choose Tools | Templates.
                if (newValue != null) {
                    btn_cran_insert.setText("Mise à jour");
                    txt_cran_code.setText(String.valueOf(newValue.getCodeP()));
                    txt_cran_symptome.setText(newValue.getSymptome());
                    ModelProblems mp = new ModelProblems();
                    ModelSolution ms = new ModelSolution();
                    List<Solution> lists = new ArrayList<>();
                    List<ProdSolu> pses = mp.getSoluProb(newValue.getCode());
                    for (ProdSolu ps : pses) {
                        lists.add(ms.getSolutionByCode(ps.getCodeS()));
                        //System.err.println(ps.getCodeS());
                    }
                    list_cran_solution1.getItems().clear();
                    list_cran_solution.getItems().clear();
                    for (Solution solution : lists) {
                        if (solution != null) {
                            list_cran_solution1.getItems().add(solution.getDescription());
                            list_cran_solution.getItems().add(solution.getDescription());
                        }
                    }
                    listS = lists;
                    p = newValue;
                }
            }
        }));
        tab_autre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Problem>() {
            @Override
            public void changed(ObservableValue<? extends Problem> observable, Problem oldValue, Problem newValue) {
                if(newValue != null){
                txt_autre_symptom.setText(newValue.getSymptome());
                btn_autre_insert.setText("Mise à jour");
                ModelProblems mp = new ModelProblems();
                ModelSolution ms = new ModelSolution();
                List<Solution> lists = new ArrayList<>();
                List<ProdSolu> pses = mp.getSoluProb(newValue.getCode());
                for (ProdSolu ps : pses) {
                    lists.add(ms.getSolutionByCode(ps.getCodeS()));
                    System.out.println(ps.getCodeS());
                }
                list_autre_solution1.getItems().clear();
                list_autre_solution.getItems().clear();
                for (Solution solution : lists) {
                    if (solution != null) {
                        list_autre_solution1.getItems().add(solution.getDescription());
                        list_autre_solution.getItems().add(solution.getDescription());
                    }
                }
                }

            }
        });
        table_bip.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Problem> observable, Problem oldValue, Problem newValue) -> {
            if(newValue != null){
                System.out.println(".changed()");
                txt_bip_code.setText(newValue.getSymptome());
                ModelBip mb = new ModelBip();
                Bip bip = mb.getBipByProblem(newValue.getCodeP());
                bipval = bip;
            }
        });

        btn_bip_playe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (bipval != null) {
                    play(bipval);
                }
            }
        });
        btn_cran_solution.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addSolution();
            }
        });
        btn_cran_insert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String val = btn_cran_insert.getText();
                if (val.equals("Insérer")) {
                    addProblem();
                } else {
                    if (p != null && listS != null) {
                        upDate(p, listS);
                    }
                }
            }
        });
        btn_autre_solution.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addAutreP();
            }
        });
        btn_autre_insert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addAutreS();
            }
        });
        btn_add_file_bip.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                selectedFile = fileChooser.showOpenDialog(Main.mstage);
                if(selectedFile.isFile())
                txt_bip_file_symptom.setText(selectedFile.getName());
            }
        });
        btn_bip_insert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txt_bip_code.getText().isEmpty() && txt_bip_file_symptom.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR : information !");
                    alert.setHeaderText(null);
                    alert.setContentText("Verifier tous les champs ! ");
                    alert.showAndWait();
                } else {
                   
                    try {
                       
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ModelBip mb = new ModelBip();
                    Bip bip = new Bip(txt_bip_code.getText(), selectedFile.getPath());
                    mb.insertBip(bip);
                    addDataToTableBip();
                    txt_bip_code.setText("");
                    txt_bip_file_symptom.setText("");
                }
            }
        });

        img_play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                play(bipval);
            }
        });

    }
    Bip bipval = null;
    File selectedFile;
    Problem p;
    List<Solution> listS;

   
    public void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    private void upDate(Problem p, List<Solution> listS) {
        ModelProblems mp = new ModelProblems();
        ModelSolution ms = new ModelSolution();
        String s = txt_cran_symptome.getText();

        if (s.isEmpty() || txt_cran_code.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR : information !");
            alert.setHeaderText(null);
            alert.setContentText("Verifier tous les champs ! ");
            alert.showAndWait();
        } else {
            p.setSymptome(s);
            mp.updateProblem(p);
//        for(Solution s : listS){
//            ms.updateSolution(s);
//        }
            txt_cran_solution.setText("");
            txt_cran_code.setText("");
            txt_cran_symptome.setText("");
            list_cran_solution.getItems().clear();
            spinner_solu_ecran.setVisible(false);
            addDataToTable();
        }
    }

    private void addAutreS() {
        String s = txt_autre_symptom.getText();
        if (s.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR : information !");
            alert.setHeaderText(null);
            alert.setContentText("Verifier tous les champs ! ");
            alert.showAndWait();
        } else {
            ModelProblems mp = new ModelProblems();
            Problem p = new Problem(s, "autre");
            mp.insertProblem(p);
            ModelSolution ms = new ModelSolution();
            p = mp.getProblemBySymtom(s);
            //List<String> sympList = list_autre_solution.getItems();
            ///String ss[] = (String[]) sympList.toArray();
            for (int i = 0; i < list_autre_solution.getItems().size(); i++) {
                Solution solution = ms.getSolutionByDescription(list_autre_solution.getItems().get(i));
                ms.isertSoluProb(solution.getCodeS(), p.getCode());
            }
            txt_autr_solution.setText("");
            txt_autre_symptom.setText("");
            list_autre_solution.getItems().clear();
            addDataToTableAutre();
        }
    }

    private void addAutreP() {
        //String syptom = txt_autre_symptom.getText();
        String solution = txt_autr_solution.getText();
        if (solution.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR : information !");
            alert.setHeaderText(null);
            alert.setContentText("Verifier tous les champs ! ");
            alert.showAndWait();
        } else {
            list_autre_solution.getItems().add(solution);
            ModelSolution modelSolution = new ModelSolution();
            Solution s = new Solution(solution);
            int code = modelSolution.isertSolution(s);
            txt_autr_solution.setText("");

        }
    }

    private void addProblem() {
        String s = txt_cran_symptome.getText();

        if (s.isEmpty() || txt_cran_code.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR : information !");
            alert.setHeaderText(null);
            alert.setContentText("Verifier tous les champs ! ");
            alert.showAndWait();
        } else {
            //int codes = Integer.parseInt(txt_cran_code.getText());
            ModelProblems mp = new ModelProblems();
            ModelSolution ms = new ModelSolution();
            Problem p = new Problem(0,txt_cran_code.getText(), s, "ecranb");
            mp.insertProblem(p);
            p = mp.getProblemBySymtom(s);
            spinner_solu_ecran.setVisible(true);

            //List<String> sympList = list_cran_solution.getItems();
            //String ss[] = (String[]) sympList.toArray();
            for (int i = 0; i < list_cran_solution.getItems().size(); i++) {
                Solution solution = ms.getSolutionByDescription(list_cran_solution.getItems().get(i));
                ms.isertSoluProb(solution.getCodeS(), p.getCode());
            }
            txt_cran_solution.setText("");
            txt_cran_code.setText("");
            txt_cran_symptome.setText("");
            list_cran_solution.getItems().clear();
            spinner_solu_ecran.setVisible(false);
            addDataToTable();
        }
    }

    private void addSolution() {

        String solution = txt_cran_solution.getText();
        if (txt_cran_code.getText().isEmpty() || solution.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR : information !");
            alert.setHeaderText(null);
            alert.setContentText("Verifier tous les champs ! ");
            alert.showAndWait();
        } else {
            //int codes = Integer.parseInt(txt_cran_code.getText());
            list_cran_solution.getItems().add(solution);
            ModelSolution modelSolution = new ModelSolution();
            Solution s = new Solution(solution);
            int code = modelSolution.isertSolution(s);
            txt_cran_solution.setText("");
            //modelSolution.isertSolu(code);
        }
    }

    private void addDataToTable() {

        ModelProblems mp = new ModelProblems();
        List<Problem> Problems = mp.getProblems("ecranb");
        if (Problems != null) {
            data_ecran = FXCollections.observableArrayList(Problems);
            tab_ecran.setItems(data_ecran);
        }

    }

    private void addDataToTableAutre() {

        ModelProblems mp = new ModelProblems();
        List<Problem> Problems = mp.getProblems("autre");
        if (Problems != null) {
            autre = FXCollections.observableArrayList(Problems);
            tab_autre.setItems(autre);
        }

    }

    private void addDataToTableBip() {

        ModelProblems mp = new ModelProblems();
        ModelBip mb = new ModelBip();
        List<Bip> bips = mb.getBip();
        List<Problem> Problemsl = new ArrayList<>();

        for (int t = 0; t < bips.size(); t++) {

            Problemsl.add(mp.getProblemBip(bips.get(t).getCodeP()));

        }

        bipso = FXCollections.observableArrayList(Problemsl);
        table_bip.setItems(bipso);

    }

    private FileInputStream fis;
    private BufferedInputStream bfs;
    Player player;

    public void play(Bip bip) {
        if (bip != null) {
            String uriString = new File(bip.getAudio()).toURI().toString();
            MediaPlayer player = new MediaPlayer(new Media(uriString));
            player.play();
        }

    }

 

    @FXML
    void back(ActionEvent event) {
        screenPage.setScreen(Main.screen1ID);
    }

    ScreensController screenPage;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screenPage = screenPage;
    }
}
