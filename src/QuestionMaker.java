import java.io.*;
import java.util.ArrayList;

/**
 * The QuestionMaker class is responsible for reading questions from a file
 * and creating an array of Question objects based on the file content.
 */
public class QuestionMaker {

    /**
     * Reads questions from the specified file and creates an array of Question objects.
     * 
     * @param filename the name of the file containing the questions.
     * @return an array of Question objects.
     * @throws IOException if there is an error reading the file.
     */
    public static Question[] getQuestions(String filename) throws IOException {
        // Create a list to hold the questions temporarily
        ArrayList<Question> questions = new ArrayList<Question>();

        // Use a BufferedReader to read the file line by line
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        // Read each line of the file
        while ((line = br.readLine()) != null) {
            // Split the line into parts using ';' as the delimiter
            String[] parts = line.split(";");

            // The first part indicates the type of question
            String type = parts[0];

            // Create the appropriate Question object based on the type
            if (type.equals("TF")) {
                // Create a True/False question and add it to the list
                String questionText = parts[1];
                boolean answer = Boolean.parseBoolean(parts[2]);
                int points = Integer.parseInt(parts[3]);
                QuestionTF tfQuestion = new QuestionTF(questionText, answer, points);
                questions.add(tfQuestion);
            } else if (type.equals("MC")) {
                // Create a Multiple Choice question
                String questionText = parts[1];
                int numChoices = Integer.parseInt(parts[2]);
                String[] choices = new String[numChoices];

                // Extract the choices from the parts array
                for (int i = 0; i < numChoices; i++) {
                    choices[i] = parts[3 + i];
                }

                String answer = parts[3 + numChoices];
                int points = Integer.parseInt(parts[4 + numChoices]);
                QuestionMC mcQuestion = new QuestionMC(questionText, choices, answer, points);
                questions.add(mcQuestion);
            } else if (type.equals("FB")) {
                // Create a Fill in the Blank question and add it to the list
                String questionText = parts[1];
                String answer = parts[2];
                int points = Integer.parseInt(parts[3]);
                QuestionFB fbQuestion = new QuestionFB(questionText, answer, points);
                questions.add(fbQuestion);
            }
        }

        // Close the BufferedReader
        br.close();

        // Convert the list of questions to an array and return it
        Question[] questionsArray = new Question[questions.size()];
        questions.toArray(questionsArray);
        return questionsArray;
    }
}

