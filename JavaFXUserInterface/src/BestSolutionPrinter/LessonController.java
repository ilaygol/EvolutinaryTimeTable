package BestSolutionPrinter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LessonController {

    @FXML
    private Label hourLabel;

    @FXML
    private Label classLabel;

    @FXML
    private Label teacherLabel;

    @FXML
    private Label dayLabel;

    @FXML
    private Label subjectLabel;

    public void setHourText(String i_Text) {
        this.hourLabel.setText(i_Text);
    }

    public void setClassText(String i_Text) {
        this.classLabel.setText(i_Text);
    }

    public void setTeacherText(String i_Text) {
        this.teacherLabel.setText(i_Text);
    }

    public void setDayText(String i_Text) {
        this.dayLabel.setText(i_Text);
    }

    public void setSubjectText(String i_Text) {
        this.subjectLabel.setText(i_Text);
    }
}
