package FilePrinter;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class FilePrinterController {

    @FXML
    private GridPane mainGridPane;

    @FXML
    private TextArea valuesTextArea;

    @FXML
    private ListView<String> valuesListView;

    public GridPane getComponent()
    {
        return mainGridPane;
    }
}
