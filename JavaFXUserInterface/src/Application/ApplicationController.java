package Application;

import DataTransferClasses.DataPrinter;
import DataTransferClasses.MutationData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationController {
    private Stage m_Stage;
    private LogicEngineManager m_Engine;
    private DataPrinter m_FileDataPrinter;
    private DataPrinter m_UpdatedDataPrinter;
    private Task<Boolean> m_Task;
    private Thread m_AlgoThread;
    private ValuesChecker m_ValuesChecker;
    private Integer m_ReqGenerations;
    private Integer m_ReqPrinting;
    private Integer m_ReqFitness;
    private Integer m_reqTimeInMinutes;

    @FXML private Label filePathLabel;
    @FXML private Label mutationUpdateStatusLabel;
    @FXML private Label statusLineLabel;
    @FXML private Label pauseStatusLabel;
    @FXML private Button loadFileBtn;
    @FXML private Button mutationSetBtn;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button stopBtn;
    @FXML private Button submitShowValueBtn;
    @FXML private TextField timeLimitTF;
    @FXML private TextField numOfGenTF;
    @FXML private TextField elitismSliderReflectionTF;
    @FXML private TextField cuttingPointsTF;
    @FXML private TextField tupplesTF;
    @FXML private TextField showEveryTF;
    @FXML private CheckBox fitnessCheck;
    @FXML private CheckBox timeCheck;
    @FXML private CheckBox generationsCheck;
    @FXML private ComboBox<Integer> fitnessLimitCombo;
    @FXML private ComboBox<String> selectionCombo;
    @FXML private ComboBox<String> crossoverCombo;
    @FXML private ComboBox<String> crossoverAspectCombo;
    @FXML private ComboBox<String> mutationCombo;
    @FXML private ComboBox<String> componentCombo;
    @FXML private ComboBox<Double> probabilityCombo;
    @FXML private ComboBox<String> showValueCombo;
    @FXML private ComboBox<Integer> selectionPercentCombo;
    @FXML private ComboBox<Double> selectionPTECombo;
    @FXML private ProgressBar fitnessProgress;
    @FXML private ProgressBar generationsProgress;
    @FXML private ProgressBar timeProgress;
    @FXML private Slider elitismSlider;

    private SimpleBooleanProperty isFileSelected;
    private SimpleBooleanProperty isActivatedAlgo;
    private Boolean isPaused=false;
    private Boolean stoppedByUser=false;

    public ApplicationController()
    {
        isFileSelected=new SimpleBooleanProperty(false);
        isActivatedAlgo=new SimpleBooleanProperty(false);
        m_ValuesChecker=new ValuesChecker();
    }

    @FXML
    private void initialize() {
        filePathLabel.setText("");
        numOfGenTF.textProperty().addListener((observable, oldValue, newValue) ->
            m_ValuesChecker.checkNumOfGenerations(numOfGenTF));
        timeLimitTF.textProperty().addListener((observable, oldValue, newValue) ->
                m_ValuesChecker.checkTime(timeLimitTF));
        tupplesTF.textProperty().addListener((a, b, c) ->
                m_ValuesChecker.checkTupples(tupplesTF));
        showEveryTF.textProperty().addListener((observable, oldValue, newValue) ->
                m_ValuesChecker.checkShowEvery(showEveryTF));
        elitismSlider.valueProperty().addListener((observable, oldValue, newValue) ->elitismSliderReflectionTF.setText(String.valueOf(newValue.intValue())));
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
        if(isPaused)
        {
            m_Engine.resumeAlgo();
            isPaused=false;
        }
        else {
            //checking values
            resetProgressBars();
            List<eStoppingCondition> stoppingConditions = createStoppingConditions();
            m_ReqPrinting = Integer.parseInt(showEveryTF.getText());
            //bind the progress bars
            m_Task = new ActivateAlgoTask(this::updateUIFromAlgoProgress, stoppingConditions, m_Engine, m_ReqGenerations, m_ReqPrinting, m_ReqFitness, m_reqTimeInMinutes);
            bindAlgoTaskToUIComponents(m_Task);
            m_AlgoThread = new Thread(m_Task);
            m_AlgoThread.start();
        }
        stoppedByUser=false;
        disabilityManagementPlay();
    }
    @FXML void onPauseBtnClick(ActionEvent event) {
        disabilityManagementPause();
        m_AlgoThread.interrupt();
        isPaused=true;

    }
    @FXML void onStopBtnClick(ActionEvent event) {
        stoppedByUser=true;
        m_Engine.setStopBoolean(true);
        if(isPaused=true) {
            m_Engine.resumeAlgo();
            isPaused = false;
        }
    }
    @FXML void onSubmitShowValueClick(ActionEvent event) { }
    @FXML void onActionFitnessCB(ActionEvent event) {
        fitnessLimitCombo.setDisable(!fitnessCheck.isSelected());
    }
    @FXML void onActionTimeCB(ActionEvent event) {
        timeLimitTF.setDisable(!timeCheck.isSelected());
    }
    @FXML void onActionGenerationsCB(ActionEvent event) {
        numOfGenTF.setDisable(!generationsCheck.isSelected());
    }
    @FXML void onMutationSetBtnClick(ActionEvent event) {

        if(mutationCombo.getValue()!=null) {
            //checks
            String mutationString = mutationCombo.getValue();
            MutationData mutationData = m_Engine.getSpecificMutation(mutationString);
            if(probabilityCombo.getValue()==null && tupplesTF.getText().equals("")&& componentCombo.getValue()==null)
                mutationUpdateStatusLabel.setText("Please pick changes.");
            else
            {
                if(probabilityCombo.getValue()!=null) {
                    //double probability=probabilityCombo.getValue();
                    //mutationData.setProbability(probability);
                }
                if(tupplesTF.getText()!="")
                    mutationData.setTupples(Integer.parseInt(tupplesTF.getText()));
                if(componentCombo.getValue()!=null)
                    mutationData.setComponent(componentCombo.getValue().charAt(0));
                m_Engine.setSpecificMutationSettings(mutationString,mutationData);
                mutationCombo.getItems().removeAll();
                mutationUpdateStatusLabel.setText("changes Saved.");
            }

        }
        else
            mutationUpdateStatusLabel.setText("Please pick Mutation.");
    }
    @FXML void onCrossoverComboChanged(ActionEvent event) {

    }
    @FXML void onMutationComboChanged(ActionEvent event) {

    }
    @FXML void onSelectionComboChanged(ActionEvent event) {

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
                m_FileDataPrinter=m_Engine.getFileData();
                cuttingPointsTF.textProperty().addListener((a, b, c) ->
                        m_ValuesChecker.checkCuttingPoints(cuttingPointsTF, m_Engine.getMaxLessons()));
                fillComboBoxes();
                disabilityManagementFileLoaded();
            }
        }));
    }
    public void bindAlgoTaskToUIComponents(Task<Boolean> i_Task) {
        i_Task.valueProperty().addListener((observable, oldVal, newVal) ->{
            isActivatedAlgo.set(newVal);
            disabilityManagementStop();
        } );
        statusLineLabel.textProperty().bind(i_Task.messageProperty());
    }

    private void disabilityManagementStop() {
        disabilityManagementFileLoaded();
        if(!stoppedByUser) {
            pauseStatusLabel.setText("Done");
        }
        else{
            pauseStatusLabel.setText("Stopped");
        }
        pauseStatusLabel.setVisible(true);
        loadFileBtn.setDisable(false);
    }
    private void disabilityManagementPause() {
        disabilityManagementFileLoaded();
        stopBtn.setDisable(false);
        pauseStatusLabel.setText("Paused");
        pauseStatusLabel.setVisible(true);
    }
    private void disabilityManagementPlay() {
        //File
        loadFileBtn.setDisable(true);

        //Controls
        startBtn.setDisable(true);
        pauseBtn.setDisable(false);
        stopBtn.setDisable(false);
        showEveryTF.setDisable(true);
        submitShowValueBtn.setDisable(true);
        showValueCombo.setDisable(true);
        pauseStatusLabel.setVisible(false);

        //Stop conditions
        generationsCheck.setDisable(true);
        timeCheck.setDisable(true);
        fitnessCheck.setDisable(true);

        //Selection
        selectionCombo.setDisable(true);
        selectionPercentCombo.setDisable(true);
        elitismSlider.setDisable(true);
        selectionPTECombo.setDisable(true);

        //Crossover
        crossoverCombo.setDisable(true);
        cuttingPointsTF.setDisable(true);
        crossoverAspectCombo.setDisable(true);

        //Mutation
        mutationCombo.setDisable(true);
        probabilityCombo.setDisable(true);
        tupplesTF.setDisable(true);
        mutationSetBtn.setDisable(true);
    }
    private void disabilityManagementFileLoaded() {
        //Controls
        startBtn.setDisable(false);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);
        showEveryTF.setDisable(false);
        submitShowValueBtn.setDisable(false);
        showValueCombo.setDisable(false);
        pauseStatusLabel.setVisible(false);

        //Stop conditions
        generationsCheck.setDisable(false);
        timeCheck.setDisable(false);
        fitnessCheck.setDisable(false);

        //Selection
        selectionCombo.setDisable(false);
        elitismSlider.setDisable(false);

        //Crossover
        crossoverCombo.setDisable(false);
        cuttingPointsTF.setDisable(false);

        //Mutation
        mutationCombo.setDisable(false);
        probabilityCombo.setDisable(false);
        tupplesTF.setDisable(false);
        mutationSetBtn.setDisable(false);
    }

    private void fillComboBoxes()
    {
        ArgumentsFiller filler=new ArgumentsFiller(m_FileDataPrinter);
        //Fitness filler
        filler.setFitnessCombo(fitnessLimitCombo);

        //Show values filler
        filler.setShowValuesCombo(showValueCombo);

        //Selection fillers
        filler.setSelectionTypeCombo(selectionCombo);
        filler.setSelectionTopPercentCombo(selectionPercentCombo);
        filler.setSelectionElitismSliderMax(elitismSlider);
        filler.setSelectionPTECombo(selectionPTECombo);

        //Crossover fillers
        filler.setCrossoverTypeCombo(crossoverCombo);
        filler.setCrossoverAspectCombo(crossoverAspectCombo);
        filler.setCrossoverCuttingPoints(cuttingPointsTF);

        //Mutation fillers
        filler.setMutationTypeCombo(mutationCombo);
        filler.setMutationProbabilityCombo(probabilityCombo);
        filler.setMutationTupples(tupplesTF);
        filler.setMutationComponentCombo(componentCombo);
    }

    public void updateUIFromAlgoProgress(ProgressData i_Progress)
    {
        double generation,fitness,time;
        if(generationsCheck.isSelected()) {
            generation = ((double) i_Progress.getGeneration() / (double) m_ReqGenerations);
            generationsProgress.setProgress(generation);
        }
        if(fitnessCheck.isSelected())
        {
            fitness=(double) i_Progress.getFitness()/(double) m_ReqFitness;
            fitnessProgress.setProgress(fitness);
        }
        if(timeCheck.isSelected())
        {
            Long totalTimeInMillis= TimeUnit.MINUTES.toMillis(m_reqTimeInMinutes);
            time=(double) i_Progress.getTimePassedInMillis()/(double) totalTimeInMillis;
            timeProgress.setProgress(time);
        }
    }

    private List<eStoppingCondition> createStoppingConditions()
    {
        List<eStoppingCondition> stoppingConditionList=new ArrayList<>();

        if(generationsCheck.isSelected()) {
            stoppingConditionList.add(eStoppingCondition.GENERATIONS);
            m_ReqGenerations=Integer.parseInt(numOfGenTF.getText());
        }
        else
        {
            m_ReqGenerations=0;
        }
        if(timeCheck.isSelected()) {
            stoppingConditionList.add(eStoppingCondition.TIME);
            m_reqTimeInMinutes=Integer.parseInt(timeLimitTF.getText());
        }
        else
        {
            m_reqTimeInMinutes=0;
        }
        if(fitnessCheck.isSelected()) {
            stoppingConditionList.add(eStoppingCondition.FITNESS);
            m_ReqFitness=fitnessLimitCombo.getValue();
        }
        else
        {
            m_ReqFitness=0;
        }
        return stoppingConditionList;
    }

    private void resetProgressBars()
    {
        timeProgress.setProgress(0);
        fitnessProgress.setProgress(0);
        generationsProgress.setProgress(0);
    }
}
