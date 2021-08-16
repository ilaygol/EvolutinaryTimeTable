package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ApplicationController {

    private Stage m_Stage;

    @FXML
    private Label filePathLabel;
    @FXML
    private Button loadFileBtn;
    @FXML
    private ComboBox<?> numOfGenCombo;
    @FXML
    private ComboBox<?> showEveryCombo;
    @FXML
    private ComboBox<?> fitnessLimitCombo;
    @FXML
    private ComboBox<?> generationsLimitCombo;
    @FXML
    private ComboBox<?> timeLimitCombo;
    @FXML
    private CheckBox fitnessCheck;
    @FXML
    private CheckBox generationsCheck;
    @FXML
    private CheckBox timeCheck;
    @FXML
    private ComboBox<?> crossoverCombo;
    @FXML
    private ComboBox<?> mutationCombo;
    @FXML
    private ComboBox<?> selectionCombo;
    @FXML
    private Button startBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button stopBtn;
    @FXML
    private Label statusLineLabel;
    @FXML
    private ProgressBar fitnessProgress;
    @FXML
    private ProgressBar generationsProgress;
    @FXML
    private ProgressBar timeProgress;
    @FXML
    private ComboBox<?> showValueCombo;
    @FXML
    private Button submitShowValueBtn;


    @FXML
    void onLoadFileClick(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(m_Stage);
        filePathLabel.setText(file.getPath());
    }

    @FXML
    void onPauseBtnClick(ActionEvent event) {

    }

    @FXML
    void onStartBtnClick(ActionEvent event) {

    }

    @FXML
    void onStopBtnClick(ActionEvent event) {

    }

    @FXML
    void onSubmitShowValueClick(ActionEvent event) {

    }

    public void setStage(Stage i_Stage) {
        this.m_Stage = i_Stage;
    }
}
