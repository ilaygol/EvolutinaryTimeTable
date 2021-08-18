package Application;

import Manager.LogicEngineManager;
import Tasks.LoadFileTask;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;

public class ApplicationController {
    private Stage m_Stage;
    private LogicEngineManager m_Engine;
    private Task<Boolean> m_Task;

    @FXML private Label filePathLabel;
    @FXML private Button loadFileBtn;
    @FXML private TextField numOfGenTF;
    @FXML private TextField showEveryTF;
    @FXML private ComboBox<String> selectionCombo;
    @FXML private ComboBox<String> crossoverCombo;
    @FXML private TextField cuttingPointsTF;
    @FXML private ComboBox<String> crossoverAspectCombo;
    @FXML private ComboBox<String> mutationCombo;
    @FXML private CheckBox fitnessCheck;
    @FXML private ComboBox<Integer> fitnessLimitCombo;
    @FXML private CheckBox timeCheck;
    @FXML private TextField timeLimitTF;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button stopBtn;
    @FXML private Label statusLineLabel;
    @FXML private ProgressBar fitnessProgress;
    @FXML private ProgressBar generationsProgress;
    @FXML private ProgressBar timeProgress;
    @FXML private ComboBox<String> showValueCombo;
    @FXML private Button submitShowValueBtn;

    private SimpleBooleanProperty isFileSelected;
    private SimpleBooleanProperty IsActivatedAlgo;

    public ApplicationController()
    {
        isFileSelected=new SimpleBooleanProperty(false);
        IsActivatedAlgo=new SimpleBooleanProperty(false);
    }

    @FXML
    private void initialize() {
        ///////////////////will delete soon//////////////////////////////
        stopBtn.setDisable(true);
        pauseBtn.setDisable(true);
        startBtn.setDisable(true);
        fitnessCheck.disableProperty().bind(isFileSelected.not());
        timeCheck.disableProperty().bind(isFileSelected.not());
        crossoverCombo.disableProperty().bind(isFileSelected.not());
        mutationCombo.disableProperty().bind(isFileSelected.not());
        selectionCombo.disableProperty().bind(isFileSelected.not());
        showValueCombo.disableProperty().bind(isFileSelected.not());
        submitShowValueBtn.disableProperty().bind(IsActivatedAlgo.not());
        filePathLabel.setText("");
        ////////////////////////////////////////////////////////////////////
        fillComboBoxes();


    }


    @FXML
    void onLoadFileClick(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file=fileChooser.showOpenDialog(m_Stage);
        if(file==null) {
            return;
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load File");
        alert.setHeaderText("Loading File....");
        m_Task=new LoadFileTask(m_Engine,file,alert);
        bindFileTaskToUIComponents(file,m_Task,alert);
        alert.show();
        new Thread(m_Task).start();
    }

    public void bindFileTaskToUIComponents(File i_File,Task<Boolean> i_Task,Alert i_Alert) {
        i_Alert.contentTextProperty().bind(i_Task.messageProperty());

        if(!isFileSelected.get()) {
            i_Task.valueProperty().addListener((observable, oldVal, newVal) ->{
                    isFileSelected.set(newVal);
                    if(newVal.booleanValue())
                        startBtn.setDisable(false);
            });

        }
        i_Task.valueProperty().addListener(((observable, oldValue, newValue) -> {
           if(newValue.booleanValue()) {
               filePathLabel.setText(i_File.getAbsolutePath());
           }
        }));
    }

    @FXML
    void onPauseBtnClick(ActionEvent event) {
        startBtn.setDisable(false);
        pauseBtn.setDisable(true);
    }

    @FXML
    void onStartBtnClick(ActionEvent event) {
        pauseBtn.setDisable(false);
        stopBtn.setDisable(false);
        startBtn.setDisable(true);
    }

    @FXML
    void onStopBtnClick(ActionEvent event) {
        stopBtn.setDisable(true);
        pauseBtn.setDisable(true);
        startBtn.setDisable(false);
    }

    @FXML
    void onSubmitShowValueClick(ActionEvent event) {

    }

    public void setEngine(LogicEngineManager i_Engine) {
        m_Engine = i_Engine;
    }

    public void setStage(Stage i_Stage) {
        this.m_Stage = i_Stage;
    }

    @FXML
    void onActionFitnessCB(ActionEvent event) {
        fitnessLimitCombo.setDisable(!fitnessCheck.isSelected());
    }

    @FXML
    void onActionTimeCB(ActionEvent event) {
        timeLimitCombo.setDisable(!timeCheck.isSelected());
    }

    private void fillComboBoxes()
    {
        //filling showValuesCombo
        for(eResultsValues value:eResultsValues.values())
        {
            showValueCombo.getItems().add(value.toString());
        }
        //filling Fitness Compo
        for(int i=1;i<=100;i++)
        {
            fitnessLimitCombo.getItems().add(i);
        }

    }
}
