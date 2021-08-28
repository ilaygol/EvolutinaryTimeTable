package Application;

import DataTransferClasses.DataPrinter;
import DataTransferClasses.RuleData;
import FilePrinter.FilePrinterController;
import RulesPrinter.RuleController;
import RulesPrinter.RulesPrinter;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ListView;
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
    BESTSOLUTIONBYROW
            {
                @Override
                public String toString() {
                    return "Best solution- By raw";
                }

                @Override
                public void show(ApplicationController i_Controller)
                {
                    System.out.println("RAW");
                    List<RuleData> rulesDataList = i_Controller.getEngine().getBestSolutionData().getRulesDataList();
                    RulesPrinter rulesPrinter=new RulesPrinter(rulesDataList,i_Controller.getDynamicRulesPane());
                    rulesPrinter.showRules();
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
                    System.out.println("Hello!");
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
                    System.out.println("Hello!");
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
