package bfiController;

import bfiDataModel.AktywoPL;
import bfiFileOperations.CSV_FileReader;
import bfiFileOperations.CSV_FileWriter;
import bfiFileOperations.HashMapFromArrayList;
import bfiFileOperations.HashMapParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class BfiController {

    @FXML
    private Button appFolderButton;

    @FXML
    private TextField appFolderTextField;

    @FXML
    private Button daneFolderButton;

    @FXML
    private TextField daneFolderTextField;

    @FXML
    private Button generujButton;

    @FXML
    private Button historiaFolderButton;

    @FXML
    private TextField historiaFolderTextField;

    @FXML
    private Label leftStatusLabel;

    @FXML
    private Button linkiAktywaPLFolderButton;

    @FXML
    private TextField linkiAktywaPLFolderTextField;

    @FXML
    private Button loginFolderButton;

    @FXML
    private TextField loginFolderTextField;

    @FXML
    private Label rightStatusLabel;

    @FXML
    private ProgressBar progresProgressBar;


    public void initialize() {
        configureButtons();
    }

    private void configureButtons() {

         setPathsToFiles("");

        appFolderButton.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            File file = dc.showDialog((new Stage()));
            try {
                appFolderTextField.setText(file.getAbsolutePath());
                setPathsToFiles(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace(); //ignore
            }
        });

        linkiAktywaPLFolderButton.setOnAction(event -> updateFilePath(linkiAktywaPLFolderTextField));
        loginFolderButton.setOnAction(event -> updateFilePath(loginFolderTextField));
        daneFolderButton.setOnAction(event -> updateFilePath(daneFolderTextField));
        historiaFolderButton.setOnAction(event -> updateFilePath(historiaFolderTextField));

        generujButton.setOnAction(event -> {
            progresProgressBar.setProgress(0.10);
            try {
                ArrayList arrayList_Linki_AktywaPL = CSV_FileReader.LinesToArrayList(linkiAktywaPLFolderTextField.getText());
                HashMap<String, AktywoPL> hashMap_AktywaPL = HashMapFromArrayList.hashMapLinkiAktywaPL(arrayList_Linki_AktywaPL);

                ArrayList arrayList_Loginy = CSV_FileReader.LinesToArrayList(loginFolderTextField.getText());
                HashMap<String, String[]> hashMap_Loginy = HashMapFromArrayList.hashMapLoginDetails(arrayList_Loginy);

                HashMapParser.updateAktywaPL(hashMap_AktywaPL,hashMap_Loginy);
                    progresProgressBar.setProgress(0.50);

                CSV_FileWriter.HashMap2File(hashMap_AktywaPL,daneFolderTextField.getText(),true,AktywoPL.toCSVStringHeaders(),false);

                CSV_FileWriter.HashMap2File(hashMap_AktywaPL,historiaFolderTextField.getText(),false,"",true);
                    progresProgressBar.setProgress(1.0);

        } catch (IOException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }});
    }

    private void updateFilePath(TextField txtField) {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
            File file = fc.showOpenDialog(new Stage());
            try {
                txtField.setText(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace(); //ignore
            }
    }

    private void setPathsToFiles(String basePath) {
        String currentFolder;
        if (basePath == "")
            currentFolder = System.getProperty("user.dir");
        else
            currentFolder = basePath;

        String linkiAktywaPLFilePath = currentFolder + "\\Settings\\Linki_aktywaPL.csv";
        String loginFilePath = currentFolder + "\\Settings\\Login.csv";
        String daneAktywaPLFilePath = currentFolder + "\\Data\\Dane_AktywaPL.csv";
        String historiaAktywaPLFilePath = currentFolder + "\\Data\\Historia_AktywaPL.csv";

        appFolderTextField.setText(currentFolder);
        linkiAktywaPLFolderTextField.setText(linkiAktywaPLFilePath);
        loginFolderTextField.setText(loginFilePath);
        daneFolderTextField.setText(daneAktywaPLFilePath);
        historiaFolderTextField.setText(historiaAktywaPLFilePath);
    }
}
