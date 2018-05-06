package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Dimension;
import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        JFrame frame = new JFrame("XML validator & transformator");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,450));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
