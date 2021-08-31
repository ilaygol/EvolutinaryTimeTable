package Application;

import BestSolutionPrinter.BestSolutionController;
import BestSolutionPrinter.LessonController;
import DataTransferClasses.DataPrinter;
import DataTransferClasses.RuleData;
import FilePrinter.FilePrinterController;
import RulesPrinter.RuleController;
import RulesPrinter.RulesPrinter;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public enum eResultsValues {
    FILEDATA
            {
                @Override
                public String toString() {
                    return "Show file data";
                }

                @Override
                public void show(ApplicationController i_Controller)
                {
                    System.out.println("file data");
                    i_Controller.getDynamicPane().setContent(i_Controller.getFilePrinterController().getComponent());
                    i_Controller.getFilePrinterController().setView(i_Controller.getFileDataPrinter());
                }
            },
    BESTSOLUTIONBYRAW
            {
                @Override
                public String toString() {
                    return "Best solution- By raw";
                }

                @Override
                public void show(ApplicationController i_Controller)
                {
                    System.out.println("RAW");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    URL url = getClass().getResource("/BestSolutionPrinter/BestSolutionPrinterComponent.fxml");
                    fxmlLoader.setLocation(url);
                    GridPane root = null;
                    try {
                        root = fxmlLoader.load(url.openStream());
                        BestSolutionController controller = (BestSolutionController) fxmlLoader.getController();
                        controller.setView(i_Controller,i_Controller.getDynamicPane(),i_Controller.getDynamicRulesPane(),i_Controller.getEngine().getBestSolutionData(),i_Controller.getEngine().getAmountOfData());
                        controller.printByRaw();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
    BESTSOLUTIONBYTEACHER
            {
                @Override
                public String toString() {
                    return "Best solution- By teacher";
                }

                @Override
                public void show(ApplicationController i_Controller)
                {
                    System.out.println("Teacher");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    URL url = getClass().getResource("/BestSolutionPrinter/BestSolutionPrinterComponent.fxml");
                    fxmlLoader.setLocation(url);
                    GridPane root = null;
                    try {
                        root = fxmlLoader.load(url.openStream());
                        BestSolutionController controller = (BestSolutionController) fxmlLoader.getController();
                        controller.setView(i_Controller,i_Controller.getDynamicPane(),i_Controller.getDynamicRulesPane(),i_Controller.getEngine().getBestSolutionData(),i_Controller.getEngine().getAmountOfData());
                        controller.printByTeacher();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
    BESTSOLUTIONBYCLASS
            {
                @Override
                public String toString() {
                    return "Best solution- By class";
                }

                @Override
                public void show(ApplicationController i_Controller)
                {
                    System.out.println("Teacher");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    URL url = getClass().getResource("/BestSolutionPrinter/BestSolutionPrinterComponent.fxml");
                    fxmlLoader.setLocation(url);
                    GridPane root = null;
                    try {
                        root = fxmlLoader.load(url.openStream());
                        BestSolutionController controller = (BestSolutionController) fxmlLoader.getController();
                        controller.setView(i_Controller,i_Controller.getDynamicPane(),i_Controller.getDynamicRulesPane(),i_Controller.getEngine().getBestSolutionData(),i_Controller.getEngine().getAmountOfData());
                        controller.printByClass();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
    EVOLUTION
            {
                @Override
                public String toString() {
                    return "Algorithm evolution";
                }

                @Override
                public void show(ApplicationController i_Controller)
                {
                    System.out.println("Hello!");
                }
            };
    public abstract String toString();
    public abstract void show(ApplicationController i_Controller);
    public static eResultsValues getResultsValueByName(String i_Name)
    {
        for(eResultsValues instance:eResultsValues.values())
        {
            if(instance.toString().toUpperCase().equals(i_Name.toUpperCase()))
                return instance;
        }
        return null;
    }
}
