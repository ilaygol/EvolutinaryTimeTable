package Application;

import DataTransferClasses.MutationData;
import Tasks.LoadFileTask;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class ValuesChecker {

    public Boolean checkNumOfGenerations(TextField i_NumOfGen)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_NumOfGen)&&Integer.parseInt(i_NumOfGen.getText())>=100)
        {
            changeToGoodBorder(i_NumOfGen);
        }
        else
        {
            changeToErrorBorder(i_NumOfGen);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkTime(TextField i_Time)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_Time))
        {
            changeToGoodBorder(i_Time);
        }
        else
        {
            changeToErrorBorder(i_Time);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkCuttingPoints(TextField i_CuttingPoints, Integer i_MaxLessons)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_CuttingPoints)&&Integer.parseInt(i_CuttingPoints.getText())<i_MaxLessons-1)
        {
            changeToGoodBorder(i_CuttingPoints);
        }
        else
        {
            changeToErrorBorder(i_CuttingPoints);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkTupples(TextField i_Tupples)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_Tupples)&&Integer.parseInt(i_Tupples.getText())<=50)
        {
            changeToGoodBorder(i_Tupples);
        }
        else
        {
            changeToErrorBorder(i_Tupples);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkShowEvery(TextField i_ShowEvery)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_ShowEvery))
        {
            changeToGoodBorder(i_ShowEvery);
        }
        else
        {
            changeToErrorBorder(i_ShowEvery);
            isGood=false;
        }

        return isGood;
    }

    public void checkMutationArguments(MutationData i_MutationsData, ComboBox i_Probability, TextField i_Tupples, ComboBox i_Component)
    {
        String mutationName=i_MutationsData.getName();
        Boolean check=i_Probability.getSelectionModel().isEmpty();
        check=i_Tupples.getText().isEmpty();
        check=mutationName.toUpperCase().equals("FLIPPING")&&i_Component.getSelectionModel().isEmpty();
        if(i_Probability.getValue()==null||i_Tupples.getText().isEmpty()||
                (mutationName.toUpperCase().equals("FLIPPING")&&i_Component.getValue()==null))
        {
            throw new RuntimeException("There are empty arguments!");
        }
        if(!checkTupples(i_Tupples))
            throw new RuntimeException("Wrong tupples value. Should be 1-50");
    }

    private Boolean checkIfTextFieldInteger(TextField i_TextField)
    {
        Boolean isGood=true;
        Integer intValue;
        try {
            intValue=Integer.parseInt(i_TextField.getText());
            if(intValue<1)
                isGood=false;
        }
        catch (Exception e)
        {
            isGood=false;
        }
        return isGood;
    }

    private void changeToErrorBorder(TextField i_TextField)
    {
        i_TextField.setStyle("-fx-border-color: red; -fx-border-width: 3");
    }

    private void changeToGoodBorder(TextField i_TextField)
    {
        i_TextField.setStyle("-fx-border-color: green; -fx-border-width: 3");
    }

    private void changeToDefaultBorder(TextField i_TextField)
    {
        i_TextField.setStyle("-fx-border-color: null; -fx-border-width: null");
    }
}
