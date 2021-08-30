package BestSolutionPrinter;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataTransferClasses.BestSolutionsData;
import DataTransferClasses.LessonData;
import DataTransferClasses.RuleData;
import RulesPrinter.RuleController;
import RulesPrinter.RulesPrinter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

public class BestSolutionController {

    private BestSolutionsData m_BestSolutionData;
    private AmountOfObjectsCalc m_MaxAmounts;
    private ScrollPane m_DynamicRoot;
    private ScrollPane m_RulesRoot;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private ListView<String> valuesListView;

    @FXML
    private void initialize()
    {
        valuesListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> printBestSolution(newValue)));
    }

    public void setView(ScrollPane i_DynamicRoot, ScrollPane i_RulesRoot, BestSolutionsData i_BestSolutionData, AmountOfObjectsCalc i_MaxAmounts)
    {
        m_DynamicRoot=i_DynamicRoot;
        m_RulesRoot=i_RulesRoot;
        m_BestSolutionData=i_BestSolutionData;
        m_MaxAmounts=i_MaxAmounts;
    }

    public void printByRaw()
    {
        valuesListView.getItems().clear();
        FlowPane flowPane=new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        m_BestSolutionData.getLessonsDataList().sort(Comparator
                .comparing(LessonData::getDay)
                .thenComparing(LessonData::getHour)
                .thenComparing(LessonData::getClassID)
                .thenComparing(LessonData::getTeacherID));
        for(LessonData lesson:m_BestSolutionData.getLessonsDataList())
        {
            flowPane.getChildren().add(createNewLessonComponent(lesson));
        }
        m_DynamicRoot.setContent(flowPane);
        List<RuleData> rulesDataList = m_BestSolutionData.getRulesDataList();
        RulesPrinter rulesPrinter=new RulesPrinter(rulesDataList,m_RulesRoot);
        rulesPrinter.showRules();
    }

    private StackPane createNewLessonComponent(LessonData i_LessonData)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("LessonComponent.fxml");
        fxmlLoader.setLocation(url);
        StackPane root = null;
        try {
            root = fxmlLoader.load(url.openStream());
            LessonController controller = (LessonController) fxmlLoader.getController();
            controller.setDayText(i_LessonData.getDay().toString());
            controller.setHourText(i_LessonData.getHour().toString());
            controller.setClassText(i_LessonData.getClassID().toString());
            controller.setTeacherText(i_LessonData.getTeacherID().toString());
            controller.setSubjectText(i_LessonData.getSubjectID().toString());
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public void setValuesListView()
//    {
//        valuesListView.getItems().clear();
//        m_BestSolutionData.
//        valuesListView.getItems().add("Subjects");
//        valuesListView.getItems().add("Teachers");
//        valuesListView.getItems().add("Classes");
//        valuesListView.getItems().add("Rules");
//        valuesListView.getItems().add("Algorithm Preferences");
//    }

    private void printBestSolution(String i_Choice)
    {

    }
}
