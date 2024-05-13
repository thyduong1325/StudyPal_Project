package StudyPal_Project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.File;
import java.util.*;

public class StudyBuddy extends Application {
	private Question[] questionsLibrary;
    private ArrayList<Integer> correctIndex = new ArrayList<>();
    private ArrayList<Integer> wrongIndex = new ArrayList<>();
    private ArrayList<Integer> passIndex = new ArrayList<>();
    private ArrayList<Integer> delayIndex = new ArrayList<>();
    private int currentIndex = 0, numQues, initialNum, earnPoint = 0, totalPoint = 0;
    private TextField numberTextField, answerTextField;
    private Stage primaryStage;
    private Text statusFile, numberText, statusNumber, mainQuestion, mainStatus, summary;
    private VBox mainBox;
    private HBox answerLine, buttonLine;
    private Font font = new Font("Verdana", 30);
    private Button pass, delay, random;
    private ScrollPane scrollPane;
    private Rectangle rct;
    private Group mainQuesGroup;
    private StackPane rootPane;
    private AudioClip audioEnd = new AudioClip(new File("/Users/duonguyenthy/Library/CloudStorage/OneDrive-UniversityofSt.Thomas/FRESHMAN/Spring 2024/CISC 230 - Class/StudyPal_Project/src/Celebration Sound Effect.mp3").toURI().toString());
    private AudioClip audioCorrect = new AudioClip(new File("/Users/duonguyenthy/Library/CloudStorage/OneDrive-UniversityofSt.Thomas/FRESHMAN/Spring 2024/CISC 230 - Class/StudyPal_Project/src/Correct Answer sound effect.mp3").toURI().toString());
    private AudioClip audioWrong = new AudioClip(new File("/Users/duonguyenthy/Library/CloudStorage/OneDrive-UniversityofSt.Thomas/FRESHMAN/Spring 2024/CISC 230 - Class/StudyPal_Project/src/Wrong Answer Sound effect.mp3").toURI().toString());
    private AudioClip audioUpload = new AudioClip(new File("/Users/duonguyenthy/Library/CloudStorage/OneDrive-UniversityofSt.Thomas/FRESHMAN/Spring 2024/CISC 230 - Class/StudyPal_Project/src/ Complete Upload Sound Effect.mp3").toURI().toString());
    private AudioClip audioNotFound = new AudioClip(new File("/Users/duonguyenthy/Library/CloudStorage/OneDrive-UniversityofSt.Thomas/FRESHMAN/Spring 2024/CISC 230 - Class/StudyPal_Project/src/File Not Found Sound Effect.mp3").toURI().toString());

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        rootPane = new StackPane();
        
        // Show welcome message and prompt user for file of questions
        Text welcomeText = new Text("Welcome to StudyPal!");
        welcomeText.setFont(font);
        welcomeText.setWrappingWidth(1000);
        welcomeText.setTextAlignment(TextAlignment.CENTER);

        // Load questions from file
        Button loadButton = new Button("Upload Question File");
        loadButton.setStyle("-fx-background-color: slateblue; "
        		+ "-fx-text-fill: white; -fx-font: 23pt Verdana; "
        		+ "-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0); "
        		+ "-fx-border-radius: 20; "
        		+ "-fx-background-radius: 20; "
        		+ "-fx-padding: 15;");
        loadButton.setOnAction(this::uploadFile);
        
        // Status text
        statusFile = new Text("Select the file holding your study questions");
        statusFile.setFont(font);
        statusFile.setWrappingWidth(1000);
        statusFile.setTextAlignment(TextAlignment.CENTER);
        
        // Prompt user for number of questions
        numberText = new Text("How many questions would you like to answer?");
        numberText.setFont(font);
        numberText.setWrappingWidth(1000);
        numberText.setTextAlignment(TextAlignment.CENTER);
        
        // Answer text box
        numberTextField = new TextField();
        numberTextField.setFont(font);
        numberTextField.setMaxWidth(100);
        numberTextField.setAlignment(Pos.CENTER);
        
        // Set the action
        numberTextField.setOnAction(this::fillNumQues);
        
        // Status text
        statusNumber = new Text("Enter number of questions");
        statusNumber.setFont(font);
        statusNumber.setWrappingWidth(1000);
        statusNumber.setTextAlignment(TextAlignment.CENTER);
        
        // Main question display
        mainQuestion = new Text();
        mainQuestion.setFont(font);
        mainQuestion.setWrappingWidth(1000);
        mainQuestion.setTextAlignment(TextAlignment.CENTER);

        // Create a VBox to center the main question text
        VBox centerBox = new VBox(mainQuestion);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setStyle("-fx-background-color: transparent;");

