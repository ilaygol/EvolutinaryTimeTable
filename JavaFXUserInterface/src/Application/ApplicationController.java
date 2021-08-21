package Application;

import DataTransferClasses.ProgressData;
import Manager.LogicEngineManager;
import Tasks.ActivateAlgoTask;
import Tasks.LoadFileTask;
import com.sun.javaws.IconUtil;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import AlgorithmClasses.*;
import java.io.File;

public class ApplicationController {
    private Stage m_Stage;
    private LogicEngineManager m_Engine;
    private Task<Boolean> m_Task;
    private ProgressData m_ProgressInEngine;
    private ValuesChecker m_ValuesChecker;

    @FXML private Label filePathLabel;
    @FXML private Label statusLineLabel;
    @FXML private TextField numOfGenTF;
    @FXML private TextField showEveryTF;
    @FXML private TextField cuttingPointsTF;
    @FXML private TextField timeLimitTF;
    @FXML private Button loadFileBtn;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button stopBtn;
    @FXML private Button submitShowValueBtn;
    @FXML private CheckBox timeCheck;
    @FXML private CheckBox fitnessCheck;
    @FXML private ComboBox<String> selectionCombo;
    @FXML private ComboBox<String> crossoverCombo;
    @FXML private ComboBox<String> crossoverAspectCombo;
    @FXML private ComboBox<String> mutationCombo;
    @FXML private ComboBox<Integer> elitismCB;
    @FXML private ComboBox<Integer> fitnessLimitCombo;
    @FXML private ComboBox<String> showValueCombo;

    @FXML private ProgressBar fitnessProgress;
    @FXML private ProgressBar generationsProgress;
    @FXML private ProgressBar timeProgress;

    private SimpleBooleanProperty isFileSelected;
    private SimpleBooleanProperty IsActivatedAlgo;

    public ApplicationController()
    {
        isFileSelected=new SimpleBooleanProperty(false);
        IsActivatedAlgo=new SimpleBooleanProperty(false);
        m_ValuesChecker=new ValuesChecker();
    }

    @FXML
    private void initialize() {
        filePathLabel.setText("");
        numOfGenTF.textProperty().addListener((observable, oldValue, newValue) -> {
            m_ValuesChecker.checkNumOfGenerations(numOfGenTF,newValue);});
    }


    public void setEngine(LogicEngineManager i_Engine) {
        m_Engine = i_Engine;
    }
    public void setStage(Stage i_Stage) {
        this.m_Stage = i_Stage;
    }

    @FXML void onLoadFileClick(ActionEvent event) {
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
    @FXML void onStartBtnClick(ActionEvent event) {

        //m_Task=new ActivateAlgoTask();
        disabilityManagementPlay();
    }
    @FXML void onPauseBtnClick(ActionEvent event) {
        disabilityManagementPause();
    }
    @FXML void onStopBtnClick(ActionEvent event) {
        disabilityManagementStop();
    }
    @FXML void onSubmitShowValueClick(ActionEvent event) { }
    @FXML void onActionFitnessCB(ActionEvent event) {
        fitnessLimitCombo.setDisable(!fitnessCheck.isSelected());
    }
    @FXML void onActionTimeCB(ActionEvent event) {
        timeLimitTF.setDisable(!timeCheck.isSelected());
    }

    public void bindFileTaskToUIComponents(File i_File,Task<Boolean> i_Task,Alert i_Alert) {
        i_Alert.contentTextProperty().bind(i_Task.messageProperty());

        if(!isFileSelected.get()) {
            i_Task.valueProperty().addListener((observable, oldVal, newVal) ->{
                isFileSelected.set(newVal);
            });

        }
        i_Task.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue.booleanValue()) {
                filePathLabel.setText(i_File.getAbsolutePath());
                fillComboBoxes();
                disabilityManagementFileLoaded();
            }
        }));
    }
    private void disabilityManagementStop() {
        startBtn.setDisable(false);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);
        crossoverCombo.setDisable(false);
        cuttingPointsTF.setDisable(false);
        numOfGenTF.setDisable(false);
        showEveryTF.setDisable(true);
        selectionCombo.setDisable(false);
        mutationCombo.setDisable(false);
        timeCheck.setDisable(false);
        fitnessCheck.setDisable(false);
        elitismCB.setDisable(false);
        loadFileBtn.setDisable(false);
    }
    private void disabilityManagementPause() {
        startBtn.setDisable(false);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);
        crossoverCombo.setDisable(false);
        cuttingPointsTF.setDisable(false);
        numOfGenTF.setDisable(false);
        showEveryTF.setDisable(true);
        selectionCombo.setDisable(false);
        mutationCombo.setDisable(false);
        timeCheck.setDisable(false);
        fitnessCheck.setDisable(false);
        elitismCB.setDisable(false);
        stopBtn.setDisable(false);
    }
    private void disabilityManagementPlay() {
        loadFileBtn.setDisable(true);
        pauseBtn.setDisable(false);
        stopBtn.setDisable(false);
        numOfGenTF.setDisable(true);
        showEveryTF.setDisable(true);
        elitismCB.setDisable(true);
        crossoverCombo.setDisable(true);
        cuttingPointsTF.setDisable(true);
        mutationCombo.setDisable(true);
        timeCheck.setDisable(true);
        fitnessCheck.setDisable(true);
        selectionCombo.setDisable(true);
        startBtn.setDisable(true);
    }
    private void disabilityManagementFileLoaded() {
        startBtn.setDisable(false);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);
        crossoverCombo.setDisable(false);
        cuttingPointsTF.setDisable(false);
        numOfGenTF.setDisable(false);
        showEveryTF.setDisable(true);
        selectionCombo.setDisable(false);
        mutationCombo.setDisable(false);
        timeCheck.setDisable(false);
        fitnessCheck.setDisable(false);
        elitismCB.setDisable(false);
        submitShowValueBtn.setDisable(false);
        showValueCombo.setDisable(false);

    }
    private void fillComboBoxes()
    {
        //filling showValuesCombo
        for(eResultsValues value:eResultsValues.values())
        {
            showValueCombo.getItems().add(value.toString());
        }
        //filling crossover combo
        for(eCrossover ec:eCrossover.values())
            crossoverCombo.getItems().add(ec.toString());
        //filling crossover aspect oriented combo
        crossoverAspectCombo.getItems().add("Teacher");
        crossoverAspectCombo.getItems().add("Class");
        //filling selection combo
        for(eSelection es:eSelection.values())
            selectionCombo.getItems().add(es.toString());
        //filling mutation Combo
        for(Mutation m:m_Engine.getMutationsList())
        {
            mutationCombo.getItems().add(m.getEType().toString(m.getMaxTupples(),m.getChar()));
        }

        //filling elitism
        for(int i=0;i<m_Engine.getInitialPopulation();i++)
        {
            elitismCB.getItems().add(i);
        }

        //filling Fitness Combo
        for(int i=1;i<=100;i++)
        {
            fitnessLimitCombo.getItems().add(i);
        }

    }
}
