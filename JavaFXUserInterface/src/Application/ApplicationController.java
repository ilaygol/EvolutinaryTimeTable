package Application;

import AlgorithmClasses.eStoppingCondition;
import DataTransferClasses.*;
import FilePrinter.FilePrinterController;
import Manager.LogicEngineManager;
import Tasks.ActivateAlgoTask;
import Tasks.LoadFileTask;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationController {
    private Stage m_Stage;
    private LogicEngineManager m_Engine;
    private DataPrinter m_FileDataPrinter;
    private DataPrinter m_UpdatedDataPrinter;
    private ArgumentsFiller m_ArgumentsFiller;
    private Task<Boolean> m_Task;
    private Thread m_AlgoThread;
    private ValuesChecker m_ValuesChecker;
    private Integer m_ReqGenerations;
    private Integer m_ReqPrinting;
    private Integer m_ReqFitness;
    private Integer m_reqTimeInMinutes;
    private FilePrinterController m_FilePrinterController;

    @FXML private ScrollPane dynamicPane;
    @FXML private ScrollPane dynamicRulesPane;
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
        mutationUpdateStatusLabel.setText("");
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
        alert.setWidth(Control.USE_COMPUTED_SIZE);
        alert.show();
        new Thread(m_Task).start();
    }
    @FXML void onStartBtnClick(ActionEvent event) {
        Boolean isCorrect=checkArgumentsBeforePlay();
        if(isCorrect) {
            updateDataPrinter();
            m_Engine.updateAlgoData(m_UpdatedDataPrinter);
            if (isPaused) {
                m_Engine.resumeAlgo();
                isPaused = false;
            } else {
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
            stoppedByUser = false;
            disabilityManagementPlay();
        }
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
    @FXML void onSubmitShowValueClick(ActionEvent event) {
        if(showValueCombo.getValue()!=null)
        {
            eResultsValues userChoice=eResultsValues.getResultsValueByName(showValueCombo.getValue().toString());
            userChoice.show(m_FileDataPrinter,m_FilePrinterController,dynamicPane);
        }
    }
    @FXML void onActionFitnessCB(ActionEvent event) {
        fitnessLimitCombo.setDisable(!fitnessCheck.isSelected());
        if(!fitnessCheck.isSelected())
        {
            fitnessLimitCombo.getSelectionModel().clearSelection();
        }
    }
    @FXML void onActionTimeCB(ActionEvent event) {
        timeLimitTF.setDisable(!timeCheck.isSelected());
        if(!timeCheck.isSelected())
        {
            timeLimitTF.setText("");
            m_ValuesChecker.changeToDefaultBorder(timeLimitTF);
        }
    }
    @FXML void onActionGenerationsCB(ActionEvent event) {
        numOfGenTF.setDisable(!generationsCheck.isSelected());
        if(!generationsCheck.isSelected())
        {
            numOfGenTF.setText("");
            m_ValuesChecker.changeToDefaultBorder(numOfGenTF);
        }
    }
    @FXML void onMutationSetBtnClick(ActionEvent event) {
        if(mutationCombo.getValue()!=null) {
            Boolean isCorrect = true;
            Integer mutationIndex = Integer.parseInt(String.valueOf(mutationCombo.getValue().charAt(0))) - 1;
            MutationData mutationData = m_UpdatedDataPrinter.getMutationsDataList().get(mutationIndex);
            try {
                m_ValuesChecker.checkMutationArguments(mutationData, probabilityCombo, tupplesTF, componentCombo);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Set mutation error");
                alert.setHeaderText("ERROR!");
                alert.setContentText(e.getMessage());
                alert.setWidth(Control.USE_COMPUTED_SIZE);
                alert.show();
                isCorrect = false;
            }
            if (isCorrect) {
                if(componentCombo.getValue()!=null)
                    mutationData.setComponent(componentCombo.getValue().charAt(0));
                mutationData.setProbability(Double.valueOf(String.valueOf(probabilityCombo.getValue())));
                mutationData.setTupples(Integer.parseInt(tupplesTF.getText()));
                mutationUpdateStatusLabel.setText("Update succeeded");
            }
            else
            {
                mutationUpdateStatusLabel.setText("");
            }
        }
    }
    @FXML void onCrossoverComboChanged(ActionEvent event) {
        if(!crossoverCombo.getItems().isEmpty()) {
            String crossoverName = crossoverCombo.getValue().toUpperCase();
            m_ArgumentsFiller.updateCrossoverAspectCombo(crossoverAspectCombo);
            m_ArgumentsFiller.updateCrossoverCuttingPoints(cuttingPointsTF);
            switch (crossoverName) {
                case "DAYTIMEORIENTED":
                    crossoverAspectCombo.getSelectionModel().clearSelection();
                    crossoverAspectCombo.setDisable(true);
                    break;
                case "ASPECTORIENTED":
                    crossoverAspectCombo.setDisable(false);
                    break;
            }
        }
    }
    @FXML void onMutationComboChanged(ActionEvent event) {
        if(!mutationCombo.getItems().isEmpty()) {
            Integer mutationIndex = Integer.parseInt(String.valueOf(mutationCombo.getValue().charAt(0)))-1;
            MutationData mutationData=m_UpdatedDataPrinter.getMutationsDataList().get(mutationIndex);
            m_ArgumentsFiller.updateMutationProbabilityCombo(probabilityCombo,mutationIndex);
            m_ArgumentsFiller.updateMutationTupples(tupplesTF,mutationIndex);
            switch (mutationData.getName().toUpperCase()) {
                case "FLIPPING":
                    m_ArgumentsFiller.updateMutationComponentCombo(componentCombo,mutationIndex);
                    componentCombo.setDisable(false);
                    break;
                case "SIZER":
                    componentCombo.getSelectionModel().clearSelection();
                    componentCombo.setDisable(true);
                    break;
            }
        }
        mutationUpdateStatusLabel.setText("");
    }
    @FXML void onSelectionComboChanged(ActionEvent event) {
        if(!selectionCombo.getItems().isEmpty()) {
            String selectionName = selectionCombo.getValue().toUpperCase();
            m_ArgumentsFiller.updateSelectionElitismSlider(elitismSlider);
            m_ArgumentsFiller.updateSelectionTopPercentCombo(selectionPercentCombo);
            m_ArgumentsFiller.updateSelectionPTECombo(selectionPTECombo);
            switch (selectionName) {
                case "TRUNCATION":
                    selectionPercentCombo.setDisable(false);
                    selectionPTECombo.getSelectionModel().clearSelection();
                    selectionPTECombo.setDisable(true);
                    break;
                case "ROULETTEWHEEL":
                    selectionPercentCombo.getSelectionModel().clearSelection();
                    selectionPercentCombo.setDisable(true);
                    selectionPTECombo.getSelectionModel().clearSelection();
                    selectionPTECombo.setDisable(true);
                    break;
                case "TOURNAMENT":
                    selectionPercentCombo.getSelectionModel().clearSelection();
                    selectionPercentCombo.setDisable(true);
                    selectionPTECombo.setDisable(false);
                    break;
            }
        }
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
                m_UpdatedDataPrinter=m_Engine.getFileData();
                m_ArgumentsFiller=new ArgumentsFiller(m_UpdatedDataPrinter);
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
        mutationUpdateStatusLabel.setText("");
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
        //Fitness filler
        m_ArgumentsFiller.setFitnessCombo(fitnessLimitCombo);

        //Show values filler
        m_ArgumentsFiller.setShowValuesCombo(showValueCombo);

        //Selection fillers
        m_ArgumentsFiller.setSelectionTypeCombo(selectionCombo);
        m_ArgumentsFiller.setSelectionTopPercentCombo(selectionPercentCombo);
        m_ArgumentsFiller.setSelectionElitismSliderMax(elitismSlider);
        m_ArgumentsFiller.setSelectionPTECombo(selectionPTECombo);

        //Crossover fillers
        m_ArgumentsFiller.setCrossoverTypeCombo(crossoverCombo);
        m_ArgumentsFiller.setCrossoverAspectCombo(crossoverAspectCombo);
        m_ArgumentsFiller.setCrossoverCuttingPoints(cuttingPointsTF);

        //Mutation fillers
        m_ArgumentsFiller.setMutationTypeCombo(mutationCombo);
        m_ArgumentsFiller.setMutationProbabilityCombo(probabilityCombo);
        m_ArgumentsFiller.setMutationTupples(tupplesTF);
        m_ArgumentsFiller.setMutationComponentCombo(componentCombo);
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

    private Boolean checkArgumentsBeforePlay()
    {
        Boolean isCorrect=true;
        try {
            m_ValuesChecker.checkStopConditionsArguments(generationsCheck,numOfGenTF,fitnessCheck,fitnessLimitCombo,timeCheck,timeLimitTF,showEveryTF);
            m_ValuesChecker.checkSelectionArguments(selectionCombo,selectionPercentCombo,selectionPTECombo);
            m_ValuesChecker.checkCrossoverArguments(crossoverCombo, cuttingPointsTF, crossoverAspectCombo, m_Engine.getMaxLessons());
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Arguments error");
            alert.setHeaderText("ERROR!");
            alert.setContentText(e.getMessage());
            alert.setWidth(Control.USE_COMPUTED_SIZE);
            alert.show();
            isCorrect=false;
        }
        return isCorrect;
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

    private void updateDataPrinter()
    {
        //Selection
        SelectionData selectionData=m_UpdatedDataPrinter.getSelectionData();
        selectionData.setType(selectionCombo.getValue().toString());
        selectionData.setElitism(Integer.parseInt(elitismSliderReflectionTF.getText()));
        if(selectionPercentCombo.getValue()!=null)
            selectionData.setPercent(selectionPercentCombo.getValue());
        if(selectionPTECombo.getValue()!=null)
            selectionData.setPTE(selectionPTECombo.getValue());

        //Crossover
        CrossoverData crossoverData=m_UpdatedDataPrinter.getCrossoverData();
        crossoverData.setName(crossoverCombo.getValue());
        if(crossoverAspectCombo.getValue()!=null)
            crossoverData.setAspect(crossoverAspectCombo.getValue().charAt(0));
        crossoverData.setNumOfCuttingPoints(Integer.parseInt(cuttingPointsTF.getText()));
    }

    private void resetProgressBars()
    {
        timeProgress.setProgress(0);
        fitnessProgress.setProgress(0);
        generationsProgress.setProgress(0);
    }

    public void setFilePrinterController(FilePrinterController i_FilePrinterController) {
        this.m_FilePrinterController = i_FilePrinterController;
    }
}
