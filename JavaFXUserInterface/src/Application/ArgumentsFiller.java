package Application;

import DataTransferClasses.DataPrinter;
import DataTransferClasses.MutationData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

public class ArgumentsFiller {
    private DataPrinter m_DataPrinter;

    public ArgumentsFiller(DataPrinter i_DataPrinter)
    {
        m_DataPrinter=i_DataPrinter;
    }

    public void setDataPrinter(DataPrinter i_DataPrinter) {
        m_DataPrinter = i_DataPrinter;
    }

    //Fitness filler
    public void setFitnessCombo(ComboBox i_ComboBox)
    {
        for(int i=1;i<=100;i++)
        {
            i_ComboBox.getItems().add(i);
        }
    }

    //Show values filler
    public void setShowValuesCombo(ComboBox i_ComboBox)
    {
        for(eResultsValues value:eResultsValues.values())
        {
            i_ComboBox.getItems().add(value.toString());
        }
    }

    //Selection fillers
    public void setSelectionTypeCombo(ComboBox i_ComboBox)
    {
        for(String name:m_DataPrinter.getSelectionNamesList())
        {
            i_ComboBox.getItems().add(name);
        }
    }
    public void setSelectionTopPercentCombo(ComboBox i_ComboBox)
    {
        for(int i=1;i<=100;i++)
        {
            i_ComboBox.getItems().add(i);
        }
    }
    public void setSelectionElitismSliderMax(Slider i_Slider)
    {
        i_Slider.setMax(m_DataPrinter.getInitialPopulation()-1);
    }

    //Crossover fillers
    public void setCrossoverTypeCombo(ComboBox i_ComboBox)
    {
        for(String name:m_DataPrinter.getCrossoverNamesList())
        {
            i_ComboBox.getItems().add(name);
        }
    }
    public void setCrossoverAspectCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().add("Teacher");
        i_ComboBox.getItems().add("Class");
    }

    //Mutation fillers
    public void setMutationTypeCombo(ComboBox i_ComboBox)
    {
        for(String name: m_DataPrinter.getMutationNamesList())
        {
            i_ComboBox.getItems().add(name);
        }
    }
    public void setMutationProbabilityCombo(ComboBox i_ComboBox)
    {
        for(int i=0;i<=10;i++)
        {
            i_ComboBox.getItems().add(String.valueOf((double)i/(double)10));
        }
    }
    public void setMutationComponentCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().add("Teacher");
        i_ComboBox.getItems().add("Class");
        i_ComboBox.getItems().add("Subject");
        i_ComboBox.getItems().add("Day");
        i_ComboBox.getItems().add("Hour");
    }

    public void refreshMutationTypeCompo(ComboBox i_ComboBox)
    {

    }
}
