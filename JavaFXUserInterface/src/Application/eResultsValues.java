package Application;

import DataTransferClasses.DataPrinter;
import FilePrinter.FilePrinterController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ListView;

public enum eResultsValues {
    FILEDATA
            {
                @Override
                public String toString() {
                    return "Show file data";
                }

                @Override
                public void show(DataPrinter i_DataPrinter, Object i_Controller, ScrollPane i_DynamicPane)
                {
                    System.out.println("file data");
                    FilePrinterController controller=(FilePrinterController) i_Controller;
                    i_DynamicPane.setContent(controller.getComponent());
                    controller.setView(i_DataPrinter);

                }
            },
    BESTSOLUTIONBYROW
            {
                @Override
                public String toString() {
                    return "Best solution- By raw";
                }

                @Override
                public void show(DataPrinter i_DataPrinter, Object i_Controller, ScrollPane i_DynamicPane)
                {
                    System.out.println("Hello!");
                }
            },
    BESTSOLUTIONBYTEACHER
            {
                @Override
                public String toString() {
                    return "Best solution- By teacher";
                }

                @Override
                public void show(DataPrinter i_DataPrinter, Object i_Controller, ScrollPane i_DynamicPane)
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
                public void show(DataPrinter i_DataPrinter, Object i_Controller, ScrollPane i_DynamicPane)
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
                public void show(DataPrinter i_DataPrinter, Object i_Controller, ScrollPane i_DynamicPane)
                {
                    System.out.println("Hello!");
                }
            };
    public abstract String toString();
    public abstract void show(DataPrinter i_DataPrinter, Object i_Controller, ScrollPane i_DynamicPane);
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