        // Create a scroll pane to contain the centered text
        scrollPane = new ScrollPane();
        scrollPane.setContent(centerBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        // Set the preferred viewport width and height of the scroll pane
        scrollPane.setPrefViewportWidth(1100); // Set the preferred width
        scrollPane.setPrefViewportHeight(200); // Set the preferred height
        
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        // Create border for the scrollpane
        rct = new Rectangle();
        rct.setFill(null);
        rct.setStroke(Color.BLACK);
        rct.setStrokeWidth(5);
        rct.setArcWidth(20);
        rct.setArcHeight(20);
        
        // Bind the size of the rectangle to the size of the scroll pane
        rct.widthProperty().bind(scrollPane.widthProperty());
        rct.heightProperty().bind(scrollPane.heightProperty());
      
        // Create a StackPane to contain the scroll pane and the rectangle
        mainQuesGroup = new Group(scrollPane, rct);
        
        // Answer text box
        answerTextField = new TextField();
        answerTextField.setFont(font);
        answerTextField.setMaxWidth(400);
        answerTextField.setAlignment(Pos.CENTER);
        
        // Set the action
        answerTextField.setOnAction(this::fillAns);
        
        // Arrange answer line
        answerLine = new HBox(20, answerTextField);
        answerLine.setAlignment(Pos.CENTER);
        
        mainStatus = new Text();
        mainStatus.setFill(Color.BLACK);
        mainStatus.setFont(font);
        mainStatus.setTextAlignment(TextAlignment.CENTER);
        
        // Pass button
        pass = new Button("Pass");
        pass.setStyle("-fx-background-color: darksalmon; "
        		+ "-fx-text-fill: white; -fx-font: 23pt Verdana; "
        		+ "-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0); "
        		+ "-fx-border-radius: 20; "
        		+ "-fx-background-radius: 20; "
        		+ "-fx-padding: 15;");
        pass.setOnAction(this::passQues);

        // Delay button
        delay = new Button("Delay");
        delay.setStyle("-fx-background-color: darkturquoise; "
        		+ "-fx-text-fill: white; -fx-font: 23pt Verdana; "
        		+ "-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0); "
        		+ "-fx-border-radius: 20; "
        		+ "-fx-background-radius: 20; "
        		+ "-fx-padding: 15;");
        delay.setOnAction(this::delayQues);
        
        // Random button
        random = new Button("Random");
        random.setStyle("-fx-background-color: plum; "
        		+ "-fx-text-fill: white; -fx-font: 23pt Verdana; "
        		+ "-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0); "
        		+ "-fx-border-radius: 20; "
        		+ "-fx-background-radius: 20; "
        		+ "-fx-padding: 15;");
        random.setOnAction(this::randomQues);
        
        // Arrange buttons line
        buttonLine = new HBox(20, pass, delay, random);
        buttonLine.setAlignment(Pos.CENTER);
        
        // Summary text at the end
        summary = new Text();
        summary.setFont(font);
        summary.setTextAlignment(TextAlignment.CENTER);
        
        // Main Box
        mainBox = new VBox(20, welcomeText, loadButton, statusFile);
        mainBox.setAlignment(Pos.CENTER);
        
        // Header box
        Text headerText = new Text("UST StudyPal");
        headerText.setFont(Font.font("Verdana",FontWeight.BOLD, 50));
        headerText.setFill(Color.WHITE);
        StackPane headerTextPane = new StackPane(headerText);
        headerTextPane.setAlignment(Pos.CENTER);

        // Create the logo
        Image image = new Image("UST_Logo.png");
        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(55); // Set width of the image view
        imageView1.setFitHeight(55); // Set height of the image view
        StackPane imagePane1 = new StackPane(imageView1);
        imagePane1.setAlignment(Pos.CENTER_LEFT);
        
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitWidth(55); // Set width of the image view
        imageView2.setFitHeight(55); // Set height of the image view
        StackPane imagePane2 = new StackPane(imageView2);
        imagePane2.setAlignment(Pos.CENTER_RIGHT);
        
        // StackPane header
        StackPane headerPane = new StackPane(headerTextPane, imagePane1, imagePane2);
        headerPane.setStyle("-fx-background-color: slateblue; ");
        headerPane.setMaxHeight(55);
        
        // Create headerBox to reposition for the header
        VBox headerBox = new VBox(headerPane);
        headerBox.setAlignment(Pos.TOP_CENTER);
        
        // Create the footer
        Text footerText = new Text("CISC 230 | Anh Bui | Emely Cortez Hernandez | Uyen Thy Duong");
        footerText.setFont(Font.font("Verdana", 15));
        footerText.setFill(Color.BLACK);
        StackPane footerTextPane = new StackPane(footerText);
        footerTextPane.setAlignment(Pos.CENTER);
        footerTextPane.setStyle("-fx-background-color: lavender; ");
        footerTextPane.setMaxHeight(55);
        
        // Create footerBox to reposition for the footer
        VBox footerBox = new VBox(footerTextPane);
        footerBox.setAlignment(Pos.BOTTOM_CENTER);
        

        // Add imageView and bannerBox to the rootPane
        rootPane.getChildren().addAll(headerBox, footerBox, mainBox);
        
        // Create the Scene
        Scene scene = new Scene(rootPane, 1300, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("StudyPal");
        primaryStage.show();
    }

    private void uploadFile(ActionEvent event) {
        // Show file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Question File");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
        	questionsLibrary = QuestionMaker.getQuestions(selectedFile);
            if (questionsLibrary.length != 0) {
                // Load questions
            	audioUpload.play();
            	statusFile.setText("Questions loaded successfully!");
            	takeRandom();
            	

                // Count down for 3 seconds
            	PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
                pause1.setOnFinished(e1 -> {
                    // Count down
                	statusFile.setText("Ready in 3");
                	PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                    pause2.setOnFinished(e2 -> {
                        // Count down
                    	statusFile.setText("Ready in 2");
                    	PauseTransition pause3 = new PauseTransition(Duration.seconds(1));
                        pause3.setOnFinished(e3 -> {
                            // Count down
                        	statusFile.setText("Ready in 1");
                        	PauseTransition pause4 = new PauseTransition(Duration.seconds(1));
                            pause4.setOnFinished(e4 -> {
                                // After the pause, clear the mainBox and display other elements
                                mainBox.getChildren().clear();
                                mainBox.getChildren().addAll(numberText, numberTextField, statusNumber);
                                mainBox.setAlignment(Pos.CENTER);
                            });
                            pause4.play();
                        });
                        pause3.play();
                    });
                    pause2.play();
                });
                pause1.play();
                
            } else {
            	statusFile.setText("Error loading questions from file!");
            }
        }
        else {
        	statusFile.setText("File has not been found!");
        	audioNotFound.play();
        }
    }
    
