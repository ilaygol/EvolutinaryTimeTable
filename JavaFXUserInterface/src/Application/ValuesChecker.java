package Application;

import javafx.scene.control.TextField;

public class ValuesChecker {

    public Boolean checkNumOfGenerations(TextField i_NumOfGen, String i_Input)
    {
        Boolean isGood=true;
        try {
            Integer.parseInt(i_Input);
            i_NumOfGen.setStyle("-fx-border-color: green");
        }
        catch (Exception e)
        {
            isGood=false;
            i_NumOfGen.setStyle("-fx-border-color: red");
        }
        return isGood;
    }
}
