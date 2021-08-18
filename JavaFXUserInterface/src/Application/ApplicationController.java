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
    @FXML private ComboBox<?> fitnessLimitCombo;
    @FXML private ComboBox<?> timeLimitCombo;
    @FXML private CheckBox fitnessCheck;
    @FXML private CheckBox timeCheck;
    @FXML private ComboBox<?> crossoverCombo;
    @FXML private ComboBox<?> mutationCombo;
    @FXML private ComboBox<?> selectionCombo;
    @FXML private TextField numOfGenTF;
    @FXML private TextField showEveryTF;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button stopBtn;
    @FXML private Label statusLineLabel;
    @FXML private ProgressBar fitnessProgress;
    @FXML private ProgressBar generationsProgress;
    @FXML private ProgressBar timeProgress;
    @FXML private ComboBox<?> showValueCombo;
    @FXML private Button submitShowValueBtn;

    private SimpleBooleanProperty isFileSelected;
    private SimpleStringProperty filePathLabelProperty;
    private SimpleBooleanProperty IsActivatedAlgo;

    public ApplicationController()
    {
        isFileSelected=new SimpleBooleanProperty(false);
        filePathLabelProperty=new SimpleStringProperty("");
        IsActivatedAlgo=new SimpleBooleanProperty(false);
    }

    @FXML
    private void initialize() {
        startBtn.disableProperty().bind(isFileSelected.not());
        pauseBtn.disableProperty().bind(isFileSelected.not());
        stopBtn.disableProperty().bind(isFileSelected.not());
        fitnessCheck.disableProperty().bind(isFileSelected.not());
        timeCheck.disableProperty().bind(isFileSelected.not());
        crossoverCombo.disableProperty().bind(isFileSelected.not());
        mutationCombo.disableProperty().bind(isFileSelected.not());
        selectionCombo.disableProperty().bind(isFileSelected.not());
        showValueCombo.disableProperty().bind(IsActivatedAlgo.not());
        submitShowValueBtn.disableProperty().bind(IsActivatedAlgo.not());
        filePathLabel.textProperty().bind(filePathLabelProperty);
    }

    @FXML
    void onActionFitnessCB(ActionEvent event) {

    }

    @FXML
    void onActionTimeCB(ActionEvent event) {

    }

    @FXML
    void onLoadFileClick(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file=fileChooser.showOpenDialog(m_Stage);
        if(file==null) {
            return;
        }
        m_Task=new LoadFileTask(m_Engine,file);
        bindFileTaskToPathLabel(m_Task);
        new Thread(m_Task).start();
    }

    public void bindFileTaskToPathLabel(Task<Boolean> aTask) {
        filePathLabelProperty.bind(aTask.messageProperty());
        if(!isFileSelected.get()) {
            aTask.valueProperty().addListener((observable, oldVal, newVal) ->
                    isFileSelected.set(newVal));
        }
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

    public void setEngine(LogicEngineManager i_Engine) {
        m_Engine = i_Engine;
    }

    public void setStage(Stage i_Stage) {
        this.m_Stage = i_Stage;
    }
}