 	private void fillNumQues(ActionEvent event) {
 		Boolean isValid1 = true;
 		try {
	 		// Take the input of number of questions
	 		numQues = Integer.parseInt(numberTextField.getText());
	 		
 		}
 		catch (NumberFormatException e) {
 			numberTextField.clear();
 			isValid1 = false;
 			statusNumber.setText("Sorry, that is not a valid input.");
        }

 		
 		
 		if (isValid1) {
 			Boolean isValid2 = true;
 			
 			if (numQues > questionsLibrary.length) {
 	 			numberTextField.clear();
 	 			isValid2 = false;
 	 			statusNumber.setText("Sorry but you have only loaded " + questionsLibrary.length + " questions.");
 	 		}
 			else if (numQues <= 0) {
 	 			numberTextField.clear();
 	 			isValid2 = false;
 	 			statusNumber.setText("Sorry but you have to do at least 1 question.");
 	 		}
 			
 	 		if (isValid2) {
 	 			initialNum = numQues;
 	 			// Count down for 3 seconds
            	PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
                pause1.setOnFinished(e1 -> {
                    // Count down
                	statusNumber.setText("Ready in 3");
                	PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                    pause2.setOnFinished(e2 -> {
                        // Count down
                    	statusNumber.setText("Ready in 2");
                    	PauseTransition pause3 = new PauseTransition(Duration.seconds(1));
                        pause3.setOnFinished(e3 -> {
                            // Count down
                        	statusNumber.setText("Ready in 1");
                        	PauseTransition pause4 = new PauseTransition(Duration.seconds(1));
                            pause4.setOnFinished(e4 -> {
                                // After the pause, clear the mainBox and display other elements
                            	mainBox.getChildren().clear();
            		            mainBox.getChildren().addAll(mainQuesGroup, answerLine, mainStatus, buttonLine);
            		            mainBox.setAlignment(Pos.CENTER);
                            });
                            pause4.play();
                        });
                        pause3.play();
                    });
                    pause2.play();
                });
                pause1.play();
 	 		}
	 		
 		
 		}
 		
 	}
 	
