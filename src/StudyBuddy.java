import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class StudyBuddy extends Application {
  private List<Question> questions;

  @Override
  public void start(Stage primaryStage) {
    Label label = new Label("Welcome to StudyPal!");
    StackPane root = new StackPane();
    root.getChildren().add(label);


    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Questions File");
    File file = fileChooser.showOpenDialog(primaryStage);
    if (file != null) {
      questions = QuestionMaker.getQuestions(file.getAbsolutePath());
      System.out.println("Questions loaded successfully.");
      // Now you can proceed with presenting questions to the user
    } else {
      System.out.println("No file selected.");
    }
    Scene scene = new Scene(root, 300, 250);

    primaryStage.setTitle("StudyPal");
    primaryStage.setScene(scene);
    primaryStage.show();
    }
}
