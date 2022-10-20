package ihController;

import ihDataModel.AktywoPL;
import ihDataModel.AktywoUSA;
import ihFileOperations.CSV_FileReader;
import ihFileOperations.CSV_FileWriter;
import ihFileOperations.HashMapFromArrayList;
import ihFileOperations.HashMapParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class IHController {

    @FXML
    private Button appFolderButton;

    @FXML
    private TextField appFolderTextField;

    @FXML
    private Button daneFolderButton;

    @FXML
    private TextField daneFolderTextField;

    @FXML
    private Button etf_appButton;

    @FXML
    private TextField etf_daneFolderTextField;

    @FXML
    private TextField etf_historiaFolderTextField;

    @FXML
    private Button eu_appButton;

    @FXML
    private TextField eu_daneFolderTextField;

    @FXML
    private TextField eu_historiaFolderTextField;

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
    private Button mainAppFolderButton;

    @FXML
    private TextField mainFolderTextField;

    @FXML
    private TextField mainLinkiAktywaFolderTextField;

    @FXML
    private TextField mainLoginFolderTextField;

    @FXML
    private Button pl_appButton;

    @FXML
    private TextField pl_daneFolderTextField;

    @FXML
    private TextField pl_historiaFolderTextField;

    @FXML
    private ProgressBar progresProgressBar;

    @FXML
    private ProgressBar progresProgressBar1;

    @FXML
    private Label rightStatusLabel;

    @FXML
    private Button runAllButton;

    @FXML
    private Button us_appButton;

    @FXML
    private TextField us_daneFolderTextField;

    @FXML
    private TextField us_historiaFolderTextField;





    public void initialize() {
        configureButtons();
        leftStatusLabel.setText("v 2.3");
    }

    private void configureButtons() {

        setPathsToFiles("");

        mainAppFolderButton.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            File file = dc.showDialog((new Stage()));
            try {
                setPathsToFiles(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace(); //ignore
            }});

        pl_appButton.setOnAction(event -> {

            try {
                ArrayList arrayList_Linki_Aktywa = CSV_FileReader.LinesToArrayList(mainLinkiAktywaFolderTextField.getText());
                HashMap<String, AktywoPL> hashMap_Aktywa = HashMapFromArrayList.aktywaHashMapFromArrayList(arrayList_Linki_Aktywa,"PL");

                ArrayList arrayList_Loginy = CSV_FileReader.LinesToArrayList(mainLoginFolderTextField.getText());
                HashMap<String, String[]> hashMap_Loginy = HashMapFromArrayList.loginHashMapArrayList(arrayList_Loginy);

                HashMapParser.updateAktywaPL(hashMap_Aktywa,hashMap_Loginy);
                CSV_FileWriter.HashMap2File(hashMap_Aktywa,pl_daneFolderTextField.getText(),AktywoPL.toCSVStringHeaders(),false);
                CSV_FileWriter.HashMap2File(hashMap_Aktywa,pl_historiaFolderTextField.getText(),AktywoPL.toCSVStringHeaders(),true);

            } catch (IOException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
            progresProgressBar1.setProgress(1.0);
        });

        us_appButton.setOnAction(event -> {

            try {
                ArrayList arrayList_Linki_Aktywa = CSV_FileReader.LinesToArrayList(mainLinkiAktywaFolderTextField.getText());
                HashMap<String, AktywoUSA> hashMap_Aktywa = HashMapFromArrayList.aktywaHashMapFromArrayList(arrayList_Linki_Aktywa,"US");

                HashMapParser.updateAktywaUSA(hashMap_Aktywa);
                CSV_FileWriter.HashMap2File(hashMap_Aktywa,us_daneFolderTextField.getText(),AktywoUSA.toCSVStringHeaders(),false);
                CSV_FileWriter.HashMap2File(hashMap_Aktywa,us_historiaFolderTextField.getText(),AktywoUSA.toCSVStringHeaders(),true);

            } catch (IOException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
            progresProgressBar1.setProgress(1.0);
        });

        runAllButton.setOnAction(event -> {
        //           progresProgressBar.setProgress(0.10);
            try {
                ArrayList arrayList_Linki_Aktywa = CSV_FileReader.LinesToArrayList(mainLinkiAktywaFolderTextField.getText());
                HashMap<String, AktywoPL> hashMap_AktywaPL = HashMapFromArrayList.aktywaHashMapFromArrayList(arrayList_Linki_Aktywa,"PL");
                HashMap<String, AktywoUSA> hashMap_AktywaUSA = HashMapFromArrayList.aktywaHashMapFromArrayList(arrayList_Linki_Aktywa,"US");

                ArrayList arrayList_Loginy = CSV_FileReader.LinesToArrayList(mainLoginFolderTextField.getText());
                    HashMap<String, String[]> hashMap_Loginy = HashMapFromArrayList.loginHashMapArrayList(arrayList_Loginy);

                HashMapParser.updateAktywaPL(hashMap_AktywaPL,hashMap_Loginy);
                    CSV_FileWriter.HashMap2File(hashMap_AktywaPL,pl_daneFolderTextField.getText(),AktywoPL.toCSVStringHeaders(),false);
                    CSV_FileWriter.HashMap2File(hashMap_AktywaPL,pl_historiaFolderTextField.getText(),AktywoPL.toCSVStringHeaders(),true);

                HashMapParser.updateAktywaUSA(hashMap_AktywaUSA);
                    CSV_FileWriter.HashMap2File(hashMap_AktywaUSA,us_daneFolderTextField.getText(),AktywoUSA.toCSVStringHeaders(),false);
                    CSV_FileWriter.HashMap2File(hashMap_AktywaUSA,us_historiaFolderTextField.getText(),AktywoUSA.toCSVStringHeaders(),true);

        } catch (IOException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
            progresProgressBar1.setProgress(1.0);
        });
    }

    private void setPathsToFiles(String basePath) {

        String currentFolderPath;

        if (basePath == "")
            currentFolderPath = System.getProperty("user.dir");
        else
            currentFolderPath = basePath;

        mainFolderTextField.setText(currentFolderPath);
        mainLinkiAktywaFolderTextField.setText(currentFolderPath + "\\Settings\\Linki_aktywa.csv");
        mainLoginFolderTextField.setText(currentFolderPath + "\\Settings\\Login.csv");
        pl_daneFolderTextField.setText(currentFolderPath + "\\Data\\Dane_AktywaPL.csv");
        pl_historiaFolderTextField.setText(currentFolderPath + "\\Data\\Historia_AktywaPL.csv");
        us_daneFolderTextField.setText(currentFolderPath + "\\Data\\Dane_AktywaUS.csv");
        us_historiaFolderTextField .setText(currentFolderPath + "\\Data\\Historia_AktywaUS.csv");
        eu_daneFolderTextField.setText("");
        eu_historiaFolderTextField.setText("");
        etf_daneFolderTextField.setText("");
        etf_historiaFolderTextField.setText("");
    }
}