 	private void fillAns(ActionEvent event) {
 		audioCorrect.stop();
 		audioWrong.stop();
 		if (answerTextField == null || answerTextField.getText().trim().isEmpty()) {
 			answerTextField.clear();
 			mainStatus.setText("You need to enter a valid answer!");
 			mainStatus.setFill(Color.BLACK);
 		}
 		else {
 			if (questionsLibrary[currentIndex].isCorrect(answerTextField.getText())) {
 				answerTextField.clear();
 				correctIndex.add(currentIndex);
 				mainStatus.setText("Correct! You get " + questionsLibrary[currentIndex].getPoints() + " points.");
 				mainStatus.setFill(Color.GREEN);
 				audioCorrect.play();
 				earnPoint += questionsLibrary[currentIndex].getPoints();
 				totalPoint += questionsLibrary[currentIndex].getPoints();
 				if (numQues == delayIndex.size() && numQues != 0) 
 	 				delayIndex.remove(0);
 				numQues --;
 				
 			}
 			else {
 				answerTextField.clear();
 				wrongIndex.add(currentIndex);
 				mainStatus.setText("Incorrect! The answer was " + questionsLibrary[currentIndex].getCorrectAnswer() + ". You lose " + questionsLibrary[currentIndex].getPoints() + " points.");
 				mainStatus.setFill(Color.RED);
 				audioWrong.play();
 				earnPoint -= questionsLibrary[currentIndex].getPoints();
 				totalPoint += questionsLibrary[currentIndex].getPoints();
 				if (numQues == delayIndex.size() && numQues != 0) 
 	 				delayIndex.remove(0);
 				numQues --;
 			}
 			
 			
 			if (numQues == delayIndex.size() && numQues != 0) {
	 			// Pause for 3 seconds
 	 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
 	            pause1.setOnFinished(e1 -> {
 	                // Count down
 	            	mainStatus.setText("Ready in 3");
 	            	mainStatus.setFill(Color.BLACK);
 	            	PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
 	                pause2.setOnFinished(e2 -> {
 	                    // Count down
 	                	mainStatus.setText("Ready in 2");
 	                	mainStatus.setFill(Color.BLACK);
 	                	PauseTransition pause3 = new PauseTransition(Duration.seconds(1));
 	                    pause3.setOnFinished(e3 -> {
 	                        // Count down
 	                    	mainStatus.setText("Ready in 1");
 	                    	mainStatus.setFill(Color.BLACK);
 	                    	PauseTransition pause4 = new PauseTransition(Duration.seconds(1));
 	                        pause4.setOnFinished(e4 -> {
 	                        	// After the pause
 	                 			answerDelay();
 	                 		
 	                        });
 	                        pause4.play();
 	                    });
 	                    pause3.play();
 	                });
 	                pause2.play();
 	            });
 	            pause1.play();
 			}
 			else if (isEndOfQues() || numQues == 0) {
 				// Pause for 2 seconds
 	 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
 	            pause1.setOnFinished(e1 -> {
                    // Count down
                	mainStatus.setText("The End");
                	mainStatus.setFill(Color.BLACK);
                	PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                    pause2.setOnFinished(e2 -> {
                    	// After the pause
                    	endOfTutor();
                   });
                   pause2.play();
 	            });
 	            pause1.play();
 				
 			}
 			
 			else {
 				// Pause for 3 seconds
 	 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
 	            pause1.setOnFinished(e1 -> {
 	                // Count down
 	            	mainStatus.setText("Ready in 3");
 	            	mainStatus.setFill(Color.BLACK);
 	            	PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
 	                pause2.setOnFinished(e2 -> {
 	                    // Count down
 	                	mainStatus.setText("Ready in 2");
 	                	mainStatus.setFill(Color.BLACK);
 	                	PauseTransition pause3 = new PauseTransition(Duration.seconds(1));
 	                    pause3.setOnFinished(e3 -> {
 	                        // Count down
 	                    	mainStatus.setText("Ready in 1");
 	                    	mainStatus.setFill(Color.BLACK);
 	                    	PauseTransition pause4 = new PauseTransition(Duration.seconds(1));
 	                        pause4.setOnFinished(e4 -> {
 	                        	// After the pause
 	                        	takeRandom();
 	                 		
 	                        });
 	                        pause4.play();
 	                    });
 	                    pause3.play();
 	                });
 	                pause2.play();
 	            });
 	            pause1.play();
 			}
 			
 		}
 		
 	}
 	
