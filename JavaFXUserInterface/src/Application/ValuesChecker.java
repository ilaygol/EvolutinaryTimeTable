package Application;

import javafx.scene.control.TextField;

public class ValuesChecker {

    public Boolean checkNumOfGenerations(TextField i_NumOfGen)
    {
        return checkIfTextFieldInteger(i_NumOfGen);
    }

    public Boolean checkTime(TextField i_Time)
    {
        return checkIfTextFieldInteger(i_Time);
    }

    public Boolean checkCuttingPoints(TextField i_CuttingPoints)
    {
        return checkIfTextFieldInteger(i_CuttingPoints);
    }

    public Boolean checkTupples(TextField i_Tupples)
    {
        return checkIfTextFieldInteger(i_Tupples);
    }

    public Boolean checkShowEvery(TextField i_ShowEvery)
    {
        return checkIfTextFieldInteger(i_ShowEvery);
    }

    private Boolean checkIfTextFieldInteger(TextField i_TextField)
    {
        Boolean isGood=true;
        try {
            Integer.parseInt(i_TextField.getText());
            changeToGoodBorder(i_TextField);
        }
        catch (Exception e)
        {
            isGood=false;
            if(i_TextField.getText().isEmpty())
            {
                changeToDefaultBorder(i_TextField);
            }
            else
            {
                changeToErrorBorder(i_TextField);
            }
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
