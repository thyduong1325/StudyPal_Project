import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The QuestionMaker class is responsible for reading questions from a file
 * and creating an array of Question objects based on the file content.
 */
public class QuestionMaker {
    public static Question[] getQuestions(File file) {
    	Question[] questions = new Question[countQuestions(file)];
        try {
            // Scan the file
            Scanner scanner = new Scanner(file);
            int quesNum = 0;
            
            // Transform file into Question object
            while (scanner.hasNextLine()) {
            	// Take each input line
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                
                // Declaire the type of question
                String type = parts[0];
                
                // True False Question
                if (type.equals("TF")) {
                	// Analyze inputs
                    String question = parts[1];
                    boolean answer = Boolean.parseBoolean(parts[2]);
                    int points = Integer.parseInt(parts[3]);
                    
                    // Add the question to the main arraylist
                    questions[quesNum] = new QuestionTF(question, answer, points);
                } 
                else if (type.equals("MC")) {
                	// Analyze inputs
                    String question = parts[1];
                    int numChoices = Integer.parseInt(parts[2]);
                    String[] choices = new String[numChoices];
                    for (int i = 0; i < numChoices; i++) {
                        choices[i] = parts[3 + i];
                    }
                    String correctChoice = parts[3 + numChoices];
                    int points = Integer.parseInt(parts[4 + numChoices]);
                    
                    // Add the question to the main arraylist
                    questions[quesNum] = new QuestionMC(question, choices, correctChoice, points);
                } 
                else if (type.equals("FB")) {
                	// Analyze inputs
                    String question = parts[1];
                    String answer = parts[2];
                    int points = Integer.parseInt(parts[3]);
                    
                    // Add the question to the main arraylist
                    questions[quesNum] = new QuestionFB(question, answer, points);
                }
                
                quesNum ++;
            }
            // Close the scan
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questions;
    }
    
    private static int countQuestions(File file) {
    	int result = 0;
    	try {
        	// Scan the file
            Scanner scanner = new Scanner(file);
            
            // Transform file into Question object
            while (scanner.hasNextLine()) {
            	// Count lines
            	result ++;
                scanner.nextLine();
            }
            // Close the scan
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    	
    }
    
}