 	private void passQues(ActionEvent event) {
 		if (!isAlreadyExist(currentIndex) && numQues != delayIndex.size()) {
 			passIndex.add(currentIndex);
	 		mainStatus.setText("Ok, let's skip that one.");
			mainStatus.setFill(Color.BLACK);
	 		// Pause for 1 seconds
	        PauseTransition pause = new PauseTransition(Duration.seconds(1));
	        pause.setOnFinished(e -> {
	            // After the pause
	        	takeRandom();
	        });
	        pause.play();
 		}
 		else if (numQues == delayIndex.size() && numQues != 0) {
 			mainStatus.setText("Sorry, you cannot skip more questions.");
 			mainStatus.setFill(Color.BLACK);
 			// Pause for 1 second
 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
	        pause1.setOnFinished(e1 -> {
		        // After the pause
	        	answerDelay();
            });
            pause1.play();
 		}
 		
 	}
 	
 	private void delayQues(ActionEvent event) {
 		if (!isAlreadyExist(currentIndex) && numQues != delayIndex.size()) {
 			delayIndex.add(currentIndex);
 			mainStatus.setText("Ok, I will ask that one later.");
 			mainStatus.setFill(Color.BLACK);
 			// Pause for 1 seconds
 	        PauseTransition pause = new PauseTransition(Duration.seconds(1));
 	        pause.setOnFinished(e -> {
 	            // After the pause
 	        	takeRandom();
 	        });
 	        pause.play();
 		}
 		else if (numQues == delayIndex.size()&& numQues != 0) {
 			mainStatus.setText("Sorry, you cannot delay more questions.");
 			mainStatus.setFill(Color.BLACK);
 			// Pause for 1 second
 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
	        pause1.setOnFinished(e1 -> {
		        // After the pause
	        	answerDelay();
            });
            pause1.play();
 			
 		}
 		
 	}
 	
 	private void randomQues(ActionEvent event) {
 		if (!isAlreadyExist(currentIndex) && numQues != delayIndex.size()) {
	 		takeRandom();
 		}
 		else if (numQues == delayIndex.size()&& numQues != 0) {
 			if (delayIndex.size() == 1) {
 				mainStatus.setText("You need to answer " + delayIndex.size() + " delay question.");
 				mainStatus.setFill(Color.BLACK);
 			}
 			else {
 				mainStatus.setText("You need to answer " + delayIndex.size() + " delay questions.");
 				mainStatus.setFill(Color.BLACK);
 			}
 			// Pause for 1 second
 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
	        pause1.setOnFinished(e1 -> {
		        // After the pause
	        	answerDelay();
            });
            pause1.play();
 			
 		}
 	}
 	
 	private void takeRandom() {
 		mainStatus.setText("");
 		mainStatus.setFill(Color.BLACK);
 		if (isEndOfQues()) {
 			mainQuestion.setText("We are out of questions!");
 			// Pause for 1 second
 			PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
	        pause1.setOnFinished(e1 -> {
		        	// After the pause
		        	endOfTutor();
            });
            pause1.play();
 		}
 		else if (numQues == delayIndex.size() && numQues != 0) {
 			answerDelay();
 		}
 		else {
 			Random gen = new Random();
	 		currentIndex = gen.nextInt(questionsLibrary.length);
	 		while (isAlreadyExist(currentIndex)) {
	 			currentIndex = gen.nextInt(questionsLibrary.length);
	 		}
	 		mainQuestion.setText(questionsLibrary[currentIndex].display());
 		}
 		
 	}
 	
 	private boolean isAlreadyExist(int num) {
 		for (int i : correctIndex) {
 			if (i == num)
 				return true;
 		}
 		for (int i : wrongIndex) {
 			if (i == num)
 				return true;
 		}
 		for (int i : passIndex) {
 			if (i == num)
 				return true;
 		}
 		for (int i : delayIndex) {
 			if (i == num)
 				return true;
 		}
 		
 		return false;
 		
 	}
 	
 	private boolean isEndOfQues() {
 		if ((correctIndex.size() + wrongIndex.size() + passIndex.size() + delayIndex.size()) == questionsLibrary.length) {
 			return true;
 		}
 		return false;
 	}
 	
 	private void answerDelay() {
 		mainStatus.setText("");
 		currentIndex = delayIndex.get(0);
 		mainQuestion.setText(questionsLibrary[currentIndex].display());
 	}
 	
 	private void endOfTutor() {
		String s = "Of the " + initialNum + " questions you attempted, you got " + correctIndex.size() +" correct.";
		s += "\nYou earned a total of " + earnPoint + " points out of " + totalPoint + " points.";
		if (initialNum == correctIndex.size()) {
			s += "\nYou did a great job!";
		}
		else {
			s += "\nBetter luck next time!";
		}
		summary.setText(s);
		mainBox.getChildren().clear();
		mainBox.getChildren().add(summary);
		mainBox.setAlignment(Pos.CENTER);
		audioEnd.play();
 	}
}
