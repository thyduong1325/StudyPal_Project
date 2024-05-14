public class QuestionTF implements Question {

    // Instance variables to store question, answer, and points
    private String question;
    private boolean answer;
    private int points;

    // Constructor to initialize question, answer, and points
    public QuestionTF(String question, Boolean answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    // Method to get the question
    @Override
    public String getQuestion() {
        return question;
    }

    // Method to check if provided answer is correct
    @Override
    public boolean isCorrect(String answer) {
        // Check if the provided answer is not "true" or "false"
        if (!answer.equalsIgnoreCase("true") && !answer.equalsIgnoreCase("false"))
            return false;
        // Check if the provided answer matches the actual answer
        else if (answer.equalsIgnoreCase("true"))
            return true == this.answer;
        return false == this.answer;
    }

    // Method to get the correct answer
    @Override
    public String getCorrectAnswer() {
        return Boolean.toString(answer);
    }

    // Method to get the points for the question
    @Override
    public int getPoints() {
        return points;
    }
    
    // Method to display the question details
    @Override
    public String display() {
        return "Points: " + this.points + "\nTrue/False: " + question;
    }
}
