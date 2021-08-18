package Application;

import Manager.LogicEngineManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private LogicEngineManager m_Engine;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("ETTUserInterface.fxml");
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load(url.openStream());
        ApplicationController controller=(ApplicationController)fxmlLoader.getController();
        controller.setStage(primaryStage);
        m_Engine=new LogicEngineManager(controller);
        controller.setEngine(m_Engine);

        Scene scene = new Scene(root, 1124, 713);
        primaryStage.setTitle("Evolutionary Time Table");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(760);

        primaryStage.show();
    }
}
